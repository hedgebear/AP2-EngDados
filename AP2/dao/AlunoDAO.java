package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Aluno;
import modelo.Modalidade;
import modelo.Professor;
import modelo.Turma;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }
    // metodo para criar o aluno
    public void createAlunoSemTurma(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno (nome, matricula, cpf, email, telefone) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getMatricula());
                pstm.setString(3, aluno.getCpf());
                pstm.setString(4, aluno.getEmail());
                pstm.setInt(5, aluno.getTelefone());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createAlunoComTurma(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno (nome, matricula, cpf, email, telefone) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getMatricula());
                pstm.setString(3, aluno.getCpf());
                pstm.setString(4, aluno.getEmail());
                pstm.setInt(5, aluno.getTelefone());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setId(rst.getInt(1));
                        for(Turma turma : aluno.getTurmas()){
                            createPresenca(aluno, turma);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPresenca(Aluno aluno, Turma turma){
        try{
            String sql = "INSERT INTO aluno_turma (fk_aluno, fk_turma) VALUES (?, ?)";

            try(PreparedStatement pstm = connection.prepareStatement(sql)){
                
                pstm.setString(1, aluno.getMatricula());
                pstm.setInt(2, turma.getCodigo_Turma());

                pstm.execute();
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


    // metodo para pegar todos
    public ArrayList<Aluno> retriveAll(){
        
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		try {
			String sql = "SELECT id, nome, matricula, cpf,  email, telefone FROM aluno ";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    String matricula = rst.getString("matricula");
                    String cpf = rst.getString("cpf");
                    String email = rst.getString("email");
                    int telefone = rst.getInt("telefone");
                    Aluno a = new Aluno(id, nome, cpf, matricula, email, telefone);
                    alunos.add(a);
                }
			}
			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public ArrayList<Aluno> retriveAllAlunosComTurma() {
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        ProfessorDAO pdao = new ProfessorDAO(connection);
        ModalidadeDAO mdao = new ModalidadeDAO(connection);
        
        Turma ultimaTurma = null;
        Aluno ultimoAluno = null;
    
        try {
            String sql = "SELECT a.id, a.nome, a.matricula, a.cpf, a.email, a.telefone, t.id, t.codigo_turma, t.data_turma, t.hora_turma, t.fk_professor, t.fk_modalidade "
                    + "FROM aluno as a "
                    + "LEFT JOIN aluno_turma AS at ON at.fk_aluno = a.matricula "
                    + "LEFT JOIN turma AS t ON at.fk_turma = t.codigo_turma";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
    
                try (ResultSet rst = pstm.executeQuery()) {
                    while (rst.next()) {
                        if (ultimoAluno == null || ultimoAluno.getId() != rst.getInt(1)) {
                            int id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String matricula = rst.getString(3);
                            String cpf = rst.getString(4);
                            String email = rst.getString(5);
                            int telefone = rst.getInt(6);
                            Aluno a = new Aluno(id, nome, cpf, matricula, email, telefone);
                            alunos.add(a);
                            ultimoAluno = a; 
                        }
    
                        if (rst.getInt(7) != 0 && (ultimaTurma == null || ultimaTurma.getId() != rst.getInt(7))) {
                            int tur_id = rst.getInt(7);
                            int cod_turma = rst.getInt(8);
                            LocalDate data_turma = rst.getObject(9, LocalDate.class);
                            String hora_turma = rst.getString(10);
                            Professor professor = pdao.consultarProfessorCodigo(rst.getInt(11));
                            Modalidade modalidade = mdao.consultarModalidadeCodigo(rst.getInt(12));
                            Turma t = new Turma(tur_id, cod_turma, data_turma, hora_turma, modalidade, professor);
                            ultimoAluno.addTurma(t);
                            ultimaTurma = t; 
                        }
                    }
                }
            }
            return alunos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    


    // Método para atualização de dados na tabela
    public void atualizarAluno(Aluno aluno) {
        try {
            String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ? WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getCpf());
                pstm.setString(3, aluno.getEmail());
                pstm.setString(4, aluno.getMatricula());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para deleção de dados da tabela
    public void deletarAluno(Aluno aluno) {
        try {
            String sql = "DELETE FROM aluno WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, aluno.getMatricula());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para consultar dados de um elemento específico da tabela
    public Aluno consultarAlunoMatricula(String matricula) {
        Aluno a = null;
        try {
            String sql = "SELECT id, nome, cpf, email, telefone "
                    + "FROM aluno "
                    + "WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1,matricula);

                try (ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        int a_id = rst.getInt(1);
                        String nome = rst.getString(2);
                        String matricula_recebida = matricula;
                        String cpf = rst.getString(3);
                        String email = rst.getString(4);
                        int telefone = rst.getInt(5);
                        a = new Aluno(a_id, nome, cpf, matricula_recebida, email, telefone);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return a;
    }
}


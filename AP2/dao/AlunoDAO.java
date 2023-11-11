package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;

import java.util.ArrayList;

import modelo.Aluno;
import modelo.Fatura;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }
    // metodo para criar o aluno
    public void createAluno(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno (nome, cpf, matricula, email, telefone) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getCpf());
                pstm.setInt(3, aluno.getMatricula());
                pstm.setString(4, aluno.getEmail());
                pstm.setInt(5, aluno.getTelefone());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setId(rst.getInt(1));
                        for (Fatura fatura : aluno.getFaturas()) {
                            FaturaDAO fdao = new FaturaDAO(connection);
                            fdao.create(fatura, aluno);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> retriveAllSemFatura(){
        
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		try {
			String sql = "SELECT id, nome, cpf, matricula, email, telefone FROM aluno";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    String cpf = rst.getString("cpf");
                    int matricula = rst.getInt("matricula");
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

    public ArrayList<Aluno> retriveAlunosComFatura(){

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Aluno ultimo = null;
        try {

            String sql = "SELECT a.id, a.nome, a.cpf, a.matricula, a.email, a.telefone, f.id, f.valor, f.data_vencimento, f.codigo_fatura "
                    + "FROM aluno AS a "
                    + "INNER JOIN fatura AS f ON a.matricula = f.fk_aluno";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimo == null || ultimo.getId() != rst.getInt(1)) {
                            int a_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String cpf = rst.getString(3);
                            int matricula = rst.getInt(4);
                            String email = rst.getString(5);
                            int telefone = rst.getInt(6);
                            Aluno a = new Aluno(a_id, nome, cpf, matricula, email, telefone);
                            alunos.add(a);
                            ultimo = a;
                        }

                        int fat_id = rst.getInt(7);
                        float valor = rst.getInt(8);
                        LocalDate data_vencimento = rst.getObject(9, LocalDate.class);
                        int cod_fatura = rst.getInt(10);
                        Fatura f = new Fatura(fat_id, valor, data_vencimento, cod_fatura);
                        ultimo.addFatura(f);
                    }
                }
                return alunos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> retriveAllComFatura(){

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Aluno ultimo = null;
        try {

            String sql = "SELECT a.id, a.nome, a.cpf, a.matricula, a.email, a.telefone, f.id, f.valor, f.data_vencimento, f.codigo_fatura "
                    + "FROM aluno AS a "
                    + "LEFT JOIN fatura AS f ON a.matricula = f.fk_aluno";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimo == null || ultimo.getId() != rst.getInt(1)) {
                            int a_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String cpf = rst.getString(3);
                            int matricula = rst.getInt(4);
                            String email = rst.getString(5);
                            int telefone = rst.getInt(6);
                            Aluno a = new Aluno(a_id, nome, cpf, matricula, email, telefone);
                            alunos.add(a);
                            ultimo = a;
                        }
                        if(rst.getInt(7) != 0){
                            int fat_id = rst.getInt(7);
                            float valor = rst.getInt(8);
                            LocalDate data_vencimento = rst.getObject(9, LocalDate.class);
                            int cod_fatura = rst.getInt(10);
                            Fatura f = new Fatura(fat_id, valor, data_vencimento, cod_fatura);
                            ultimo.addFatura(f);
                        }
                    }
                }
                return alunos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para atualização de dados na tabela
    /* ou podemos fazer um metodo q a gnt da o aluno e o id dele como parametro
     * e  na hora do where ele pega o id q foi dado como parametro.
     */
    public void atualizarAluno(Aluno aluno) {
        try {
            String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ? WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getCpf());
                pstm.setString(3, aluno.getEmail());
                pstm.setInt(4, aluno.getMatricula());

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
                pstm.setInt(1, aluno.getMatricula());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para consultar dados de um elemento específico da tabela
    public Aluno consultarAluno(Aluno aluno) {
        Aluno a = null;
        try {
            String sql = "SELECT a.id, a.nome, a.cpf, a.matricula, a.email, a.telefone, f.id, f.valor, f.data_vencimento, f.codigo_fatura "
                    + "FROM aluno AS a "
                    + "INNER JOIN fatura AS f ON a.matricula = f.fk_aluno "
                    + "WHERE matricula = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1,aluno.getMatricula());

                try (ResultSet rst = pstm.getResultSet()) {
                    if (rst.next()) {
                        int a_id = rst.getInt(1);
                        String nome = rst.getString(2);
                        String cpf = rst.getString(3);
                        int matricula = rst.getInt(4);
                        String email = rst.getString(5);
                        int telefone = rst.getInt(6);
                        a = new Aluno(a_id, nome, cpf, matricula, email, telefone);
                        
                        int fat_id = rst.getInt(7);
                        float valor = rst.getInt(8);
                        LocalDate data_vencimento = rst.getObject(9, LocalDate.class);
                        int cod_fatura = rst.getInt(10);
                        Fatura f = new Fatura(fat_id, valor, data_vencimento, cod_fatura);
                        a.addFatura(f);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return a;
    }
}


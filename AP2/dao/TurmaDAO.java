package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Turma;
import modelo.Professor;
import modelo.Modalidade;
import modelo.Aluno;


public class TurmaDAO {

    private Connection connection;

    public TurmaDAO(Connection connection) {
        this.connection = connection;
    }

    public void createSemAluno(Turma turma, Professor professor, Modalidade modalidade) {
        try {
            String sql = "INSERT INTO turma (codigo_turma, data_turma, hora_turma, fk_professor, fk_modalidade) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                pstm.setInt(1, turma.getCodigo_Turma());
                pstm.setObject(2, turma.getData_Turma());
                pstm.setString(3, turma.getHora_Turma());
                pstm.setInt(4, professor.getCodigo_professor());
                pstm.setInt(5, modalidade.getCodigo_Modalidade());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        turma.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createComAluno(Turma turma, Professor professor, Modalidade modalidade) {
        try {
            String sql = "INSERT INTO turma (codigo_turma, data_turma, hora_turma, fk_professor, fk_modalidade) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                pstm.setInt(1, turma.getCodigo_Turma());
                pstm.setObject(2, turma.getData_Turma());
                pstm.setString(3, turma.getHora_Turma());
                pstm.setInt(4, professor.getCodigo_professor());
                pstm.setInt(5, modalidade.getCodigo_Modalidade());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        turma.setId(rst.getInt(1));
                        for(Aluno aluno : turma.getAlunos()){
                            createPresenca(turma, aluno);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPresenca(Turma turma, Aluno aluno){
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

    public ArrayList<Turma> retriveAllTurmasComAlunos(){
        
        ArrayList<Turma> turmas = new ArrayList<Turma>();
        ProfessorDAO pdao = new ProfessorDAO(connection);
        ModalidadeDAO mdao = new ModalidadeDAO(connection);
        Turma ultimaTurma = null;
        Aluno ultimoAluno = null;

        try {
            String sql = "SELECT t.id, t.codigo_turma, t.data_turma, t.hora_turma, t.fk_professor, t.fk_modalidade, a.id, a.nome, a.cpf, a.matricula, a.email, a.telefone "
            + "FROM turma as t "
            + "LEFT JOIN aluno_turma AS at ON at.fk_turma = t.codigo_turma "
            + "LEFT JOIN aluno AS a ON at.fk_aluno = a.matricula";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try(ResultSet rst = pstm.executeQuery()){
                    while (rst.next()) {
                        if (ultimaTurma == null || ultimaTurma.getId() != rst.getInt(1)) {
                            int tur_id = rst.getInt(1);
                            int cod_turma = rst.getInt(2);
                            LocalDate data_turma = rst.getObject(3,LocalDate.class);
                            String hora_turma = rst.getString(4);
                            Professor professor = pdao.consultarProfessorCodigo(rst.getInt(5));
                            Modalidade modalidade = mdao.consultarModalidadeCodigo(rst.getInt(6));
                            Turma t = new Turma(tur_id, cod_turma, data_turma, hora_turma, modalidade, professor);
                            turmas.add(t);
                            ultimaTurma = t;

                        }

                        if((rst.getInt(7) != 0) && (ultimoAluno == null || ultimoAluno.getId() != rst.getInt(7))){
                            int id = rst.getInt(7);
                            String nome = rst.getString(8);
                            String cpf = rst.getString(9);
                            String matricula = rst.getString(10);
                            String email = rst.getString(11);
                            int telefone = rst.getInt(12);
                            Aluno a = new Aluno(id, nome, cpf, matricula, email, telefone);
                            ultimaTurma.addAluno(a);
                        }
                    }
                }
            }
            return turmas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarTurma(Turma turma) {
        try {
            String sql = "UPDATE turma SET data_turma = ?, hora_turma = ? WHERE codigo_turma = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setObject(1, turma.getData_Turma());
                pstm.setString(2, turma.getHora_Turma());
                pstm.setInt(3, turma.getCodigo_Turma());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarTurma(Turma turma) {
        try {
            String sql = "DELETE FROM turma WHERE codigo_turma = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, turma.getCodigo_Turma());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Turma consultarTurmaEspcSemAlunos(Turma turma) {
        Turma t = null;
        ProfessorDAO pdao = new ProfessorDAO(connection);
        ModalidadeDAO mdao = new ModalidadeDAO(connection);
        try {
            String sql = "SELECT id, codigo_turma, data_turma, hora_turma, fk_professor , fk_modalidade "
                    + "FROM turma "
                    + "WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1,turma.getId());

                try (ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        int tur_id = rst.getInt(1);
                        int cod_turma = rst.getInt(2);
                        LocalDate data_turma = rst.getObject(3, LocalDate.class);
                        String hora_turma = rst.getString(4);
                        Professor professor = pdao.consultarProfessorCodigo(rst.getInt(5));
                        Modalidade modalidade = mdao.consultarModalidadeCodigo(rst.getInt(6));
                        t = new Turma(tur_id, cod_turma, data_turma, hora_turma, modalidade, professor);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return t ;
    }

    public Turma consultarTurmaEspcComAlunos(Turma turma) {
        Turma t = null;
        ProfessorDAO pdao = new ProfessorDAO(connection);
        ModalidadeDAO mdao = new ModalidadeDAO(connection);
        try {
            String sql = "SELECT t.id, t.codigo_turma, t.data_turma, t.hora_turma, t.fk_professor, t.fk_modalidade, a.id, a.nome, a.cpf, a.matricula, a.email, a.telefone "
            + "FROM turma as t "
            + "LEFT JOIN aluno_turma AS at ON at.fk_turma = t.codigo_turma "
            + "LEFT JOIN aluno AS a ON at.fk_aluno = a.matricula "
            + "WHERE t.codigo_turma = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1,turma.getCodigo_Turma());

                try (ResultSet rst = pstm.executeQuery()) {
                    while(rst.next()) {
                        if (t == null) {
                            int tur_id = rst.getInt(1);
                            int cod_turma = rst.getInt(2);
                            LocalDate data_turma = rst.getObject(3, LocalDate.class);
                            String hora_turma = rst.getString(4);
                            Professor professor = pdao.consultarProfessorCodigo(rst.getInt(5));
                            Modalidade modalidade = mdao.consultarModalidadeCodigo(rst.getInt(6));
                            t = new Turma(tur_id, cod_turma, data_turma, hora_turma, modalidade, professor);
                        }
                        
                        if (rst.getInt(7) != 0) {
                            int id = rst.getInt(7);
                            String nome = rst.getString(8);
                            String cpf = rst.getString(9);
                            String matricula = rst.getString(10);
                            String email = rst.getString(11);
                            int telefone = rst.getInt(12);
                            Aluno a = new Aluno(id, nome, cpf, matricula, email, telefone);
                            t.addAluno(a);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return t ;
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import java.util.ArrayList;

import modelo.Professor;
import modelo.Turma;
import modelo.Modalidade;

public class ProfessorDAO {

    private Connection connection;

    public ProfessorDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Professor professor) {
        try {
            String sql = "INSERT INTO professor (nome, codigo_professor, cpf, telefone, email, especializacao, contaBanco) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, professor.getNome());
                pstm.setInt(2, professor.getCodigo_professor());
                pstm.setString(3, professor.getCpf());
                pstm.setInt(4, professor.getTelefone());
                pstm.setString(5, professor.getEmail());
                pstm.setString(6, professor.getEspecializacao());
                pstm.setString(7, professor.getContaBanco());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        professor.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Professor> retriveAllSemTurma(){

        ArrayList<Professor> professor = new ArrayList<Professor>();

        try {
            String sql = "SELECT id, nome, codigo_professor, cpf, telefone, email, especializacao, contaBanco FROM professor";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                int prof_id = rst.getInt("id");
                int cod_prof = rst.getInt("codigo_professor");
                String nome_prof = rst.getString("nome");
                String cpf_prof = rst.getString("cpf");
                String espec_prof = rst.getString("especializacao");
                String conta_prof = rst.getString("contaBanco");
                String email_prof = rst.getString("email");
                int tel_prof = rst.getInt("telefone");
                Professor p = new Professor(prof_id, cod_prof, nome_prof, cpf_prof, espec_prof, conta_prof, email_prof, tel_prof);
                professor.add(p);
            }
            return professor;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Professor> retriveAllComTurma(){

        ArrayList<Professor> professor = new ArrayList<Professor>();
        Professor ultimo = null;
        try {
            String sql = "SELECT p.id, p.nome, p.codigo_professor, p.cpf, p.telefone, p.email, p.especializacao, p.contaBanco, t.id, t.codigo_turma, t.data_turma, t.hora_turma" 
            + "FROM professor as p"
            + "INNER JOIN turma as t on p.codigo_professor = t.fk_professor";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimo == null || ultimo.getId() != rst.getInt(1)) {
                            int prof_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            int codigo_prof = rst.getInt(3);
                            String cpf = rst.getString(4);
                            int telefone = rst.getInt(5);
                            String email = rst.getString(6);
                            String especializacao = rst.getString(7);
                            String contaBanco = rst.getString(8);
                            Professor p = new Professor(prof_id, codigo_prof, nome, cpf, especializacao, contaBanco, email, telefone);
                            professor.add(p);
                            ultimo = p;
                        }
                        int tur_id = rst.getInt(9);
                        int codigo_turma = rst.getInt(10);
                        LocalDate data_turma = rst.getObject(11, LocalDate.class);
                        String hora_turma = rst.getString(12);
                        Turma t = new Turma(tur_id, codigo_turma, data_turma, hora_turma);
                        ultimo.addTurma(t);
                }
            }
            return professor;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            }
        }

    }

    public ArrayList<Professor> retriveAllComSemTurma(){

        ArrayList<Professor> professor = new ArrayList<Professor>();
        Professor ultimo = null;
        try {
            String sql = "SELECT p.id, p.nome, p.codigo_professor, p.cpf, p.telefone, p.email, p.especializacao, p.contaBanco, t.id, t.codigo_turma, t.data_turma, t.hora_turma" 
            + "FROM professor as p"
            + "LEFT JOIN turma as t on p.codigo_professor = t.fk_professor";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimo == null || ultimo.getId() != rst.getInt(1)) {
                            int prof_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            int codigo_prof = rst.getInt(3);
                            String cpf = rst.getString(4);
                            int telefone = rst.getInt(5);
                            String email = rst.getString(6);
                            String especializacao = rst.getString(7);
                            String contaBanco = rst.getString(8);
                            Professor p = new Professor(prof_id, codigo_prof, nome, cpf, especializacao, contaBanco, email, telefone);
                            professor.add(p);
                            ultimo = p;
                        }

                        if(rst.getInt(7) != 0){
                            int tur_id = rst.getInt(9);
                            int codigo_turma = rst.getInt(10);
                            LocalDate data_turma = rst.getObject(11, LocalDate.class);
                            String hora_turma = rst.getString(12);
                            Turma t = new Turma(tur_id, codigo_turma, data_turma, hora_turma);
                            ultimo.addTurma(t);
                        }
                }
            }
            return professor;
        } catch (SQLException e) {
            throw new RuntimeException(e);
            }
        }

    }

    public void atualizarProfessor(Professor professor) {
        try {
            String sql = "UPDATE professor SET nome = ?, cpf = ?, telefone = ?, email = ?, especializacao = ?, contaBanco = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, professor.getNome());
                pstm.setString(2, professor.getCpf());
                pstm.setInt(3, professor.getTelefone());
                pstm.setString(4, professor.getEmail());
                pstm.setString(5, professor.getEspecializacao());
                pstm.setString(6, professor.getContaBanco());
                pstm.setInt(7, professor.getId());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarProfessor(Professor professor) {
        try {
            String sql = "DELETE FROM professor WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, professor.getId());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Professor consultarProfessorCodigo(int codigo_professor) {
        Professor p = null;
        try {
            String sql = "SELECT p.id, p.nome, p.cpf, p.telefone, p.email, p.especializacao, p.contaBanco, t.id, t.codigo_turma, t.data_turma, t.hora_turma" 
                    + "FROM professor as p"
                    + "INNER JOIN turma as t on p.codigo_professor = t.fk_professor";
                    + "WHERE codigo_professor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1,codigo_professor);

                try (ResultSet rst = pstm.getResultSet()) {
                    if (rst.next()) {
                        int prof_id = rst.getInt(1);
                        String nome = rst.getString(2);
                        String cpf = rst.getString(3);
                        int telefone = rst.getInt(4);
                        String email = rst.getString(5);
                        String especializacao = rst.getString(6);
                        String contaBanco = rst.getString(7);
                        p = new Professor(prof_id, codigo_professor, nome, cpf, especializacao, contaBanco, email, telefone);
                        
                        int tur_id = rst.getInt(9);
                        int codigo_turma = rst.getInt(10);
                        LocalDate data_turma = rst.getObject(11, LocalDate.class);
                        String hora_turma = rst.getString(12);
                        Turma t = new Turma(tur_id, codigo_turma, data_turma, hora_turma);
                        p.addTurma(t);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
}

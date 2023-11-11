package AP2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import AP2.modelo.Professor;

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

    public ArrayList<Professor> retriveAll(){

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

}
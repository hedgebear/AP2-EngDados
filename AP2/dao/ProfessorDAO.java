package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;

import modelo.Professor;


public class ProfessorDAO {

    private Connection connection;

    public ProfessorDAO(Connection connection) {
        this.connection = connection;
    }

    public void createProfessor(Professor professor) {
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
        
        ArrayList<Professor> professores = new ArrayList<Professor>();

		try {
			String sql = "SELECT id, codigo_professor, nome, cpf, especializacao, contaBanco, email, telefone FROM professor ";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int id = rst.getInt("id");
                    int codigo_professor = rst.getInt("codigo_professor");
                    String nome = rst.getString("nome");
                    String cpf = rst.getString("cpf");
                    String especializacao = rst.getString("especializacao");
                    String contaBanco = rst.getString("contaBanco");
                    String email = rst.getString("email");
                    int telefone = rst.getInt("telefone");
                    Professor p = new Professor(id, codigo_professor, nome, cpf, especializacao, contaBanco, email, telefone);
                    professores.add(p);
                }
			}
			return professores;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public void atualizarProfessor(Professor professor) {
        try {
            String sql = "UPDATE professor SET nome = ?, cpf = ?, telefone = ?, email = ?, especializacao = ?, contaBanco = ? WHERE codigo_professor = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, professor.getNome());
                pstm.setString(2, professor.getCpf());
                pstm.setInt(3, professor.getTelefone());
                pstm.setString(4, professor.getEmail());
                pstm.setString(5, professor.getEspecializacao());
                pstm.setString(6, professor.getContaBanco());
                pstm.setInt(7, professor.getCodigo_professor());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarProfessor(Professor professor) {
        try {
            String sql = "DELETE FROM professor WHERE  = codigo_professor ?";

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
            String sql = "SELECT id, nome, cpf, especializacao, contaBanco, email, telefone FROM professor " 
                        + "WHERE codigo_professor = ?";
    
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, codigo_professor);
    
                try (ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        int prof_id = rst.getInt(1);
                        String nome = rst.getString(2);
                        String cpf = rst.getString(3);
                        String especializacao = rst.getString(4);
                        String contaBanco = rst.getString(5);
                        String email = rst.getString(6);
                        int telefone = rst.getInt(7);
                        p = new Professor(prof_id, codigo_professor, nome, cpf, especializacao, contaBanco, email, telefone);
                        
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
}

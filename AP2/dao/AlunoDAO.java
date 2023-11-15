package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import modelo.Aluno;

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
                pstm.setString(3, aluno.getMatricula());
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
    // metodo para pegar todos
    public ArrayList<Aluno> retriveAll(){
        
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		try {
			String sql = "SELECT id, nome, cpf, matricula, email, telefone FROM aluno ";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    String cpf = rst.getString("cpf");
                    String matricula = rst.getString("matricula");
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

    // Método para atualização de dados na tabela
    public void atualizarAluno(Aluno aluno) {
        try {
            String sql = "UPDATE aluno SET nome = ?, cpf = ?, email = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getCpf());
                pstm.setString(3, aluno.getEmail());
                pstm.setInt(4, aluno.getId());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para deleção de dados da tabela
    public void deletarAluno(Aluno aluno) {
        try {
            String sql = "DELETE FROM aluno WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, aluno.getId());

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
                        String cpf = rst.getString(3);
                        String matricula_recebida = matricula;
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


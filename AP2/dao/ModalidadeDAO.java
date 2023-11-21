package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;


import modelo.Modalidade;


public class ModalidadeDAO {

    private Connection connection;

    public ModalidadeDAO(Connection connection) {
        this.connection = connection;
    }

    public void createModalidade(Modalidade modalidade) {
        try {
            String sql = "INSERT INTO modalidade (nome, codigo_modalidade) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, modalidade.getNome());
                pstm.setInt(2, modalidade.getCodigo_Modalidade());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        modalidade.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     public ArrayList<Modalidade> retriveAll(){
        
        ArrayList<Modalidade> modalidades = new ArrayList<Modalidade>();

		try {
			String sql = "SELECT id, nome, codigo_modalidade FROM modalidade ";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    int codigo_modalidade = rst.getInt("codigo_modalidade");
                    Modalidade m = new Modalidade(id, nome, codigo_modalidade);
                    modalidades.add(m);
                }
			}
			return modalidades;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

   

    public void atualizarModalidade(Modalidade modalidade) {
        try {
            String sql = "UPDATE modalidade SET nome = ? WHERE codigo_modalidade = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, modalidade.getNome());
                pstm.setInt(2, modalidade.getCodigo_Modalidade());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarModalidade(Modalidade modalidade) {
        try {
            String sql = "DELETE FROM modalidade WHERE codigo_modalidade = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, modalidade.getCodigo_Modalidade());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Modalidade consultarModalidadeCodigo(int codigo_modalidade) {
        Modalidade m = null;
        try {
            String sql = "SELECT id, nome, codigo_modalidade FROM modalidade "
                    + "WHERE codigo_modalidade = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1,codigo_modalidade);

                try (ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        int m_id = rst.getInt(1);
                        String nome = rst.getString(2);
                        m = new Modalidade(m_id, nome, codigo_modalidade);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return m;
    }

}

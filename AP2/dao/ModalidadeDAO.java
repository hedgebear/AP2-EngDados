package AP2.dao;

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

    public void create(Modalidade modalidade) {
        try {
            String sql = "INSERT INTO fatura (nome, codigo_modalidade) VALUES (?, ?)";

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
        
        ArrayList<Modalidade> modalidade = new ArrayList<Modalidade>();

		try {
			String sql = "SELECT id, nome, codigo_modalidade FROM modalidade";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                int mod_id = rst.getInt("id");
                String nome = String.values() [rst.getString("nome")];
                int cod_mod = rst.getInt("codigo_modalidade");
                Modalidade m = new Modalidade(mod_id, nome, cod_mod);
                modalidade.add(m);
			}
			return modalidade;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

}

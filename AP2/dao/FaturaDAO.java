package AP2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import modelo.Aluno;
import modelo.Fatura;

public class FaturaDAO {

    private Connection connection;

    public FaturaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Fatura fatura, Pessoa aluno) {
        try {
            String sql = "INSERT INTO fatura (valor, data_vencimento, codigo_fatura, fk_aluno) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setFloat(1, fatura.getValor());
                pstm.setLocalDate(2, fatura.getData_Vencimento());
                pstm.setInt(3, fatura.getCodigo_Fatura());
                pstm.setInt(4, aluno.getId());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        fatura.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Fatura> retriveAll(){
        
        ArrayList<Fatura> fatura = new ArrayList<Fatura>();

		try {
			String sql = "SELECT id, valor, data_vencimento, codigo_fatura, fk_aluno FROM telefone";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                int fat_id = rst.getInt("id");
                float valor = rst.getFloat("valor");
                LocalDate dat_ven = LocalDate.values()[rst.getLocalDate("data_vencimento")];
                int cod_fat = rst.getInt("codigo_fatura");
                int alun = rst.getInt("fk_aluno");
                Fatura f = new Fatura(fat_id, valor, dat_ven, cod_fat, alun);
                fatura.add(f);
			}
			return fatura;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

}

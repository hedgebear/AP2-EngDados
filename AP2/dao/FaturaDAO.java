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



public class FaturaDAO {

    private Connection connection;

    public FaturaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Fatura fatura, Aluno aluno) {
        try {
            String sql = "INSERT INTO fatura (valor, codigo_fatura, data_vencimento, fk_aluno) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setFloat(1, fatura.getValor());
                pstm.setInt(2, fatura.getCodigo_Fatura());
                pstm.setObject(3, fatura.getData_Vencimento());
                pstm.setString(4, aluno.getMatricula());
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

    public ArrayList<Fatura> retriveAllFaturas(){
        
        ArrayList<Fatura> faturas = new ArrayList<Fatura>();
        AlunoDAO adao = new AlunoDAO(connection);
		try {
			String sql = "SELECT id, valor, data_vencimento, codigo_fatura, fk_aluno FROM fatura ";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while(rst.next()){
                    int fat_id = rst.getInt("id");
                    float valor = rst.getFloat("valor");
                    LocalDate dat_ven = rst.getObject("data_vencimento",LocalDate.class);
                    int cod_fat = rst.getInt("codigo_fatura");
                    String alun = rst.getString("fk_aluno");
                    Aluno fk_aluno = adao.consultarAlunoMatricula(alun);
                    Fatura f = new Fatura(fat_id, valor, dat_ven, cod_fat, fk_aluno);
                    faturas.add(f);
                }
            }
			return faturas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    // metodo para atualizar dados da tabela
    public void atualizarFatura(Fatura fatura) {
        try {
            String sql = "UPDATE fatura SET valor = ?, data_vencimento = ? WHERE codigo_fatura = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setDouble(1, fatura.getValor());
                pstm.setObject(2, fatura.getData_Vencimento());
                pstm.setInt(3, fatura.getCodigo_Fatura());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para deleção de dados da tabela
    public void deletarFatura(Fatura fatura) {
        try {
            String sql = "DELETE FROM fatura WHERE codigo_fatura = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, fatura.getCodigo_Fatura());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para consultar dados de um elemento específico da tabela
    public Fatura consultarfatura(Fatura fatura) {
        Fatura f = null;
        AlunoDAO adao = new AlunoDAO(connection);
        try {
            String sql = "SELECT id, valor, data_vencimento, codigo_fatura, fk_aluno FROM fatura "
                    + "WHERE codigo_fatura = ? ";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1,fatura.getCodigo_Fatura());

                try (ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        int fat_id = rst.getInt(1);
                        float valor = rst.getInt(2);
                        LocalDate data_vencimento = rst.getObject(3, LocalDate.class);
                        int cod_fatura = rst.getInt(4);
                        Aluno fk_aluno = adao.consultarAlunoMatricula(rst.getString("fk_aluno"));
                        f = new Fatura(fat_id, valor, data_vencimento, cod_fatura, fk_aluno);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return f ;
    }

}

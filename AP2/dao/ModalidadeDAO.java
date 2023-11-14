package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import modelo.Modalidade;
import modelo.Turma;

public class ModalidadeDAO {

    private Connection connection;

    public ModalidadeDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Modalidade modalidade) {
        try {
            String sql = "INSERT INTO modalidade (nome, codigo_modalidade) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, modalidade.getNome());
                pstm.setInt(2, modalidade.getCodigo_Modalidade());
                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        modalidade.setId(rst.getInt(1));
                        for (Turma turma : modalidade.getTurmas()) {
                            TurmaDAO tdao = new TurmaDAO(connection);
                            tdao.create(turma, modalidade);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Modalidade> retriveAllSemTurma(){
        
        ArrayList<Modalidade> modalidade = new ArrayList<Modalidade>();

		try {
			String sql = "SELECT id, nome, codigo_modalidade FROM modalidade";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                int mod_id = rst.getInt("id");
                String nome = rst.getString("nome");
                int cod_mod = rst.getInt("codigo_modalidade");
                Modalidade m = new Modalidade(mod_id, nome, cod_mod);
                modalidade.add(m);
			}
			return modalidade;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public ArrayList<Modalidade> retriveAllComTurma(){
        
        ArrayList<Modalidade> modalidade = new ArrayList<Modalidade>();
        Modalidade ultimo = null

		try {
			String sql = "SELECT m.id, m.nome, m.codigo_modalidade, t.id, t.codigo_turma, t.data_turma, t.hora_turma"
                + "FROM modalidade as m"
                + "INNER JOIN turma as t on m.codigo_modalidade = t.fk_modalidade";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimo == null || ultimo.getId() != rst.getInt(1)) {
                            int mod_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            int cod_mod = rst.getInt(3);
                            Modalidade m = new Modalidade(mod_id, nome, cod_mod);
                            modalidade.add(m);
                            ultimo = m;
                        }
                        int tur_id = rst.getInt(4);
                        float codigo_turma = rst.getInt(5);
                        LocalDate data_turma = rst.getObject(6, LocalDate.class);
                        String hora_turma = rst.getString(7);
                        Turma t = new Turma(tur_id, codigo_turma, data_turma, hora_turma);
                        ultimo.addTurma(t);
                    }
                }            
			}
			return modalidade;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public ArrayList<Modalidade> retriveAllComSemTurma(){
        
        ArrayList<Modalidade> modalidade = new ArrayList<Modalidade>();
        Modalidade ultimo = null

		try {
			String sql = "SELECT m.id, m.nome, m.codigo_modalidade, t.id, t.codigo_turma, t.data_turma, t.hora_turma"
                + "FROM modalidade as m"
                + "LEFT JOIN turma as t on m.codigo_modalidade = t.fk_modalidade";
			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimo == null || ultimo.getId() != rst.getInt(1)) {
                            int mod_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            int cod_mod = rst.getInt(3);
                            Modalidade m = new Modalidade(mod_id, nome, cod_mod);
                            modalidade.add(m);
                            ultimo = m;
                        }
                        if(rst.getInt(4) != 0){
                            int tur_id = rst.getInt(4);
                            float codigo_turma = rst.getInt(5);
                            LocalDate data_turma = rst.getObject(6, LocalDate.class);
                            String hora_turma = rst.getString(7);
                            Turma t = new Turma(tur_id, codigo_turma, data_turma, hora_turma);
                            ultimo.addTurma(t);
                        }
                    }
                }            
			}
			return modalidade;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public void atualizarModalidade(Modalidade modalidade) {
        try {
            String sql = "UPDATE modalidade SET nome = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, modalidade.getNome());
                pstm.setInt(2, modalidade.getId());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarModalidade(Modalidade modalidade) {
        try {
            String sql = "DELETE FROM modalidade WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, modalidade.getId());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Professor consultarModalidadeCodigo(int codigo_modalidade) {
        Professor p = null;
        try {
            String sql = "SELECT m.id, m.nome, t.id, t.codigo_turma, t.data_turma, t.hora_turma"
                    + "FROM modalidade as m"
                    + "INNER JOIN turma as t on m.codigo_modalidade = t.fk_modalidade";
                    + "WHERE codigo_modalidade = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1,codigo_modalidade);

                try (ResultSet rst = pstm.getResultSet()) {
                    if (rst.next()) {
                        int m_id = rst.getInt(1);
                        String nome = rst.getString(2);
                        m = new Modalidade(m_id, nome);
                        
                        int tur_id = rst.getInt(3);
                        float codigo_turma = rst.getInt(4);
                        LocalDate data_turma = rst.getObject(5, LocalDate.class);
                        String hora_turma = rst.getString(6);
                        Turma t = new Turma(tur_id, codigo_turma, data_turma, hora_turma);
                        m.addTurma(t);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return m;
    }

}

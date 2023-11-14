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


public class TurmaDAO {

    private Connection connection;

    public TurmaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Turma turma, Professor professor, Modalidade modalidade) {
        try {
            String sql = "INSERT INTO turma (codigo_turma, data_turma, hora_turma, fk_professor, fk_modalidade) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, turma.getCodigo_turma());
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

    public ArrayList<Turma> retriveAllTurmas(){
        
        ArrayList<Turma> turma = new ArrayList<Turma>();

        try {
            String sql = "SELECT id, codigo_turma, data_turma, hora_turma FROM turma";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                int tur_id = rst.getInt("id");
                int cod_turma = rst.getInt("codigo_turma");
                LocalDate data_turma = rst.getObject("data_turma",LocalDate.class);
                String hora_turma = rst.getString("hora_turma");
                Turma t = new Turma(tur_id, cod_turma, data_turma, hora_turma);
                turma.add(t);
            }
            return turma;
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
                pstm.setInt(1, fatura.getCodigo_Turma());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Turma consultarTurma(Turma turma) {
        Turma t = null;
        try {
            String sql = "SELECT t.id, t.codigo_Turma, t.data_turma, t.hora_turma, t.fk_professor , t.fk_modalidade"
                    + "WHERE codigo_fatura = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1,turma.getCodigo_Turma());

                try (ResultSet rst = pstm.getResultSet()) {
                    if (rst.next()) {
                        int tur_id = rst.getInt(7);
                        int cod_turma = rst.getInt(8);
                        LocalDate data_turma = rst.getObject(9, LocalDate.class);
                        String hora_turma = rst.getString(10);
                        t = new Turma(tur_id, cod_turma, data_turma, hora_turma);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return t ;
    }
}

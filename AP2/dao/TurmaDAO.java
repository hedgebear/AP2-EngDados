package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Aluno;
import modelo.Turma;
import modelo.Professor;
import modelo.Modalidade;


public class TurmaDAO {

    private Connection connection;

    public TurmaDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Turma turma, Aluno aluno, Professor professor, Modalidade modalidade) {
        try {
            String sql = "INSERT INTO turma (codigo_turma, data_turma, hora_turma, fk_professor, fk_modalidade) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, turma.getCodigo_Turma());
                pstm.setLocalDate(2, turma.getData_Turma());
                pstm.setInt(3, turma.getHora_Turma());
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

    public ArrayList<Turma> retriveAll(){
        
        ArrayList<Turma> turma = new ArrayList<Turma>();

        try {
            String sql = "SELECT id, codigo_turma, data_turma, hora_turma, fk_professor, fk_modalidade FROM turma";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                ResultSet rst = pstm.getResultSet();
                int tur_id = rst.getInt("id");
                int cod_turma = rst.getInt("codigo_turma");
                LocalDate daturma = rst.getObject("data_turma",LocalDate.class);
                String hor_turma = rst.getString("hora_turma");
                Turma t = new Turma(tur_id, cod_turma, daturma, hor_turma);
                turma.add(t);
            }
            return turma;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
package AP2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;

import java.util.ArrayList;

import AP2.modelo.Aluno;
import AP2.modelo.Fatura;
import AP2.modelo.Telefone;
import AP2.modelo.TipoTelefone;

public class AlunoDAO {

    private Connection connection;

    public AlunoDAO(Connection connection) {
        this.connection = connection;
    }
    /* 
    public void createAlunolala(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno (nome, cpf, matricula, email, telefone) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getCpf());
                pstm.setObject(3, aluno.getDataNascimento());
                pstm.setInt(4, aluno.getIdade());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setId(rst.getInt(1));
                        for (Fatura fatura : aluno.getFaturas()) {
                            FaturaDAO fdao = new FaturaDAO(connection);
                            fdao.create(fatura, aluno)
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } 
    */
    public void createAluno(Aluno aluno) {
        try {
            String sql = "INSERT INTO aluno (nome, cpf, matricula, email, telefone) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, aluno.getNome());
                pstm.setString(2, aluno.getCpf());
                pstm.setInt(3, aluno.getMatricula());
                pstm.setString(4, aluno.getEmail());
                pstm.setInt(5, aluno.getTelefone());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        aluno.setId(rst.getInt(1));
                        for (Fatura fatura : aluno.getFaturas()) {
                            FaturaDAO fdao = new FaturaDAO(connection);
                            fdao.create(fatura, aluno);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> retriveAllSemFatura(){
        
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		try {
			String sql = "SELECT id, nome, cpf, matricula, email, telefone FROM aluno";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    String cpf = rst.getString("cpf");
                    int matricula = rst.getInt("matricula");
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

    public ArrayList<Aluno> retriveAlunosComFatura(){

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Aluno ultimo = null;
        try {

            String sql = "SELECT a.id, a.nome, a.cpf, a.matricula, a.email, a.telefone, f.id, f.valor, f.data_vencimento, f.codigo_fatura "
                    + "FROM aluno AS a "
                    + "INNER JOIN fatura AS f ON a.id = f.fk_aluno";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimo == null || ultimo.getId() != rst.getInt(1)) {
                            int a_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String cpf = rst.getString(3);
                            int matricula = rst.getInt(4);
                            String email = rst.getString(5);
                            int telefone = rst.getInt(6);
                            Aluno a = new Aluno(a_id, nome, cpf, matricula, email, telefone);
                            alunos.add(a);
                            ultimo = a;
                        }

                        int fat_id = rst.getInt(7);
                        float valor = rst.getInt(8);
                        LocalDate data_vencimento = rst.getObject(9, LocalDate.class)
                        int cod_fatura = rst.getInt(10);
                        Fatura f = new Fatura(fat_id, valor, data_vencimento, cod_fatura);
                        ultimo.addFatura(f);
                    }
                }
                return alunos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> retriveAllComFatura(){

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Aluno ultimo = null;
        try {

            String sql = "SELECT a.id, a.nome, a.cpf, a.matricula, a.email, a.telefone, f.id, f.valor, f.data_vencimento, f.codigo_fatura "
                    + "FROM aluno AS a "
                    + "LEFT JOIN fatura AS f ON a.id = f.fk_aluno";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultimo == null || ultimo.getId() != rst.getInt(1)) {
                            int a_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String cpf = rst.getString(3);
                            int matricula = rst.getInt(4);
                            String email = rst.getString(5);
                            int telefone = rst.getInt(6);
                            Aluno a = new Aluno(a_id, nome, cpf, matricula, email, telefone);
                            alunos.add(a);
                            ultimo = a;
                        }
                        if(rst.getInt(7) != 0){
                            int fat_id = rst.getInt(7);
                            float valor = rst.getInt(8);
                            LocalDate data_vencimento = rst.getObject(9, LocalDate.class)
                            int cod_fatura = rst.getInt(10);
                            Fatura f = new Fatura(fat_id, valor, data_vencimento, cod_fatura);
                            ultimo.addFatura(f);
                        }
                    }
                }
                return alunos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para inserção de dados na tabela
    public void inserirNaTabela(TipoDeDado objetoDeDados) {
        try {
            String sql = "INSERT INTO minha_tabela (coluna1, coluna2, coluna3) VALUES (?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setTipoDeDado(1, objetoDeDados.getColuna1());
                pstm.setTipoDeDado(2, objetoDeDados.getColuna2());
                pstm.setTipoDeDado(3, objetoDeDados.getColuna3());

                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir na tabela: " + e);
        }
    }

    // Método para atualização de dados na tabela
    public void atualizarNaTabela(TipoDeDado objetoDeDados) {
        try {
            String sql = "UPDATE minha_tabela SET coluna1 = ?, coluna2 = ?, coluna3 = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setTipoDeDado(1, objetoDeDados.getColuna1());
                pstm.setTipoDeDado(2, objetoDeDados.getColuna2());
                pstm.setTipoDeDado(3, objetoDeDados.getColuna3());
                pstm.setInt(4, objetoDeDados.getId());

                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tabela: " + e);
        }
    }

    // Método para deleção de dados da tabela
    public void deletarDaTabela(int id) {
        try {
            String sql = "DELETE FROM minha_tabela WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, id);

                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar da tabela: " + e);
        }
    }

    // Método para consultar dados de um elemento específico da tabela
    public TipoDeDado consultarElemento(int id) {
        TipoDeDado objetoDeDados = null;
        try {
            String sql = "SELECT coluna1, coluna2, coluna3 FROM minha_tabela WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, id);

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        objetoDeDados = new TipoDeDado(
                            rs.getTipoDeDado("coluna1"),
                            rs.getTipoDeDado("coluna2"),
                            rs.getTipoDeDado("coluna3")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar elemento da tabela: " + e);
        }
        return objetoDeDados;
    }

    // Método para consultar dados de todos os elementos da tabela
    public ArrayList<TipoDeDado> consultarTodosElementos() {
        ArrayList<TipoDeDado> listaDeDados = new ArrayList<>();
        try {
            String sql = "SELECT id, coluna1, coluna2, coluna3 FROM minha_tabela";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        TipoDeDado objetoDeDados = new TipoDeDado(
                            rs.getTipoDeDado("coluna1"),
                            rs.getTipoDeDado("coluna2"),
                            rs.getTipoDeDado("coluna3")
                        );
                        objetoDeDados.setId(rs.getInt("id"));
                        listaDeDados.add(objetoDeDados);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar todos os elementos da tabela: " + e);
        }
        return listaDeDados;
    }
    
}

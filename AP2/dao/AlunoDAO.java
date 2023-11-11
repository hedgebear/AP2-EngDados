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
    } */

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
                            fdao.create(fatura, aluno)
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

    public ArrayList<Pessoa> retriveAllComTelefone(){

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        Pessoa ultima = null;
        try {

            String sql = "SELECT p.id, p.nome, p.cpf, p.data_nascimento, p.idade, t.id, t.tipo, t.codigo_pais, t.codigo_area, t.numero "
                    + "FROM pessoa AS p "
                    + "LEFT JOIN telefone AS t ON p.id = t.fk_pessoa";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultima == null || ultima.getId() != rst.getInt(1)) {
                            int p_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String cpf = rst.getString(3);
                            LocalDate data = rst.getObject(4, LocalDate.class);
                            int idade = rst.getInt(5);
                            Pessoa p = new Pessoa(p_id, nome, cpf, data, idade);
                            pessoas.add(p);
                            ultima = p;
                        }

                        if (rst.getInt(6) != 0){
                            int tel_id = rst.getInt(6);
                            TipoTelefone tipo = TipoTelefone.values()[rst.getInt(7)];
                            int cod_pais = rst.getInt(8);
                            int cod_area = rst.getInt(9);
                            int numero = rst.getInt(10);
                            Telefone t = new Telefone(tel_id, tipo, cod_pais, cod_area, numero);
                            ultima.addTelefone(t);
                        }
                    }
                }
                return pessoas;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Exemplo de má prática de programação
    public void createInjection(Pessoa pessoa) throws SQLException {

        String sgbd = "mysql";
        String endereco = "localhost";
        String bd = "agenda";
        String usuario = "root";
        String senha = "mysqlroot";

        Connection connection = DriverManager.getConnection(
                "jdbc:" + sgbd + "://" + endereco + "/" + bd + "?useTimezone=true&serverTimezone=UTC", usuario, senha);

        Statement stm = connection.createStatement();
        stm.execute("INSERT INTO Pessoa (nome, cpf, data_nascimento, idade) VALUES ('"
                + pessoa.getNome() + "', '" + pessoa.getCpf() + "', '" + pessoa.getDataNascimento() + "', "
                + pessoa.getIdade() + ")", Statement.RETURN_GENERATED_KEYS);
        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            Integer id = rst.getInt(1);
            pessoa.setId(id);
        }
        rst.close();
        connection.close();
    }

    // Exemplo de má prática de programação
    public ArrayList<Pessoa> retrieveInjection(Pessoa pessoa) throws SQLException {

        String sgbd = "mysql";
        String endereco = "localhost";
        String bd = "agenda";
        String usuario = "root";
        String senha = "mysqlroot";

        Connection connection = DriverManager.getConnection(
                "jdbc:" + sgbd + "://" + endereco + "/" + bd + "?useTimezone=true&serverTimezone=UTC", usuario, senha);

        Statement stm = connection.createStatement();
        stm.execute("SELECT nome FROM Pessoa WHERE cpf = '"+pessoa.getCpf()+"'");
        ResultSet rst = stm.getResultSet();
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        while (rst.next()) {
            //int p_id = rst.getInt(1);
            //String nome = rst.getString(2);
            //String cpf = rst.getString(3);
            //LocalDate data = rst.getObject(4, LocalDate.class);
            //int idade = rst.getInt(5);
            String nome = rst.getString(1);
            Pessoa p = new Pessoa(0, nome, "", LocalDate.now());
            pessoas.add(p);
        }
        rst.close();
        connection.close();
        return pessoas;
    }

}


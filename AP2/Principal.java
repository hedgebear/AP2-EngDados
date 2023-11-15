import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.Aluno;
import modelo.Fatura;
import modelo.Modalidade;
import modelo.Professor;
import modelo.Turma;
import dao.ConnectionFactory;
import dao.AlunoDAO;
import dao.FaturaDAO;
import dao.ModalidadeDAO;
import dao.ProfessorDAO;
import dao.TurmaDAO;

public class Principal{


    public static void main(String[] args) throws SQLException {
        //Instaciando objetos
        Aluno aluno1 = new Aluno("Lucas Fernandes", "00011122200","202203369016","lucas2002mkx@gmail.com",999665643 );
        Fatura fatura1 = new Fatura(2000,LocalDate.of(2023,11,30),0001,aluno1);
        

        
        // Pessoa pessoa2 = new Pessoa("Gabriel Martinez", "00011122211", LocalDate.of(2001, 2, 05));
        // Telefone telefone20 = new Telefone(TipoTelefone.Celular,55,21,989963144);
        // Telefone telefone21 = new Telefone(TipoTelefone.Residencial,55,21,22298312);
        // pessoa2.addTelefone(telefone20);
        // pessoa2.addTelefone(telefone21);  
        
        // Pessoa pessoa3 = new Pessoa("Joao Curvello", "00011122222", LocalDate.of(2002, 3, 10));
        // Telefone telefone30 = new Telefone(TipoTelefone.Celular,55,21,994378235);
        // pessoa3.addTelefone(telefone30);

        // Pessoa pessoa4 = new Pessoa("Joao Correia", "00011122233", LocalDate.of(2003, 4, 15));
        // Telefone telefone40 = new Telefone(TipoTelefone.Celular,55,21,964695794);
        // Telefone telefone41 = new Telefone(TipoTelefone.Celular,55,21,96469579);
        // pessoa4.addTelefone(telefone40);
        // pessoa4.addTelefone(telefone41);  

        // Pessoa pessoa5 = new Pessoa("Joao Constant", "00011122244", LocalDate.of(2004, 5, 20)); //55 21 999309064
        // Pessoa pessoa6 = new Pessoa("Matheus Herzog", "00011122255", LocalDate.of(2005, 6, 25)); //55 21 960197272  55 21 96525522
        // Pessoa pessoa7 = new Pessoa("Thaís Bustamante", "00011122266", LocalDate.of(2000, 7, 30)); //55 21 973013773  55 21 24870553
        // Pessoa pessoa8 = new Pessoa("Théo Mauricio", "00011122277", LocalDate.of(2001, 8, 01)); //55 24 992675080  55 24 92675080
        // Pessoa pessoa9 = new Pessoa("Victor Lobianco", "00011122288", LocalDate.of(2002, 9, 05));//55 21 992471219

        
        System.out.println(aluno1);
        System.out.println(fatura1);

        // System.out.println(pessoa2);
        // System.out.println(pessoa3);
        // System.out.println(pessoa4);
        // System.out.println(pessoa5);
        // System.out.println(pessoa6);
        // System.out.println(pessoa7);
        // System.out.println(pessoa8);
        // System.out.println(pessoa9);
        System.out.println("Acabei de printar os objetos em memoria\n\n\n");

        //Fazendo a conexão com o banco
        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        //criando conexão dos DAO
        AlunoDAO adao = new AlunoDAO(connection);
        FaturaDAO fdao = new FaturaDAO(connection);
        ModalidadeDAO mdao = new ModalidadeDAO(connection);
        ProfessorDAO pdao = new ProfessorDAO(connection);
        TurmaDAO tdao = new TurmaDAO(connection);
        
        // criando os objetos no banco
        adao.createAlunoSemTurma(aluno1);
        fdao.create(fatura1, aluno1);
        // pdao.createComTelefone(pessoa2);
        // pdao.createComTelefone(pessoa3);
        // pdao.createComTelefone(pessoa4);
        // pdao.createComTelefone(pessoa5);
        // pdao.createComTelefone(pessoa6);
        // pdao.createComTelefone(pessoa7);
        // pdao.createComTelefone(pessoa8);
        // pdao.createComTelefone(pessoa9);
 
        //selecionando todos os objetos do banco
        ArrayList<Aluno> alunos =  adao.retriveAll();
        ArrayList<Fatura> faturas = fdao.retriveAllFaturas();
        //ArrayList<Pessoa> pessoas =  pdao.retriveAllSemTelefone();
        System.out.println("Comecei a printar os objetos do BD\n");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
        for (Fatura fatura : faturas) {
            System.out.println(fatura);
        }

        //selecionando apenas um objeto pelo banco

        //upgrade de objetos do banco
        
        //deletando objetos do banco

    }

}
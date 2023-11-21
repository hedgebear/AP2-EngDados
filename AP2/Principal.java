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
        Aluno aluno2 = new Aluno("Beatriz Turi", "00011122500","202203795211","beatrizturiparaujo@gmail.com",969815773 );
        Aluno aluno3 = new Aluno("Bingo da Silva", "00011122547","202206995211","bingoS@gmail.com",969885773 );
        Aluno aluno4 = new Aluno("Karlos conka", "01511122500","202203795241","beata@gmail.com",969815573 );
        Aluno aluno5 = new Aluno("josias ana", "14011122547","202206995217","bingAS@gmail.com",949885773 );
        Aluno aluno6 = new Aluno("Maria Oliveira", "21011122500", "202203795111", "maria@gmail.com", 979815773);
        Aluno aluno8 = new Aluno("Mario Armario", "21011682500", "202203795175", "marioArmario@gmail.com", 979845773);
        Aluno aluno9 = new Aluno("Ludimilo", "45011122500", "202207895111", "LudMilo@gmail.com", 976815773);
        Aluno aluno10 = new Aluno("Luizo Sonzo", "21022222500", "202903795111", "Luizo@gmail.com", 939815773);
        
        

        Professor professor1 = new Professor(1, "Janio quadros",  "11554426958", "lutador", "090909", "cachotto@gmail.com",969885773 );
        Modalidade modalidade1 = new Modalidade("Natacao", 17);
        Professor professor2 = new Professor(2, "Ana Paula", "22554426958", "coreógrafa", "080808", "ana@gmail.com", 959885773);
        Modalidade modalidade2 = new Modalidade("Dança Contemporânea", 18);
        Modalidade modalidade4 = new Modalidade("judo", 19);

        Turma turma1 = new Turma(23,LocalDate.of(2023,10,10) , "13:00", modalidade1, professor1);
        Turma turma2 = new Turma(24,LocalDate.of(2023,10,10) , "14:00", modalidade1, professor1);
        Turma turma3 = new Turma(25, LocalDate.of(2023, 10, 11), "15:00", modalidade2, professor2);
        Turma turma4 = new Turma(26, LocalDate.of(2023, 10, 11), "16:00", modalidade2, professor2);
        Turma turma5 = new Turma(27, LocalDate.of(2023, 10, 12), "17:00", modalidade2, professor2);
        Turma turma6 = new Turma(28, LocalDate.of(2023, 10, 12), "18:00", modalidade2, professor2);
        Turma turma8 = new Turma(29, LocalDate.of(2023, 10, 12), "19:00", modalidade4, professor1);
        Turma turma9 = new Turma(30, LocalDate.of(2023, 10, 12), "20:00", modalidade4, professor1);
        


        Fatura fatura1 = new Fatura(2000,LocalDate.of(2023,11,30),1,aluno1);
        Fatura fatura7 = new Fatura(2300,LocalDate.of(2023,12,30),7,aluno1);
        Fatura fatura2 = new Fatura(1500, LocalDate.of(2023, 11, 15), 2, aluno2);
        Fatura fatura8 = new Fatura(1300, LocalDate.of(2023, 12, 15), 8, aluno2);
        Fatura fatura3 = new Fatura(1800, LocalDate.of(2023, 11, 20), 3, aluno3);
        Fatura fatura9 = new Fatura(1400, LocalDate.of(2023, 12, 20), 9, aluno3);
        Fatura fatura4 = new Fatura(2000, LocalDate.of(2023, 11, 25), 4, aluno4);
        Fatura fatura10 = new Fatura(2700, LocalDate.of(2023, 12, 25), 10, aluno4);
        Fatura fatura5 = new Fatura(1700, LocalDate.of(2023, 11, 28), 5, aluno5);
        Fatura fatura11 = new Fatura(1600, LocalDate.of(2023, 12, 28), 11, aluno5);
        Fatura fatura6 = new Fatura(1900, LocalDate.of(2023, 11, 1), 6, aluno6);
        Fatura fatura12 = new Fatura(3100, LocalDate.of(2023, 12, 1), 12, aluno6);

        turma1.addAluno(aluno1);
        turma1.addAluno(aluno2);
        turma1.addAluno(aluno3);

        turma2.addAluno(aluno1);
        turma2.addAluno(aluno2);
        turma2.addAluno(aluno3);

        turma3.addAluno(aluno3);
        turma3.addAluno(aluno4);
        turma3.addAluno(aluno5);
        turma3.addAluno(aluno6);

        turma4.addAluno(aluno3);
        turma4.addAluno(aluno4);
        turma4.addAluno(aluno5);
        turma4.addAluno(aluno6);

        turma5.addAluno(aluno5);
        turma5.addAluno(aluno6);

        turma6.addAluno(aluno5);
        turma6.addAluno(aluno6);

        aluno1.addTurma(turma1);
        aluno1.addTurma(turma2);

        aluno2.addTurma(turma1);
        aluno2.addTurma(turma2);

        aluno3.addTurma(turma1);
        aluno3.addTurma(turma2);
        aluno3.addTurma(turma3);
        aluno3.addTurma(turma4);

        aluno4.addTurma(turma3);
        aluno4.addTurma(turma4);

        aluno5.addTurma(turma3);
        aluno5.addTurma(turma4);
        aluno5.addTurma(turma5);
        aluno5.addTurma(turma6);

        aluno6.addTurma(turma3);
        aluno6.addTurma(turma4);
        aluno6.addTurma(turma5);
        aluno6.addTurma(turma6);


        
        System.out.println(aluno1);
        System.out.println(aluno2);
        System.out.println(aluno3);
        System.out.println(aluno4);
        System.out.println(aluno5);
        System.out.println(aluno6);

        System.out.println(professor1);
        System.out.println(modalidade1);
        System.out.println(professor2);
        System.out.println(modalidade2);

        System.out.println(turma1);
        System.out.println(turma2);
        System.out.println(turma3);
        System.out.println(turma4);
        System.out.println(turma5);
        System.out.println(turma6);

        System.out.println(fatura1);
        System.out.println(fatura2);
        System.out.println(fatura3);
        System.out.println(fatura4);
        System.out.println(fatura5);
        System.out.println(fatura6);
        System.out.println(fatura7);
        System.out.println(fatura8);
        System.out.println(fatura9);
        System.out.println(fatura10);
        System.out.println(fatura11);
        System.out.println(fatura12);
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
        adao.createAlunoSemTurma(aluno2);
        adao.createAlunoSemTurma(aluno3);
        adao.createAlunoSemTurma(aluno4);
        adao.createAlunoSemTurma(aluno5);
        adao.createAlunoSemTurma(aluno6);
        fdao.create(fatura1, aluno1);
        fdao.create(fatura7, aluno1);
        mdao.createModalidade(modalidade1);
        pdao.createProfessor(professor1);
        mdao.createModalidade(modalidade2);
        pdao.createProfessor(professor2);
        tdao.createComAluno(turma1, professor1, modalidade1);
        tdao.createComAluno(turma2, professor1, modalidade1);
        tdao.createComAluno(turma3, professor1, modalidade2);
        tdao.createComAluno(turma4, professor2, modalidade1);
        tdao.createComAluno(turma5, professor2, modalidade2);
        tdao.createComAluno(turma6, professor2, modalidade2);

 
        //selecionando todos os objetos do banco
        ArrayList<Aluno> alunos =  adao.retriveAll();
        ArrayList<Aluno> alunosTurma =  adao.retriveAllAlunosComTurma();
        ArrayList<Fatura> faturas = fdao.retriveAllFaturas();
        ArrayList<Turma> turmas = tdao.retriveAllTurmasComAlunos();
        ArrayList<Modalidade> modalidades = mdao.retriveAll();
        ArrayList<Professor> professores = pdao.retriveAll();

        System.out.println("teste dos alunos\n");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
        System.out.println("teste dos alunos com turma\n");
         for (Aluno aluno : alunosTurma) {
             System.out.println(aluno);
         }
        System.out.println("teste das faturas\n");
        for (Fatura fatura : faturas) {
             System.out.println(fatura);
        }
        System.out.println("Teste de turmas com aluno\n");
        for (Turma turma : turmas) {
            System.out.println(turma);
        }
        System.out.println("teste das modalidades\n");
        for (Modalidade modalidade : modalidades) {
             System.out.println(modalidade);
        }
        System.out.println("teste dos professores\n");
        for (Professor professor : professores) {
             System.out.println(professor);
        }

        //selecionando apenas um objeto pelo banco
        System.out.println("apenas um objeto\n");
        System.out.println(fdao.consultarfatura(fatura6));
        System.out.println(adao.consultarAlunoMatricula("202206995211"));
        System.out.println(mdao.consultarModalidadeCodigo(17));
        System.out.println(pdao.consultarProfessorCodigo(2));
        System.out.println(tdao.consultarTurmaEspcComAlunos(turma6));
        System.out.println(tdao.consultarTurmaEspcSemAlunos(turma6));
        //upgrade de objetos do banco
        Aluno aluno13 = new Aluno("Maria Oliveira Moraes", "21011122500", "202203795111", "mariaMoraes@gmail.com", 979815773);
        adao.atualizarAluno(aluno13);
        Fatura fatura13 = new Fatura(50, LocalDate.of(2023, 12, 10),12 , aluno13);
        fdao.atualizarFatura(fatura13);
        Modalidade modalidade3 = new Modalidade("Boliche", 18);
        mdao.atualizarModalidade(modalidade3);
        Professor professor3 = new Professor(2, "Holando", "19815730715", "crossfit", "030621", "holando@gmail.com", 959885473);
        pdao.atualizarProfessor(professor3);
        Turma turma7 = new Turma(28, LocalDate.of(2023, 7, 12), "18:00", modalidade3, professor3);
        tdao.atualizarTurma(turma7);
        //deletando objetos do banco
        adao.deletarAluno(aluno13);
        fdao.deletarFatura(fatura12);
        mdao.deletarModalidade(modalidade2);
        pdao.deletarProfessor(professor2);
        tdao.deletarTurma(turma6);
    }

}
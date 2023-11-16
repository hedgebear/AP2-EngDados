package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Turma {

	private int id;
	private int codigo_turma;
	private LocalDate data_turma;
	private String hora_turma;
	private Modalidade modalidade;
	private Professor professor;
	private ArrayList<Aluno> alunos;

	public Turma(int id, int codigo_turma, LocalDate data_turma, String hora_turma, Modalidade modalidade, Professor professor) {
		this.id = id;
		this.codigo_turma = codigo_turma;
		this.data_turma = data_turma;
		this.hora_turma = hora_turma;
		this.modalidade = modalidade;
		this.professor = professor;
		this.alunos = new ArrayList<Aluno>();
	}

	public Turma(int codigo_turma, LocalDate data_turma, String hora_turma, Modalidade modalidade, Professor professor) {
		this.codigo_turma = codigo_turma;
		this.data_turma = data_turma;
		this.hora_turma = hora_turma;
		this.modalidade = modalidade;
		this.professor = professor;
		this.alunos = new ArrayList<Aluno>();
	}

	public Turma(int id, int codigo_turma, LocalDate data_turma, String hora_turma, Modalidade modalidade, Professor professor,ArrayList<Aluno> alunos) {
		this.id = id;
		this.codigo_turma = codigo_turma;
		this.data_turma = data_turma;
		this.hora_turma = hora_turma;
		this.modalidade = modalidade;
		this.professor = professor;
		this.alunos = alunos;
	}

	public Turma(int codigo_turma, LocalDate data_turma, String hora_turma, Modalidade modalidade, Professor professor,ArrayList<Aluno> alunos) {
		this.codigo_turma = codigo_turma;
		this.data_turma = data_turma;
		this.hora_turma = hora_turma;
		this.modalidade = modalidade;
		this.professor = professor;
		this.alunos = alunos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo_Turma() {
		return codigo_turma;
	}

	public void setCodigo_Turma(int codigo_turma) {
		this.codigo_turma = codigo_turma;
	}

	public LocalDate getData_Turma() {
		return data_turma;
	}

	public void setData_Turma(LocalDate data_turma) {
		this.data_turma = data_turma;
	}

	public String getHora_Turma(){	
		return hora_turma;
	}

	public void setHora_Turma(String hora_turma) {
		this.hora_turma = hora_turma;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

    public ArrayList<Aluno> getAlunos(){
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }
	
    public void addAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
    }
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Turma: ").append(codigo_turma)
          .append(", Data: ").append(data_turma)
          .append(", Hora: ").append(hora_turma)
          .append(", Modalidade: ").append(modalidade)
          .append(", Professor: ").append(professor)
          .append(", Alunos: ").append(Arrays.toString(alunos.toArray()));
        return sb.toString();
    }

	
}

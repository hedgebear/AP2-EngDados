package modelo;

import java.util.ArrayList;

public class Aluno {

	private int id;
	private String nome;
	private String cpf;
	private String matricula;
	private String email;
	private int telefone;
	private ArrayList<Turma> turmas;
	

	public Aluno(int id, String nome, String cpf, String matricula, String email, int telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
		this.turmas = new ArrayList<Turma>();
	}
	
	public Aluno(String nome, String cpf, String matricula, String email, int telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
		this.turmas = new ArrayList<Turma>();
	}

	 public Aluno(int id, String nome, String cpf, String matricula, String email, int telefone, ArrayList<Turma> turmas) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.email = email;
        this.telefone = telefone;
        this.turmas = new ArrayList<>();  
    }

    public Aluno(String nome, String cpf, String matricula, String email, int telefone, ArrayList<Turma> turmas) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.email = email;
        this.telefone = telefone;
        this.turmas = new ArrayList<>();  
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(ArrayList<Turma> turmas) {
        this.turmas = turmas;
    }

    public void addTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public void removeTurma(Turma turma) {
        this.turmas.remove(turma);
    }
	
	@Override
    public String toString() {
        return "{'aluno':{'id': " + this.id + ", 'nome': '" + this.nome +
               "', 'cpf': '" + this.cpf + "', 'matricula': '" + this.matricula +
               "', 'email': '" + this.email + "', 'telefone': " + this.telefone + "}}";
    }

	public Turma[] getAlunos() {
		return null;
	}
	
}
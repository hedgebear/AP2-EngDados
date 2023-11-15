package modelo;

import java.util.ArrayList;

public class Aluno {

	private int id;
	private String nome;
	private String cpf;
	private String matricula;
	private String email;
	private int telefone;
	private ArrayList<Fatura> faturas;
	

	public Aluno(int id, String nome, String cpf, String matricula, String email, int telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
		this.faturas = new ArrayList<Fatura>();
	}
	
	public Aluno(String nome, String cpf, String matricula, String email, int telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
		this.faturas = new ArrayList<Fatura>();
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

	public ArrayList<Fatura> getFaturas(){
		return faturas;
	}
	
	public void setFaturas(ArrayList<Fatura> faturas) {
		this.faturas = faturas;
	}

	public void addFatura(Fatura fatura) {
        this.faturas.add(fatura);
    }

    public void removeFatura(Fatura fatura) {
        this.faturas.remove(fatura);
    }
	
	@Override
	public String toString() {
		return "{'aluno':{'id': " + this.id + ", 'nome': '" + this.nome +
			"', 'cpf': '" + this.cpf + "', 'matricula': " + this.matricula +
			", 'Email': '" + this.email + "'}}";
	}
	
}
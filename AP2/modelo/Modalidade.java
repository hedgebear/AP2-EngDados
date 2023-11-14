package modelo;

import java.util.ArrayList;

public class Modalidade {

	private int id;
	private String nome;
	private int codigo_modalidade;
	private ArrayList<Turma> turmas;

	public Modalidade(int id, String nome, int codigo_modalidade) {
		this.id = id;
		this.nome = nome;
		this.codigo_modalidade = codigo_modalidade;
	}
	
	public Modalidade(String nome, int codigo_modalidade) {
		this.nome = nome;
		this.codigo_modalidade = codigo_modalidade;
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
	
	public int getCodigo_Modalidade() {
		return codigo_modalidade;
	}

	public void setCodigo_Modalidade(int codigo_modalidade) {
		this.codigo_modalidade = codigo_modalidade;
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
		return "{'modalidade':{'id': " + this.id + ", 'nome': '" + this.nome + "}}";
	}
}


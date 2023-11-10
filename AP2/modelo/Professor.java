package AP2.modelo;

import java.util.ArrayList;

public class Professor {

	private int id;
	private int codigo_professor;
	private String nome;
	private String cpf;
	private String especialização;
	private String contaBanco;
	private String email;
	private ArrayList<Telefone> telefones;

	public Professor(int id, String nome, String cpf, ArrayList<Telefone> telefones) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefones = telefones;
	}

	public Professor(String nome, String cpf, ArrayList<Telefone> telefones) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefones = telefones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCodigo_professor() {
		return codigo_professor;
	}

	public void setCodigo_professor(int codigo_professor) {
		this.codigo_professor = codigo_professor;
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

	public String getEspecialização() {
		return especialização;
	}

	public void setEspecialização(String especialização) {
		this.especialização = especialização;
	}

	public String getContaBanco() {
		return contaBanco;
	}

	public void setContaBanco(String contaBanco) {
		this.contaBanco = contaBanco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void addTelefone(Telefone telefone) {
		this.telefones.add(telefone);
	}

	public void removeTelefone(Telefone telefone) {
		this.telefones.remove(telefone);
	}

	@Override
	public String toString() {
		return "{'professor':{'id': " + this.id + ", 'codigo_professor': " + this.codigo_professor 
		+ ", 'nome': '" + this.nome + "', 'cpf': '" + this.cpf + "', 'especialização': '" + this.especialização 
		+ "', 'contaBanco': '" + this.contaBanco + "', 'Email': '" + this.email + "'}}";
	}
	
}


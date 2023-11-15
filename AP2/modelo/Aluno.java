package modelo;

public class Aluno {

	private int id;
	private String nome;
	private String cpf;
	private String matricula;
	private String email;
	private int telefone;
	

	public Aluno(int id, String nome, String cpf, String matricula, String email, int telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Aluno(String nome, String cpf, String matricula, String email, int telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.email = email;
		this.telefone = telefone;
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
	
	@Override
    public String toString() {
        return "{'aluno':{'id': " + this.id + ", 'nome': '" + this.nome +
               "', 'cpf': '" + this.cpf + "', 'matricula': '" + this.matricula +
               "', 'email': '" + this.email + "', 'telefone': " + this.telefone + "}}";
    }
	
}
package modelo;

public class Modalidade {

	private int id;
	private String nome;
	private int codigo_modalidade;

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
	
	@Override
    public String toString() {
        return "{'Modalidade':{'id': " + this.id + ", 'nome': '" + this.nome +
               "', 'codigo_modalidade': " + this.codigo_modalidade + "}}";
    }
}


package modelo;

import java.time.LocalDate;

public class Fatura {

	private int id;
	private float valor;
	private LocalDate data_vencimento;
	private int codigo_fatura;
	private Aluno aluno;

	public Fatura(int id, float valor, LocalDate data_vencimento, int codigo_fatura, Aluno aluno) {
		this.id = id;
		this.valor = valor;
		this.data_vencimento = data_vencimento;
		this.codigo_fatura = codigo_fatura;
		this.aluno = aluno;
	}
	
	public Fatura(float valor, LocalDate data_vencimento, int codigo_fatura,Aluno aluno) {
		this.valor = valor;
		this.data_vencimento = data_vencimento;
		this.codigo_fatura = codigo_fatura;
		this.aluno = aluno;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public LocalDate getData_Vencimento() {
		return data_vencimento;
	}

	public void setData_Vencimento(LocalDate data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public int getCodigo_Fatura() {
		return codigo_fatura;
	}

	public void setCodigo_Fatura(int codigo_fatura) {
		this.codigo_fatura = codigo_fatura;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	@Override
	public String toString() {
		return "{'identificacao':{'id': " + this.id + ", 'codigo da fatura': '" + this.codigo_fatura + "}}";
	}
	
}
package modelo;

import java.time.LocalDate;

public class Turma {

	private int id;
	private int codigo_turma;
	private LocalDate data_turma;
	private String hora_turma;
	private Modalidade modalidade;
	private Professor professor;

	public Turma(int id, int codigo_turma, LocalDate data_turma, String hora_turma) {
		this.id = id;
		this.codigo_turma = codigo_turma;
		this.data_turma = data_turma;
		this.hora_turma = hora_turma;
	}

	public Turma(int codigo_turma, LocalDate data_turma, String hora_turma) {
		this.codigo_turma = codigo_turma;
		this.data_turma = data_turma;
		this.hora_turma = hora_turma;
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
	
	@Override
	public String toString() {
		return "{'turma':{'id': " + this.id + ", 'codigo_turma': " + this.codigo_turma +
			", 'data_turma': '" + this.data_turma + "', 'hora_turma': '" + this.hora_turma + "'}}";
	}

}

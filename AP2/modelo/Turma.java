package modelo;

import java.time.LocalDate;

public class Turma {

	private int id;
	private int codigo_turma;
	private LocalDate data;
	private String hora;

	public Turma(int id, int codigo_turma, LocalDate data, String hora) {
		this.id = id;
		this.codigo_turma = codigo_turma;
		this.data = data;
		this.hora = hora;
	}

	public Turma(int codigo_turma, LocalDate data, String hora) {
		this.codigo_turma = codigo_turma;
		this.data = data;
		this.hora = hora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo_turma() {
		return codigo_turma;
	}

	public void setCodigo_turma(int codigo_turma) {
		this.codigo_turma = codigo_turma;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getHora(){	
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	@Override
	public String toString() {
		return "{'turma':{'id': " + this.id + ", 'codigo_turma': " + this.codigo_turma +
			", 'data': '" + this.data + "', 'hora': '" + this.hora + "'}}";
	}

}

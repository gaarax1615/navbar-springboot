package com.example.demo.model;

/**
 * Representa un registro; la lista de dropdown se genera según la cantidad de registros.
 */
public class Registro {

	private int numero;
	private String etiqueta;

	public Registro(int numero, String etiqueta) {
		this.numero = numero;
		this.etiqueta = etiqueta;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
}

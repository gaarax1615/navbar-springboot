package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Registro;

/**
 * Servicio que entrega la lista de registros. La cantidad define el dropdown (Tab 1..N).
 * Aquí puedes conectar después con base de datos o API.
 */
@Service
public class RegistroService {

	/**
	 * Lista dinámica de registros. El tamaño de esta lista define cuántos ítems hay en el dropdown.
	 */
	public List<Registro> obtenerRegistros() {
		List<Registro> lista = new ArrayList<>();
		// Cantidad de registros: 10 tabs (Tab 1 .. Tab 10). Puedes cambiar o traer de BD.
		int cantidad = 10;
		for (int i = 1; i <= cantidad; i++) {
			lista.add(new Registro(i, "Tab " + i));
		}
		return lista;
	}
}

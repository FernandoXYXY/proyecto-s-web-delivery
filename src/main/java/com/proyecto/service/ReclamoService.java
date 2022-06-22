package com.proyecto.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Reclamo;

public interface ReclamoService {
	
	public abstract List<Reclamo> listarTodosReclamo();
	public abstract List<Reclamo> listarReclamoPorParametros(int estado, Date fecha, int idCliente, int idTipo);
	
	//metodo para el crud
	public abstract Reclamo insertarReclamo(Reclamo reclamo);
	public abstract Reclamo actualizaReclamo(Reclamo reclamo);
	public abstract void eliminarReclamo(Reclamo reclamo);
	public abstract Optional<Reclamo> buscarPorId(int id);
}

package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Sede;

public interface RegistroSedeService {
	
	public Sede insertaSede(Sede obj);
	public List<Sede> listaSedes();
	public List<Sede> listaSedePorPaisNombreEstado(
			String nombre, int estado, int pais, String codigoPostal, int idSede);
	//CRUD
	public abstract Sede insertaActualizaSede(Sede obj);
	public abstract List<Sede> listarSedePorNombre(String nombre);
	public abstract void eliminaSede(int id);
	public abstract Optional<Sede> buscarSede(int id);
	
}

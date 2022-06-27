package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Sede;
import com.proyecto.repository.RegistroSedeRepository;

@Service
public class RegistroSedeServiceImpl implements RegistroSedeService{

	@Autowired
	private RegistroSedeRepository repository;
	
	@Override
	public Sede insertaSede(Sede obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Sede> listaSedes() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Sede> listaSedePorPaisNombreEstado(String nombre, int estado, int pais, String codigoPostal, int idSede ) {
		// TODO Auto-generated method stub
		return repository.listaSedePorPaisNombreEstado(nombre, estado, pais, codigoPostal, idSede);
	}

	
	///CRUD
	
	@Override
	public Sede insertaActualizaSede(Sede obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<Sede> listarSedePorNombre(String nombre) {
		// TODO Auto-generated method stub
		return repository.listarSedePorNombte(nombre);
	}

	@Override
	public void eliminaSede(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Sede buscaSede(int id) {
		return repository.buscaSede(id);
	}

}

package com.proyecto.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Cliente;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public List<Cliente> listaCliente() {
		return repository.findAll();
	}
	@Override
	public Cliente insertaCliente(Cliente obj) {
		
		return repository.save(obj);
	}
	@Override
	public List<Cliente> listarClientes(String nombres, String apellidos,String dni, String correo,String direccion, int idUbigeo, int estado)
		{
			
			return repository.listarClientes(nombres,apellidos,dni,correo,direccion,idUbigeo,estado);
		}
	
	/*crud*/
	@Override
	public Cliente insertaActualizaCliente(Cliente obj) {
		return repository.save(obj);
		
	}
	@Override
	public List<Cliente> listaClientePorNombre(String nombres) {
		return repository.listaClientePorNombre(nombres);
	}
	@Override
	public void eliminaCliente(int id) {
		repository.deleteById(id);
	}


	@Override
	public Optional<Cliente> buscaCliente(int id) {
		return repository.findById(id);
	}




}

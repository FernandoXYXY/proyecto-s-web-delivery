package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Marca;
import com.proyecto.entidad.Proveedor;

public interface ProveedorService {

	public abstract List<Proveedor> listaProveedor();
	public abstract Proveedor insertaProveedor(Proveedor obj);
	
	
	
	public abstract List<Proveedor> listaProveedorPorTodosUbigeo(
			String razonsocial, String ruc,String direccion,String telefono,
			String celular, String contacto, int idUbigeo, int estado);
	
	public abstract Proveedor insertaActualizaProveedor(Proveedor proveedor);
	public abstract List<Proveedor> listaPorRazonSocialLike(String razonsocial);
	
	public abstract void eliminaProveedor(int id);
    public abstract Proveedor buscaProveedor(int id);
}

package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Producto;

public interface ProductoService {

	public abstract Producto insertarProducto(Producto obj);
	
	public  List<Producto> listarproductos();
	
	public List<Producto> listaproductoporparmetros(String nombre, String serie, int idMarca, int idPais, int estado );
	
	public abstract Producto insetaractualizarproducto(Producto producto);
	
	public abstract List<Producto> listaProductopornombre(String nombre);
	
	/*c*/
	 public abstract void eliminaProducto(int id);
	 public abstract Producto buscaProducto(int id);
	
	
	

}

package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.util.Constantes;
import com.proyecto.entidad.Marca;
import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaDocente() {
		List<Proveedor> lista = proveedorService.listaProveedor();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);//obj.setEstado(Constantes.ESTADO_ACTIVO);
			Proveedor objSalida = proveedorService.insertaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaProveedorConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaProveedorPorTodosConUbigeo(
			@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
			@RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
			@RequestParam(name = "direccion", required = false, defaultValue = "") String direccion,
			@RequestParam(name = "telefono", required = false, defaultValue = "") String telefono,
			@RequestParam(name = "celular", required = false, defaultValue = "") String celular,
			@RequestParam(name = "contacto", required = false, defaultValue = "") String contacto,
			@RequestParam(name = "idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Proveedor> lista = proveedorService.listaProveedorPorTodosUbigeo(
"%"+razonsocial+"%", "%"+ruc+"%", "%"+direccion+"%", "%"+telefono+"%", "%"+celular+"%", "%"+contacto+"%", idUbigeo, estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar (͠≖ ͜ʖ͠≖)👌");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PostMapping("/registraProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaDocente(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProveedor(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(2);
			Proveedor objSalida =  proveedorService.insertaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", "Se registro correctamente (⌐■_■)👌");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	@PutMapping("/actualizaProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaDocente(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			Proveedor objSalida =  proveedorService.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", "Se actualizo correctamente (⌐■_■)👌");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	@GetMapping("/listaProveedorPorRazonLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedorPorRazonLike(@PathVariable("nom") String nom) {
		List<Proveedor> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = proveedorService.listaPorRazonSocialLike("%");
			}else {
				lista = proveedorService.listaPorRazonSocialLike("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	
	
//eliminarr
	@DeleteMapping("/eliminaProveedor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaProveedor(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			Proveedor opt=proveedorService.buscaProveedor(id);
			
			if (opt !=null) {
				proveedorService.eliminaProveedor(id);
				Proveedor x=proveedorService.buscaProveedor(id);
				if (x==null) {
					salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
				} else {
					salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
				}
			}else {
				salida.put("mensaje", Constantes.MENSAJE_ELI_NO_EXISTE_ID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

}

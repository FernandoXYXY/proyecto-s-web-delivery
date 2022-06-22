package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.proyecto.entidad.Sede;
import com.proyecto.service.RegistroSedeService;
import com.proyecto.util.AppSettings;
import com.proyecto.util.Constantes;

@RestController
@RequestMapping("/url/sede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SedeController {
	@Autowired
	private RegistroSedeService registroSedeService;
	
	@GetMapping
	@RequestMapping("/listaSedes")
	@ResponseBody
	public ResponseEntity<List<Sede>> ListaSede(){
		List<Sede> lista = registroSedeService.listaSedes();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@RequestMapping("/RegistraSede")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> RegistraSede(@RequestBody Sede obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setEstado(1);
			obj.setFechaRegistro(new Date());
			Sede objSede = registroSedeService.insertaSede(obj);;
			if(objSede.getNombre() == null) {
				salida.put("mensaje", "no se registro la sede");
			}else {
				salida.put("mensaje", "Se registro Correctamente la Sede");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			salida.put("mensaje", "No se registro, Felicidades tu trabajo esta mal hecho");
		}
		
		return ResponseEntity.ok(salida);
		
	}
	
	@GetMapping("/listaSedeNEP")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaSedeNEP(
			@RequestParam(value="nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value="estado", required = false, defaultValue = "-1") int estado,
			@RequestParam(value="pais", required = false, defaultValue = "-1") int pais,
			@RequestParam(value="codPostal", required = false, defaultValue = "") String codPostal,
			@RequestParam(value="idSede", required = false, defaultValue = "-1") int idSede ){
		Map<String, Object> salida = new HashMap<String, Object>();
		
		try {
			List<Sede> lista = registroSedeService.listaSedePorPaisNombreEstado(nombre + "%",
					estado, pais,"%" + codPostal + "%", idSede);
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
		
		
		return ResponseEntity.ok(salida);
	}
   /**
    *CRUD
    */
	
	
	@GetMapping("/listarSedePorNombre/{nombre}")
	@ResponseBody
	public ResponseEntity<List<Sede>> listaClientePorNombre(@PathVariable("nombre") String nombre){
		List<Sede> lista = null;
		
		try {
			if(nombre.equals("todos")) {
				lista = registroSedeService.listarSedePorNombre("%");
			}else {
				lista = registroSedeService.listarSedePorNombre("%" + nombre + "%");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraSede")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaSede(@RequestBody Sede obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdSede(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Sede objSalida = registroSedeService.insertaActualizaSede(obj);
			if(objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);	
			}else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaSede")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaSede(@RequestBody Sede obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Sede objSalida = registroSedeService.insertaActualizaSede(obj);
			if(objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	@DeleteMapping("/eliminarSede/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaSede(@PathVariable("id")int id){
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Sede> opt = registroSedeService.buscarSede(id);
			if(opt.isPresent()){
				registroSedeService.eliminaSede(id);
				opt = registroSedeService.buscarSede(id);
				if(opt.isEmpty()) {
					salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
				}else {
					salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
				}
			}else {
				salida.put("mensaje", Constantes.MENSAJE_ELI_NO_EXISTE_ID);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			salida.put("mensaje", Constantes.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

package com.cdisejemplo.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import com.cdisejemplo.springboot.app.errors.DataBaseBancoException;
import com.cdisejemplo.springboot.app.models.dao.IClienteDao;
import com.cdisejemplo.springboot.app.models.entity.Cliente;

import com.cdisejemplo.springboot.app.validator.ClienteValidator;

import java.util.Map;

import javax.validation.Valid;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private ClienteValidator clienteValidator;
	
	@RequestMapping(value = "/lista-clientes", method = RequestMethod.GET)
	public String clienteLista(Model model) {
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "lista-clientes";
	}
	
	@RequestMapping(value = "/form-cliente")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Nuevo cliente, ingrese los datos.");
		return "form-cliente";
	}
	
	@RequestMapping(value = "/form-cliente/{id_cliente}")
	public String editar(@PathVariable(value = "id_cliente") Long id_cliente, Map<String, Object> model) {
		Cliente cliente = null;
		
		if(id_cliente > 0) {
			cliente = clienteDao.findOne(id_cliente);
		}else {
			return "redirect:/lista-clientes";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Edite el cliente");
		return "form-cliente";
	}
	
	@RequestMapping(value = "/form-cliente", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		
		clienteValidator.validate(cliente, result);
		
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene los campos correctamente");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar datos, por favor escriba correctamente");
			return "form-cliente";
		}else {
			model.addAttribute("result", false);
		}
		
		model.addAttribute("titulo", "Formulario de cuenta");
		model.addAttribute("mensaje", "Se envió la información correctamente");
		
		try {
			clienteDao.save(cliente);
		}catch(DataBaseBancoException e) {
			e.printStackTrace();
			flash.addAttribute("mensaje", e.getMessage());
		}
		
		
		status.setComplete();
		
		return "redirect:/lista-clientes";
	}
	
	@RequestMapping(value="/eliminar-cliente/{id_cliente}")
	public String eliminar(@PathVariable(value = "id_cliente") Long id_cliente) {
		
		
		
		if(id_cliente > 0) {
			clienteDao.delete(id_cliente);
		}
		return "redirect:/lista-clientes";
	}
}

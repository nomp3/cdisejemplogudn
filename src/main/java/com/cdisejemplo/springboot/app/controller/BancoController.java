package com.cdisejemplo.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdisejemplo.springboot.app.errors.DataBaseBancoException;
import com.cdisejemplo.springboot.app.models.dao.IBancoDao;
import com.cdisejemplo.springboot.app.models.entity.Banco;
import com.cdisejemplo.springboot.app.validator.BancoValidator;

import java.util.Map;

import javax.validation.Valid;

@Controller
@SessionAttributes("banco")
public class BancoController {

	@Autowired
	private IBancoDao bancoDao;
	
	@Autowired
	private BancoValidator bancoValidator;
	
	@RequestMapping(value = "/lista-bancos", method = RequestMethod.GET)
	public String bancoLista(Model model) {
		model.addAttribute("titulo", "Lista de bancos");
		model.addAttribute("bancos", bancoDao.findAll());
		return "lista-bancos";
	}
	
	@RequestMapping(value = "/form-banco")
	public String crear(Map<String, Object> model) {
		Banco banco = new Banco();
		model.put("banco", banco);
		model.put("titulo", "Nuevo banco, ingrese los datos.");
		return "form-banco";
	}
	
	@RequestMapping(value = "/form-banco/{id_banco}")
	public String editar(@PathVariable(value = "id_banco") Long id_banco, Map<String, Object> model) {
		Banco banco = null;
		
		if(id_banco > 0) {
			banco = bancoDao.findOne(id_banco);
		}else {
			return"redirect:/lista-bancos";
		}
		model.put("banco", banco);
		model.put("titulo", "Edite el banco");
		return "form-banco";
	}
	
	@RequestMapping(value = "/form-banco", method = RequestMethod.POST)
	public String guardar(@Valid Banco banco, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		
		bancoValidator.validate(banco, result);
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene los datos correctamente");
			 model.addAttribute("result", result.hasErrors());
			 model.addAttribute("mensaje", "Error al enviar datos, por favor escriba correctamente.");
			return "form-banco";
		}else {
			model.addAttribute("result", false);
		}
		try {
			bancoDao.save(banco);
		}catch(DataBaseBancoException e) {
			 e.printStackTrace();
			 flash.addAttribute("mensaje", e.getMessage());
		}
		
		status.setComplete();
		
		return "redirect:/lista-bancos";
	}
	
	@RequestMapping(value ="/eliminar-banco/{id_banco}")
	public String eliminar(@PathVariable(value = "id_banco") Long id_banco) {
		if(id_banco > 0) {
			bancoDao.delete(id_banco);
		}
		return "redirect:/lista-bancos";
	}
	
}

package com.cdisejemplo.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdisejemplo.springboot.app.editors.ClientePropertyEditors;
import com.cdisejemplo.springboot.app.errors.DataBaseBancoException;
import com.cdisejemplo.springboot.app.models.dao.IClienteDao;
import com.cdisejemplo.springboot.app.models.dao.ICuentaDao;
import com.cdisejemplo.springboot.app.models.entity.Cliente;
import com.cdisejemplo.springboot.app.models.entity.Cuenta;
import com.cdisejemplo.springboot.app.validator.CuentaValidator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

@Controller
@SessionAttributes("cuenta")
public class CuentaController {

	@Autowired
	private ICuentaDao cuentaDao;
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private ClientePropertyEditors clienteEditor;
	
	@Autowired
	private CuentaValidator cuentaValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cliente.class, "cliente", clienteEditor);
		
		binder.addValidators(cuentaValidator);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);//Este metodo indica que no podemos ser flexibles con el formato de entrada
		binder.registerCustomEditor(Date.class, "diaCreacion", new CustomDateEditor(dateFormat, false));
	}
	
	
	@RequestMapping(value = "/lista-cuentas", method = RequestMethod.GET)
	public String cuentaLista(Model model, Map <String, Object> modelCuenta) {
		Cuenta cuenta = new Cuenta();
		modelCuenta.put("cuenta", cuenta);
		model.addAttribute("titulo", "Lista de cuentas");
		model.addAttribute("cuentas", cuentaDao.findAll());
		return "lista-cuentas";
	}
	
	@RequestMapping(value = "/form-cuenta")
	public String crear(Map<String, Object> model, Model modelList) {
		Cuenta cuenta = new Cuenta();
		model.put("cuenta", cuenta);
		model.put("titulo", "Nueva cuenta, ingrese los datos.");
		modelList.addAttribute("listaClientes", clienteDao.findAll());
		return "form-cuenta";
	}
	
	@RequestMapping(value = "/form-cuenta/{id_cuenta}")
	public String editar(@PathVariable(value = "id_cuenta") Long id_cuenta, Map<String, Object> model) {
		Cuenta cuenta = null;
		
		if(id_cuenta != null && id_cuenta > 0) {
			cuenta = cuentaDao.findOne(id_cuenta);
		}else {
			return "redirect:/lista-cuentas";
		}
		model.put("cuenta", cuenta);
		model.put("titulo", "Edite la cuenta");
		return "form-cuenta";
	}
	
	@RequestMapping(value = "/form-cuenta", method = RequestMethod.POST)
	public String guardar(@Valid Cuenta cuenta,BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {
		 
		//cuentaValidator.validate(cuenta, result);
		
		if(result.hasErrors()) {
			 model.addAttribute("titulo", "Llene los campos correctamente");
			 model.addAttribute("result", result.hasErrors());
			 model.addAttribute("mensaje", "Error al enviar datos, por favor escriba correctamente.");
			 return "form-cuenta";
		 }else {
			 model.addAttribute("result", false);
		 }
		
		 model.addAttribute("titulo", "Formulario de cuenta");
		 model.addAttribute("mensaje", "Se envió la información correctamente");
		
		 try {
			 cuentaDao.save(cuenta);
		 } catch(DataBaseBancoException e) {
			 e.printStackTrace();
			 flash.addAttribute("mensaje", e.getMessage());
		 }
		 
		 status.setComplete();
		 
		 return "redirect:lista-cuentas";
	}
	
	@RequestMapping(value="/eliminar-cuenta/{id_cuenta}")
	public String eliminar(@PathVariable(value = "id_cuenta") Long id_cuenta) {
		
		
		if(id_cuenta != null && id_cuenta > 0) {
			cuentaDao.delete(id_cuenta);
		}
		return "redirect:/lista-cuentas";
	}
	
	@RequestMapping(value="/buscar-numero-telefono", method = RequestMethod.POST)
	public String cargarCuentasNumeroTelefono(Cuenta cuenta, RedirectAttributes flash) {
		
		if(!cuentaDao.findByNumeroTelefono(cuenta.getNumeroTelefono()).isEmpty()) {
			flash.addFlashAttribute("listCuentasNumTel", cuentaDao.findByNumeroTelefono(cuenta.getNumeroTelefono()));
			flash.addFlashAttribute("mensajeSucces", "Se encontraron cuentas");
		}else flash.addFlashAttribute("mensaje", "No se encontraron cuentas.");
		
		return "redirect:/lista-cuentas";
	}
	
}

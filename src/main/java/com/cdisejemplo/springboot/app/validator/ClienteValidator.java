package com.cdisejemplo.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cdisejemplo.springboot.app.models.entity.Cliente;


@Component
public class ClienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		//Con este metodo aseguramos que esta clase es asignable
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cliente cliente = (Cliente)target;
		
		//Con el objeto errors manejamos el error en caso de que exista segun el metodo de validación
		//el segundo parametro es el campo que deseamos validar, es necesario que sea similar al atributo de la cuenta
		//el tercer parametro es el mensaje de error que esta en el messages.properties
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.cliente.nombre");
	
		if(!cliente.getNombre().matches("[a-z,A-Z,ÁÉÍÓÚáéíóúñÑ]{1,15}?[ ]?[a-z,A-Z,ÁÉÍÓÚáéíóúñÑ]{1,15}")) {
			errors.rejectValue("nombre", "format.cliente.nombre");
			
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty.cliente.apellido");
		
		if(!cliente.getApellido().matches("[a-z,A-Z,ÁÉÍÓÚáéíóúñÑ]{1,15}?[ ]?[a-z,A-Z,ÁÉÍÓÚáéíóúñÑ]{1,15}")) {
			errors.rejectValue("apellido", "format.cliente.apellido");
			
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroTelefono", "NotEmpty.cliente.numeroTelefono");
		
		if(!cliente.getNumeroTelefono().matches("[0-9]{10}")) {
			errors.rejectValue("numeroTelefono", "format.cliente.numeroTelefono");
			
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.cliente.email");
		
		if(!cliente.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
			errors.rejectValue("email", "format.cliente.email");
			
		}
		
		
	}

}

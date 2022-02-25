package com.cdisejemplo.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cdisejemplo.springboot.app.models.entity.Cuenta;

@Component
public class CuentaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Cuenta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Cuenta cuenta = (Cuenta)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.cuenta.nombre");
		
		if(!cuenta.getNombre().matches("[a-z,A-Z,ÁÉÍÓÚáéíóúñÑ]{1,15}?[ ]?[a-z,A-Z,ÁÉÍÓÚáéíóúñÑ]{1,15}")) {
			errors.rejectValue("nombre", "format.cuenta.nombre");
			
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "saldo", "NotEmpty.cuenta.saldo");
		
		if(cuenta.getSaldo() <= 999.0) {
			errors.rejectValue("saldo", "minRequerido.cuenta.saldo");
			
		}
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroTelefono", "NotEmpty.cuenta.numeroTelefono");
		
		if(!cuenta.getNumeroTelefono().matches("[0-9]{10}")) {
			errors.rejectValue("numeroTelefono", "format.cuenta.numeroTelefono");
			
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diaCreacion", "NotEmpty.cuenta.diaCreacion");
		
		if(cuenta.getDiaCreacion() == null) {
			errors.rejectValue("diaCreacion", "typeMisMatch.cuenta.diaCreacion");
			
		}
		

	}

}

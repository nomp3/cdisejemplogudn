package com.cdisejemplo.springboot.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cdisejemplo.springboot.app.models.entity.Banco;


@Component
public class BancoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Banco.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Banco banco = (Banco)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.banco.nombre");
		
		if(!banco.getNombre().matches("[a-z,A-Z,ÁÉÍÓÚáéíóúñÑ]{1,15}")) {
			errors.rejectValue("nombre", "format.banco.nombre");
			
		}
		
ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.banco.ubicacion");
		
		if(!banco.getUbicacion().matches("[a-z,A-Z,ÁÉÍÓÚáéíóúñÑ]{1,15}")) {
			errors.rejectValue("ubicacion", "format.banco.ubicacion");
			
		}
		

	}

}

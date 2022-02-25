package com.cdisejemplo.springboot.app.errors;

public class DataBaseBancoException extends RuntimeException {

	private static final long serialVersionUID = 679401661508482577L;
	
	public DataBaseBancoException() {
		super("Contacte con la administración, hubo un error con la base de datos.");
	}
	
}

package com.cdisejemplo.springboot.app.models.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "bancos")
public class Banco implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6929961748323299644L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_banco;
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
	@Column(name = "ubicacion")
	@NotEmpty
	private String ubicacion;

	public Long getId_banco() {
		return id_banco;
	}

	public void setId_banco(Long id_banco) {
		this.id_banco = id_banco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

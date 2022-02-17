package com.cdisejemplo.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7702402151338124371L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	
	@Column(name = "nombre")
	@NotEmpty
	private String nombre;
	
	@Column(name = "apellido")
	@NotEmpty
	private String apellido;
	
	@Column(name = "puesto")
	@NotEmpty
	private String puesto;
	
	@Column(name = "ingreso")
	@NonNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ingreso;

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Date getIngreso() {
		return ingreso;
	}

	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

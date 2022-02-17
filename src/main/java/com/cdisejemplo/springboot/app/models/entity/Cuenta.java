package com.cdisejemplo.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

@Entity 
@Table(name= "cuentas")
public class Cuenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4865985190802978554L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cuenta;
	
	@Column
	@NonNull
	private String nombre;
	
	@Column
	@NonNull
	private double saldo;
	
	@Column(name = "numero_telefono")
	private String numeroTelefono;
	
	@Column(name = "dia_creacion")
	@NonNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date diaCreacion;

	@JoinColumn(name ="id_cliente", referencedColumnName = "id_cliente", nullable = false )
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private Cliente cliente ;

	public Long getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(Long id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public Date getDiaCreacion() {
		return diaCreacion;
	}

	public void setDiaCreacion(Date diaCreacion) {
		this.diaCreacion = diaCreacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}

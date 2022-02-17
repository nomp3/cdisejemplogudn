package com.cdisejemplo.springboot.app.models.entity;


import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;



@Entity
@Table(name = "tarjetas")
public class Tarjeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2109514701831441494L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tarjeta;
	
	@Column(name = "numero_tarjeta",nullable = false, length = 16 )
	@NotEmpty
	private String numeroTarjeta;
	
	@Column(name = "icv")
	@NotEmpty
	private String icv;
	
	@Column(name = "tipo_tarjeta")
	@NotEmpty
	private String tipoTarjeta;
	
	@JoinColumn(name ="id_cuenta", referencedColumnName = "id_cuenta", nullable = false )
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Cuenta cuenta;

	public Long getId_tarjeta() {
		return id_tarjeta;
	}

	public void setId_tarjeta(Long id_tarjeta) {
		this.id_tarjeta = id_tarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getIcv() {
		return icv;
	}

	public void setIcv(String icv) {
		this.icv = icv;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

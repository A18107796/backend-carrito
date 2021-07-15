package com.app.carritodecompras.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado extends Persona implements Serializable {

	private String tipo_empleado;

	/**
	 * 
	 */
	public Empleado(Integer id) {
		super(id);
	}

	public Empleado() {
		// TODO Auto-generated constructor stub
	}

	public String getTipo_empleado() {
		return tipo_empleado;
	}

	public void setTipo_empleado(String tipo_empleado) {
		this.tipo_empleado = tipo_empleado;
	}

	private static final long serialVersionUID = 1L;

}

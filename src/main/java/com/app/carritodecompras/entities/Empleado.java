package com.app.carritodecompras.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado extends Persona implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

}

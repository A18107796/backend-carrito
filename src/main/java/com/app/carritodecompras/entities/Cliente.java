package com.app.carritodecompras.entities;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "clientes")
public class Cliente extends Persona implements Serializable {

	public Cliente(Integer id) {
		super(id);
	}
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;

}

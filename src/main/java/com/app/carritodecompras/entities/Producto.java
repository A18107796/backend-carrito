package com.app.carritodecompras.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombres;

	private String foto;

	private String descripcion;

	private double precio;

	private int stock;

	private boolean isOfertDay;

	public Producto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isOfertDay() {
		return isOfertDay;
	}

	public void setOfertDay(boolean isOfertDay) {
		this.isOfertDay = isOfertDay;
	}

	public boolean hasStock(int fStock) {
		int returnSTOCK = this.stock - fStock;
		System.out.println(returnSTOCK);
		if (returnSTOCK >= 0) {
			return true;
		} else {
			return false;
		}

	}

	public int getStockReducido(int fStock) {
		int returnSTOCK = this.stock - fStock;
		return returnSTOCK;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

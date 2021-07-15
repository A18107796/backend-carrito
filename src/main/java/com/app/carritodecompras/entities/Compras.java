package com.app.carritodecompras.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "compras")
public class Compras implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	@JsonIgnoreProperties(value = { "compras", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pago")
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
	private Pago pago;

	private Date fecha_compras;

	private String tipo_pago;

	private String num_tarjeta;

	private String expiration;

	private String direccion_envio;

	private int cvv;

	private String tipo_tarjeta;

	private String estado;

	private String img;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_compras")
	@JsonIgnoreProperties({ "carreras", "hibernateLazyInitializer", "handler" })
	private List<Detalle_compras> detalles;

	public Compras() {
		this.detalles = new ArrayList<>();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public Date getFecha_compras() {
		return fecha_compras;
	}

	public void setFecha_compras(Date fecha_compras) {
		this.fecha_compras = fecha_compras;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Detalle_compras> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle_compras> detalles) {
		this.detalles = detalles;
	}

	public String getTipo_pago() {
		return tipo_pago;
	}

	public void setTipo_pago(String tipo_pago) {
		this.tipo_pago = tipo_pago;
	}

	public String getNum_tarjeta() {
		return num_tarjeta;
	}

	public void setNum_tarjeta(String num_tarjeta) {
		this.num_tarjeta = num_tarjeta;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getTipo_tarjeta() {
		return tipo_tarjeta;
	}

	public void setTipo_tarjeta(String tipo_tarjeta) {
		this.tipo_tarjeta = tipo_tarjeta;
	}

	public String getDireccion_envio() {
		return direccion_envio;
	}

	public void setDireccion_envio(String direccion_envio) {
		this.direccion_envio = direccion_envio;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getTotal() {
		double totalcreditos = 0.0;
		for (Detalle_compras d : detalles) {

			totalcreditos += d.getSubtotal();
		}
		return totalcreditos;
	}

}

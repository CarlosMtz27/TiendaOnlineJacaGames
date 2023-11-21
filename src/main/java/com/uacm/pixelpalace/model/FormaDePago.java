package com.uacm.pixelpalace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "formas_de_pago")
public class FormaDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo; // Puede ser "Tarjeta de crédito" o "PayPal"
    private String nombreTitular;
    private String numero; // Número de tarjeta de crédito o dirección de correo de PayPal
    private String fechaExpiracion; // Fecha de expiración de la tarjeta (en el caso de tarjeta de crédito)
    private String codigoSeguridad; // Código de seguridad de la tarjeta (CVV/CVC)

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public FormaDePago() {
    }

    public FormaDePago(String tipo, String nombreTitular, String numero, String fechaExpiracion, String codigoSeguridad) {
        this.tipo = tipo;
        this.nombreTitular = nombreTitular;
        this.numero = numero;
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }
    
    

    public FormaDePago(String tipo, String nombreTitular, String numero) {
		this.tipo = tipo;
		this.nombreTitular = nombreTitular;
		this.numero = numero;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(String codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	@Override
	public String toString() {
		return "FormaDePago [id=" + id + ", tipo=" + tipo + ", nombreTitular=" + nombreTitular + ", numero=" + numero
				+ ", fechaExpiracion=" + fechaExpiracion + ", codigoSeguridad=" + codigoSeguridad + ", usuario="
				+ usuario + "]";
	}
    
    
}


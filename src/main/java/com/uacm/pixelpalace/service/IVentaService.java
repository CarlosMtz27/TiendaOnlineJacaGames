package com.uacm.pixelpalace.service;

import java.util.List;
import java.util.Optional;

import com.uacm.pixelpalace.model.Usuario;
import com.uacm.pixelpalace.model.Venta;

public interface IVentaService {
	List<Venta> findAll();
	Optional<Venta> findById(Integer id);
	Venta save (Venta venta);
	String generarNumeroVenta();
	List<Venta> findByUsuario (Usuario usuario);
}

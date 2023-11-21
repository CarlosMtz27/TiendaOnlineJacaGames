package com.uacm.pixelpalace.service;

import java.util.List;
import java.util.Optional;

import com.uacm.pixelpalace.model.FormaDePago;
import com.uacm.pixelpalace.model.Usuario;


public interface IFormaPagoService {
	FormaDePago save (FormaDePago formaPago);
	public Optional<FormaDePago> get(Integer id);
	public void update(FormaDePago formaPago);
	public void delete(Integer id);
	List<FormaDePago> findAllByUsuarioId(Integer usuarioId);
	FormaDePago findByNumeroAndUsuarioId(String numero, Integer usuarioId);
}

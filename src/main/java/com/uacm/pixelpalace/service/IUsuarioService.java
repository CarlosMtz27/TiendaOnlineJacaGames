package com.uacm.pixelpalace.service;

import java.util.List;
import java.util.Optional;

import com.uacm.pixelpalace.model.FormaDePago;
import com.uacm.pixelpalace.model.Producto;
import com.uacm.pixelpalace.model.Usuario;

public interface IUsuarioService {
	List<Usuario> findAll();
	Optional<Usuario> findById(Integer id);
	Usuario save (Usuario usuario);
	Optional<Usuario> findByEmail(String email);
	public void update(Usuario usuario);

}

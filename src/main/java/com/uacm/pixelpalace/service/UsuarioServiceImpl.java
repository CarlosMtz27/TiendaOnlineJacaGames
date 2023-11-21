package com.uacm.pixelpalace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uacm.pixelpalace.model.FormaDePago;
import com.uacm.pixelpalace.model.Usuario;
import com.uacm.pixelpalace.repository.IFormaPagoRepository;
import com.uacm.pixelpalace.repository.IUsuarioRepository;



@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private IFormaPagoRepository formapago;

	@Override
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public void update(Usuario usuario) {
		System.out.println();
		System.out.println(usuario);
		System.out.println(usuario);
		System.out.println();
		usuarioRepository.save(usuario);
	}


}

package com.uacm.pixelpalace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uacm.pixelpalace.model.FormaDePago;


@Repository
public interface IFormaPagoRepository extends JpaRepository<FormaDePago, Integer>{
	List<FormaDePago> findAllByUsuarioId(Integer usuarioId);
	FormaDePago findByNumeroAndUsuarioId(String numero, Integer usuarioId);
	Optional<FormaDePago> findById(Integer id);
	
}

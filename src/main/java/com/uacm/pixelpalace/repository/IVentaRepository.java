package com.uacm.pixelpalace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uacm.pixelpalace.model.Usuario;
import com.uacm.pixelpalace.model.Venta;


@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer> {
	List<Venta> findByUsuario (Usuario usuario);
}

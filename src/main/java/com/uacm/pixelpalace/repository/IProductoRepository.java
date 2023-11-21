package com.uacm.pixelpalace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uacm.pixelpalace.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

}

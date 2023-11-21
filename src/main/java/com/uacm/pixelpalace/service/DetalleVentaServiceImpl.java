package com.uacm.pixelpalace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uacm.pixelpalace.model.DetalleVenta;
import com.uacm.pixelpalace.repository.IDetalleVentaRepository;


@Service
public class DetalleVentaServiceImpl implements IDetalleVentaService{

	@Autowired
	private IDetalleVentaRepository detalleVentaRepository;

	@Override
	public DetalleVenta save(DetalleVenta detalleVenta) {

		return detalleVentaRepository.save(detalleVenta);
	}
	
	

	/*@Autowired
	private IDetalleOrdenRepository detalleOrdenRepository;

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}*/

}

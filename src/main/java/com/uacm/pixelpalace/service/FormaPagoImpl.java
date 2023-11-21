package com.uacm.pixelpalace.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uacm.pixelpalace.model.FormaDePago;
import com.uacm.pixelpalace.model.Usuario;
import com.uacm.pixelpalace.repository.IFormaPagoRepository;


@Service
public class FormaPagoImpl implements IFormaPagoService{

	@Autowired
	private IFormaPagoRepository formaPagoRepository;
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public FormaDePago save(FormaDePago formaPago) {

		return formaPagoRepository.save(formaPago);
	}

	@Override
	public Optional<FormaDePago> get(Integer id) {

		return formaPagoRepository.findById(id);
	}

	@Override
	public void update(FormaDePago formaPago) {
		formaPagoRepository.save(formaPago);

	}

	@Override
	public void delete(Integer id) {
		formaPagoRepository.deleteById(id);

	}
	

	@Override
	public List<FormaDePago> findAllByUsuarioId(Integer idUsuario) {
		
		return formaPagoRepository.findAllByUsuarioId(idUsuario);
	}

	@Override
	public FormaDePago findByNumeroAndUsuarioId(String numero, Integer usuarioId) {
		//try {
		    FormaDePago formaDePago = entityManager.createQuery(
		        "SELECT f FROM FormaDePago f " +
		        "WHERE f.numero = :numero " +
		        "AND f.usuario.id = :usuarioId", FormaDePago.class)
		        .setParameter("numero", numero)
		        .setParameter("usuarioId", usuarioId)
		        .getSingleResult();
		    return formaDePago;
		    // Realiza las operaciones necesarias con el objeto formaDePago
		//} catch (NoResultException e) {
			
		    // Manejar el caso en el que no se encontró ningún resultado
		//}
		//return formaDePago;
	}
	
}

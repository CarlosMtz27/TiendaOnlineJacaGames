package com.uacm.pixelpalace.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uacm.pixelpalace.model.FormaDePago;
import com.uacm.pixelpalace.model.Producto;
import com.uacm.pixelpalace.model.Usuario;
import com.uacm.pixelpalace.model.Venta;
import com.uacm.pixelpalace.service.IFormaPagoService;
import com.uacm.pixelpalace.service.IUsuarioService;
import com.uacm.pixelpalace.service.IVentaService;
import com.uacm.pixelpalace.service.ProductoService;
import com.uacm.pixelpalace.service.SendMailService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);
	
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IVentaService ventaService;
	
	@Autowired
	private IFormaPagoService formaPagoService;
	
	@Autowired
	private SendMailService sendMailService;
	
	private String codigoVerificacion;
	
	//Creamos el objeto para encriptar la contraseña del usuario
	BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
	
	
	// /usuario/registro
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}
	@GetMapping("/terminos")
    public String mostrarTerminos() {
        return "usuario/terminos-condiciones"; 
    }
	
	@GetMapping("/aviso-privacidad")
	public String mostrarAvisoPrivacidad() {
		return "usuario/aviso-privacidad";
	}
	
	@GetMapping("/acuerdos")
	public String mostrarAvisoAcuerdosUsuario() {
		return "usuario/acuerdos-usuario";
	}
	
	@GetMapping("/terminos-legales")
	public String mostrarTerminosLegales() {
		return "usuario/informacion-legal";
	}
	
	@GetMapping("/reembolsos")
	public String mostrarReembolsos() {
		return "usuario/reembolsos";
	}
	
	@PostMapping("/save")
	public String save(Usuario usuario) {
		logger.info("Usuario registro: {}", usuario);
		usuario.setTipo("USER");
		usuario.setPassword( passEncode.encode(usuario.getPassword()));//encriptamos la contraseña con el objeto que creamos
		usuarioService.save(usuario);		
		return "redirect:/";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@GetMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		logger.info("Accesos : {}", usuario);
		
		Optional<Usuario> user=usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
		
		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			
			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("Usuario no existe");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/recuperarContraseña")
	public String recuperarContraseña() {
		return "usuario/recuperar-contraseña";
	}
	
	@PostMapping("/enviarCodigoRecuperacion")
	public String codigoRecuperacion(@RequestParam String email ,Model model, HttpSession session) {
		codigoVerificacion = generarCodigo();
		
		
		sendMailService.sendStyledMail("pixelpalaceuacm@gmail.com", email, "Codigo de verifcacion",codigoVerificacion);
        
		model.addAttribute("correoUsuario",email);
	    return "usuario/codigo-verificacion";
	}
	
	public static String generarCodigo() {
        int longitud = 6;

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();
        StringBuilder codigo = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = random.nextInt(caracteres.length());

            codigo.append(caracteres.charAt(indice));
        }

        return codigo.toString();
    }
	
	@PostMapping("/verificarCodigoRecuperacion")
	public String verificarcodigoRecuperacion(@RequestParam String codigo,@RequestParam String email ,Model model, HttpSession session) {
		
		if(codigoVerificacion.equals(codigo)) {
			model.addAttribute("correoUsuario", email);
			return "usuario/cambiar-contraseña";
		}else {
		
		return "usuario/error-codigo-recuperacion";
		}
	}
	
	@PostMapping("/actualizarContraseña")
	public String actualizarContaseña(@RequestParam String email, @RequestParam String newPassword,@RequestParam String confirmPassword,Model model, HttpSession session) {
		Optional<Usuario> usuario;
		usuario = usuarioService.findByEmail(email);
		
		if(newPassword.equals(confirmPassword)) {
			usuario.get().setPassword(passEncode.encode(newPassword));
			usuarioService.update(usuario.get());
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		Usuario usuario= usuarioService.findById(  Integer.parseInt(session.getAttribute("idusuario").toString()) ).get();
		List<Venta> ventas= ventaService.findByUsuario(usuario);
		//logger.info("ordenes {}", ordenes);
		
		model.addAttribute("ventas", ventas);
		
		return "usuario/compras";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
		logger.info("Id de la orden: {}", id);
		Optional<Venta> venta=ventaService.findById(id);
		
		model.addAttribute("detalles", venta.get().getDetalle());
		
		
		//session
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "usuario/detallecompra";
	}
	
	@GetMapping("/cerrar")
	public String cerrarSesion( HttpSession session ) {
		session.removeAttribute("idusuario");
		return "redirect:/";
	}
	@GetMapping("/juegos")
	public String Listajuegos(Model model) {
	    List<Producto> juegos = productoService.findAll();
	    model.addAttribute("productos", juegos);
	    return "usuario/juegos";
	}

	
	@PostMapping("/agregar-tarjeta")
	public String agregarTarjeta(Model model, HttpSession session, @ModelAttribute("formaDePago") FormaDePago formaDePago) {
	    // Obtener el ID del usuario de la sesión
	    Integer userId = (Integer) session.getAttribute("idusuario");
	    Optional<Usuario> usuario = usuarioService.findById(userId);
        formaDePago.setUsuario(usuario.get());
        FormaDePago forma = new FormaDePago();
        String url="";
	    List<FormaDePago> formas = formaPagoService.findAllByUsuarioId(userId);
	    Boolean resultado=false;
	    int indice=0;
	    for(int i=0; i<formas.size();i++) {
	    	if(!(formas.get(i).getNumero().equals(formaDePago.getNumero()))) {
	    		
	    		resultado=false;
	    		System.out.println(resultado);
	    	}else {
	    		
	    		resultado=true;
	    		indice=i;
	    		break;
	    	}
	    }
	    
	    if(resultado == false) {
	    	formaPagoService.save(formaDePago);
	    	forma=formaPagoService.findByNumeroAndUsuarioId(formaDePago.getNumero(),formaDePago.getUsuario().getId());
	    	url="redirect:/order?formaDePagoId=" + forma.getId();
	    }else {
	    	forma=formaPagoService.findByNumeroAndUsuarioId(formas.get(indice).getNumero(), formas.get(indice).getUsuario().getId());
	    	url="redirect:/order?formaDePagoId=" + forma.getId();
	    }	
	    
	    model.addAttribute("formaDePago", forma);
	    
	    return url;
	    
	}
	
	/*@PostMapping("/agregar-tarjeta")
	public String agregarTarjeta(Model model, HttpSession session, @ModelAttribute("formaDePago") FormaDePago formaDePago) {
	    Integer userId = (Integer) session.getAttribute("idusuario");
	    Optional<Usuario> usuario = usuarioService.findById(userId);

	    

	    // Generar el código único
	    String codigoJuego = generarCodigoJuego();

	    // Datos del correo
	    String asunto = "Gracias por tu compra";
	    String mensaje = String.format(
	        "Hola %s,\n\nGracias por tu compra en Pixel Palace.\n\n"
	        + "Los datos de tu tarjeta son:\n"
	        + "Número de tarjeta: %s\n"
	        + "Nombre del titular: %s\n"
	        + "Fecha de expiración: %s\n\n"
	        + "Tu código para tu juego de \"[Nombre del juego]\" es: %s\n\n"
	        + "¡Disfruta de tu juego!\n\nEquipo de Pixel Palace.",
	        usuario.get().getNombre(), 
	        formaDePago.getNumero(), 
	        formaDePago.getNombreTitular(), 
	        formaDePago.getFechaExpiracion(), 
	        codigoJuego
	    );

	    // Enviar correo
	    sendMailService.sendStyledMail("pixelpalaceuacm@gmail.com", usuario.get().getEmail(), asunto, mensaje);

	    // Redirigir a la página de orden con el ID de la forma de pago
	    FormaDePago formaGuardada = formaPagoService.findByNumeroAndUsuarioId(formaDePago.getNumero(), formaDePago.getUsuario().getId());
	    return "redirect:/order?formaDePagoId=" + formaGuardada.getId();
	}*/



}

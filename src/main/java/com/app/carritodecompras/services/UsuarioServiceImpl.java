package com.app.carritodecompras.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.carritodecompras.dao.UsuarioDAO;
import com.app.carritodecompras.entities.Cliente;
import com.app.carritodecompras.entities.Empleado;
import com.app.carritodecompras.entities.Persona;
import com.app.carritodecompras.entities.Rol;
import com.app.carritodecompras.entities.Usuario;
import com.app.carritodecompras.generics.service.GenericServiceImpl;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, UsuarioDAO, Integer>
		implements UsuarioService, UserDetailsService {

	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private ClienteService serviceCliente;

	@Autowired
	private EmpleadoService serviceEmpleado;

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Override
	public Usuario findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = dao.findByUsername(username);

		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario " + username + " en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario " + username + " en el sistema");
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
				.peek(authority -> logger.info("Rol: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(username, usuario.getPassword(), usuario.isEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional
	public Usuario save(Usuario alumno) {

		Persona p;
		String password = "";
		if (alumno.getEmpleado() != null) {
			Rol rol = new Rol();
			Rol rol2 = new Rol();
			rol.setId((long) 1);

			if (alumno.getEmpleado().getTipo_empleado().equalsIgnoreCase("MOTORIZADO")) {
				rol2.setId((long) 4);
			} else if (alumno.getEmpleado().getTipo_empleado().equalsIgnoreCase("COCINERO")) {
				rol2.setId((long) 5);
			} else if (alumno.getEmpleado().getTipo_empleado().equalsIgnoreCase("AYUDANTE")) {
				rol2.setId((long) 6);
			}

			alumno.getRoles().add(rol);
			alumno.getRoles().add(rol2);
			
			alumno.setUsuario(alumno.getEmpleado().getEmail());
			password = alumno.getEmpleado().getDni();
			p = serviceEmpleado.save(alumno.getEmpleado());
			alumno.setEmpleado(new Empleado(p.getId()));
	
			
			
		} else {
			Rol rol = new Rol();
			rol.setId((long) 2);
			alumno.getRoles().add(rol);
			p = serviceCliente.save(alumno.getCliente());
			alumno.setCliente(new Cliente(p.getId()));
			password = alumno.getPassword();
		}
		System.out.println("PASS: " + password);
		password = passwordEncoder.encode(password);
		alumno.setPassword(password);
		alumno.setEnabled(true);
		return super.save(alumno);
	}

}

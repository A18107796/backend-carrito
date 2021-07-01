package com.app.carritodecompras.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.carritodecompras.dao.UsuarioDAO;
import com.app.carritodecompras.entities.Empleado;
import com.app.carritodecompras.entities.Usuario;
import com.app.carritodecompras.generics.service.GenericServiceImpl;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, UsuarioDAO, Integer> implements  UsuarioService, UserDetailsService{

	
	
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

}

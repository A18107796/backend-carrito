package com.app.carritodecompras.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.app.carritodecompras.entities.Usuario;
import com.app.carritodecompras.services.UsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<String, Object>();
		
		if(usuario.getEmpleado() == null) {
			info.put("username", usuario.getUsuario());
			info.put("nombres", usuario.getCliente().getNombres().toUpperCase());
			info.put("id_usuario", usuario.getId());
			info.put("id_cliente", usuario.getCliente().getId());
				
		}else if(usuario.getCliente() == null) {
			
			info.put("username", usuario.getUsuario());
			info.put("nombres", usuario.getEmpleado().getNombres().toUpperCase());
			info.put("id_usuario", usuario.getId());
			info.put("id_empleado", usuario.getEmpleado().getId());
		}
		
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}

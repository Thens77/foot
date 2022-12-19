package ma.emsi.foot.securite;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.emsi.foot.service.UtilisateurService;

import org.springframework.util.StringUtils;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	JWTUtils jwtUtils ;
	
	@Autowired
	UtilisateurService utilisateurService;
		
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		String username = null ;
		String jwt = null;
		if(StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer")) {
			jwt = authHeader.substring(7) ;
			username = jwtUtils.extractUsername(jwt);
		}
		
		if(StringUtils.hasLength(username) && SecurityContextHolder.getContext().getAuthentication()==null ) {
			UserDetails userDetails = utilisateurService.loadUserByUsername(username);
			if(jwtUtils.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		System.out.println("from filter " + username);
		
		filterChain.doFilter(request, response);
	}


}

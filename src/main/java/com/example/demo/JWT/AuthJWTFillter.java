package com.example.demo.JWT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthJWTFillter extends GenericFilter {
	
	// Danh sách xử lý logout nhưng token vẫn còn hiệu lực
	List<String> blackList = new ArrayList<String>();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("vao fillter");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader("token");
		if (token != null && blackList.indexOf(token) == -1) {
			Claims body = Jwts.parser().setSigningKey("KeyBiMat").parseClaimsJws(token).getBody();
			String user = body.getSubject();
			String pass = body.get("pass", String.class);
			// lay ve cac role
			Collection<LinkedHashMap> role = body.get("role", Collection.class);
			Collection<GrantedAuthority> collectionRole = new ArrayList<GrantedAuthority>();
			role.stream().forEach(new Consumer<LinkedHashMap>() {

				@Override
				public void accept(LinkedHashMap t) {
					String get = (String) t.get("authority");
					System.out.println("role" + get);
					SimpleGrantedAuthority authority = new SimpleGrantedAuthority(get);
					collectionRole.add(authority);
				}

			});
			if (user != null && !user.isEmpty()) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, pass, collectionRole);
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				System.out.println("qua fillter");
				chain.doFilter(request, response);
			}
		}

	}

}

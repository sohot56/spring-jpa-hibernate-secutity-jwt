package com.example.demo.JWT;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenFillter extends AbstractAuthenticationProcessingFilter {

	protected AuthenFillter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(authenticationManager);
	}

	public AuthenFillter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		// hàm xác thực. truyền vào đối tượng cần xác thực và trả về đối tượng xác thực đầy đủ bao gồm user,pass role
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user, pass, Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("login thanh cong..............");
		UserDetails userDetails = (UserDetails) authResult.getPrincipal();
		SecurityContextHolder.getContext().setAuthentication(authResult);
		String token = Jwts.builder().setSubject(userDetails.getUsername())
				.signWith(SignatureAlgorithm.HS512, "KeyBiMat")
				.setExpiration(new Date(System.currentTimeMillis() + 100*60*60*24))
				.claim("pass", userDetails.getPassword())
				.claim("role", userDetails.getAuthorities())
				.compact();
		response.addHeader("token", token);
	}

}

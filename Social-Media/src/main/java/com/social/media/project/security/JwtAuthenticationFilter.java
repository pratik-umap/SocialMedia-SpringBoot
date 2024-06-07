package com.social.media.project.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	JwtHelper jwtHelper;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestHeader = request.getHeader("Authorization");
		String username=null,token=null,value=null;
		if ( requestHeader == null && request.getCookies() != null) {
			Cookie[] cookies = request.getCookies();
			 value = cookies[0].getValue();
			requestHeader="Bearer "+value;
		}
		 
		if (requestHeader != null && requestHeader.startsWith("Bearer")) {
			token = requestHeader.substring(7);
		    try {
			  username = jwtHelper.getUsernameFromToken(token);
		    }catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			 // fetch user details from username
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		         Boolean validateToken = jwtHelper.validateToken(token, userDetails);
		       
		         if (validateToken) {
		        	 // set authentication
		        	 UsernamePasswordAuthenticationToken  authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		        	 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		        	 SecurityContextHolder.getContext().setAuthentication(authentication);
		         }else {
					System.out.println("validation failed");
				}
		
		}
		filterChain.doFilter(request, response);
	}

}

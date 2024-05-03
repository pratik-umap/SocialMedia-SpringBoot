 package com.social.media.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.media.project.entity.UserEntity;
import com.social.media.project.payload.JwtRequest;
import com.social.media.project.repository.UserRepository;
import com.social.media.project.security.JwtHelper;
import com.social.media.project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtHelper jwtHelper;
 	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserEntity user){
		 UserEntity findByUsername = userRepository.findByUsername(user.getUsername());
		 if (findByUsername == null) {
			 UserEntity createUser = userService.createUser(user);
			 UserDetails userDetails = userDetailService.loadUserByUsername(createUser.getUsername());
			 String token = jwtHelper.generateToken(userDetails);
			 return ResponseEntity.status(HttpStatus.OK).body(token);
		 }
		 return ResponseEntity.status(HttpStatus.OK).body("user already exist");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody JwtRequest request){
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
	    authenticationManager.authenticate(authenticationToken);
	    
	    UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
	    String token = jwtHelper.generateToken(userDetails);
	    return ResponseEntity.status(HttpStatus.OK).body(token);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request,HttpServletResponse response){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return ResponseEntity.status(HttpStatus.OK).body("user logout successfully");
	}
}

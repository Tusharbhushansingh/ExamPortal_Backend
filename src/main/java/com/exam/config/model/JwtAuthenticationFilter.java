package com.exam.config.model;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.service.impl.UserdetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserdetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestToken = request.getHeader("Authorization");
		System.out.println(requestToken);
		
		String username =  null;
		String jwtToken = null;
		
		if (requestToken!=null && requestToken.startsWith("Bearer ")) {
			//yes
			jwtToken = requestToken.substring(7);
			try {
			username = this.jwtUtil.extractUsername(jwtToken);
			}
			catch(ExpiredJwtException e) {
				System.out.println("jwtvtoken has expired");
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
		}
		else {
			//no
			System.out.println("Invalid token ,  not start with bearer string");
		}
		
		//validated
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);
			if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
				//token is valid
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthentication =
						new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities()
								);
				
				usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
			}
			
		} else {
			System.out.println("Token is not valid");
		}
		
		filterChain.doFilter(request, response);
	}

}

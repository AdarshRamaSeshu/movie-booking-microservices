package com.TicketBooking.UserModule.Configurations;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.TicketBooking.UserModule.Service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService customUserDetailsService;

    JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService){
        this.jwtUtil=jwtUtil;
        this.customUserDetailsService=customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                //Extract Authorization header
                String authHeader=request.getHeader("Authorization");

                String token=null;
                String email=null;

                //check if authHeader is not null

                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    token=authHeader.substring(7);
                    email=jwtUtil.extractEmail(token);
                }

                //email is present and user not authenticated
                if(email !=null && SecurityContextHolder.getContext().getAuthentication()==null){

                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                    //validate token with userdetails
                    if(jwtUtil.isvalidToken(token, userDetails)){

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // set authentication ion context
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    }

                    
                    
                }

       //contunue our filter chain
       filterChain.doFilter(request, response);


    }

 


}

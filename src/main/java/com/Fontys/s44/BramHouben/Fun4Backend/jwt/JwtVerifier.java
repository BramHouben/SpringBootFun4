package com.Fontys.s44.BramHouben.Fun4Backend.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtVerifier extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //todo kijk naar coockies
        String authHeader = httpServletRequest.getHeader("Authorization");
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies == null) {
            if (Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
        }

        String cookieTokenValue = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieTokenValue = cookie.getValue();

            }
        }

        //     String token = authHeader.replace("Bearer ","");

        try {

            String securePass = "QtoE8DuMTxs8nSC9ocWQgGXScWsjTCCw";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(securePass.getBytes())).parseClaimsJws(cookieTokenValue);

            Claims claimsJwsBody = claimsJws.getBody();
            String username = claimsJwsBody.getSubject();

            var authorities = (List<Map<String, String>>) claimsJwsBody.get("authorities");

            Set<SimpleGrantedAuthority> authoritySet = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, authoritySet
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("token %s  not valid", cookieTokenValue));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}

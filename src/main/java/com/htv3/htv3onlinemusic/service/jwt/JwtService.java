package com.htv3.htv3onlinemusic.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    // Key ma hoa token
    private static final String KEY_UNLOCK_TOKEN = "htv3chanel";
    // thời gian token sống
    private static final long EXPIRE_TIME = 33310000000L;

    public String createToken(Authentication authentication) {
        // Lấy đối tượng từ đăng nhập
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, KEY_UNLOCK_TOKEN)
                .compact();
    }

    // Lấy tên user từ token:
    public String getUsernameFromJwtToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(KEY_UNLOCK_TOKEN)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return username;
    }
}

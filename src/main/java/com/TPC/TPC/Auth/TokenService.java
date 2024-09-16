package com.TPC.TPC.auth;

import com.TPC.TPC.Users.UsersRepository;
import com.TPC.TPC.Users.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private final UsersRepository usersRepository;
    private Algorithm algorithm;

    public TokenService(UsersRepository usersRepository, @Value("${jwt.secret}") String secret) {
        this.usersRepository = usersRepository;
        algorithm = Algorithm.HMAC256(secret);
    }

    public Token create(Users user){
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

        String token = JWT.create()
                .withIssuer("TPC")
                .withSubject(user.getEmail())
                .withClaim("role", "admin")
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(token);
    }

    public Users getUserFromToken(String token) {
        var email =JWT.require(algorithm)
                .withIssuer("TPC")
                .build()
                .verify(token)
                .getSubject();

        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}

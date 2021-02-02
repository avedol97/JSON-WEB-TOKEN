package pl.krystian.auth;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginApi {

    @PostMapping("/login")
    public String get(@RequestBody User user) {
        //Sprawdzienie czy użytkownik istnieje w bazie danych oraz
        //czy login i hasło jest poprawne
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("roles", "user") //rola
                .setIssuedAt(new Date(currentTimeMillis)) //ważność tokena od
                .setExpiration(new Date(currentTimeMillis + 20000)) //ważność tokena do
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();
    }
}

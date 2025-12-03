package arias.serverauth.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.function.Function;

@RestController
@RequestMapping("/api")
public class AuthController {

    UserDetailsManager users;
    PasswordEncoder passwordEncoder;
    Function<UserDetails,String> genereTokenFunction;

    public AuthController(UserDetailsManager users, PasswordEncoder passwordEncoder, Function<UserDetails, String> genereTokenFunction) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.genereTokenFunction = genereTokenFunction;
    }

    record LoginRequest(String email,String password){}
    record LoginResponse(String token,String tokenType){}

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req){
        try{
            UserDetails user = users.loadUserByUsername(req.email());
            if(passwordEncoder.matches(req.password(),user.getPassword())){
                String token = genereTokenFunction.apply(user);
                return ResponseEntity.ok(new LoginResponse(token,"Bearer"));
            }
        }
        catch (UsernameNotFoundException unnfe){
            unnfe.printStackTrace();
        }
        return  ResponseEntity.badRequest().build();
    }

    record CreateUserRequest(String email, String password, String roles){}

    @PostMapping("users")
    public ResponseEntity<UserDetails> register(@RequestBody CreateUserRequest req, UriComponentsBuilder base){
        if(users.userExists(req.email())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        UserDetails user = User.withUsername(req.email())
                .password(passwordEncoder.encode(req.password()))
                .roles(req.roles()==null ? new String[]{"USER"} : req.roles().split(","))
                .build();

        users.createUser(user);
        URI location = base.path("/api/users/{username}").buildAndExpand(user.getUsername()).toUri();
        return ResponseEntity.created(location).body(user);
    }
}

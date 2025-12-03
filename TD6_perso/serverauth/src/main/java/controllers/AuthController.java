package controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    record LoginRequest(String email, String password) {}
    record LoginResponse(String token, String tokenType) {}

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        return null;
    }
}

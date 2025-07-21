package com.example.fn;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilter {

    @Autowired
    private JwtUtil jwtUtil;

    public boolean validateToken(HttpRequestMessage<?> request, final ExecutionContext context) {
        String authHeader = request.getHeaders().get("authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            if (jwtUtil.validateToken(token, username)) {
                return true;
            } else {
                context.getLogger().warning("Token is not valid.");
                return false;
            }
        } else {
            context.getLogger().warning("Authorization header is missing or invalid.");
            return false;
        }
    }
}

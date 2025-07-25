package com.example.fn;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class FunctionHandler {

    public HttpResponseMessage register(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request to register a user.");

        UserService userService = FunctionApplication.getContext().getBean(UserService.class);

        Optional<User> user = request.getBody();
        if (user.isPresent()) {
            userService.register(user.get());
            return request.createResponseBuilder(HttpStatus.OK).body("User registered successfully.").build();
        } else {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a user in the request body.").build();
        }
    }

    public HttpResponseMessage login(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request to log in a user.");

        UserService userService = FunctionApplication.getContext().getBean(UserService.class);
        JwtUtil jwtUtil = FunctionApplication.getContext().getBean(JwtUtil.class);

        Optional<User> user = request.getBody();
        if (user.isPresent()) {
            User authenticatedUser = userService.login(user.get());
            if (authenticatedUser != null && authenticatedUser.getPassword().equals(user.get().getPassword())) {
                String token = jwtUtil.generateToken(authenticatedUser.getUsername());
                return request.createResponseBuilder(HttpStatus.OK).body(token).build();
            } else {
                return request.createResponseBuilder(HttpStatus.UNAUTHORIZED).body("Invalid credentials.").build();
            }
        } else {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a user in the request body.").build();
        }
    }


}

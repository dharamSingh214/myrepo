package com.example.fn;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class Function extends FunctionApplication {

    @FunctionName("register")
    public HttpResponseMessage register(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            final ExecutionContext context) {
        return super.register(request, context);
    }

    @FunctionName("login")
    public HttpResponseMessage login(
            @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<User>> request,
            final ExecutionContext context) {
        return super.login(request, context);
    }
}

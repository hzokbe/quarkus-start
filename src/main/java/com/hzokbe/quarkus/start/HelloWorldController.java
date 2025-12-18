package com.hzokbe.quarkus.start;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello-world")
public class HelloWorldController {
    @GET
    public String helloWorld() {
        return "Hello, world!";
    }
}

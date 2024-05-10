package com.example.onlinelearningplatform;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/api")
@SwaggerDefinition(info = @Info(
        title = "Example Service",
        description = "A simple example of apiee",
        version = "1.0.0")
)

public class HelloApplication extends Application {

}
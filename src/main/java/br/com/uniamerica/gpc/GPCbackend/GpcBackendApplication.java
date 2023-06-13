//------------------Package----------------------
package br.com.uniamerica.gpc.GPCbackend;

//------------------Imports----------------------

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

//------------------------------------------------
@SpringBootApplication
public class GpcBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpcBackendApplication.class, args);
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8082")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Content-Type")
                .allowCredentials(true);
    }

}

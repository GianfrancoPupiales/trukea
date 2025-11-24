package com.apirip.trukea.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Ruta virtual → carpeta local
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/C:/trukea/uploads/")
                .setCachePeriod(3600) // 1 hora
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}

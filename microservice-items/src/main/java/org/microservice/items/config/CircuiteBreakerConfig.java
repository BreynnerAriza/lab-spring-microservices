package org.microservice.items.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuiteBreakerConfig {

    @Bean
    Customizer<Resilience4JCircuitBreakerFactory> circuitBreakerFactoryCustomizer() {
        return (factory -> factory.configureDefault(id -> {
            return new Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(
                            CircuitBreakerConfig.custom()
                                    .slidingWindowSize(10) /* NUMERO DE SOLICITUDES PARA CALCULAR EL PORCENTAJE*/
                                    .failureRateThreshold(40) /*PORCENTAJE MAXIMO DE FALLO PARA PASAR A ESTADO ABIERTO*/
                                    .waitDurationInOpenState(Duration.ofSeconds(20L))
                                    .permittedNumberOfCallsInHalfOpenState(5)
                                    .build()
                    )
                    .build();
        }));
    }

}

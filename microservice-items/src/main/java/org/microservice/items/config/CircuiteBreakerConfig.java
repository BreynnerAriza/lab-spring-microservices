package org.microservice.items.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;
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
        return (factory -> factory.configureDefault(id ->
                new Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(
                            CircuitBreakerConfig.custom()
                                    .slidingWindowSize(10) /* NUMERO DE SOLICITUDES PARA CALCULAR EL PORCENTAJE*/
                                    .failureRateThreshold(40) /*PORCENTAJE MAXIMO DE FALLO PARA PASAR A ESTADO ABIERTO*/
                                    .waitDurationInOpenState(Duration.ofSeconds(20L)) /*CONFIGURA EL TIEMPO EN EL VA A ESTAR EN ESTADO ABIERTO*/
                                    .permittedNumberOfCallsInHalfOpenState(5) /* CONFIGURA EL NUMERO DE SOLICITUDES QUE PERMITIRA EN ESTADO SEMIABIERTO*/
                                    .slowCallRateThreshold(40) /*CONFIGURA EL PORCENTAJE MAXIMO DE SOLICITUDES LENTAS PARA PASAR A MODO ABIERTO*/
                                    .slowCallDurationThreshold(Duration.ofSeconds(3L)) /*INDICA EL TIEMPO DESDE QUE LA LLAMADA SE PUEDE CONSIDERAR COMO LENTA*/
                                    .build()
                    )
                    .timeLimiterConfig(
                            TimeLimiterConfig.custom()
                                    .timeoutDuration(Duration.ofSeconds(4L)) /*DEJAR POR AHORA ASI Y REVISAR AHORITA*/
                                    .build()
                    )
                    .build()
        ));
    }

}

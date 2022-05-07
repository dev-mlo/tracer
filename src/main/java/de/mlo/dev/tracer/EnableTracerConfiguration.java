package de.mlo.dev.tracer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ComponentScan(basePackages = "de.mlo.dev.tracer")
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@EnableAspectJAutoProxy
public @interface EnableTracerConfiguration {
}

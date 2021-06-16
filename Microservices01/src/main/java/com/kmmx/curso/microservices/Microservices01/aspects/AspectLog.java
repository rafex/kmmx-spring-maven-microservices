package com.kmmx.curso.microservices.Microservices01.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLog {

    @Before("execution(* create(..))")
    public void log() {
        System.out.println("hi! my aspect!");
    }
}

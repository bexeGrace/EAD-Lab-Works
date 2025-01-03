package com.ead.lab;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.ead.lab.BookRegistrationServlet.*(..))")
    public void logBookRegistrationServletMethods(JoinPoint joinPoint) {
        System.out.println(
                "[LOGGING ASPECT] BookRegistrationServlet method called: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.ead.lab.DisplayBooksServlet.*(..))")
    public void logDisplayBooksServletMethods(JoinPoint joinPoint) {
        System.out.println(
                "[LOGGING ASPECT] DisplayBooksServlet method called: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.ead.lab.DeleteBookServlet.*(..))")
    public void logDeleteBookServletMethods(JoinPoint joinPoint) {
        System.out.println(
                "[LOGGING ASPECT] DeleteTaskServlet method called: " + joinPoint.getSignature().getName());
    }

    @Before("execution(* com.ead.lab.SearchBooksServlet.*(..))")
    public void logSearchBooksServletMethods(JoinPoint joinPoint) {
        System.out.println(
                "[LOGGING ASPECT] SearchBooksServlet method called: " + joinPoint.getSignature().getName());
    }
}

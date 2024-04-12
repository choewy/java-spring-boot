package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimeTraceAop {

  @Around(
    "execution(* com.example.demo..*(..)) && !target(com.example.demo.SpringConfig)"
  )
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    String context = joinPoint.toString();

    long start = System.currentTimeMillis();

    try {
      return joinPoint.proceed();
    } finally {
      long finish = System.currentTimeMillis();
      long milliseconds = finish - start;
      System.out.println("[" + context + "] " + milliseconds + "ms");
    }
  }
}

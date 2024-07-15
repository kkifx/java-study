package org.aop.aoptest.aspect;

import org.aop.aoptest.demos.web.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthAspect {

  @Pointcut("@annotation(org.aop.aoptest.annotation.AuthCheck)")
  public void pointcut() {}

  @Around("pointcut()")
  public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("这是around切面的作用");
    return joinPoint.proceed();
  }

  @AfterReturning(returning = "res", pointcut = "pointcut()")
  public Object reviseRes(JoinPoint joinPoint, Object res) throws Throwable {
    System.out.println(res);
    User res1 = (User) res;
    res1.setName("李白");
    return "修改返回值成功";
  }
}

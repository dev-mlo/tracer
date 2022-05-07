package de.mlo.dev.tracer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import javax.annotation.PostConstruct;
import java.beans.Transient;

@Log4j2
@Aspect
@Component
public class Tracer {

    PublishSubject<Action> subject = PublishSubject.create();

    public Tracer(){
        subject.doOnEach(action ->{
            Action action1 = (Action) action.getValue();
            log.info("Time {}", action1.time);
        }).subscribe();
    }

    @NoTrace
    @Around("!@annotation(NoTrace) " +
            "&& !within(is(FinalType)) " +
            "&& execution(public * *(..))")
    public Object proceed(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        Object returnValue = joinPoint.proceed();

        long stop = System.nanoTime();
        long time = stop - start;

        subject.onNext(new Action(joinPoint, returnValue, time));

        return returnValue;
    }

    @Getter
    @AllArgsConstructor
    static class Action{
        final ProceedingJoinPoint proceedingJoinPoint;
        final Object returnValue;
        final long time;
    }
}

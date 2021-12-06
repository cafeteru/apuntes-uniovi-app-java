package es.uniovi.apuntesunioviapp.aspects;

import java.time.LocalDate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import es.uniovi.apuntesunioviapp.dtos.UserDto;
import es.uniovi.apuntesunioviapp.infrastructure.messages.UserMessages;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class AspectUserDto {
    @Before(value = "execution(* es.uniovi.apuntesunioviapp.controllers.UserController.create(..))" +
        " || execution(* es.uniovi.apuntesunioviapp.controllers.UserController.update(..))")
    public void check(JoinPoint joinPoint) {
        log.info("check() - start");
        var userDto = (UserDto) joinPoint.getArgs()[0];
        if (userDto.getBirthDate().isAfter(LocalDate.now())) {
            log.error(UserMessages.LIMIT_BIRTH_DATE);
            throw new IllegalArgumentException(UserMessages.LIMIT_BIRTH_DATE);
        }
        log.info("check() - end");
    }
}

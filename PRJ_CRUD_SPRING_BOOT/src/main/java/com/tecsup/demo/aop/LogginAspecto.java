package com.tecsup.demo.aop;

import com.tecsup.demo.domain.entities.Alumno;
import com.tecsup.demo.domain.entities.Auditoria;
import com.tecsup.demo.domain.entities.Curso;
import com.tecsup.demo.domain.persistence.AuditoríaDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.Calendar;

@Component
@Aspect
public class LogginAspecto {

    private Long tx;

    @Autowired
    private AuditoríaDao auditoriaDao;

    @Around("execution(* com.tecsup.demo.services.*ServiceImpl.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        Long currTime = System.currentTimeMillis();
        tx = System.currentTimeMillis();
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = "tx[" + tx + "] - " + joinPoint.getSignature().getName();
        //logger.info(metodo + "()");
        if (joinPoint.getArgs().length > 0)
            logger.info(metodo + "() INPUT:" + Arrays.toString(joinPoint.getArgs()));
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
        logger.info(metodo + "(): tiempo transcurrido " + (System.currentTimeMillis() - currTime) + " ms.");
        return result;
    }

    @After("execution(* com.tecsup.demo.controllers.*Controller.guardar*(..)) ||" +
            "execution(* com.tecsup.demo.controllers.*Controller.editar*(..)) ||" +
            "execution(* com.tecsup.demo.controllers.*Controller.eliminar*(..))")
    public void auditoriaGeneral(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String metodo = joinPoint.getSignature().getName();
        Object[] parametros = joinPoint.getArgs();

        String nombreEntidad = "";
        Integer id = null;

        try {
            if (parametros[0] instanceof Alumno alumno) {
                nombreEntidad = "alumnos";
                id = alumno.getId();
            } else if (parametros[0] instanceof Curso curso) {
                nombreEntidad = "cursos";
                id = curso.getId();
            } else if (joinPoint.getTarget().getClass().getSimpleName().contains("Alumno")) {
                nombreEntidad = "alumnos";
                id = (Integer) parametros[0];
            } else if (joinPoint.getTarget().getClass().getSimpleName().contains("Curso")) {
                nombreEntidad = "cursos";
                id = (Integer) parametros[0];
            }

            String usuario = "usuario"; // Puedes integrar Spring Security para obtener el usuario real

            logger.info("📝 [Auditoría] Tabla: {}, ID: {}, Acción: {}", nombreEntidad, id, metodo);

            auditoriaDao.save(new Auditoria(
                    nombreEntidad,
                    id,
                    Calendar.getInstance().getTime(),
                    usuario,
                    metodo
            ));

        } catch (Exception e) {
            logger.error("Error al registrar auditoría en " + metodo, e);
        }
    }
}



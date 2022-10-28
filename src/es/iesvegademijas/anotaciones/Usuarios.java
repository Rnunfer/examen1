package es.iesvegademijas.anotaciones;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Usuarios {
	UsuarioAnotado[] value();
}

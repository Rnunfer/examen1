package es.iesvegademijas.anotaciones;

import java.lang.annotation.*;

@Repeatable(Usuarios.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UsuarioAnotado {

	String usuario();
	String hashPasswd();
	
}

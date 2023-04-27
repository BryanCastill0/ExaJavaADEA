package ms.Usuarios.prueba.controller;

import lombok.extern.slf4j.Slf4j;
import ms.Usuarios.prueba.entity.Usuario;
import ms.Usuarios.prueba.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("Usuarios")
@RestController
@Slf4j
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

      @GetMapping("/{login}")
      //Validar que el usuario existe en la base de datos
    public ResponseEntity<?>existeUsuario(@PathVariable String login){
        boolean existe= usuarioService.existeUsuario(login);
        if(existe){
            return ResponseEntity.ok("El usuario si existe");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El usuario no existe");
        }
    }

    @GetMapping("/{login}/{pass}")
       //    Validar la Contraseña
    public String validarPass(@PathVariable String login, @PathVariable String pass) {
        return usuarioService.validarPass(login,pass);
    }

    @GetMapping("/acceso/{login}")
    //Permitir ingresar o no inresar si la fecha paso su vigencia
    public ResponseEntity<?> accesoVigencia(@PathVariable String login) {
        boolean fechaValida = usuarioService.validarFecha(login);
        if (!fechaValida) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La fecha de vigencia ha expirado, acceso denegado");
        }
        return ResponseEntity.ok("Puede accesar, fecha de vigencia se encuentra activa");
    }

    @GetMapping("/validar-fecha/{login}")
    public ResponseEntity<String> validarFecha(@PathVariable String login) {
        boolean fechaValida = usuarioService.validarFecha(login);
        if (fechaValida) {
            return ResponseEntity.ok("Fecha de caducidad aun en tiempo");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La fecha de caducidad ha vencido o el usuario no existe");
        }
    }

    @PostMapping("/alta")
    public Usuario insertarUsuario(@RequestBody Usuario usuario) throws NoSuchAlgorithmException {
        return usuarioService.generarAlta(usuario);
    }

    @PutMapping("/baja")
    public Usuario baja(@RequestBody Usuario usuario){
        return usuarioService.generarBaja(usuario);
    }

    @PutMapping("/modificar")
    public Usuario modificar(@RequestBody Usuario usuario){
        return usuarioService.modificar(usuario);
    }

    @GetMapping("/user/{login}")
    public Usuario buscarUsuario(@PathVariable String login) {
        return usuarioService.getUsuario(login);
    }

    @GetMapping("/lista")
    public List<Usuario> getUsuarios(){
        return usuarioService.getUsuarios();
    }

}

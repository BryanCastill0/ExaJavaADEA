package ms.Usuarios.prueba.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ms.Usuarios.prueba.entity.Usuario;
import ms.Usuarios.prueba.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Data
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioServicePass usuarioServicePass;

    public boolean existeUsuario(String login) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        return usuario.isPresent();
    }

    public Usuario getUsuario(String login) {
        return usuarioRepository.findByLogin(login).get();
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario generarAlta(Usuario usuario) throws NoSuchAlgorithmException {
        String pass1= usuario.getPass();
        String passencode=usuarioServicePass.encriptar(pass1);
        usuario.setPass(passencode);
        return usuarioRepository.save(usuario);
    }

    public Usuario generarBaja(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario modificar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }



    public boolean validarFecha(String login) {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        if (usuario.isPresent()) {
            Date fecha = new Date();
            Date fechaVigencia = usuario.get().getFechaVigencia();
            if (fechaVigencia.before(fecha)) {
                return false;
            } else {
                return true;
            }
        }return false;
    }

    public String validarPass(String login, String pass){
        boolean existe= existeUsuario(login);
        String msje= "";
        System.out.println (usuarioRepository.obtenerUsuarios());
        if(existe){
            Usuario usuario = usuarioRepository.findByLogin(login).get();
            if(usuario.getPass().equals(pass)){
                msje="Usuario y Pass valida";
            }else{
                msje="Usuario y Pass no existe";
            }
        }else{
            msje="Usuario no encotrado";
        }
        return msje;
    }


}



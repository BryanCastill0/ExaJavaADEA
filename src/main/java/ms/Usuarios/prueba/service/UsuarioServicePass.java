package ms.Usuarios.prueba.service;

import lombok.extern.slf4j.Slf4j;
import ms.Usuarios.prueba.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@Slf4j
public class UsuarioServicePass {
    @Autowired
    UsuarioRepository usuarioRepository;

    public String encriptar(String pass) throws NoSuchAlgorithmException {
        // Encriptar con SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pass.getBytes());
        byte[] digest = md.digest();

        // Convertir hash en bytes a una cadena de caracteres Base64
        String base64Encoded = Base64.getEncoder().encodeToString(digest);

        // Imprimir resultados
//        System.out.println("Base64: " + base64Encoded);
        return base64Encoded;
    }

    // Función para convertir bytes en una cadena hexadecimal
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}

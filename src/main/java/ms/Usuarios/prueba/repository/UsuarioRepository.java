package ms.Usuarios.prueba.repository;

import ms.Usuarios.prueba.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

    @Query(value = "select * from usuarios", nativeQuery = true)
    List<Usuario> obtenerUsuarios();
}


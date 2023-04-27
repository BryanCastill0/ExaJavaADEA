package ms.Usuarios.prueba.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    private String login;

    @Column(name = "pass")
    private String pass;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cliente")
    private Double cliente;

    @Column(name = "email")
    private String email;

    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;

    @Column(name = "fech_baja")
    private Date fechaBaja;

    @Column(name = "status")
    private Character status;

    @Column(name = "intentos")
    private Double intentos;

    @Column(name = "fecha_revocado")
    private Date fechaRevocado;

    @Column(name = "fecha_vigencia")
    private Date fechaVigencia;

    @Column(name = "no_acceso")
    private Integer noAcceso;

    @Column(name="apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "area")
    private Integer area;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

}

package com.seguridad.seguridad.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.seguridad.seguridad.modelo.Persona;

public interface RepositorioPersona extends JpaRepository<Persona, Integer>{
    // Metodo para invocar el procedimiento almacenado para listar las personas al momento de agregar un nuevo rol fan page

    @Query(value = "SELECT * FROM perfil_personal.persona_listar()", nativeQuery = true)
    List<Persona> persona_listar();

}

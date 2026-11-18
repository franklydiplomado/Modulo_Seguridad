package com.seguridad.seguridad.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.seguridad.seguridad.modelo.TipoPrivacidad;

import jakarta.transaction.Transactional;

public interface RepositorioTipoPrivacidad extends JpaRepository<TipoPrivacidad, Integer> {
    // Método para listar con nuestro propio procedimiento almacenado 
    // es la forma más segura, en vez de implementar el FindAll por defecto
	@Query(value = "SELECT *FROM seguridad.tipo_privacidad_listar()", nativeQuery = true)
    List <TipoPrivacidad> tipo_privacidad_listar();

    // Método para añadir un nuevo tipo de privacidad con nuestro procedimiento almacenado (es la forma más segura):
	@Transactional
	@Query(value = "SELECT seguridad.tipo_privacidad_agregar(:nom_tip, :est_tip)", nativeQuery = true)
	Integer tipo_privacidad_agregar(@Param("nom_tip") String nom_tip, @Param("est_tip") String est_tip);

}



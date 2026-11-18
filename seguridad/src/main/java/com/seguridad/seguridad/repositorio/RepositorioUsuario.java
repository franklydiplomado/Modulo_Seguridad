package com.seguridad.seguridad.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.seguridad.seguridad.modelo.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
    // Método para listar con nuestro propio procedimiento almacenado 
    // es la forma más segura, en vez de implementar el FindAll por defecto
	@Query(value = "SELECT *FROM seguridad.usuario_listar()", nativeQuery = true)
    List <Usuario> usuario_listar();

    // Método para añadir un nuevo usuario con nuestro procedimiento almacenado (es la forma más segura):
	@Transactional
	@Query(value = "SELECT seguridad.usuario_agregar(:ali_usu, :ema_usu, :cla_usu, :est_rol)", nativeQuery = true)
	Integer usuario_agregar(@Param("ali_usu") String ali_usu, @Param("ema_usu") String ema_usu, @Param("cla_usu") String cla_usu, @Param("est_usu") String est_usu);

}

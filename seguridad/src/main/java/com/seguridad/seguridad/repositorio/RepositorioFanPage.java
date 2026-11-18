package com.seguridad.seguridad.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.seguridad.seguridad.modelo.FanPage;


public interface RepositorioFanPage extends JpaRepository<FanPage, Integer> {

    @Query(value = "SELECT * FROM perfil_empresarial.fan_page_listar()", nativeQuery = true)
    List<FanPage> fan_page_listar();

}

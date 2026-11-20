package com.seguridad.seguridad.servicio;

import java.util.List;
import org.springframework.stereotype.Service;

import com.seguridad.seguridad.modelo.TipoPrivacidad;
import com.seguridad.seguridad.repositorio.RepositorioTipoPrivacidad;

@Service
public class ServicioTipoPrivacidad {
 private final RepositorioTipoPrivacidad tipoRepositorio;

     public ServicioTipoPrivacidad(RepositorioTipoPrivacidad tipoRepositorio) {
        this.tipoRepositorio = tipoRepositorio;
    }

        public List<TipoPrivacidad> listarTipos(){
            return tipoRepositorio.tipo_privacidad_listar();
    }
    
    // Método para agregar un nuevo tipo de privacidad usando nuestro procedimiento de SQL
    public void agregarTipoPrivacidad(TipoPrivacidad tipo) {
        // Usamos getters para obtener los valores
            tipoRepositorio.tipo_privacidad_agregar(
            tipo.getNom_tip(),
            tipo.getEst_tip()
        );
    } 

public TipoPrivacidad buscarTipo(int cod_tip) {
    return tipoRepositorio.tipo_privacidad_buscar(cod_tip);
}

public void editarTipo(TipoPrivacidad tipo) {
    tipoRepositorio.tipo_privacidad_modificar(
        tipo.getCod_tip(), tipo.getNom_tip(), tipo.getEst_tip());
}

public boolean eliminarTipo(int cod_tip) {
    if (tipoRepositorio.tipo_privacidad_buscar(cod_tip) != null) {
        tipoRepositorio.tipo_privacidad_eliminar(cod_tip);
        return true;
    }
    return false;
}



}


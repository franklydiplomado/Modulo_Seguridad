package com.seguridad.seguridad.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.seguridad.seguridad.modelo.Usuario;
import com.seguridad.seguridad.repositorio.RepositorioUsuario;

@Service 
public class ServicioUsuario {

    private final RepositorioUsuario usuarioRepositorio;

        public ServicioUsuario(RepositorioUsuario usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

        public List<Usuario> listarUsuarios(){
        return usuarioRepositorio.usuario_listar();
    }  

    // Método para agregar un usuario usando nuestro procedimiento de SQL
    public void agregarUsuario(Usuario usuario) {
        // Usamos getters para obtener los valores
            usuarioRepositorio.usuario_agregar(
            usuario.getAli_usu(),
            usuario.getEma_usu(),
            usuario.getCla_usu(),
            usuario.getEst_usu()
        );
    } 

}

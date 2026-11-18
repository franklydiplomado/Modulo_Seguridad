package com.seguridad.seguridad.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seguridad.seguridad.modelo.Usuario;
import com.seguridad.seguridad.servicio.ServicioUsuario;



@Controller 
public class ControladorUsuario {
        
    private final ServicioUsuario us;

    public ControladorUsuario(ServicioUsuario us) {
        this.us = us;
    }

    //Solicitud de la Vista Usuario Inicial
    @GetMapping("/usuario")
    public String verVistaUsuario(){
        return "usuario";
    }
    //Solicitud de la Vista Usuario Listar
    @GetMapping("/usuario_listar")
    public String listarUsuario(Model modelo) {
        List<Usuario> usuarios = us.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_listar";
    }

    //Metodo para llamar a la vista de agregar usuario
    @GetMapping("/usuario_agregar")
    public String agregarUsuario(Model modelo) {
        Usuario usuario = new Usuario();
        usuario.setAli_usu("");
        usuario.setEma_usu("");
        usuario.setCla_usu("");
        usuario.setEst_usu("");
        modelo.addAttribute("usuario", usuario);
        return "usuario_agregar";
    }

    // Metodo para guardar los datos introducidos en el formulario
    @PostMapping("/usuario_agregar")
    public String agregarUsuario(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes redirectAttrs) {
        try {
            usuario.setAli_usu(usuario.getAli_usu() != null ? usuario.getAli_usu().trim() : "");
            usuario.setEma_usu(usuario.getEma_usu() != null ? usuario.getEma_usu().trim() : "");
            usuario.setCla_usu(usuario.getCla_usu() != null ? usuario.getCla_usu().trim() : "");
            usuario.setEst_usu(usuario.getEst_usu() != null ? usuario.getEst_usu().toUpperCase() : "A");
            us.agregarUsuario(usuario);            
            redirectAttrs.addFlashAttribute("exito", "¡Usuario creado correctamente!");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Error al crear el usuario: " + e.getMessage());
        }
        return "redirect:/usuario_listar";
    }

}

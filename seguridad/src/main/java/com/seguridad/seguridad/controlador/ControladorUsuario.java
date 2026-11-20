package com.seguridad.seguridad.controlador;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.seguridad.seguridad.modelo.Usuario;
import com.seguridad.seguridad.servicio.ServicioUsuario;

@Controller
public class ControladorUsuario {

    private final ServicioUsuario us;

    public ControladorUsuario(ServicioUsuario us) {
        this.us = us;
    }

    @GetMapping("/usuario")
    public String verVistaUsuario() {
        return "usuario";
    }

    @GetMapping("/usuario_listar")
    public String listarUsuario(Model modelo) {
        List<Usuario> usuarios = us.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "usuario_listar";
    }

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

    // ---------- VER ----------
    @GetMapping("/usuario_ver")
    public String verUsuario(@RequestParam("cod_usu") int cod_usu, Model modelo, RedirectAttributes flash) {
        Usuario usuario = us.buscarUsuario(cod_usu);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario solicitado no existe.");
            return "redirect:/usuario_listar";
        }
        modelo.addAttribute("usuario", usuario);
        return "usuario_ver";
    }

    // ---------- EDITAR ----------
    @GetMapping("/usuario_editar")
    public String editarUsuario(@RequestParam("cod_usu") int cod_usu, Model modelo, RedirectAttributes flash) {
        Usuario usuario = us.buscarUsuario(cod_usu);
        if (usuario == null) {
            flash.addFlashAttribute("error", "El usuario que intentas editar no existe.");
            return "redirect:/usuario_listar";
        }
        usuario.setCla_usu(""); // la clave no se muestra; vacía = conservar la actual
        modelo.addAttribute("usuario", usuario);
        return "usuario_editar";
    }

    @PostMapping("/usuario_editar")
    public String editarUsuario(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes flash) {
        try {
            usuario.setAli_usu(usuario.getAli_usu() != null ? usuario.getAli_usu().trim() : "");
            usuario.setEma_usu(usuario.getEma_usu() != null ? usuario.getEma_usu().trim() : "");
            usuario.setCla_usu(usuario.getCla_usu() != null ? usuario.getCla_usu().trim() : "");
            usuario.setEst_usu(usuario.getEst_usu() != null ? usuario.getEst_usu().toUpperCase() : "A");
            us.editarUsuario(usuario);
            flash.addFlashAttribute("exito", "¡Usuario modificado correctamente!");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Ocurrió un error al editar el usuario: " + e.getMessage());
        }
        return "redirect:/usuario_listar";
    }

    // ---------- ELIMINAR ----------
    @GetMapping("/usuario_eliminar")
    public String eliminarUsuario(@RequestParam("cod_usu") int cod_usu, RedirectAttributes flash) {
        try {
            if (us.eliminarUsuario(cod_usu)) {
                flash.addFlashAttribute("exito", "El usuario ha sido eliminado correctamente.");
            } else {
                flash.addFlashAttribute("error", "El usuario no existe o ya fue eliminado.");
            }
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el usuario (puede tener datos relacionados).");
        }
        return "redirect:/usuario_listar";
    }
}
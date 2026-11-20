package com.seguridad.seguridad.controlador;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seguridad.seguridad.modelo.TipoPrivacidad;
import com.seguridad.seguridad.servicio.ServicioTipoPrivacidad;

import org.springframework.ui.Model;

@Controller 
public class ControladorTipoPrivacidad {

        private final ServicioTipoPrivacidad tp;

    public ControladorTipoPrivacidad(ServicioTipoPrivacidad tp) {
        this.tp = tp;
    }
    
    @GetMapping("/tipo_privacidad")
    public String verVistaTipoPrivacidad(){
        return "tipo_privacidad";
    }

    //Solicitud de la Vista Tipo Privacidad Listar
    @GetMapping("/tipo_privacidad_listar")
    public String listarTipoPrivacidad(Model modelo) {
        List<TipoPrivacidad> tipos = tp.listarTipos();
        modelo.addAttribute("tipos", tipos);
        return "tipo_privacidad_listar";
    }

    //Metodo para llamar a la vista de agregar tipo de privacidad
    @GetMapping("/tipo_privacidad_agregar")
    public String agregarTipoPrivacidad(Model modelo) {
        TipoPrivacidad tipo = new TipoPrivacidad();
        tipo.setNom_tip("");
        tipo.setEst_tip("");
        modelo.addAttribute("tipo", tipo);
        return "tipo_privacidad_agregar";
    }

    // Metodo para guardar los datos introducidos en el formulario
    @PostMapping("/tipo_privacidad_agregar")
    public String agregarTipoPrivacidad(@ModelAttribute("tipo") TipoPrivacidad tipo, RedirectAttributes redirectAttrs) {
        try {
            tipo.setNom_tip(tipo.getNom_tip() != null ? tipo.getNom_tip().trim() : "");
            tipo.setEst_tip(tipo.getEst_tip() != null ? tipo.getEst_tip().toUpperCase() : "A");
            tp.agregarTipoPrivacidad(tipo);            
            redirectAttrs.addFlashAttribute("exito", "¡Tipo de privacidad creado correctamente!");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Error al crear el tipo de privacidad: " + e.getMessage());
        }
        return "redirect:/tipo_privacidad_listar";
    }

@GetMapping("/tipo_privacidad_ver")
public String verTipo(@RequestParam("cod_tip") int cod_tip, Model modelo) {
    modelo.addAttribute("tipo", tp.buscarTipo(cod_tip));
    return "tipo_privacidad_ver";
}

@GetMapping("/tipo_privacidad_editar")
public String editarTipo(@RequestParam("cod_tip") int cod_tip, Model modelo) {
    modelo.addAttribute("tipo", tp.buscarTipo(cod_tip));
    return "tipo_privacidad_editar";
}

@PostMapping("/tipo_privacidad_editar")
public String editarTipo(@ModelAttribute("tipo") TipoPrivacidad tipo, RedirectAttributes flash) {
    try {
        tp.editarTipo(tipo);
        flash.addFlashAttribute("exito", "¡Tipo de privacidad modificado exitosamente!");
    } catch (Exception e) {
        flash.addFlashAttribute("error", "Ocurrió un error al editar el tipo de privacidad.");
    }
    return "redirect:/tipo_privacidad_listar";
}

@GetMapping("/tipo_privacidad_eliminar")
public String eliminarTipo(@RequestParam("cod_tip") int cod_tip, RedirectAttributes flash) {
    try {
        if (tp.eliminarTipo(cod_tip)) {
            flash.addFlashAttribute("exito", "El tipo de privacidad fue eliminado correctamente.");
        } else {
            flash.addFlashAttribute("error", "El tipo de privacidad no existe o ya fue eliminado.");
        }
    } catch (Exception e) {
        flash.addFlashAttribute("error", "No se pudo eliminar el tipo de privacidad.");
    }
    return "redirect:/tipo_privacidad_listar";
}



}

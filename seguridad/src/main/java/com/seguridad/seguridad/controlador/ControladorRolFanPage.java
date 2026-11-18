package com.seguridad.seguridad.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.seguridad.seguridad.modelo.RolFanPage;
import com.seguridad.seguridad.servicio.ServicioFanPage;
import com.seguridad.seguridad.servicio.ServicioPersona;
import com.seguridad.seguridad.servicio.ServicioRol;
import com.seguridad.seguridad.servicio.ServicioRolFanPage;

@Controller
public class ControladorRolFanPage {

    private final ServicioRolFanPage rfp;
    private final ServicioPersona persona;
    private final ServicioFanPage fanPage;
    private final ServicioRol rol;

    

    public ControladorRolFanPage(ServicioRolFanPage rfp, ServicioPersona persona, ServicioFanPage fanPage,
    ServicioRol rol) {
        this.rfp = rfp;
        this.persona = persona;
        this.fanPage = fanPage;
        this.rol = rol;
    }



    @GetMapping("/rol_fan_page")
    public String VerVistaRolFanPage() {
        return "rol_fan_page";
    }

    // Listar todos los roles de fan page
    @GetMapping("/rol_fan_page_listar")
    public String listarRolFanPage(Model modelo) {
        List<RolFanPage> rolfanpages = rfp.listarRolFanPage();
        modelo.addAttribute("rolfanpages", rolfanpages);
        return "rol_fan_page_listar";
    }

    //Realiza el llamado o la invocación a la vista de agregar, esta es diferente por el tipo de tabla que estamos trabajando.
    @GetMapping("/rol_fan_page_agregar")
    public String verAgregarRolFanPage(Model modelo) {
        //Crea un objeto vacío de tipo RolFanPage y lo añade al modelo con el nombre "rolfanpage", este objeto se utiliza para hacer un enlace de datos que permita que los campos del formulario de la vista agregar "form th:object="${rolfanpage}" recojan los datos que el usuario escriba o seleccione.
        modelo.addAttribute("rolfanpage", new RolFanPage());
        //Llama al servicio de persona para obtener una lista de todas las personas registradas en el sistema y las guarda con el nombre "personas".
        modelo.addAttribute("personas", persona.listarPersonas());
        //Llama al servicio de fanpage para obtener una lista de todos los fanpages registrados en el sistema y los guarda con el nombre "fanpages".
        modelo.addAttribute("fanpages", fanPage.listarFanPages());
        //Llama al servicio de roles para obtener una lista de todos los roles registrados en el sistema y los guarda con el nombre "roles".
        modelo.addAttribute("roles", rol.listarRoles());
        //Despues de cargar todas las listas, retorna a la vista de agregar
        return "rol_fan_page_agregar";
    }

    //Para enviar la el nuevo rol fan page registrado en la vista de agregar
    // Guardar nuevo rol fan page con la limpieza de datos y manejo de errores
    @PostMapping ("/rol_fan_page_agregar")
    public String agregarRolFanPage(@ModelAttribute ("rolfanpage") RolFanPage rolfanpage,
    RedirectAttributes redirectAttrs) {
        try {
            // Si el estatus del rolfanpage no esta nulo
            rolfanpage.setEst_rol_fan(rolfanpage.getEst_rol_fan() != null ?
            rolfanpage.getEst_rol_fan().trim().toUpperCase() : "A");
            rfp.agregarRolFanPage(rolfanpage);
            redirectAttrs.addFlashAttribute("exito", "¡Rol Fan Page creado correctamente!");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Error al crear el rol fan page: " + e.getMessage());
        }
        return "redirect:/rol_fan_page_listar";
    }

    @GetMapping("/rol_fan_page_ver")
    public String verDetalleRolFanPage(@RequestParam("cod_rol_fan") int cod_rol_fan,
    Model modelo, RedirectAttributes redirectAttrs) {
        RolFanPage rolfanpage = rfp.buscarRolFanPage(cod_rol_fan);
    if (rolfanpage == null) {
        redirectAttrs.addFlashAttribute("error", "El Rol Fan Page solicitado no existe.");
        return "redirect:/rol_fan_page_listar";
    }
    modelo.addAttribute("rolfanpage", rolfanpage);
    return "rol_fan_page_ver";
}

    // Metodo para mostrar la vista de editar rol fan page
    @GetMapping("/rol_fan_page_editar")
    public String editarRolFanPage(@RequestParam ("cod_rol_fan") int cod_rol_fan, Model modelo, RedirectAttributes redirectAttrs)
    {
        RolFanPage rolfanpage = rfp.buscarRolFanPage(cod_rol_fan);
        if (rolfanpage == null) {
            redirectAttrs.addFlashAttribute("error", "El rol de fan page que intentas editar no existe.");
            return "redirect:/rol_fan_page_listar";
        }
        modelo.addAttribute("rolfanpage", rolfanpage);
        modelo.addAttribute("personas", persona.listarPersonas());
        modelo.addAttribute("fanpages", fanPage.listarFanPages());
        modelo.addAttribute("roles", rol.listarRoles());
        return "rol_fan_page_editar";
    }

    // Para llevar a cabo la edición
    @PostMapping("/rol_fan_page_editar")
    public String editarRolFanPage(@ModelAttribute("rolfanpage") RolFanPage rolfanpage,
    RedirectAttributes redirectAttrs) {
        try {
            rfp.modificarRolFanPage(rolfanpage);
            redirectAttrs.addFlashAttribute("exito", "¡Rol Fan Page modificado correctamente!");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Ocurrió un error al editar el rol fan page."+e.getMessage());
        }
        return "redirect:/rol_fan_page_listar";
    }

    //Eliminar
    @GetMapping ("/rol_fan_page_eliminar")
    public String eliminarRolFanPage(@org.springframework.web.bind.annotation.
    RequestParam("cod_rol_fan") int cod_rol_fan, RedirectAttributes flash) {
            try {
                boolean eliminado = rfp.eliminarRolFanPage(cod_rol_fan);
                if(eliminado){
                    flash.addFlashAttribute("exito", "El rol fan page ha sido eliminado correctamente.");
                }
                else
                {
                    flash.addFlashAttribute("error", "El rol fan page no existe o ya fue eliminado.");
                }
            } catch (Exception e) {
                flash.addFlashAttribute("error", "No se pudo eliminar el rol fan page.");
            }
            return "redirect:/rol_fan_page_listar";
        }
    
}


package com.seguridad.seguridad.servicio;

import java.util.List;
import org.springframework.stereotype.Service;
import com.seguridad.seguridad.modelo.FanPage;
import com.seguridad.seguridad.repositorio.RepositorioFanPage;

@Service
public class ServicioFanPage {

    private RepositorioFanPage fanpageRepositorio;

    public ServicioFanPage(RepositorioFanPage fanpageRepositorio){
        this.fanpageRepositorio = fanpageRepositorio;
    }

    public List<FanPage> listarFanPages(){
        return fanpageRepositorio.fan_page_listar();
    }
}
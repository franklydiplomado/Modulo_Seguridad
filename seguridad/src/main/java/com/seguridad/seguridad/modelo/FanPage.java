package com.seguridad.seguridad.modelo;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fan_page", schema = "perfil_empresarial")
public class FanPage {
    @Id
    @Column(name = "cod_fan_pag")  
    private int cod_fan_pag;

    @Column(name = "nom_fan_pag")  
    private String nom_fan_pag;

    @Column(name = "des_fan_pag")  
    private String des_fan_pag;

    // Constructor vacío 
    public FanPage() {}

    //Constructor completo
    public FanPage(int cod_fan_pag, String nom_fan_pag, String des_fan_pag, String per_fan_pag, Date fec_fan_pag, String est_fan_pag) {
        this.cod_fan_pag = cod_fan_pag;
        this.nom_fan_pag = nom_fan_pag;
        this.des_fan_pag = des_fan_pag;
    }
    //Constructor todos menos el código
    public FanPage(String nom_fan_pag, String des_fan_pag, String per_fan_pag, Date fec_fan_pag, String est_fan_pag) {
        this.nom_fan_pag = nom_fan_pag;
        this.des_fan_pag = des_fan_pag;
    }
    //Constructor sólo el código
    public FanPage(int cod_fan_pag) {
        this.cod_fan_pag = cod_fan_pag;
    }

    //Getters and Setters
    public int getCod_fan_pag() {
        return cod_fan_pag;
    }
    public void setCod_fan_pag(int cod_fan_pag) {
        this.cod_fan_pag = cod_fan_pag;
    }
    public String getNom_fan_pag() {
        return nom_fan_pag;
    }
    public void setNom_fan_pag(String nom_fan_pag) {
        this.nom_fan_pag = nom_fan_pag;
    }
    public String getDes_fan_pag() {
        return des_fan_pag;
    }
    public void setDes_fan_pag(String des_fan_pag) {
        this.des_fan_pag = des_fan_pag;
    } 
}

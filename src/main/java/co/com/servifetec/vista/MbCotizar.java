/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.servifetec.vista;

import co.com.servifetec.logica.LogicaEmail;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author wilme
 */
@ViewScoped
@ManagedBean(name = "MbCotizar")
public class MbCotizar implements Serializable {

    private String nombre;
    private String nombreEmpresa;
    private String email;
    private String telefono;

    @EJB
    private LogicaEmail le;

    public MbCotizar() {
    }

    @PostConstruct
    public void init() {
        le = new LogicaEmail();
        nombre = "";
        nombreEmpresa = "";
        email = "";
        telefono = "";
    }

    public void accionEnviar() {
        if (le.pre_envio(nombre, nombreEmpresa, email, telefono)) {
            System.out.println("se ha enviado desde view");
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Exitoso", "su solicitud ha sido enviada, pronto nos pondremos en contacto."));
            init();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}

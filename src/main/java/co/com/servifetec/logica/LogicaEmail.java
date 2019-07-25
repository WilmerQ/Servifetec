/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.servifetec.logica;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author wilme
 */
@Stateless
public class LogicaEmail implements Serializable {

    public Boolean pre_envio(String nombre, String nombreEmpresa, String email, String telefono) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String temp = dateFormat.format(new Date());
            
            final String mensajehtml = "<p>Cordial saludo,</p>\n"
                    + "<p>&nbsp;</p>\n"
                    + "<p>Se ha registrado una solicitud de cotizaci&oacute;n a trav&eacute;s de la p&aacute;gina de <strong>SERVIFETEC</strong> con los siguientes datos:</p>\n"
                    + "<p><strong>Nombre: </strong>" + nombre + "</p>\n"
                    + "<p><strong>Nombre empresa: </strong>" + nombreEmpresa + "</p>\n"
                    + "<p><strong>Correo electr&oacute;nico: </strong>" + email + "</p>\n"
                    + "<p><strong>Numero tel&eacute;fono: </strong>" + telefono + "</p>\n"
                    + "<p><strong>Fecha: </strong>" + temp + "</p>\n"
                    + "<p>Se recomienda ponerse en contacto con la persona en cuesti&oacute;n.</p>\n"
                    + "<p>&nbsp;</p>\n"
                    + "<p>Atte.</p>\n"
                    + "<p><a href=\"http://www.servifetec.com.co\">www.servifetec.com.co</a></p>";
            final List<String> destinarios = new ArrayList<>();
            destinarios.add("servifetecadolfo1@gmail.com");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    enviarCorreoelectronico(destinarios, new ArrayList<String>(), new ArrayList<String>(), "Cotización - Actividad en la página Servifetec.com", mensajehtml);
                }
            }).start();

            return Boolean.TRUE;
        } catch (Exception e) {
            System.out.println("*************************************************");
            System.out.println("Error: " + e.getMessage());
            return Boolean.FALSE;
        }
    }

    private void enviarCorreoelectronico(List<String> destinatarioTo, List<String> destinatarioCC, List<String> destinatarioBCC, String asunto, String cuerpoMensaje) {
        try {
            Properties props = new Properties();
            String host = "smtp.gmail.com";
            String username = "soporte.servifetec@gmail.com";
            String password = "servifetec.2019";
            props.put("mail.smtps.auth", "true");
            Session session = Session.getDefaultInstance(props);
            session.setDebug(false);

            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html");
            // Quien envia el correo
            message.setFrom(new InternetAddress("soporte.servifetec@gmail.com", "Servifetec.com"));

            for (int i = 0; i < destinatarioTo.size(); i++) {
                if (destinatarioTo.get(i) != null) {
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(destinatarioTo.get(i)));
                }
            }

            for (int i = 0; i < destinatarioCC.size(); i++) {
                if (destinatarioCC.get(i) != null) {
                    message.addRecipient(Message.RecipientType.CC,
                            new InternetAddress(destinatarioCC.get(i)));
                }
            }
            for (int i = 0; i < destinatarioBCC.size(); i++) {
                if (destinatarioBCC.get(i) != null) {
                    message.addRecipient(Message.RecipientType.BCC,
                            new InternetAddress(destinatarioBCC.get(i)));
                }
            }
            message.setSubject(asunto);
            message.setContent(cuerpoMensaje, "text/html");
            Transport t = session.getTransport("smtps");
            t.connect(host, username, password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (Exception e) {
            System.out.println("**************************************");
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

/*
 * Fichero: EnviarMail.java
 * Autor: Chuidiang
 * Fecha: 5/04/07 18:14
 */

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Ejemplo de envio de correo simple con JavaMail
 *
 * @author Chuidiang
 *
  */
public class EnviarMail
{
    /**
     * main de prueba
     * @param args Se ignoran.
     */
    public static void main(String[] args)
    {
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "brueradamian@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("bruera@noreply.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("brueradamian@gmail.com"));
            message.setSubject("System Configuration");
            message.setText(
                "Settings of new configuration as been loaded into FTPServer");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("brueradamian@gmail.com", "www.640intelPRO.net");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
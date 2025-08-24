package org.polimi.nexbuy.service.mail;

import freemarker.template.*;
import jakarta.annotation.Nullable;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.polimi.nexbuy.exception.SendingMailException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Classe Service per l'invio di email.
 */
@Service
@Transactional
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    /**
     * Metodo per l'invio di email.
     * @param toAddress Indirizzo email del destinatario.
     * @param subject Oggetto dell'email.
     * @param cc Indirizzo email in copia.
     * @param bcc Indirizzo email in copia nascosta.
     * @param templateName Nome del template da utilizzare.
     * @param model Modello da utilizzare per il template.
     * @throws SendingMailException Eccezione lanciata in caso di errore durante l'invio dell'email.
     */
    public void sendRegistrationEmail(
            String toAddress,
            String subject,
            @Nullable String cc,
            @Nullable String bcc,
            String templateName,
            Map<String, Object> model) throws SendingMailException {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(toAddress);
            if (cc != null) {
                mimeMessageHelper.setCc(cc);
            }
            if (bcc != null){
                mimeMessageHelper.setBcc(bcc);
            }
            mimeMessageHelper.setSubject(subject);

            Template template = configuration.getTemplate(templateName + ".ftl");
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setText(content, true);

            javaMailSender.send(mimeMessage);

        } catch (MailException e) {
            throw new SendingMailException("Errore durante l'invio della mail: MailException occurred.");
        } catch (MessagingException e) {
            throw new SendingMailException("Errore durante l'invio della mail: MessagingException occurred.");
        } catch (TemplateException e) {
            throw new SendingMailException("Errore durante l'invio della mail: TemplateException occurred.");
        } catch (IOException e) {
            throw new SendingMailException("Errore durante l'invio della mail: IOException occurred.");
        }

    }

}

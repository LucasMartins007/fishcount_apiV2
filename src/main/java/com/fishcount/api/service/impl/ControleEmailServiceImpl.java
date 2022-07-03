package com.fishcount.api.service.impl;

import com.fishcount.api.config.beans.EmailBean;
import com.fishcount.api.repository.TemplateRepository;
import com.fishcount.api.service.ControleEmailService;
import com.fishcount.common.model.classes.DadosEmail;
import com.fishcount.common.model.dto.ControleEmailDTO;
import com.fishcount.common.model.entity.ControleEmail;
import com.fishcount.common.model.entity.Email;
import com.fishcount.common.model.entity.Pessoa;
import com.fishcount.common.model.entity.Template;
import com.fishcount.common.model.enums.EnumTipoEmail;
import com.fishcount.common.model.enums.EnumTipoEnvioEmail;
import com.fishcount.common.utils.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class ControleEmailServiceImpl extends AbstractServiceImpl<ControleEmail, Integer, ControleEmailDTO> implements ControleEmailService {

    @Autowired
    private EmailBean emailBean;

    @Autowired
    private Environment environment;

    @Value("${email.suporte}")
    private String emailSuporte;

    @Override
    public void enviarEmail(Pessoa pessoa, EnumTipoEnvioEmail tipoEnvioEmail) {
        final DadosEmail dadosEmail = gerarDadosEmail(pessoa, tipoEnvioEmail);

        if (environment.getActiveProfiles()[0].equals("development")) {
            System.out.println(dadosEmail.getCorpoEmail());
            registrarEnvioEmail(dadosEmail);
            return;
        }

        try {
            JavaMailSender mailSender = emailBean.getMailSender();

            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setTo(dadosEmail.getEmailDestinatario());
            helper.setSubject(dadosEmail.getAssunto());
            helper.setText(dadosEmail.getCorpoEmail(), true);

            mailSender.send(mail);

            registrarEnvioEmail(dadosEmail);
        } catch (MessagingException | MailException e) {
            registrarExcecaoEnvioEmail(dadosEmail, e);
        }
    }

    private void registrarExcecaoEnvioEmail(DadosEmail dadosEmail, Exception e) {
        final ControleEmail controleEmail = gerarControleEmail(dadosEmail);
        controleEmail.setEnviado(false);
        controleEmail.setExcecaoEnvio(e.getMessage());
    }

    private void registrarEnvioEmail(DadosEmail dadosEmail) {
        final ControleEmail controleEmail = gerarControleEmail(dadosEmail);

        controleEmail.setEnviado(true);
        getRepository().save(controleEmail);
    }

    private ControleEmail gerarControleEmail(DadosEmail dadosEmail) {
        final ControleEmail controleEmail = new ControleEmail();

        controleEmail.setCorpoEmail(dadosEmail.getCorpoEmail());
        controleEmail.setEmailDestinatario(dadosEmail.getEmailDestinatario());
        controleEmail.setEmailRemetente(dadosEmail.getEmailRementente());
        controleEmail.setAssunto(dadosEmail.getAssunto());
        controleEmail.setNomeDestinatario(dadosEmail.getNomeDestinatario());
        controleEmail.setPessoa(dadosEmail.getPessoa());

        return controleEmail;
    }

    private DadosEmail gerarDadosEmail(Pessoa pessoa, EnumTipoEnvioEmail tipoEnvioEmail) {
        final DadosEmail dadosEmail = new DadosEmail();

        final Template template = getRepository(TemplateRepository.class).findByTipoEnvioEmail(tipoEnvioEmail);

        dadosEmail.setCorpoEmail(template.getCorpoHtml());
        dadosEmail.setAssunto(template.getAssunto());
        dadosEmail.setNomeDestinatario(pessoa.getNome());
        dadosEmail.setPessoa(pessoa);

        final String emailDestinatario = ListUtil.stream(pessoa.getEmails())
                .filter(EnumTipoEmail::isPrincipal)
                .map(Email::getDescricao)
                .findFirst()
                .orElse(emailSuporte);

        dadosEmail.setEmailDestinatario(emailDestinatario);

        return dadosEmail;
    }
}

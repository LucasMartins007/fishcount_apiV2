package com.fishcount.api.service.impl;

import com.fishcount.api.config.beans.EmailBean;
import com.fishcount.api.repository.ControleEmailRepository;
import com.fishcount.api.repository.TemplateRepository;
import com.fishcount.api.service.ControleEmailService;
import com.fishcount.api.service.pattern.AbstractServiceImpl;
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
    private TemplateRepository templateRepository;

    @Autowired
    private ControleEmailRepository controleEmailRepository;

    @Autowired
    private EmailBean emailBean;

    @Autowired
    private Environment environment;

    @Value("${email.suporte}")
    private String emailSuporte;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void enviarEmail(Pessoa pessoa, EnumTipoEnvioEmail tipoEnvioEmail, boolean isSuporte) {
        final DadosEmail dadosEmail = gerarDadosEmail(pessoa, tipoEnvioEmail, isSuporte);

        try {
            JavaMailSender mailSender = emailBean.getMailSender();

            String emailPrincipal = ListUtil.stream(pessoa.getEmails())
                    .filter(EnumTipoEmail::isPrincipal)
                    .map(Email::getDescricao)
                    .findFirst()
                    .orElse(emailSuporte);

            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setFrom(from);
            helper.setTo(emailPrincipal);
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
        controleEmailRepository.save(controleEmail);
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

    private DadosEmail gerarDadosEmail(Pessoa pessoa, EnumTipoEnvioEmail tipoEnvioEmail, boolean isSuporte) {
        final DadosEmail dadosEmail = new DadosEmail();

        final Template template = templateRepository.findByTipoEnvioEmail(tipoEnvioEmail);

        dadosEmail.setCorpoEmail(template.getCorpoHtml());
        dadosEmail.setAssunto(template.getAssunto());
        dadosEmail.setNomeDestinatario(pessoa.getNome());
        dadosEmail.setPessoa(pessoa);

        String emailDestinatario = ListUtil.stream(pessoa.getEmails())
                .filter(EnumTipoEmail::isPrincipal)
                .map(Email::getDescricao)
                .findFirst()
                .orElse(emailSuporte);

        emailDestinatario = isSuporte ? emailSuporte : emailDestinatario;

        dadosEmail.setEmailDestinatario(emailDestinatario);

        return dadosEmail;
    }
}

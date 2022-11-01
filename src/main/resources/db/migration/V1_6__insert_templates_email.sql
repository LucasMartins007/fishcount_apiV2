INSERT INTO public.fish_template
(id, assunto, corpo_html, tipo_envio_email, ativo, data_atualizacao, data_inclusao)
VALUES(nextval('gen_id_fish_template'), 'Solicitação de alteração de senha', '<html><b> Alteração de senha </b></html>', 1, true, '2022-07-03 00:00:00.000', '2022-07-03 00:00:00.000');

INSERT INTO public.fish_template
(id, assunto, corpo_html, tipo_envio_email, ativo, data_atualizacao, data_inclusao)
VALUES(nextval('gen_id_fish_template'), 'Novo Cadastro', '<html><b> Obrigado por usar o sistema da FishCount </b></html>', 2, true, '2022-07-03 00:00:00.000', '2022-07-03 00:00:00.000');

INSERT INTO public.fish_template
(id, assunto, corpo_html, tipo_envio_email, ativo, data_atualizacao, data_inclusao)
VALUES(nextval('gen_id_fish_template'), 'Confirmação de Email', '<html><b> Confirme seu e-mail </b></html>', 3, true, '2022-07-03 00:00:00.000', '2022-07-03 00:00:00.000');

INSERT INTO public.fish_template
(id, assunto, corpo_html, tipo_envio_email, ativo, data_atualizacao, data_inclusao)
VALUES(nextval('gen_id_fish_template'), 'Exceção no sistema', '<html><b> Ocorreu uma exceção no sistema </b></html>', 4, true, '2022-07-03 00:00:00.000', '2022-07-03 00:00:00.000');
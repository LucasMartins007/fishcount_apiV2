-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- DROP SEQUENCE public.gen_fish_id_email;

CREATE SEQUENCE public.gen_fish_id_email
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_fish_id_plano;

CREATE SEQUENCE public.gen_fish_id_plano
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_analise;

CREATE SEQUENCE public.gen_id_analise
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_email;

CREATE SEQUENCE public.gen_id_email
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_endereco;

CREATE SEQUENCE public.gen_id_endereco
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_especie;

CREATE SEQUENCE public.gen_id_especie
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_analise;

CREATE SEQUENCE public.gen_id_fish_analise
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_cobranca_pix;

CREATE SEQUENCE public.gen_id_fish_cobranca_pix
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_endereco;

CREATE SEQUENCE public.gen_id_fish_endereco
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_especie;

CREATE SEQUENCE public.gen_id_fish_especie
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_lote;

CREATE SEQUENCE public.gen_id_fish_lote
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_pagamento;

CREATE SEQUENCE public.gen_id_fish_pagamento
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_tanque;

CREATE SEQUENCE public.gen_id_fish_tanque
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_taxa_crescimento;

CREATE SEQUENCE public.gen_id_fish_taxa_crescimento
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_telefone;

CREATE SEQUENCE public.gen_id_fish_telefone
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_titulo;

CREATE SEQUENCE public.gen_id_fish_titulo
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_titulo_parcela;

CREATE SEQUENCE public.gen_id_fish_titulo_parcela
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_fish_usuario;

CREATE SEQUENCE public.gen_id_fish_usuario
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_lote;

CREATE SEQUENCE public.gen_id_lote
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_plano;

CREATE SEQUENCE public.gen_id_plano
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_tank;

CREATE SEQUENCE public.gen_id_tank
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_taxa_crescimento;

CREATE SEQUENCE public.gen_id_taxa_crescimento
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_telefone;

CREATE SEQUENCE public.gen_id_telefone
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_titulo;

CREATE SEQUENCE public.gen_id_titulo
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_titulo_parcela;

CREATE SEQUENCE public.gen_id_titulo_parcela
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.gen_id_usuario;

CREATE SEQUENCE public.gen_id_usuario
	INCREMENT BY 50
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;-- public.fish_analise definition

-- Drop table

-- DROP TABLE public.fish_analise;

CREATE TABLE public.fish_analise (
	id int4 NOT NULL,
	data_analise timestamp NULL,
	peso_medio numeric(19, 2) NULL,
	qtde_media_racao numeric(19, 2) NULL,
	qtde_racao numeric(19, 2) NULL,
	id_tanque int4 NULL,
	CONSTRAINT fish_analise_pkey PRIMARY KEY (id)
);


-- public.fish_cobranca_pix definition

-- Drop table

-- DROP TABLE public.fish_cobranca_pix;

CREATE TABLE public.fish_cobranca_pix (
	id int4 NOT NULL,
	chav_devedor varchar(255) NULL,
	data_criacao timestamp NULL,
	data_expiracao timestamp NULL,
	observacao_pagador varchar(255) NULL,
	saldo numeric(19, 2) NULL,
	status_cobranca int4 NULL,
	tx_id varchar(255) NULL,
	valor numeric(19, 2) NULL,
	id_pagamento int4 NULL,
	id_usuario int4 NULL,
	id_pagamento_parcela int4 NULL,
	CONSTRAINT fish_cobranca_pix_pkey PRIMARY KEY (id)
);


-- public.fish_email definition

-- Drop table

-- DROP TABLE public.fish_email;

CREATE TABLE public.fish_email (
	id int4 NOT NULL,
	ativo bool NULL,
	data_atualizacao timestamp NULL,
	data_inclusao timestamp NULL,
	descricao varchar(255) NULL,
	tipo_email int4 NULL,
	id_usuario int4 NULL,
	CONSTRAINT fish_email_pkey PRIMARY KEY (id)
);


-- public.fish_endereco definition

-- Drop table

-- DROP TABLE public.fish_endereco;

CREATE TABLE public.fish_endereco (
	id int4 NOT NULL,
	data_atualizacao timestamp NULL,
	data_inclusao timestamp NULL,
	estado varchar(255) NULL,
	numero varchar(255) NULL,
	pais varchar(255) NULL,
	rua varchar(255) NULL,
	tipo_endereco int4 NULL,
	id_usuario int4 NULL,
	CONSTRAINT fish_endereco_pkey PRIMARY KEY (id)
);


-- public.fish_especie definition

-- Drop table

-- DROP TABLE public.fish_especie;

CREATE TABLE public.fish_especie (
	id int4 NOT NULL,
	data_atualizacao timestamp NULL,
	data_inclusao timestamp NULL,
	descricao varchar(255) NULL,
	peso_medio numeric(19, 2) NULL,
	qtde_media_racao int4 NULL,
	tamanho_medio numeric(19, 2) NULL,
	unidade_peso_medio varchar(255) NULL,
	unidade_peso_racao varchar(255) NULL,
	unidade_tamanho varchar(255) NULL,
	id_taxa_crescimento int4 NULL,
	CONSTRAINT fish_especie_pkey PRIMARY KEY (id)
);


-- public.fish_lote definition

-- Drop table

-- DROP TABLE public.fish_lote;

CREATE TABLE public.fish_lote (
	id int4 NOT NULL,
	data_atualizacao timestamp NULL,
	data_inclusao timestamp NULL,
	descritpion varchar(255) NULL,
	id_usuario int4 NULL,
	CONSTRAINT fish_lote_pkey PRIMARY KEY (id)
);


-- public.fish_pagamento definition

-- Drop table

-- DROP TABLE public.fish_pagamento;

CREATE TABLE public.fish_pagamento (
	id int4 NOT NULL,
	acrescimo numeric(19, 2) NULL,
	data_alteracao timestamp NULL,
	data_inclusao timestamp NULL,
	data_vencimento timestamp NULL,
	desconto numeric(19, 2) NULL,
	qtde_parcelas int4 NULL,
	saldo numeric(19, 2) NULL,
	status_pagamento int4 NULL,
	tipo_pagamento int4 NULL,
	valor numeric(19, 2) NULL,
	id_plano int4 NULL,
	id_titulo int4 NULL,
	id_usuario int4 NULL,
	CONSTRAINT fish_pagamento_pkey PRIMARY KEY (id)
);


-- public.fish_pagamento_parcela definition

-- Drop table

-- DROP TABLE public.fish_pagamento_parcela;

CREATE TABLE public.fish_pagamento_parcela (
	id int4 NOT NULL,
	acrescimo numeric(19, 2) NULL,
	data_alteracao timestamp NULL,
	data_inclusao timestamp NULL,
	data_vencimento timestamp NULL,
	desconto numeric(19, 2) NULL,
	saldo numeric(19, 2) NULL,
	status_pagamento int4 NULL,
	tipo_pagamento int4 NULL,
	valor numeric(19, 2) NULL,
	id_pagamento int4 NULL,
	id_titulo_parcela int4 NULL,
	CONSTRAINT fish_pagamento_parcela_pkey PRIMARY KEY (id)
);


-- public.fish_plano definition

-- Drop table

-- DROP TABLE public.fish_plano;

CREATE TABLE public.fish_plano (
	id int4 NOT NULL,
	ativo bool NULL,
	descricao varchar(255) NULL,
	max_taque int4 NULL,
	min_taque int4 NULL,
	num_parcelas int4 NULL,
	valor_maximo numeric(19, 2) NULL,
	valor_minimo numeric(19, 2) NULL,
	CONSTRAINT fish_plano_pkey PRIMARY KEY (id)
);


-- public.fish_tanque definition

-- Drop table

-- DROP TABLE public.fish_tanque;

CREATE TABLE public.fish_tanque (
	id int4 NOT NULL,
	data_proxima_analise timestamp NULL,
	data_ultima_analise timestamp NULL,
	data_ultimo_tratamento timestamp NULL,
	descricao varchar(255) NULL,
	qtde_ultima_analise int4 NULL,
	id_especie int4 NULL,
	id_lote int4 NULL,
	CONSTRAINT fish_tanque_pkey PRIMARY KEY (id)
);


-- public.fish_taxa_crescimento definition

-- Drop table

-- DROP TABLE public.fish_taxa_crescimento;

CREATE TABLE public.fish_taxa_crescimento (
	id int4 NOT NULL,
	data_atualizacao timestamp NULL,
	data_inclusao timestamp NULL,
	intervalo int4 NULL,
	qtde_aumento numeric(19, 2) NULL,
	unidade_aumento varchar(255) NULL,
	unidade_intervalo varchar(255) NULL,
	id_especie int4 NULL,
	CONSTRAINT fish_taxa_crescimento_pkey PRIMARY KEY (id)
);


-- public.fish_telefone definition

-- Drop table

-- DROP TABLE public.fish_telefone;

CREATE TABLE public.fish_telefone (
	id int4 NOT NULL,
	ativo bool NULL,
	data_atualizacao timestamp NULL,
	data_inclusao timestamp NULL,
	descricao varchar(255) NULL,
	tipo_telefone int4 NULL,
	id_usuario int4 NULL,
	CONSTRAINT fish_telefone_pkey PRIMARY KEY (id)
);


-- public.fish_titulo definition

-- Drop table

-- DROP TABLE public.fish_titulo;

CREATE TABLE public.fish_titulo (
	id int4 NOT NULL,
	acrescimo numeric(19, 2) NULL,
	data_alteracao timestamp NULL,
	data_inclusao timestamp NULL,
	data_vencimento timestamp NULL,
	desconto numeric(19, 2) NULL,
	qtde_parcelas int4 NULL,
	saldo numeric(19, 2) NULL,
	status_titulo int4 NULL,
	tipo_titulo int4 NULL,
	valor numeric(19, 2) NULL,
	id_pagamento int4 NULL,
	id_usuario int4 NULL,
	CONSTRAINT fish_titulo_pkey PRIMARY KEY (id)
);


-- public.fish_titulo_parcela definition

-- Drop table

-- DROP TABLE public.fish_titulo_parcela;

CREATE TABLE public.fish_titulo_parcela (
	id int4 NOT NULL,
	acrescimo numeric(19, 2) NULL,
	data_alteracao timestamp NULL,
	data_inclusao timestamp NULL,
	data_vencimento timestamp NULL,
	desconto numeric(19, 2) NULL,
	saldo numeric(19, 2) NULL,
	status_titulo int4 NULL,
	tipo_titulo int4 NULL,
	valor numeric(19, 2) NULL,
	id_pagamento_parcela int4 NULL,
	id_titulo int4 NULL,
	CONSTRAINT fish_titulo_parcela_pkey PRIMARY KEY (id)
);


-- public.fish_usuario definition

-- Drop table

-- DROP TABLE public.fish_usuario;

CREATE TABLE public.fish_usuario (
	id int4 NOT NULL,
	ativo bool NULL,
	cpf varchar(255) NULL,
	data_alteracao timestamp NULL,
	data_inclusao timestamp NULL,
	nome varchar(255) NULL,
	senha varchar(255) NULL,
	CONSTRAINT fish_usuario_pkey PRIMARY KEY (id)
);


-- public.flyway_schema_history definition

-- Drop table

-- DROP TABLE public.flyway_schema_history;

CREATE TABLE public.flyway_schema_history (
	installed_rank int4 NOT NULL,
	"version" varchar(50) NULL,
	description varchar(200) NOT NULL,
	"type" varchar(20) NOT NULL,
	script varchar(1000) NOT NULL,
	checksum int4 NULL,
	installed_by varchar(100) NOT NULL,
	installed_on timestamp NOT NULL DEFAULT now(),
	execution_time int4 NOT NULL,
	success bool NOT NULL,
	CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank)
);
CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


INSERT INTO public.fish_taxa_crescimento
(id, data_atualizacao, data_inclusao, intervalo, qtde_aumento, unidade_aumento, unidade_intervalo, id_especie)
VALUES(51, '2022-05-23 00:00:00.000', '2022-05-23 00:00:00.000', 250, 2.00, 'CM', 'DIA', null);


INSERT INTO public.fish_especie
(id, data_atualizacao, data_inclusao, descricao, peso_medio, qtde_media_racao, tamanho_medio, unidade_peso_medio, unidade_peso_racao, unidade_tamanho, id_taxa_crescimento)
VALUES(1, '2022-05-23 00:00:00.000', '2022-05-23 00:00:00.000', 'especie teste', 300.00, 20, 10.00, 'GR', 'GR', 'CM', null);


INSERT INTO public.fish_plano
(id, ativo, descricao, max_taque, min_taque, num_parcelas, valor_maximo, valor_minimo)
VALUES(1, true, 'Plano teste', 20, 10, 5, 200.00, 100.00);


 create sequence gen_id_analise start 1 increment 50;
 create sequence gen_id_email start 1 increment 50;
 create sequence gen_id_endereco start 1 increment 50;
 create sequence gen_id_especie start 1 increment 50;
 create sequence gen_id_lote start 1 increment 50;
 create sequence gen_id_plano start 1 increment 50;
 create sequence gen_id_tank start 1 increment 50;
 create sequence gen_id_taxa_crescimento start 1 increment 50;
 create sequence gen_id_telefone start 1 increment 50;
 create sequence gen_id_titulo start 1 increment 50;
 create sequence gen_id_titulo_parcela start 1 increment 50;
 create sequence gen_id_usuario start 1 increment 50;
 
    
    create table analise (
       id int4 not null,
        data_analise timestamp,
        peso_medio numeric(19, 2),
        qtde_media_racao numeric(19, 2),
        qtde_racao numeric(19, 2),
        id_tanque int4,
        primary key (id)
    );
 
    
    create table email (
       id int4 not null,
        ativo boolean,
        data_atualizacao timestamp,
        data_inclusao timestamp,
        descricao varchar(255),
        tipo_email int4,
        id_usuario int4,
        primary key (id)
    );
 
    
    create table endereco (
       id int4 not null,
        data_atualizacao timestamp,
        data_inclusao timestamp,
        estado varchar(255),
        numero varchar(255),
        pais varchar(255),
        rua varchar(255),
        tipo_endereco int4,
        id_usuario int4,
        primary key (id)
    );
 
    
    create table especie (
       id int4 not null,
        data_atualizacao timestamp,
        data_inclusao timestamp,
        descricao varchar(255),
        peso_medio numeric(19, 2),
        qtde_media_racao int4,
        tamanho_medio numeric(19, 2),
        unidade_peso_medio varchar(255),
        unidade_peso_racao varchar(255),
        unidade_tamanho varchar(255),
        id_taxa_crescimento int4,
        primary key (id)
    );
 
    
    create table lote (
       id int4 not null,
        data_atualizacao timestamp,
        data_inclusao timestamp,
        descritpion varchar(255),
        id_usuario int4,
        primary key (id)
    );
 
    
    create table plano (
       id int4 not null,
        descricao varchar(255),
        miax_taque int4,
        min_taque int4,
        valor_maximo float8,
        valor_minimo float8,
        primary key (id)
    );
 
    
    create table tanque (
       id int4 not null,
        data_proxima_analise timestamp,
        data_ultima_analise timestamp,
        data_ultimo_tratamento timestamp,
        descricao varchar(255),
        qtde_ultima_analise int4,
        id_specie int4,
        id_lote int4,
        primary key (id)
    );
 
    
    create table taxa_crescimento (
       id int4 not null,
        data_atualizacao timestamp,
        data_inclusao timestamp,
        intervalo int4,
        periodo_analise int4,
        qtde_aumento numeric(19, 2),
        unidade_aumento varchar(255),
        unidade_itempo varchar(255),
        id_especie int4,
        primary key (id)
    );
 
    
    create table telefone (
       id int4 not null,
        ativo boolean,
        data_atualizacao timestamp,
        data_inclusao timestamp,
        descricao varchar(255),
        tipo_telefone int4,
        id_usuario int4,
        primary key (id)
    );
 
    
    create table titulo (
       id int4 not null,
        acrescimo float8,
        data_alteracao timestamp,
        data_inclusao timestamp,
        data_vencimento timestamp,
        desconto float8,
        qtde_parcelas int4,
        saldo float8,
        status_titulo int4,
        tipo_titulo int4,
        valor float8,
        id_plano int4,
        id_usuario int4,
        primary key (id)
    );
 
    
    create table titulo_parcela (
       id int4 not null,
        acrescimo float8,
        data_alteracao timestamp,
        data_inclusao timestamp,
        data_vencimento timestamp,
        desconto float8,
        saldo float8,
        status_titulo int4,
        tipo_titulo int4,
        valor float8,
        id_titulo int4,
        primary key (id)
    );
 
    
    create table usuario (
       id int4 not null,
        ativo boolean,
        data_alteracao timestamp,
        data_inclusao timestamp,
        nome varchar(255),
        senha varchar(255),
        primary key (id)
    );
 
    
    alter table if exists analise 
       add constraint fk_analise_to_tanque 
       foreign key (id_tanque) 
       references tanque;
 
    
    alter table if exists email 
       add constraint fk_email_id_usario 
       foreign key (id_usuario) 
       references usuario;
 
    
    alter table if exists endereco 
       add constraint fk_endereco_to_usuario 
       foreign key (id_usuario) 
       references usuario;
 
    
    alter table if exists especie 
       add constraint fk_especie_to_taxa_crescimento 
       foreign key (id_taxa_crescimento) 
       references taxa_crescimento;
 
    
    alter table if exists lote 
       add constraint fk_lote_to_usuario 
       foreign key (id_usuario) 
       references usuario;
 
    
    alter table if exists tanque 
       add constraint fk_tank_id_specie 
       foreign key (id_specie) 
       references especie;
 
    
    alter table if exists tanque 
       add constraint fk_tanque_to_lote 
       foreign key (id_lote) 
       references lote;
 
    
    alter table if exists taxa_crescimento 
       add constraint fk_taxa_crescimento_to_especie 
       foreign key (id_especie) 
       references especie;
 
    
    alter table if exists telefone 
       add constraint fk_telefone_to_usuario 
       foreign key (id_usuario) 
       references usuario;
 
    
    alter table if exists titulo 
       add constraint fk_titulo_to_plano 
       foreign key (id_plano) 
       references plano;
 
    
    alter table if exists titulo 
       add constraint fk_titulo_to_usuario 
       foreign key (id_usuario) 
       references usuario;
 
    
    alter table if exists titulo_parcela 
       add constraint fk_titulo_parcela_to_titulo 
       foreign key (id_titulo) 
       references titulo;



INSERT INTO public.taxa_crescimento
(id, data_atualizacao, data_inclusao, intervalo, periodo_analise, qtde_aumento, unidade_aumento, unidade_itempo, id_especie, unidade_intervalo)
VALUES(51, '2022-05-23 00:00:00.000', '2022-05-23 00:00:00.000', 250, 0, 2.00, 'CM', 'DIA', 1, 'DIA');



INSERT INTO public.especie
(id, data_atualizacao, data_inclusao, descricao, peso_medio, qtde_media_racao, tamanho_medio, unidade_peso_medio, unidade_peso_racao, unidade_tamanho, id_taxa_crescimento)
VALUES(1, '2022-05-23 00:00:00.000', '2022-05-23 00:00:00.000', 'especie teste', 300.00, 20, 10.00, 'GR', 'GR', 'CM', 51);


INSERT INTO public.fish_taxa_crescimento
(id, data_atualizacao, data_inclusao, intervalo, qtde_aumento, unidade_aumento, unidade_intervalo, id_especie, ativo)
VALUES(nextval('gen_id_fish_taxa_crescimento'), '2022-05-23 00:00:00.000', '2022-05-23 00:00:00.000', 250, 2.00, 'CM', 'DIA', null, true);

UPDATE public.fish_taxa_crescimento
    SET  id_especie = 1
    WHERE ID = 1;
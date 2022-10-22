
INSERT INTO public.fin_plano
(id, ativo, data_atualizacao, data_inclusao, descricao, max_taque, min_taque, qtde_parcela, valor_maximo, valor_minimo, valor_parcela_maximo, valor_parcela_minimo)
VALUES(nextval('gen_id_fin_plano'), true, '2022-07-26 23:24:57.573', '2022-07-26 23:24:57.573', 'Corporativo', 300, 250, 10, 5000.00, 3500.00, 291.66, 83.33);


INSERT INTO public.fin_plano
(id, ativo, data_atualizacao, data_inclusao, descricao, max_taque, min_taque, qtde_parcela, valor_maximo, valor_minimo, valor_parcela_maximo, valor_parcela_minimo)
VALUES(nextval('gen_id_fin_plano'), true, '2022-07-26 23:24:57.573', '2022-07-26 23:24:57.573', 'Executivo', 300, 250, 10, 3500.00, 1000.00,  500.00, 350.00);


INSERT INTO public.fin_plano
(id, ativo, data_atualizacao, data_inclusao, descricao, max_taque, min_taque, qtde_parcela, valor_maximo, valor_minimo, valor_parcela_maximo, valor_parcela_minimo)
VALUES(nextval('gen_id_fin_plano'), true, '2022-07-26 23:24:57.573', '2022-07-26 23:24:57.573', 'Pessoal', 300, 250, 10, 1000.00, 600.00, 166.66, 100.00);

UPDATE public.fish_especie
    SET  id_taxa_crescimento = 1
    WHERE ID = 1;
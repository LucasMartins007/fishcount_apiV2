package com.fishcount.common.model.entity;

import com.fishcount.common.model.pattern.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
@Entity
@Getter
@Setter
@Table(name = "usuario")
public class Usuario extends AbstractEntity<Integer> {
    
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_usuario", sequenceName = "gen_id_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_usuario")
    private Integer Id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lote> lotes;
    
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;

    @Column(name = "data_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
}

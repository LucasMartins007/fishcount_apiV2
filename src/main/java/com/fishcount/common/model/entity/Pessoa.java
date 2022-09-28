package com.fishcount.common.model.entity;

import com.fishcount.common.model.entity.pattern.AbstractEntity;
import com.fishcount.common.utils.DateUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "fish_pessoa")
public class Pessoa extends AbstractEntity<Integer> {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "id_fish_pessoa", sequenceName = "gen_id_fish_pessoa", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_fish_pessoa")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Transient
    private String senha;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_fish_pessoa_to_fish_usuario"))
    private Usuario usuario;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lote> lotes;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}

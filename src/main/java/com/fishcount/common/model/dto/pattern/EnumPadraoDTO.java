/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fishcount.common.model.dto.pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author lucas
 */
@Getter
@Setter
@NoArgsConstructor
public class EnumPadraoDTO {

    private Object key;

    private Object name;

    private String value;

    public EnumPadraoDTO(IEnum<?> enumPadrao) {
        this.key = enumPadrao.getName();
        this.name = enumPadrao.getKey();
        this.value = enumPadrao.getValue();
    }
}

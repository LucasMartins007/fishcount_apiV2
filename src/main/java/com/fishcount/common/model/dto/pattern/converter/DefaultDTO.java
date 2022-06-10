package com.fishcount.common.model.dto.pattern.converter;

import com.fishcount.common.model.dto.pattern.AbstractDTO;
import com.fishcount.common.utils.ListUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author lucas
 * @param <D>
 */
@Data
public class DefaultDTO<D extends AbstractDTO<?>> implements Serializable {

    private List<D> content;

    @SuppressWarnings("unchecked")
    public DefaultDTO(D content) {
        this.content = ListUtil.toList(content);
    }

    public DefaultDTO(List<D> content) {
        this.content = ListUtil.isNullOrEmpty(content) ? Collections.emptyList() : content;
    }
}

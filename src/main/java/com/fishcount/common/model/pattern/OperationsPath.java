package com.fishcount.common.model.pattern;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author lucas
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationsPath {

    public static final String ID = "/{id}";

    public static final String PARENT_ID = "/{parentId}";

    public static final String CHILD_ID = "/{childId}";


}

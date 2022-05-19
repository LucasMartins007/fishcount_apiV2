
package com.fishcount.common.utils.optional;

/**
 *
 * @author Lucas Martins
 */
@FunctionalInterface
public interface ThrowableFunction<T, R, E extends Throwable> {

    R apply(T t) throws E;

}


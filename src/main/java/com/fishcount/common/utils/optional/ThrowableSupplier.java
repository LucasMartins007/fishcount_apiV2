
package com.fishcount.common.utils.optional;

/**
 * @param <T>
 * @param <E>
 * @author Lucas Martins
 */
@FunctionalInterface
public interface ThrowableSupplier<T, E extends Throwable> {

    T get() throws E;

}


package com.fishcount.common.utils.optional;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @param <T> Tipo do ponteiro opcional
 * @author Lucas Martins
 */
public final class OptionalUtil<T> {

    private static final OptionalUtil<?> EMPTY = new OptionalUtil<>();

    private final T value;

    private OptionalUtil() {
        value = null;
    }

    private OptionalUtil(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> OptionalUtil<T> empty() {
        @SuppressWarnings("unchecked")
        OptionalUtil<T> opt = (OptionalUtil<T>) EMPTY;

        return opt;
    }

    public static <T> OptionalUtil<T> of(T value) {
        return new OptionalUtil<>(value);
    }

    public static <T> OptionalUtil<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public static <T> OptionalUtil<T> ofFallible(ThrowableSupplier<T, ?> supplier) {
        try {
            return of(supplier.get());
        } catch (@SuppressWarnings("UseSpecificCatch") Throwable t) {
            return empty();
        }
    }

    public static <T> OptionalUtil<T> ofFallibleNullable(ThrowableSupplier<T, ?> supplier) {
        try {
            return ofNullable(supplier.get());
        } catch (@SuppressWarnings("UseSpecificCatch") Throwable t) {
            return empty();
        }
    }

    public boolean isPresent() {
        return value != null;
    }

    public T get() {
        return getWithMessage("No value present");
    }

    public T getWithMessage(String message) {
        if (value == null) {
            throw new NoSuchElementException(message);
        }

        return value;
    }

    public T orElse(T fallback) {
        return isPresent() ? value : fallback;
    }

//    public T orElseGet(Supplier<? extends T> fallback) {
//        return isPresent() ? value : fallback.get();
//    }
    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isPresent()) {
            return value;
        }

        throw exceptionSupplier.get();
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (isPresent()) {
            consumer.accept(value);
        }
    }

    public <X extends Throwable> T ifPresentThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isPresent()) {
            throw exceptionSupplier.get();
        }
        return null;
    }

    public void ifAbsent(Runnable action) {
        if (!isPresent()) {
            action.run();
        }
    }
    
    public <X extends Throwable> T ifAbsentThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (!isPresent()){
            throw exceptionSupplier.get();
        }
        return null;
    }

    public void consume(Consumer<? super T> ifPresent, Runnable ifAbsent) {
        if (isPresent()) {
            ifPresent.accept(value);
        } else {
            ifAbsent.run();
        }
    }

    public OptionalUtil<T> peekIfPresent(Consumer<? super T> consumer) {
        ifPresent(consumer);
        return this;
    }

    public OptionalUtil<T> filter(Predicate<? super T> predicate) {
        return isPresent() && predicate.test(value) ? this : empty();
    }

    public <U> OptionalUtil<U> map(Function<? super T, ? extends U> mapper) {
        if (!isPresent()) {
            return empty();
        }

        return ofNullable(mapper.apply(value));
    }

    public <U> OptionalUtil<U> mapFallible(ThrowableFunction<? super T, ? extends U, ?> mapper) {
        try {
            return ofNullable(mapper.apply(get()));
        } catch (@SuppressWarnings("UseSpecificCatch") Throwable t) {
            return empty();
        }
    }

    public <U> OptionalUtil<U> flatMap(Function<? super T, OptionalUtil<U>> mapper) {
        if (!isPresent()) {
            return empty();
        }

        return Objects.requireNonNull(mapper.apply(value));
    }

    public <U> OptionalUtil<U> flatMapFallible(ThrowableFunction<? super T, OptionalUtil<U>, ?> mapper) {
        try {
            return Objects.requireNonNull(mapper.apply(get()));
        } catch (@SuppressWarnings("UseSpecificCatch") Throwable t) {
            return empty();
        }
    }

    public OptionalUtil<T> fillIfEmpty(OptionalUtil<T> fallback) {
        return isPresent() ? this : Objects.requireNonNull(fallback);
    }

    public OptionalUtil<T> supplyIfEmpty(Supplier<OptionalUtil<T>> supplier) {
        return isPresent() ? this : Objects.requireNonNull(supplier.get());
    }

    @Override
    public String toString() {
        return isPresent() ? String.format("OptionalUtil[%s]", value) : "OptionalUtil.empty";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof OptionalUtil)) {
            return false;
        }

        return Objects.equals(value, ((OptionalUtil<?>) obj).value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}

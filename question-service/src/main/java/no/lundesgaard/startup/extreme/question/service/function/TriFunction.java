package no.lundesgaard.startup.extreme.question.service.function;

public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

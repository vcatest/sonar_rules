import java.util.concurrent.atomic.AtomicInteger;

public class RSPEC_7629 {
    public void gathererExample() {
        Gatherer<Integer, AtomicInteger, Integer> gatherer = Gatherer.ofSequential(
            () -> new AtomicInteger(-1),
            (state, number, downstream) -> {
                if (state.get() < 0) {
                    state.set(number);
                    return true;
                }
                return downstream.push(number - state.get());
            },
            Gatherer.defaultFinisher() // Noncompliant: useless finisher
        );
    }
}

// Dummy Gatherer class for illustration
class Gatherer<T, S, R> {
    public static <T, S, R> Gatherer<T, S, R> ofSequential(
        java.util.function.Supplier<S> supplier,
        GathererProcessor<S, T, R> processor,
        java.util.function.Function<S, R> finisher
    ) {
        return new Gatherer<>();
    }
    public static <T, S, R> java.util.function.Function<S, R> defaultFinisher() {
        return s -> null;
    }
}

interface GathererProcessor<S, T, R> {
    boolean process(S state, T value, Downstream<R> downstream);
}

interface Downstream<R> {
    boolean push(R value);
}

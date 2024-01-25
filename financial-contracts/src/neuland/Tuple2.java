package neuland;

public record Tuple2<A, B>(A first, B second) {
    static <A, B>  Tuple2<A, B>of(A first, B second) {
        return new Tuple2<>(first, second);
    }
}

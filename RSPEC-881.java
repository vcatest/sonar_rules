public class RSPEC_881 {
    public void example() {
        int u8a, u8b = 1, u8c = 2;
        int foo, bar = 4;
        u8a = ++u8b + u8c--; // Noncompliant
        foo = bar++ / 4;     // Noncompliant
    }
}

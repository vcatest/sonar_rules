public class RSPEC_864 {
    public void operatorPrecedenceExample() {
        int a = 1, b = 2, c = 3, x, y;
        x = a + b - c;
        x = a + 1 << b;  // Noncompliant
        y = a == b ? a * 2 : a + b;  // Noncompliant

        if (a > b || c < d || a == d) { /* ... */ }
        if (a > b && c < d || a == b) { /* Noncompliant */ }
        if ((a = f(b, c)) == 1) { /* Noncompliant; == evaluated first */ }
    }

    private int d = 4;
    private int f(int b, int c) { return b + c; }
}

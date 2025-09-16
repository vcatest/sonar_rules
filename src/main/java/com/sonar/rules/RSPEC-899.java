import java.io.File;
import java.util.concurrent.locks.Lock;

public class RSPEC_899 {
    public void doSomething(File file, Lock lock) {
        file.delete();  // Noncompliant
        // ...
        lock.tryLock(); // Noncompliant
    }
}

public class RSPEC_7610 {
    public void onCreate() {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        Log.i("LoginAttempt", "Login attempt: " + username + " with password: " + password); // Noncompliant
    }
    
    // Mock classes for compilation
    private EditText findViewById(int id) { return new EditText(); }
    
    static class EditText {
        public Text getText() { return new Text(); }
        static class Text {
            public String toString() { return "mockText"; }
        }
    }
    
    static class R {
        static class id {
            static final int username = 1;
            static final int password = 2;
        }
    }
    
    static class Log {
        public static void i(String tag, String message) {
            System.out.println(tag + ": " + message);
        }
    }
}

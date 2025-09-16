public class RSPEC_7606 {
    public void onCreate() {
        WebView aWebView = new WebView(this);
        aWebView.getSettings().setJavaScriptEnabled(true);
        setContentView(aWebView);

        String name = getIntent().getStringExtra("name");
        if (name == null) {
            name = "Guest";
        }

        aWebView.evaluateJavascript("greeting('" + name + "')", null); // Noncompliant
    }
    
    // Mock methods and classes
    private Intent getIntent() { return new Intent(); }
    private void setContentView(WebView view) { }
    
    static class WebView {
        public WebView(Object context) { }
        public Settings getSettings() { return new Settings(); }
        public void evaluateJavascript(String script, Object callback) { }
        
        static class Settings {
            public void setJavaScriptEnabled(boolean enabled) { }
        }
    }
    
    static class Intent {
        public String getStringExtra(String name) { return "mockValue"; }
    }
}

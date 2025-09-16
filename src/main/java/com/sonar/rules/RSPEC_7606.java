import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

public class RSPEC_7606 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView aWebView = new WebView(this);
        aWebView.getSettings().setJavaScriptEnabled(true);
        setContentView(aWebView);

        String name = getIntent().getStringExtra("name");
        if (name == null) {
            name = "Guest";
        }

        aWebView.evaluateJavascript("greeting('" + name + "')", null); // Noncompliant
    }
}

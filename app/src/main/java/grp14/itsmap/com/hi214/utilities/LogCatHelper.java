package grp14.itsmap.com.hi214.utilities;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LogCatHelper {

    private final String processId = Integer.toString(android.os.Process.myPid());

    private TextView textView;
    private String tag;

    public void init(TextView textView, String tag) {
        this.textView = textView;
        this.tag = tag;

        try {
            Runtime.getRuntime().exec("logcat");
        } catch (Exception e) {
            Log.d(tag, e.toString());
        }
    }

    public void clearLogcat() {
        try {
            Runtime.getRuntime().exec("logcat -c");
        } catch (Exception e) {
            Log.d(tag, e.toString());
            setTextView(e.toString());
        }
    }

    public void postLogcat() {
        clearLogcat();

        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.contains(processId) && line.contains(tag)) {
                    log.append(line);
                    log.append("\n");
                }
            }

            if(textView != null) {
                setTextView(log.toString());
            } else {
                Log.d(tag, "The textview was null");
            }
        }
        catch (Exception e) {
            Log.d(tag, e.toString());
            setTextView(e.toString());
        }

        clearLogcat();
    }

    private void setTextView(String text) {
        if(textView != null) {
            textView.append(text);
        }
    }
}
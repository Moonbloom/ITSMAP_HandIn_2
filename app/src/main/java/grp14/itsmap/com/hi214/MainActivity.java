package grp14.itsmap.com.hi214;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private String TAG = "ITSMAP - HI2";
    private String stateTag = "itsmap_save_restore_text";

    private TextView logcatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        logcatTextView = (TextView) findViewById(R.id.logcat_textview);

        createLogAndToast(getString(R.string.on_create));
    }

    @Override
    protected void onStart() {
        super.onStart();

        createLogAndToast(getString(R.string.on_start));
    }

    @Override
    protected void onResume() {
        super.onResume();

        createLogAndToast(getString(R.string.on_resume));
    }

    @Override
    protected void onPause() {
        super.onPause();

        createLogAndToast(getString(R.string.on_pause));
    }

    @Override
    protected void onStop() {
        super.onStop();

        createLogAndToast(getString(R.string.on_stop));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        createLogAndToast(getString(R.string.on_destroy));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        createLogAndToast(getString(R.string.on_restart));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String text = logcatTextView.getText().toString();
        outState.putString(stateTag, text);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String text = savedInstanceState.getString(stateTag);
        logcatTextView.setText(text);
    }

    private void createLogAndToast(String text) {
        Log.d(TAG, text);
        logcatTextView.append(text);
        logcatTextView.append("\n");

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        } else { //Show different toast on API 8 devices and below
            Toast.makeText(this, getString(R.string.old_phone_toast), Toast.LENGTH_SHORT).show();
        }
    }
}
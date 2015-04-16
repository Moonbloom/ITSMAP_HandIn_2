package grp14.itsmap.com.hi214;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import grp14.itsmap.com.hi214.utilities.LogCatHelper;

public class MainActivity extends Activity {

    private String TAG = "ITSMAP - HI2";
    private String stateTag = "itsmap_save_restore_text";

    private LogCatHelper logCatHelper = new LogCatHelper();

    private TextView logcatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        logcatTextView = (TextView) findViewById(R.id.logcat_textview);
        logCatHelper.init(logcatTextView, TAG);

        createLog(getString(R.string.on_create));
        createToast(getString(R.string.state_launched));
    }

    @Override
    protected void onStart() {
        super.onStart();

        createLog(getString(R.string.on_start));
    }

    @Override
    protected void onResume() {
        super.onResume();

        createLog(getString(R.string.on_resume));
        createToast(getString(R.string.state_running));
    }

    @Override
    protected void onPause() {
        super.onResume();

        createLog(getString(R.string.on_pause));
    }

    @Override
    protected void onStop() {
        super.onResume();

        createLog(getString(R.string.on_stop));
    }

    @Override
    protected void onDestroy() {
        super.onResume();

        createLog(getString(R.string.on_destroy));
        createToast(getString(R.string.state_destroyed));
    }

    @Override
    protected void onRestart() {
        super.onResume();

        createLog(getString(R.string.on_restart));
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
        logcatTextView.append(text);
    }

    private void createLog(String text) {
        Log.d(TAG, text);
        logCatHelper.postLogcat();
    }

    private void createToast(String text) {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        } else { //Show different toast on API 8 devices and below
            Toast.makeText(this, getString(R.string.old_phone_toast), Toast.LENGTH_LONG).show();
        }
    }
}
package grp14.itsmap.com.hi214;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import grp14.itsmap.com.hi214.utilities.LogCatHelper;

public class MainActivity extends Activity {

    public static String TAG = "ITSMAP - HI2";

    private LogCatHelper logCatHelper = new LogCatHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        TextView logcatTextView = (TextView) findViewById(R.id.logcat_textview);
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

    private void createLog(String text) {
        Log.d(TAG, text);
        logCatHelper.postLogcat();
    }

    @SuppressWarnings("deprecation")
    private void createToast(String text) {
        //Need to use Build.VERSION.SDK as SDK_INT wasn't introduced until API level 4, and we support API level 3. Otherwise it'd throw an exception when run on API Level 3 devices.
        int version = Integer.valueOf(Build.VERSION.SDK);

        //Normal on non-API 3 devices
        if(version > 3) {
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        } else { //Show different toast on API 3 devices and below
            Toast.makeText(this, getString(R.string.old_phone_toast), Toast.LENGTH_LONG).show();
        }
    }
}
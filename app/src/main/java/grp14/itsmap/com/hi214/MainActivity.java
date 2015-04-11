package grp14.itsmap.com.hi214;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

    private String TAG = "Lifecycle log: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, getString(R.string.on_create));

        createToast(getString(R.string.state_launched));

        setContentView(R.layout.main_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, getString(R.string.on_start));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, getString(R.string.on_resume));

        createToast(getString(R.string.state_running));
    }

    @Override
    protected void onPause() {
        super.onResume();

        Log.d(TAG, getString(R.string.on_pause));
    }

    @Override
    protected void onStop() {
        super.onResume();

        Log.d(TAG, getString(R.string.on_stop));
    }

    @Override
    protected void onDestroy() {
        super.onResume();

        Log.d(TAG, getString(R.string.on_destroy));

        createToast(getString(R.string.state_destroyed));
    }

    @Override
    protected void onRestart() {
        super.onResume();

        Log.d(TAG, getString(R.string.on_restart));
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
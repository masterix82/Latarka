package eu.dekert.latarka;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    //////Deklaracja zmiennych//////////////////////////////////////////////////////////////////////
    public static Camera Aparat = null;
    private boolean stan;
    Button przycisk;
    ////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //stan latarki wylaczona
        stan = false;
        przycisk = (Button)findViewById(R.id.button);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////


//wlaczenie latarki
    public void flashLightOn() {

        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                Aparat = Camera.open();
                Parameters p = Aparat.getParameters();
                p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                Aparat.setParameters(p);
                Aparat.startPreview();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Nie obsugiwany wyjatek flashLightOn()",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //wylaczenie latarki
    public void flashLightOff() {
        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                Aparat.stopPreview();
                Aparat.release();
                Aparat = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "Nie obsugiwany wyjatek flashLightOff",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //sterowanie latarka
    public void Lampka (View view) {

        if (stan) {
            flashLightOff();
            stan = false;
            przycisk.setText("Włącz");

        }
        else {
            flashLightOn();
            stan = true;
            przycisk.setText("Wyłącz");

        }


    }


}

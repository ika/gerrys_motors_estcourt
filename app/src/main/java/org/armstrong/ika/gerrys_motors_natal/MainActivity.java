package org.armstrong.ika.gerrys_motors_natal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends ActionBarMenuActivity {

    public static int backButtonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setTitle(R.string.app_name);

    }

    public void resetBackButton() {
        backButtonCount = 0;
    }

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            backButtonCount = 0;
        } else {
            ToastMe("Press the back button once again to close the application.");
            backButtonCount++;
        }

    }

    public void ToastMe(String toast) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }

}

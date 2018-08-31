package org.armstrong.ika.gerrys_motors_natal;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ika on 3/23/17.
 */

public class ActionBarMenuActivity extends AppCompatActivity {

    private String TAG = "LOGGING: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Intent a = new Intent(this, MainActivity.class);
                startActivity(a);
                break;

            case R.id.menu_about:

                String CONTENT = LoadAsset("contact.txt");

                final Dialog dialog;
                dialog = new Dialog(this);
                dialog.setContentView(R.layout.activity_about);
                dialog.setTitle("Contact Details");

                TextView text = (TextView) dialog.findViewById(R.id.ListTextNr);
                text.setText(CONTENT);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;

            default:
                break;
        }
        return true;
    }

    public String LoadAsset(String inputFile) {

        String mAsset = null;

        try {
            InputStream stream = getAssets().open(inputFile);

            int size = stream.available();

            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();

            mAsset = new String(buffer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return mAsset;

    }


}

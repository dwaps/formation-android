package fr.dwaps.formationandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends Activity {
    final String FILENAME = "mon-fichier.txt";

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
    }

    public void save(View v) throws Exception {
        FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);

        if (fos != null) {
            fos.write(et.getText().toString().getBytes());
            fos.close();
        }
    }

    public void load(View v) throws Exception {
        FileInputStream fis = openFileInput(FILENAME);
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[1024];

        if (fis != null) {
            while(fis.read(buffer) != -1) {
                sb.append(new String(buffer));
            }

            et.setText(sb.toString());
            fis.close();
        }
    }

    public void delete(View v) {
    }
}

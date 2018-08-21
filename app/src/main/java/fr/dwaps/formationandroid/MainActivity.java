package fr.dwaps.formationandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String PREF_FILE_INFOS = "infos";
    private static final String PREF_FILE_THEME = "theme";
    private final String PREF_COLOR = "Ma couleur";
    private final String PREF_AGE = "Mon age";
    private final String PREF_EMAIL = "Mon email";

    SharedPreferences prefInfos, prefTheme;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefInfos = getSharedPreferences(PREF_FILE_INFOS, MODE_PRIVATE);
        prefTheme = getSharedPreferences(PREF_FILE_THEME, MODE_PRIVATE);
        et = findViewById(R.id.et);
    }

    public void save(View v) {
        prefTheme.edit().putString(PREF_COLOR, et.getText().toString()).apply();
    }

    public void load(View v) {
        et.setText(prefTheme.getString(PREF_COLOR, ""));
    }

    public void delete(View v) {
        prefTheme.edit().remove(PREF_COLOR).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        prefTheme.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                et.setText("La préférence a bien été mise à jour");
            }
        });
    }
}

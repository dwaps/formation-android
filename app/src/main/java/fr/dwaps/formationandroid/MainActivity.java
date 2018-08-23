package fr.dwaps.formationandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStoragePublicDirectory;
import static android.os.Environment.getExternalStorageState;

public class MainActivity extends Activity {
    final String LOG_TAG = "DWAPS FORMATION";
    final String FILENAME = "mon-fichier.txt";

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
    }

    public void save(View v) {
        String storageState = getExternalStorageState();

        switch (storageState) {
            case Environment.MEDIA_MOUNTED:
                File extDir = getExternalFilesDir(null); // A la racine du dossier files de l'app
                File rootDir = getExternalStorageDirectory();
                File publicRootDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

                FileWriter writer = null;
                File dir = new File(publicRootDir.getAbsolutePath()+"/DossierTest");
                dir.mkdirs();
                File f = new File(dir, "hello.txt");

                try {
                    writer = new FileWriter(f);
                    writer.write("Salut les cocos !");
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
            case Environment.MEDIA_MOUNTED_READ_ONLY:
                // TODO
                break;
            case Environment.MEDIA_REMOVED:
                // TODO
                break;
            default:
        }
    }

    public void load(View v) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILENAME);
            fis.read();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Fichier inexistant !", Toast.LENGTH_SHORT).show();
        }
    }

    public void delete(View v) {
        String dir = getFilesDir().getAbsolutePath();
        File file = new File(dir, FILENAME);
        if (file.delete()) {
            Toast.makeText(this, "Suppression du fichier", Toast.LENGTH_SHORT).show();
        }
    }
}

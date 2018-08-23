package fr.dwaps.formationandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import fr.dwaps.formationandroid.dao.ContactDAO;
import fr.dwaps.formationandroid.model.Contact;

public class MainActivity extends Activity {
    final String LOG_TAG = "DWAPS FORMATION";

    private Contact contact;

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);

        contact = new Contact("Daphné", "Cornillon", "0651279211", "contact@dwaps.fr", 29);
    }

    public void save(View v) {
        ContactDAO dao = new ContactDAO(this);
        dao.openWritableDB();
        dao.create(contact);
        dao.closeDB();
    }

    public void load(View v) {
        ContactDAO dao = new ContactDAO(this);
        dao.openReadableDB();
        Contact contact = dao.find(1);
        dao.closeDB();
        if (contact != null) {
            et.setText(contact.getFirstname() + " a " + contact.getAge() + " ans.");
        }
    }

    public void delete(View v) {
        ContactDAO dao = new ContactDAO(this);
        dao.openWritableDB();
        dao.removeDB();
    }

    public void gotoDBActivity(View v) {
        Intent intent = new Intent(this, AndroidDatabaseManager.class);
        startActivity(intent);
    }
}

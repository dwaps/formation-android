package fr.dwaps.formationandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import fr.dwaps.formationandroid.model.Contact;

import static fr.dwaps.formationandroid.utils.Constants.*;

public class ContactDAO {
    private SQLiteDatabase db;
    private ContactDBHelper ContactBDD;

    public ContactDAO(Context ctx) {
        ContactBDD = new ContactDBHelper(ctx, DB_CONTACT_NAME, null, 1);
    }

    public void openWritableDB() { db = ContactBDD.getWritableDatabase(); }
    public void openReadableDB() { db = ContactBDD.getReadableDatabase(); }
    public void closeDB() { db.close(); }
    public void removeDB() { ContactBDD.onUpgrade(db, 1, 1); }

    public long create(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(TABLE_CONTACT_COL_FIRSTNAME, contact.getFirstname());
        cv.put(TABLE_CONTACT_COL_LASTNAME, contact.getLastname());
        cv.put(TABLE_CONTACT_COL_TEL, contact.getTel());
        cv.put(TABLE_CONTACT_COL_EMAIL, contact.getEmail());
        cv.put(TABLE_CONTACT_COL_AGE, contact.getAge());
        return db.insert(TABLE_CONTACT_NAME, null, cv);
    }

    public Contact find(int id) {
        Cursor cursor = db.query(
            TABLE_CONTACT_NAME,
            new String[] {
                TABLE_CONTACT_COL_ID,
                TABLE_CONTACT_COL_FIRSTNAME,
                TABLE_CONTACT_COL_LASTNAME,
                TABLE_CONTACT_COL_TEL,
                TABLE_CONTACT_COL_EMAIL,
                TABLE_CONTACT_COL_AGE
            },
            TABLE_CONTACT_COL_ID + "="+id,
            null, null, null, null);
        return cursorToContact(cursor);
    }

    public ArrayList<Contact> findAll() {
        Cursor cursor = db.query(
            TABLE_CONTACT_NAME,
            new String[] {
                TABLE_CONTACT_COL_ID,
                TABLE_CONTACT_COL_FIRSTNAME,
                TABLE_CONTACT_COL_LASTNAME,
                TABLE_CONTACT_COL_TEL,
                TABLE_CONTACT_COL_EMAIL,
                TABLE_CONTACT_COL_AGE
            },
            null, null, null, null,
            TABLE_CONTACT_COL_FIRSTNAME
        );

        return cursorToContacts(cursor);
    }

    public int update(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(TABLE_CONTACT_COL_FIRSTNAME, contact.getFirstname());
        cv.put(TABLE_CONTACT_COL_LASTNAME, contact.getLastname());
        cv.put(TABLE_CONTACT_COL_EMAIL, contact.getEmail());
        cv.put(TABLE_CONTACT_COL_TEL, contact.getTel());
        cv.put(TABLE_CONTACT_COL_AGE, contact.getAge());
        return db.update(TABLE_CONTACT_NAME, cv, TABLE_CONTACT_COL_ID+"="+contact.getId(), null);
    }

    public int remove(int id) {
        return db.delete(TABLE_CONTACT_NAME, TABLE_CONTACT_COL_ID+"="+id, null);
    }

    private Contact cursorToContact(Cursor cursor) {
        if (cursor.getCount() < 1) {
            cursor.close();
            return new Contact();
        }

        cursor.moveToFirst();
        Contact contact = new Contact(
            cursor.getInt(TABLE_CONTACT_NUM_COL_ID),
            cursor.getString(TABLE_CONTACT_NUM_COL_FIRSTNAME),
            cursor.getString(TABLE_CONTACT_NUM_COL_LASTNAME),
            cursor.getString(TABLE_CONTACT_NUM_COL_TEL),
            cursor.getString(TABLE_CONTACT_NUM_COL_EMAIL),
            cursor.getInt(TABLE_CONTACT_NUM_COL_AGE)
        );

        cursor.close();
        return contact;
    }

    private ArrayList<Contact> cursorToContacts(Cursor cursor) {
        if (cursor.getCount() == 0) {
            cursor.close();
            return null;
        }

        cursor.moveToFirst();
        ArrayList<Contact> contacts = new ArrayList<>();

        while (cursor.moveToNext()) {
            Contact c = new Contact(
                cursor.getInt(TABLE_CONTACT_NUM_COL_ID),
                cursor.getString(TABLE_CONTACT_NUM_COL_FIRSTNAME),
                cursor.getString(TABLE_CONTACT_NUM_COL_LASTNAME),
                cursor.getString(TABLE_CONTACT_NUM_COL_TEL),
                cursor.getString(TABLE_CONTACT_NUM_COL_EMAIL),
                cursor.getInt(TABLE_CONTACT_NUM_COL_AGE)
            );
            contacts.add(c);
        }

        cursor.close();
        return contacts;
    }
}

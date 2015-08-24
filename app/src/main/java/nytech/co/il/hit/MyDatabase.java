package nytech.co.il.hit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by top on 08/08/2015.
 */
public class MyDatabase {

    public static final String MYTAG = "my_debug_tag";
    private static final String DEBUG_TAG = "PasswordDatabase";
    private static final String KEYֹROWID = "_id";
    private static final String KEY_ALIAS = "alias";
    private static final String KEY_USER_NAME = "user_Name";
    private static final String KEY_PASSWORD = "password";

    public static final String DATABASE_NAME = "passwordDb";
    public static final String DATABASE_TABLE = "passwordTable";
    public static final int DATABASE_VERSION = 1;

    public PasswordDatabase ourHelper;
    private final Context ourContext;
    public SQLiteDatabase ourDatabase;


    private static class PasswordDatabase extends SQLiteOpenHelper {

        public PasswordDatabase(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /*create our table DB */
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEYֹROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_ALIAS + " TEXT NOT NULL, " +
                            KEY_USER_NAME + " TEXT NOT NULL, " +
                            KEY_PASSWORD + " TEXT NOT NULL);"
            );

        }

        /*upgrade our table if exists */
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public MyDatabase(Context c) {
        ourContext = c;
    }

    /* open our DB for writing */
    public MyDatabase open() {
        ourHelper = new PasswordDatabase(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    /* close our DB     */
    public void close() {
        ourHelper.close();
    }

    /* Entry point get DATA from the user and add to DB */
    public long createEntry(String alias, String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ALIAS, alias);
        cv.put(KEY_USER_NAME, username);
        cv.put(KEY_PASSWORD, password);
        Log.d(DEBUG_TAG, "full Entry");
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    /* get all DATA from the DB and show on screen */
    public String[] getAllData() {
        String[] columns = new String[]{KEYֹROWID, KEY_ALIAS, KEY_USER_NAME, KEY_PASSWORD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        String[] result = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
        int iRow = c.getColumnIndex(KEYֹROWID);
        int iAlias = c.getColumnIndex(KEY_ALIAS);
        int iUsername = c.getColumnIndex(KEY_USER_NAME);
        int iPassword = c.getColumnIndex(KEY_PASSWORD);
        int i = 0;
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            result[i] = c.getString(iRow) + ". " + "Alias : " + " " + c.getString(iAlias) + "\n    User Name :" +
                    " " + c.getString(iUsername) + "\n    Password :" + " " + c.getString(iPassword);
            i = i + 1;
        }

        return result;
    }

    public String getUsername(String alias) {
        String[] columns = new String[]{KEYֹROWID, KEY_ALIAS, KEY_USER_NAME, KEY_PASSWORD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ALIAS + "=?",
                new String[]{alias}, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            String username = c.getString(2);
            c.close();
            return username;
        }

        c.close();
        return " ";
    }

    public String getPassword(String alias) {
        String[] columns = new String[]{KEYֹROWID, KEY_ALIAS, KEY_USER_NAME, KEY_PASSWORD};
        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ALIAS + "=?",
                new String[]{alias}, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            String password = c.getString(3);
            c.close();
            return password;
        }

            return null;
        }


    public void delAlias(String alias) {
        String whereClause = KEY_ALIAS + "=?";
        String[] whereArgs = new String[]{String.valueOf(alias)};
        ourDatabase.delete(DATABASE_TABLE, whereClause, whereArgs);
        }


    }








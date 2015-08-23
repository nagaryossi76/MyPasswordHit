package nytech.co.il.hit;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by top nagar on 22/08/2015.
 *
 */
public class MyContentProvider extends ContentProvider {
    /* fields for my content provider */
    static final String PROVIDER_NAME = "il.co.nytech.provider";
    static final String URL = "content://" + PROVIDER_NAME + "/alias";
    static final Uri CONTENT_URI = Uri.parse(URL);

    /* fields for the database */
    static final String KEY_ROWID = "id";
    static final String KEY_ALIAS = "alias";
    static final String KEY_USER_NAME = "username";
    static final String KEY_PASSWORD = "password";

    /* integer values used in content URI */
    static final int ALIAS = 1;
    static final int ALIAS_ITEM = 2;

    /* our Helper to work with DB */
    DBHelper dbHelper;

    // projection map for a query
    private static HashMap<String, String> values;

    // maps content URI "patterns" to the integer values that were set above
    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "alias", ALIAS);
        uriMatcher.addURI(PROVIDER_NAME, "alias/#", ALIAS_ITEM);
    }

    /* database declarations */
    private SQLiteDatabase database;
    static final String DATABASE_NAME = "AliasDb";
    static final String TABLE_NAME = "AliasTable";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_ALIAS + " TEXT NOT NULL, " +
                    KEY_USER_NAME + " TEXT NOT NULL, " +
                    KEY_PASSWORD + " TEXT NOT NULL);";


    /* class that creates and manages the provider's database */
    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            Log.w(DBHelper.class.getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ". Old data will be destroyed");
            db.execSQL("DROP TABLE IF EXISTS " +  TABLE_NAME);
            onCreate(db);
        }
    }


    /* Implement  our Content Provider methods */
    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        Context context = getContext();
        dbHelper = new DBHelper(context);
        // permissions to be writable
        database = dbHelper.getWritableDatabase();

        if(database == null)
            return false;
        else
            return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        // the TABLE_NAME to query on
        queryBuilder.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            // maps all database column names
            case ALIAS:
                queryBuilder.setProjectionMap(values);
                break;
            case ALIAS_ITEM:
                queryBuilder.appendWhere( KEY_ROWID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == ""){
            // No sorting-> sort on alias by default
            sortOrder = KEY_ALIAS;
        }
        Cursor cursor = queryBuilder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        long row = database.insert(TABLE_NAME, "", values);

        // If record is added successfully
        if(row > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        int count = 0;

        switch (uriMatcher.match(uri)){
            case ALIAS:
                count = database.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            case ALIAS_ITEM:
                count = database.update(TABLE_NAME, values, KEY_ROWID  +
                        " = " + uri.getLastPathSegment() +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri );
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        int count = 0;

        switch (uriMatcher.match(uri)){
            case ALIAS:
                // delete all the records of the table
                count = database.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case ALIAS_ITEM:
                String id = uri.getLastPathSegment();	//gets the id
                count = database.delete( TABLE_NAME, KEY_ROWID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" +
                                selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;


    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        switch (uriMatcher.match(uri)){
            // Get all friend-birthday records
            case ALIAS:
                return "vnd.android.cursor.dir/vnd.example.alias";
            // Get a particular friend
            case ALIAS_ITEM:
                return "vnd.android.cursor.item/vnd.example.alias";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


}



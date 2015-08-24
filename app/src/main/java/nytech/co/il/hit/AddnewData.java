package nytech.co.il.hit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by top on 09/08/2015.
 */
public class AddnewData extends android.app.Fragment implements View.OnClickListener{

    EditText etNAlias, etNUserName, etNPassword;
    Button btNSaveItem;

    private static final String MYTAG = "my_debug_tag";

    public AddnewData() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_addnewdata, container, false);


        btNSaveItem = (Button) v.findViewById(R.id.btNSaveItem);
        etNAlias = (EditText)v.findViewById(R.id.etNAlias);
        etNUserName = (EditText)v.findViewById(R.id.etNUserName);
        etNPassword = (EditText)v.findViewById(R.id.etNPassword);

        btNSaveItem.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btNSaveItem:
                MyDatabase entry = new MyDatabase(getActivity());
                String alias = etNAlias.getText().toString();
                String username = etNUserName.getText().toString();
                String password = etNPassword.getText().toString();

                Log.d(MYTAG, "open my DB");
                entry.open();
                Log.d(MYTAG, "opened my DB");
                Log.d(MYTAG, "Enter my DB");
                entry.createEntry(alias, username, password);
                Log.d(MYTAG, "Entered my DB");
                entry.close();
                Log.d(MYTAG, "closed my DB");
                etNAlias.setText("");
                etNUserName.setText("");
                etNPassword.setText("");
                Toast.makeText(getActivity(), "New Alias " + alias + " Add to DB!",
                        Toast.LENGTH_SHORT).show();
                break;

                // Add a new birthday record
/*
                ContentValues values = new ContentValues();
                values.put(MyContentProvider.KEY_ALIAS, alias);
                values.put(MyContentProvider.KEY_USER_NAME,username);
                values.put(MyContentProvider.KEY_PASSWORD, password);

                Uri uri = getContentResolver().insert(
                        MyContentProvider.CONTENT_URI, values);

                Toast.makeText(getBaseContext(),
                        "NyTech: " + uri.toString()
                        + " inserted!", Toast.LENGTH_LONG).show(); */
        }
    }



}

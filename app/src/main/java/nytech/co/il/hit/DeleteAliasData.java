package nytech.co.il.hit;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by top on 19/08/2015.
 */
public class DeleteAliasData extends Fragment implements View.OnClickListener {
    EditText etDeleteAlias;
    Button btDeleteAlias;

    private static final String MYTAG = "my_debug_tag";

    public DeleteAliasData(){
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_deletealiasdata, container, false);

        btDeleteAlias = (Button) v.findViewById(R.id.btDeleteAlias);
        etDeleteAlias = (EditText)v.findViewById(R.id.etDeleteAlias);

        btDeleteAlias.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btDeleteAlias:
                try {
                    MyDatabase delitem = new MyDatabase(getActivity());
                    /* get alias to delete from the DB*/
                    String alias = etDeleteAlias.getText().toString();
                    Log.d(MYTAG, "open my DB");
                    delitem.open();
                    Log.d(MYTAG, "opened my DB");
                    Log.d(MYTAG, "praper to delete from my DB");
                    delitem.delAlias(alias);
                    Log.d(MYTAG, "Entered my DB");
                    delitem.close();
                    Log.d(MYTAG, "closed my DB");
                    etDeleteAlias.setText("");

                    Toast.makeText(getActivity(), "Your Alias " + alias + " Remove From DB!",
                            Toast.LENGTH_SHORT).show();
                    break;
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(e.getMessage())
                            .setTitle("invalid! , Wrong Alias")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
        }
    }
}


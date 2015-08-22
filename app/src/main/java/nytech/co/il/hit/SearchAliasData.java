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
import android.widget.TextView;

/**
 * Created by top on 19/08/2015.
 */
public class SearchAliasData extends Fragment implements View.OnClickListener{

    EditText etSearchAlias;
    Button btsearchAlias;
    TextView txtAlias, txtUsername, txtPassword;

    private static final String MYTAG = "my_debug_tag";

    public SearchAliasData() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_searchaliasdata, container, false);

        btsearchAlias = (Button) v.findViewById(R.id.btsearchAlias);
        etSearchAlias = (EditText)v.findViewById(R.id.etSearchAlias);
        txtAlias = (TextView)v.findViewById(R.id.txtAlias);
        txtUsername = (TextView)v.findViewById(R.id.txtUsername);
        txtPassword = (TextView)v.findViewById(R.id.txtPassword);

        btsearchAlias.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btsearchAlias:
                try {
                    MyDatabase getAlias = new MyDatabase(getActivity());
                    /* get alias to search from the user*/
                    String alias = etSearchAlias.getText().toString();

                    String returnUsername, returnPassword;
                    Log.d(MYTAG, "open my DB");
                    getAlias.open();
                    Log.d(MYTAG, "opened my DB");
                    Log.d(MYTAG, "get my User Name from my DB");
                    /* get user name of the alias*/
                    returnUsername = getAlias.getUsername(alias);
                    Log.d(MYTAG, "get my Password from my DB");
                    /* get password of the alias*/
                    returnPassword = getAlias.getPassword(alias);
                    Log.d(MYTAG, "Entered my DB");
                    getAlias.close();
                    Log.d(MYTAG, "closed my DB");
                    /* print details of the alias on screen*/
                    txtAlias.setText("Alias: " + alias);
                    txtUsername.setText("User Name: " + returnUsername);
                    txtPassword.setText("Password: " + returnPassword);
                    Log.d(MYTAG, "print details of the alias on scree");
                    break;
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("try agin, your Alias is invalid!")
                            .setTitle("invalid! , Wrong Alias")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
        }
    }
}
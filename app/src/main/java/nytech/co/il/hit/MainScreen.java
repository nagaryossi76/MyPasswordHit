package nytech.co.il.hit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;


public class MainScreen extends Activity {



    private static final String MYTAG = "my_debug_tag";
    public Button btNew, btEdit, btSearch, btShow, btDelete, btLogoff, btNSaveItem;



    View.OnClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* display our main screen */
        setContentView(R.layout.activity_main_screen);

        /* initialize our buttons*/
        btNew = (Button)findViewById(R.id.btNew);
        btDelete = (Button)findViewById(R.id.btDel);
        btEdit = (Button)findViewById(R.id.btEdit);
        btSearch = (Button)findViewById(R.id.btSearch);
        btShow = (Button)findViewById(R.id.btShow);
        btLogoff = (Button)findViewById(R.id.btLogoff);

        listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /* make switches for the all main buttons*/
                switch (v.getId()){
                    case R.id.btNew:
                        /* this part replace Fragment to NewItem Fragment */
                 /*       Fragment frNew;
                        frNew = new AddnewData();
                        FragmentManager fmNew = getFragmentManager();
                        FragmentTransaction ftNew = fmNew.beginTransaction();
                        ftNew.replace(R.id.frscreen, frNew);
                        ftNew.commit();
                        Log.d(MYTAG, "new Fragment AddnewData"); */

                        ContentValues values = new ContentValues();
                        values.put(MyContentProvider.KEY_ALIAS, "Bank Poalim");
                        values.put(MyContentProvider.KEY_USER_NAME, "my user");
                        values.put(MyContentProvider.KEY_PASSWORD, "my password");
                        Uri uri = getContentResolver().insert
                                (MyContentProvider.CONTENT_URI, values);
                        Toast.makeText(getBaseContext(), "New record inserted"
                                , Toast.LENGTH_LONG)
                                .show();



                        break;
                    case R.id.btEdit:

                        break;
                    case R.id.btDel:
                         /* this part replace Fragment to Search Alias Fragment */
                        Fragment frDel;
                        frDel = new DeleteAliasData();
                        FragmentManager fmDel = getFragmentManager();
                        FragmentTransaction ftDel = fmDel.beginTransaction();
                        ftDel.replace(R.id.frscreen, frDel);
                        ftDel.commit();
                        Log.d(MYTAG, "new Fragment Delete Alias");


                        break;
                    case R.id.btSearch:
                        /* this part replace Fragment to Search Alias Fragment */
                        Fragment frSearch;
                        frSearch = new SearchAliasData();
                        FragmentManager fmSearch = getFragmentManager();
                        FragmentTransaction ftSearch = fmSearch.beginTransaction();
                        ftSearch.replace(R.id.frscreen, frSearch);
                        ftSearch.commit();
                        Log.d(MYTAG, "new Fragment Search Alias");

                        break;
                    case R.id.btShow:
                        /* this part replace Fragment to DataShow Fragment */
                        Fragment frShow;
                        frShow = new DataShow();
                        FragmentManager fmShow = getFragmentManager();
                        FragmentTransaction ftShow = fmShow.beginTransaction();
                        ftShow.replace(R.id.frscreen, frShow);
                        ftShow.commit();
                        Log.d(MYTAG, "new Fragment ShowData");

                        break;
                    case R.id.btLogoff:
                        Log.d(MYTAG, "see you ...");
                        ParseUser.logOut();
                        // this will now be null
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        break;
                }
            }
        };

        btNew.setOnClickListener(listener);
        btEdit.setOnClickListener(listener);
        btDelete.setOnClickListener(listener);
        btSearch.setOnClickListener(listener);
        btShow.setOnClickListener(listener);
        btLogoff.setOnClickListener(listener);
    }
 }







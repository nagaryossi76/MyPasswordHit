package nytech.co.il.hit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.ParseUser;


public class MainScreen extends Activity {



    private static final String MYTAG = "my_debug_tag";
    public Button  btEdit,  btShow, btNSaveItem;
    public ImageButton btSearch, btNew, btDelete, btLogoff;



    View.OnClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* display our main screen */
        setContentView(R.layout.activity_main_screen);

        /* initialize our buttons*/
        btNew = (ImageButton)findViewById(R.id.btNew);
        btDelete = (ImageButton)findViewById(R.id.btDel);
        btEdit = (Button)findViewById(R.id.btEdit);
        btSearch = (ImageButton)findViewById(R.id.btSearch);
        btShow = (Button)findViewById(R.id.btShow);
        btLogoff = (ImageButton)findViewById(R.id.btLogoff);

        listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /* make switches for the all main buttons*/
                switch (v.getId()){
                    case R.id.btNew:
                        /* this part replace Fragment to NewItem Fragment */
                        Fragment frNew;
                        frNew = new AddnewData();
                        addFragment(frNew);


                    /*    ContentValues values = new ContentValues();
                        values.put(MyContentProvider.KEY_ALIAS, "Bank Poalim");
                        values.put(MyContentProvider.KEY_USER_NAME, "my user");
                        values.put(MyContentProvider.KEY_PASSWORD, "my password");
                        Uri uri = getContentResolver().insert
                                (MyContentProvider.CONTENT_URI, values);
                        Toast.makeText(getBaseContext(), "New record inserted"
                                , Toast.LENGTH_LONG)
                                .show();  */



                        break;
                    case R.id.btEdit:

                        break;
                    case R.id.btDel:
                         /* this part replace Fragment to Search Alias Fragment */
                        Fragment frDel;
                        frDel = new DeleteAliasData();
                        addFragment(frDel);
                        break;
                    case R.id.btSearch:
                        /* this part replace Fragment to Search Alias Fragment */
                        Fragment frSearch;
                        frSearch = new SearchAliasData();
                        addFragment(frSearch);
                        break;
                    case R.id.btShow:
                        /* this part replace Fragment to DataShow Fragment */
                        Fragment frShow;
                        frShow = new DataShow();
                        addFragment(frShow);
                        break;
                    case R.id.btLogoff:
                        Log.d(MYTAG, "see you ...");
                        Toast.makeText(MainScreen.this, "I Hope to See You Aagin!",
                                Toast.LENGTH_SHORT).show();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms
                                ParseUser.logOut();
                                // this will now be null
                                ParseUser currentUser = ParseUser.getCurrentUser();
                                finish();
                            }
                        }, 1500);



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
    /* add Fragment to main screen */
    private void addFragment(Fragment fragment) {
        FragmentManager fmNew = getFragmentManager();
        FragmentTransaction ftNew = fmNew.beginTransaction();
        ftNew.replace(R.id.frscreen, fragment);
        ftNew.commit();
        Log.d(MYTAG, "new Fragment!!!");
    }
}







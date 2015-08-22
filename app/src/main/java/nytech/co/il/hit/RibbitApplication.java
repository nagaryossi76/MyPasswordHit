package nytech.co.il.hit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by top on 02/08/2015.
 * Hello GitHub :) ...
 */
public class RibbitApplication extends Application {
    public static final String MYTAG = "my_debug_tag";
    @Override
    public void onCreate() {
        super.onCreate();
        //  Enable Local Datastore.
        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, "vBx79VjLMK5SjivsSEgYFyLWBhvhdi119sgAurBq",
                "jze2FLXLS7nTINqhKmxvaTgeedlhk8ZZ2tLAMjsB");



    }
}

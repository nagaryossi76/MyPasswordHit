package nytech.co.il.hit;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by top on 09/08/2015.
 */
public class DataShow extends Fragment {


    private static final String MYTAG = "my_debug_tag";
    ListView mListView;

    public DataShow(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        /* inflate the layout to our view */
        View v = inflater.inflate(R.layout.layout_showdata,container,false);

        /* order the list view and get data from our DB */
        mListView = (ListView)v.findViewById(R.id.listView);
        MyDatabase info = new MyDatabase(getActivity());
        Log.d(MYTAG, "open DB");
        info.open();
        Log.d(MYTAG, "call getAllData");
        String [] data = info.getAllData();
        info.close();
        Log.d(MYTAG, "create ArrayAdapter for ListView");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);
        Log.d(MYTAG, "return view off DataShow");
        return v;
    }
}

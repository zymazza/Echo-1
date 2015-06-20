package echopro.theechoapp.com.echo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chav on 6/17/2015.
 */
public class EchoListActivity extends Activity {
    ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        final ListView list = (ListView) findViewById(R.id.echo_list);
        events = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LiveEvents");
        query.whereGreaterThan("numEchoes", 10);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d("SUCCESS", "Retrieving objects");
                    for (int i = 0; i < objects.size(); i++){
                        events.add(new Event(objects.get(i).getObjectId(),
                                objects.get(i).getString("name"),
                                objects.get(i).getInt("numEchoes"),
                                objects.get(i).getString("when")));
                    }
                } else {
                    Log.d("FAILED", "Couldn't retrieve objects");

                }
            }
        });

        final EchoAdapter adapter = new EchoAdapter(this, events);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Event) list.getAdapter().getItem(position)).echo();

                adapter.notifyDataSetChanged();

            }
        });
    }

    private void setupEvents(){

    }
}

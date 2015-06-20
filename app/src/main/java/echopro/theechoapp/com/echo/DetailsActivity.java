package echopro.theechoapp.com.echo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by Chav on 6/16/2015.
 */
public class DetailsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        TextView event_name = (TextView) findViewById(R.id.event_name_details);
        final TextView where_txt = (TextView) findViewById(R.id.where_text_details);
        final TextView when_txt = (TextView) findViewById(R.id.when_text_details);
        final TextView description_txt = (TextView) findViewById(R.id.description_text_details);

        Intent i = getIntent();
        event_name.setText(i.getStringExtra("EVENTNAME_DETAILS"));
        String id =  i.getStringExtra("EVENTID");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LiveEvents");
        final ArrayList<String> cats = new ArrayList<>();

        // Retrieve the object by id
        Log.i("ECHO", id);
        query.getInBackground(id, new GetCallback<ParseObject>() {
            public void done(ParseObject event, ParseException e) {
                if (e == null) {
                    where_txt.setText(event.getString("address"));
                    when_txt.setText(event.getString("when"));
                    description_txt.setText(event.getString("description"));
                    if (event.getBoolean("alcohol")) cats.add("Alcohol");
                    if (event.getBoolean("casual")) cats.add("Casual");
                    if (!event.getBoolean("entryFee")) cats.add("Free");
                    if (event.getBoolean("music")) cats.add("Music");
                    if (event.getBoolean("outdoors")) cats.add("Outdoors");
                    if (event.getBoolean("sports")) cats.add("Sports");
                } else {
                    Log.i("ECHO", "Parse exception");
                    e.printStackTrace();
                }
            }
        });


        ListView list_cat = (ListView) findViewById(R.id.category_list);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                cats);
        list_cat.setAdapter(adapter);
    }
}

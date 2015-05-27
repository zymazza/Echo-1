package echopro.theechoapp.com.echo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;


public class EchoActivity extends ActionBarActivity {

    ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echo);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ZeSNF8z2CVizoQ5sqCzQ3Ovw8uaXGJpdeDYMQpG9", "XEwTGrjYCtuDKuQzA80J9EdG6qxCPGYX4CIEL8wc");

        final ListView list = (ListView) findViewById(R.id.echo_list);
        events = new ArrayList<>();
        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Event");
        query.whereGreaterThan("echoes", 10);
        query.findInBackground(new FindCallback<ParseObject>() {
           public void done(List<ParseObject> objects, ParseException e) {
               if (e == null) {
                   Log.d("SUCCESS", "Retrieving objects");
                   for (int i = 0; i < objects.size(); i++){
                       events.add(new Event(objects.get(i).getString("event_name"),
                               objects.get(i).getInt("echoes")));
                   }
               } else {
                   Log.d("FAILED", "Couldn't retrieve objects");

               }
           }
       });*/



        events.add(new Event("Lounge", 12));
        events.add(new Event("Dancefest", 200));
        events.add(new Event("President's cup", 5));
        events.add(new Event("Drag Ball", 20));
        events.add(new Event("Phi Delt Fraturday", 6));
        events.add(new Event("The Jug", 120));
        Event random = new Event("Old ass event", 50);


        /*ArrayAdapter<Event> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.echo_list_item, R.id.event_name, events);
        list.setAdapter(adapter);*/
        final EchoAdapter adapter = new EchoAdapter(this, events);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((Event) list.getAdapter().getItem(position)).echo();
                //list.setAdapter(adapter);
                //refresh();
                adapter.notifyDataSetChanged();

            }
        });

    }

    public void refresh(){
        for (int i = 0;i < events.size(); i++){
            ParseObject gameScore = new ParseObject("Event");
            gameScore.put("event_name", events.get(i).getEventName());
            gameScore.put("echoes", events.get(i).getEventEchoes());
            gameScore.saveInBackground();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_echo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

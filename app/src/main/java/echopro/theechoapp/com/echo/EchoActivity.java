package echopro.theechoapp.com.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;

public class EchoActivity extends ActionBarActivity {
    private FragmentTabHost mTabHost;
    ArrayList<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*
            WifiManager wifiMgr = (WifiManager) getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            int ip = wifiInfo.getIpAddress();
            String ipAddress = Formatter.formatIpAddress(ip);
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echo);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "E9jTFndZrFCSn94m6sE1EyWwW9BIRZCXYlXOfLLP", "LNmQM4dK86SQeGWGh0TxvgujzCKYIOsesWIilury");

        // Create the tabs in main_fragment.xml
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        TabHost.TabSpec home = mTabHost.newTabSpec("Home");
        TabHost.TabSpec create = mTabHost.newTabSpec("Create");
        TabHost.TabSpec more = mTabHost.newTabSpec("More");

        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator("Home"),
                EchoListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("create").setIndicator("Create"),
                CreateFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("more").setIndicator("More"),
                MoreFragment.class, null);


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

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
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_echo, menu);
        try {
            //this.getActionBar().setLogo(R.drawable.ic_echo);
            this.getSupportActionBar().setLogo(R.drawable.logo);
        } catch (NullPointerException n){
            Log.i("ECHOFAIL", "Failed to insert logo. Null pointer exception.");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.refresh){
            Intent i = new Intent();
        }

        return super.onOptionsItemSelected(item);
    }
}

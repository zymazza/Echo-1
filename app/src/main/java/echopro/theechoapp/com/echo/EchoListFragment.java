package echopro.theechoapp.com.echo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EchoListFragment extends Fragment {
    ArrayList<Event> events;
    String TAG = "ECHO";
    ListView list;
    Location location;
    private ProgressBar spinner;
    EchoAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView enter");


        setRetainInstance(true);
        View rootView = inflater.inflate(R.layout.list_activity, container, false);
        spinner=(ProgressBar)rootView.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        list = (ListView) rootView.findViewById(R.id.echo_list);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("LiveEvents");
        query.whereGreaterThan("numEchoes", 3);
        spinner.setVisibility(View.VISIBLE);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d("SUCCESS", "Retrieving objects");
                    for (int i = 0; i < objects.size(); i++){

                        boolean inList = false;
                        for (int j = 0; j < events.size(); j++) {
                            if (objects.get(i).getString("name").equals(
                                    events.get(j).getEventName())){
                                inList = true;
                            }
                        }
                        if (!inList)
                            events.add(new Event(objects.get(i).getObjectId(),
                                    objects.get(i).getString("name"),
                                objects.get(i).getInt("numEchoes"),
                                    objects.get(i).getString("when")));

                        Log.i(TAG, String.valueOf(events.size()));
                    }

                    sortEvents();

                    try {

                        Collections.sort(events);
                        adapter.notifyDataSetChanged();
                    } catch (NullPointerException n){
                        //
                    }
                    spinner.setVisibility(View.GONE);

                } else {
                    Log.d("FAILED", "Couldn't retrieve objects");

                }
            }
        });
        Log.i(TAG, "final " + String.valueOf(events.size()));

        adapter = new EchoAdapter(getActivity().getApplicationContext(),
                events);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //((Event) list.getAdapter().getItem(position)).echo();

                adapter.notifyDataSetChanged();
                Intent i = new Intent(getActivity().getApplicationContext(), DetailsActivity.class);
                i.putExtra("EVENTNAME_DETAILS", ((Event) list.getAdapter().
                        getItem(position)).getEventName());
                i.putExtra("EVENTID", ((Event) list.getAdapter().
                        getItem(position)).event_id);
                //i.putExtra("EVENTNAME_DETAILS", ((Event) list.getAdapter().
                //        getItem(position)).getEventName());
                startActivity(i);
            }
        });



        Log.i(TAG, "onCreateView exit");
        return rootView;
    }

    private void sortEvents(){
        for (int i = 1; i < events.size(); i++){
            int j = i - 1;
            while (j >= 0 &&
                    events.get(i).getEventEchoes() > events.get(j).getEventEchoes()){
                Event temp = events.get(i);

                events.set(i,events.get(j));
                events.set(j, temp);
                j--;
            }
        }

    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState)
    {
        Log.i(TAG, "onCreateView enter");
        super.onInflate(activity, attrs, savedInstanceState);
        Log.i(TAG, "onCreateView exit");
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        Log.i(TAG, "onActivityCreated enter");
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null)
        {
            String tag = savedInstanceState.getString("tag");
            Log.i(TAG, "savedInstanceState.tag=" + (tag == null?"null":tag));
        }
        else
        {
            Log.i(TAG, "savedInstanceState is null");
        }
        Log.i(TAG, "onActivityCreated exit");
    }

    @Override
    public void onAttach(Activity activity)
    {

        Log.i(TAG, "onAttach enter");
        super.onAttach(activity);
        events = new ArrayList<>();
        LocationManager locationManager = (LocationManager) this.getActivity().
                getSystemService(Context.LOCATION_SERVICE);
                //this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                //makeUseOfNewLocation(location);
                EchoListFragment.this.location = location;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

// Register the listener with the Location Manager to receive location updates
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (IllegalArgumentException e){
            //
        }
        Log.i(TAG, "onAttach exit");
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.i(TAG, "onCreate enter");
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate exit");
    }

    @Override
    public void onDestroy()
    {
        Log.i(TAG, "onDestroy enter");
        super.onDestroy();

        Log.i(TAG, "onDestroy exit");
    }

    @Override
    public void onDestroyView()
    {

        Log.i(TAG, "onDestroyView enter");
        super.onDestroyView();

        Log.i(TAG, "onDestroyView exit");
    }

    @Override
    public void onDetach()
    {
        Log.i(TAG, "onDetach enter");
        super.onDetach();
        Log.i(TAG, "onDetach exit");
    }

    @Override
    public void onPause()
    {
        Log.i(TAG, "onPause enter");
        super.onPause();
        Log.i(TAG, "onPause exit");
    }

    @Override
    public void onResume()
    {
        Log.i(TAG, "onResume enter");
        super.onResume();

        Log.i(TAG, "onResume exit");
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState)
    {
        Log.i(TAG, "onViewStateRestored enter");
        super.onViewStateRestored(savedInstanceState);
        Log.i(TAG, "onViewStateRestored exit");
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        Log.i(TAG, "onSaveInstanceState enter");
        super.onSaveInstanceState(outState);
        //outState.putString("tag", TAG);
        //outState.putSerializable("list", events);
        Log.i(TAG, "onSaveInstanceState exit");
    }

    @Override
    public void onStart()
    {
        Log.i(TAG, "onStart enter");
        super.onStart();
        Log.i(TAG, "onStart exit");
    }

    @Override
    public void onStop()
    {
        Log.i(TAG, "onStop enter");
        super.onStop();
        Log.i(TAG, "onStop exit");
    }
}
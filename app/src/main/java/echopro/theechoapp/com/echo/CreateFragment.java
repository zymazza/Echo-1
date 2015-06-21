package echopro.theechoapp.com.echo;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

import java.util.List;

public class CreateFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.create_fragment, container, false);
        final EditText eventName = (EditText)rootView.findViewById(R.id.event_name);
        final EditText description = (EditText) rootView.findViewById(R.id.description_text_create);
        final CheckBox entryFee = (CheckBox) rootView.findViewById(R.id.entry_fee);
        final CheckBox casual = (CheckBox) rootView.findViewById(R.id.casual);
        final CheckBox alcohol = (CheckBox) rootView.findViewById(R.id.alcohol);
        final CheckBox outdoors = (CheckBox) rootView.findViewById(R.id.outdoors);
        final CheckBox music = (CheckBox) rootView.findViewById(R.id.music);
        final CheckBox sports = (CheckBox) rootView.findViewById(R.id.sports);
        final EditText location = (EditText) rootView.findViewById(R.id.where_text_create);
        final DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        datePicker.setCalendarViewShown(false);
        final TimePicker timePicker = (TimePicker) rootView.findViewById(R.id.time_picker);
        final String locationString;
        final ParseGeoPoint loc = null;
        final ImageButton done = (ImageButton) rootView.findViewById(R.id.done_button);
        done.setClickable(true);



        final Geocoder geo = new Geocoder(getActivity());
        try {
            List<Address> addresses = geo.getFromLocationName(location.getText().toString(),
                    1);

            loc.setLatitude(addresses.get(0).getLatitude());
            loc.setLongitude(addresses.get(0).getLongitude());
        } catch (Exception e){

        }
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:save directly to Parse
                ParseObject event = new ParseObject("LiveEvents");
                event.put("address", location.getText().toString());
                try {
                    event.put("Location", loc);
                } catch (Exception e) {

                }
                event.put("alcohol", alcohol.isChecked());
                event.put("casual", casual.isChecked());
                event.put("description", description.getText().toString());
                event.put("entryFee", entryFee.isChecked());
                event.put("music", music.isChecked());
                event.put("numEchoes", 0);
                event.put("outdoors", outdoors.isChecked());
                event.put("sports", sports.isChecked());
                event.put("when", datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + " " +
                        timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute());
                event.put("name", eventName.getText().toString());
                event.saveInBackground();
                Toast.makeText(getActivity(), "Event Sent", Toast.LENGTH_SHORT).show();
                done.setClickable(false);
            }
        });


        return rootView;
    }

    @Override
    public void onPause(){
        //this.setArguments(null);
        setRetainInstance(false);
    }

}
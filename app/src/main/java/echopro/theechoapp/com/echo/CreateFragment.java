package echopro.theechoapp.com.echo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Event newEvent = new Event();
        final View rootView = inflater.inflate(R.layout.create_fragment, container, false);

        final EditText eventName = (EditText)rootView.findViewById(R.id.event_name);
        final EditText description = (EditText) rootView.findViewById(R.id.description_text_create);
        final CheckBox entryFee = (CheckBox) rootView.findViewById(R.id.entry_fee);
        final CheckBox casual = (CheckBox) rootView.findViewById(R.id.casual);
        final CheckBox alcohol = (CheckBox) rootView.findViewById(R.id.alcohol);
        final CheckBox outdoors = (CheckBox) rootView.findViewById(R.id.outdoors);
        final CheckBox music = (CheckBox) rootView.findViewById(R.id.music);
        final CheckBox sports = (CheckBox) rootView.findViewById(R.id.sports);

        EditText location = (EditText) rootView.findViewById(R.id.where_text_create);

        final DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        final TimePicker timePicker = (TimePicker) rootView.findViewById(R.id.time_picker);
        //timePicker.g
        Button done = (Button) rootView.findViewById(R.id.done_button);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Event created = new Event();
                created.setEventName(eventName.getText().toString());
                Bundle attr = new Bundle();
                attr.putString("DESCRIPTION", description.getText().toString());
                attr.putBoolean("ALCOHOL", alcohol.isChecked());
                attr.putBoolean("CASUAL", casual.isChecked());
                attr.putBoolean("SPORTS", sports.isChecked());
                attr.putBoolean("MUSIC", music.isChecked());
                attr.putBoolean("ENTRYFEE", entryFee.isChecked());
                attr.putBoolean("OUTDOORS", outdoors.isChecked());
                created.setAttributes(attr);
                int year;
                int month;
                int day;
                int hour;
                int minute;
                int am_pm;
                year = datePicker.getYear();
                month = datePicker.getMonth();
                day = datePicker.getDayOfMonth();
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();

                created.setDate(year, month, day, hour, minute);*/

                //TODO:save directly to Parse
            }
        });
        return rootView;
    }

}
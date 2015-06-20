package echopro.theechoapp.com.echo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by Chav on 26-May-15.
 */
public class EchoAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Event> values;

    public EchoAdapter(Context c, ArrayList<Event> values){
        //super(c, R.layout.echo_list_item, values);
        this.context = c;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Event getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.echo_list_item, parent, false);
        TextView event_name = (TextView) rowView.findViewById(R.id.event_name);
        TextView echoes = (TextView) rowView.findViewById(R.id.echoes);
        TextView date = (TextView) rowView.findViewById(R.id.time_picker);
        ImageView echo_bar = (ImageView) rowView.findViewById(R.id.echo_bar);
        event_name.setText(values.get(position).getEventName());
        echoes.setText(String.valueOf(values.get(position).getEventEchoes()));
        date.setText(values.get(position).date_String);
        final int pos = position;
        echo_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.get(pos).echo();
                if(values.get(pos).isEchoed()) {
                    update(values.get(pos), 1);
                }
                else {
                    update(values.get(pos), -1);
                }
                EchoAdapter.this.notifyDataSetChanged();

            }
        });

        echoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                values.get(pos).echo();
                if(values.get(pos).isEchoed()) {
                    update(values.get(pos), 1);
                }
                else {
                    update(values.get(pos), -1);
                }
                EchoAdapter.this.notifyDataSetChanged();
            }
        });
        if (values.get(position).isEchoed()){
            echo_bar.setImageResource(R.drawable.bluebars);
        }else{
            echo_bar.setImageResource(R.drawable.white_bars_2);
        }



        return rowView;
    }

    private void update(Event e, int num){
        //if(e.updated) return;
        //e.updated = true;
        final int number = num;
        ParseQuery<ParseObject> query = ParseQuery.getQuery("LiveEvents");

        // Retrieve the object by id
        Log.i("ECHO", e.event_id);
        query.getInBackground(e.event_id, new GetCallback<ParseObject>() {
            public void done(ParseObject event, ParseException e) {
                if (e == null) {

                    event.increment("numEchoes", number);
                    event.saveEventually();

                } else {
                    Log.i("ECHO", "Parse exception");
                    e.printStackTrace();
                }
            }
        });

    }
}

package echopro.theechoapp.com.echo;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chav on 26-May-15.
 */
public class Event implements Parcelable, Comparable<Event> {
    String event_id;
    String name;
    Calendar date;
    int echoes;
    boolean echoed;
    String description;
    boolean entryFee;
    boolean outdoors;
    boolean sports;
    boolean casual;
    boolean alcohol;
    boolean approved;
    GeoLocation location;
    String location_string;
    String date_String;


    public Event(){
        echoes = 0;
        date = Calendar.getInstance();
    }

    public Event(String id, String n, int e, String d){
        event_id = id;
        name = n;
        echoes = e;
        echoed = false;
        date_String = d;
    }

    public int compareTo(Event e){
        if (this.echoes < e.echoes) return 1;
        if (this.echoes > e.echoes) return -1;
        return 0;
    }

    public void setDate(Date d){
        this.date.setTime(d);
    }

    public void setDate(int year, int month, int day, int hour, int minute){
        date.set(year, month, day, hour, minute);
    }

    public void setDate(int hour, int minute){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        setDate(year, month, day, hour, minute);
    }

    public void setLocationString(String l){
        this.location_string = l;
    }

    public String getLocationString(){
        return location_string;
    }

    public void setAttributes(Bundle attr){
        alcohol = attr.getBoolean("ALCOHOL");
        entryFee = attr.getBoolean("ENTRYFEE");
        casual = attr.getBoolean("CASUAL");
        sports = attr.getBoolean("SPORTS");
        description = attr.getString("DESCRIPTION");
    }


    public void setEventName(String name){
        this.name =  name;
    }

    public boolean isEchoed(){
        return echoed;
    }

    public void echo(){
        echoed = !echoed;
        if (echoed){ echoes++;}
        else{ echoes--;}
    }

    public String getEventName(){
        return this.name;
    }

    public int getEventEchoes(){
        return this.echoes;
    }

    public Calendar getDate(){
        return date;
    }


    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    public void writeToParcel(Parcel out, int flags) {
        Bundle bundle = new Bundle();

        bundle.putString("EventID", event_id);
        bundle.putInt("Echoes", echoes);
        bundle.putBoolean("Echoed", echoed);
        bundle.putString("EventDescription", description);
        bundle.putBoolean("EntryFee", entryFee);
        bundle.putBoolean("Outdoors",outdoors);
        bundle.putBoolean("Sports",sports);
        bundle.putBoolean("Alcohol",alcohol);
        bundle.putString("LocationString", location_string);
        bundle.putString("EventName", this.name);
        out.writeBundle(bundle);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    private Event(Parcel e){
        Bundle bundle = e.readBundle();
        this.name = bundle.getString("EventName");
        this.event_id = bundle.getString("EventID");
        this.echoes = bundle.getInt("Echoes");
        this.echoed = bundle.getBoolean("Echoed");
        this.description = bundle.getString("EventDescription");
        this.entryFee = bundle.getBoolean("EntryFee");
        this.outdoors = bundle.getBoolean("Outdoors");
        this.sports = bundle.getBoolean("Sports");
        this.alcohol = bundle.getBoolean("Alcohol");
        this.location_string = bundle.getString("LocationString");

    }

}

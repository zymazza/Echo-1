package echopro.theechoapp.com.echo;

import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Chav on 26-May-15.
 */
public class Event {
    UUID event_id;
    String name;
    Calendar date;
    int echoes;
    boolean echoed;

    public Event(){
        int ups = 0;
        date = Calendar.getInstance();
    }

    public Event(String n, int e){
        name = n;
        echoes = e;
        echoed = false;
        //date = Calendar.getInstance();
    }

    public void setDate(int year, int month, int day, int hour, int minute){
        date.set(year, month, day, hour, minute);
    }

    public void setDate(int hour, int minute){
        //date.set()
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
}

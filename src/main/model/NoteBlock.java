package model;

import org.json.JSONObject;

import java.awt.*;

//Represents a Note Block, plays a single note with an instrument, pitch, and duration
public class NoteBlock implements Block {

    protected String instrument;
    protected String pitch;
    protected String duration;

    //REQUIRES: instrument, pitch, and duration are all valid
    //EFFECTS: create a NoteBlock with a given instrument, pitch, and duration
    public NoteBlock(String instrument, String pitch, String duration) {
        this.instrument = instrument;
        this.pitch = pitch;
        this.duration = duration;
    }

    //getters and setters

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }



    //REQUIRES: all string fields are valid
    //EFFECTS: returns the musical string of the note
    @Override
    public String convertBlock() {
        return "I[" + this.instrument + "] " + this.pitch + this.duration;
    }

    @Override
    public void drawBlock(int x, int y, Graphics g) {
        g.setColor(Color.CYAN);
        g.fill3DRect(x, y, 200, 75, true);
        g.setColor(Color.BLACK);
        g.drawString("NoteBlock", x + 40, y + 20);
        g.drawString("Instrument: " + instrument, x + 40, y + 34);
        g.drawString("Pitch: " + pitch, x + 40, y + 48);
        g.drawString("Duration: " + duration, x + 40, y + 62);
    }

    //EFFECTS: returns the NoteBlock as a JSONObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("type", "NoteBlock");
        json.put("instrument", instrument);
        json.put("pitch", pitch);
        json.put("duration", duration);
        return json;
    }

}

package model;

import org.json.JSONObject;

import java.awt.*;

//Represents a chord, plays a chord from a instrument, pitch, duration, and chordType
public class ChordBlock extends NoteBlock {

    private String chordType;

    //EFFECTS: construct a ChordBlock from instrument, pitch, duration, and chord type
    public ChordBlock(String instrument, String pitch, String duration, String chordType) {
        super(instrument, pitch, duration);
        this.chordType = chordType;
    }

    //getters and setters

    public String getChordType() {
        return chordType;
    }

    public void setChordType(String chordType) {
        this.chordType = chordType;
    }

    //REQUIRES: all string fields are valid
    //EFFECTS: produce the musical Strings for the chord
    @Override
    public String convertBlock() {
        return "I[" + this.instrument + "] " + this.pitch + this.chordType + this.duration;
    }

    @Override
    public void drawBlock(int x, int y, Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fill3DRect(x, y, 200, 75, true);
        g.setColor(Color.BLACK);
        g.drawString("ChordBlock: " + chordType, x + 40, y + 20);
        g.drawString("Instrument: " + instrument, x + 40, y + 34);
        g.drawString("Pitch: " + pitch, x + 40, y + 48);
        g.drawString("Duration: " + duration, x + 40, y + 62);
    }

    //EFFECTS: return the chord as a JSONObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("type", "ChordBlock");
        json.put("instrument", instrument);
        json.put("pitch", pitch);
        json.put("duration", duration);
        json.put("chordType", chordType);
        return json;
    }
}

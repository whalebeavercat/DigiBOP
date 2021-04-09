package model;

import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;

//The class for a loop block, plays a certain note many times
public class LoopBlock extends NoteBlock {

    private int loop;

    //REQUIRES: instrument, pitch, duration are all valid
    //EFFECTS: Construct a LoopBlock with the given instruments, pitch, duration, and loop time
    public LoopBlock(String instrument, String pitch, String duration, int loop) {
        super(instrument, pitch, duration);
        this.loop = loop;
    }

    //getters and setters

    public int getLoop() {
        return loop;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    //REQUIRES: all string fields are valid
    //EFFECTS: produce the musical Strings for the loop
    @Override
    public String convertBlock() {
        String musicalLoop = "I[" + instrument + "]";
        for (int i = 0; i < loop; i++) {
            musicalLoop += " " + pitch + duration;
        }
        return musicalLoop;
    }

    @Override
    public void drawBlock(int x, int y, Graphics g) {
        g.setColor(Color.ORANGE);
        g.fill3DRect(x, y, 200, 75, true);
        g.setColor(Color.BLACK);
        g.drawString("LoopBlock Loop: " + loop, x + 40, y + 20);
        g.drawString("Instrument: " + instrument, x + 40, y + 34);
        g.drawString("Pitch: " + pitch, x + 40, y + 48);
        g.drawString("Duration: " + duration, x + 40, y + 62);
    }

    //EFFECTS: returns the LoopBlock as a JSONObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("type", "LoopBlock");
        json.put("instrument", instrument);
        json.put("pitch", pitch);
        json.put("duration", duration);
        json.put("loop", loop);
        return json;
    }
}

package model;

import org.json.JSONObject;

import java.awt.*;

//Represents a Rest Block, rests for a certain duration
public class RestBlock implements Block {

    private String duration;

    //REQUIRES: duration is valid
    //EFFECTS: Construct a new Rest Block with a certain duration
    public RestBlock(String duration) {
        this.duration = duration;
    }

    //getters and setters

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    //REQUIRE: all string fields are valid
    //EFFECTS: Return a rest of a certain duration in musical strings
    @Override
    public String convertBlock() {
        return "R" + duration;
    }

    @Override
    public void drawBlock(int x, int y, Graphics g) {
        g.setColor(Color.GREEN);
        g.fill3DRect(x, y, 200, 75, true);
        g.setColor(Color.BLACK);
        g.drawString("RestBlock", x + 40, y + 20);
        g.drawString("Duration: " + duration, x + 40, y + 40);
    }

    //EFFECTS: returns the RestBlock as a JSONObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("type", "RestBlock");
        json.put("duration", duration);
        return json;
    }

}

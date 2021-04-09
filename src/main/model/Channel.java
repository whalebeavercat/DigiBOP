package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Stores the musical data from Scripts, convert them to playable data, and perform them in voices
public class Channel implements Writable {

    private List<Script> scriptList;
    private int bpm = 90;  //default bpm

    //EFFECTS: construct a channel and player that contains Scripts
    public Channel() {
        this.scriptList = new ArrayList<>();
    }

    //getter
    public List<Script> getScriptList() {
        return this.scriptList;
    }

    public int getBpm() {
        return this.bpm;
    }

    //setter
    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    //EFFECTS: returns true if a script contains the given scriptName, false otherwise
    public boolean containsScriptName(String name) {
        return this.getScriptNames().contains(name);
    }

    //REQUIRES: one of the Scripts in the list has the name given and not empty, no repeated names
    //EFFECTS: returns the Script in the Channel
    public Script accessScript(String name) {
        Script scriptWithName = null;
        for (Script script : scriptList) {
            if (name.equals(script.getScriptName())) {
                scriptWithName = script;
            }
        }
        return scriptWithName;
    }

    //MODIFIES: this
    //EFFECTS: adds the Script to the end of Channel scriptList
    public void addScript(Script newScript) {
        this.scriptList.add(newScript);
    }

    //MODIFIES: this
    //EFFECTS: removes the Script in the Channel
    public void removeScript(Script script) {
        scriptList.remove(script);
    }

    //EFFECTS: returns the channel size (the size of scriptList)
    public int channelSize() {
        return scriptList.size();
    }

    //EFFECTS: returns the names of all scripts in the channel
    public List<String> getScriptNames() {
        ArrayList<String> scriptNames = new ArrayList<>();
        for (Script script : scriptList) {
            scriptNames.add(script.getScriptName());
        }
        return scriptNames;
    }

    //EFFECTS: returns the list of Scripts in readable musical strings, return bpm if empty
    public String convertChannel() {
        String musicalString = "T" + bpm + " ";
        for (int i = 0; i < this.channelSize(); i++) {
            musicalString += "V" + i + " " + scriptList.get(i).convertScript();
        }
        return musicalString;
    }

    //EFFECTS: Convert Channel to a JsonObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("bpm", bpm);
        json.put("scriptList", scriptsToJson());
        return json;
    }

    //EFFECTS: returns the JSONArray of all scripts in scriptList
    private JSONArray scriptsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Script script : scriptList) {
            jsonArray.put(script.convertToJson());
        }

        return jsonArray;
    }
}

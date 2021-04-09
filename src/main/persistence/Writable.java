package persistence;

import org.json.JSONObject;

//Represents an object that can be converted to JSON and saved
//CITATION: code taken and modified from Writable.java in JsonSerializationDemo
//          URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    //EFFECTS: returns the object as a JSONObject
    public JSONObject convertToJson();
}

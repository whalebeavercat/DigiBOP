package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
 * Represents a reader that reads channel from JSON data stored in file
 * CITATION: code taken and modified from JsonReader.java in JsonSerializationDemo
 *           URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Channel read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseChannel(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses channel from JSON object and returns it
    private Channel parseChannel(JSONObject jsonObject) {
        int bpm = jsonObject.getInt("bpm");
        Channel channel = new Channel();
        channel.setBpm(bpm);
        addScripts(channel, jsonObject);
        return channel;
    }

    // MODIFIES: channel
    // EFFECTS: parses scripts from JSON object and adds them to the channel
    private void addScripts(Channel channel, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scriptList");
        for (Object json : jsonArray) {
            JSONObject nextScript = (JSONObject) json;
            parseScript(channel, nextScript);
        }
    }

    //MODIFIES: channel
    //EFFECTS: parses a script from JSON object and adds them to the channel
    private void parseScript(Channel channel, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int scriptX = jsonObject.getInt("scriptX");
        int scriptY = jsonObject.getInt("scriptY");
        Script script = new Script(scriptX, scriptY, name);
        addBlocks(script, jsonObject);
        channel.addScript(script);
    }

    //MODIFIES: script
    //EFFECTS: parses blocks from JSON object and adds them to the script
    private void addBlocks(Script script, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("blockList");
        for (Object json : jsonArray) {
            JSONObject nextBlock = (JSONObject) json;
            addBlock(script, nextBlock);
        }
    }

    // MODIFIES: script
    // EFFECTS: parses blocks from JSON object and adds it to the channel
    private void addBlock(Script script, JSONObject jsonObject) {
        Block block = null;
        String type = jsonObject.getString("type");
        String duration = jsonObject.getString("duration");
        if (type.equals("NoteBlock")) {
            String instrument = jsonObject.getString("instrument");
            String pitch = jsonObject.getString("pitch");
            block = new NoteBlock(instrument, pitch, duration);
        } else if (type.equals("LoopBlock")) {
            String instrument = jsonObject.getString("instrument");
            String pitch = jsonObject.getString("pitch");
            int loop = jsonObject.getInt("loop");
            block = new LoopBlock(instrument, pitch, duration, loop);
        } else if (type.equals("ChordBlock")) {
            String instrument = jsonObject.getString("instrument");
            String pitch = jsonObject.getString("pitch");
            String chordType = jsonObject.getString("chordType");
            block = new ChordBlock(instrument, pitch, duration, chordType);
        } else {
            block = new RestBlock(duration);
        }
        script.addBlock(block);
    }
}

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.WorkspaceUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

//Scripts of musical blocks that are run in sequence
public class Script implements Writable {

    public static final int WIDTH = 200;
    public static final int HEIGHT = 75;
    private String scriptName;
    private List<Block> blockList;
    private int scriptX;
    private int scriptY;

    //REQUIRES: the name of the script has non-zero length
    //EFFECTS: construct a Script Object with name and empty ArrayList
    public Script(String name) {
        this.scriptName = name;
        this.blockList = new ArrayList<>();
    }

    //EFFECTS: construct a Script Object with name, x, y and empty ArrayList from Point
    public Script(Point corner, String name) {
        this.scriptX = (int) corner.getX() / WorkspaceUI.GRID_WIDTH * WorkspaceUI.GRID_WIDTH;
        this.scriptY = (int) corner.getY() / WorkspaceUI.GRID_WIDTH * WorkspaceUI.GRID_WIDTH;
        this.scriptName = name;
        this.blockList = new ArrayList<>();
    }

    //EFFECTS: construct a Script Object with name, x, y and empty ArrayList
    public Script(int x, int y, String name) {
        this.scriptX = x;
        this.scriptY = y;
        this.scriptName = name;
        this.blockList = new ArrayList<>();
    }

    //getters and setters

    public String getScriptName() {
        return scriptName;
    }

    public List<Block> getBlockList() {
        return blockList;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public int getScriptX() {
        return scriptX;
    }

    public int getScriptY() {
        return scriptY;
    }

    //MODIFIES: this
    //EFFECTS: adds the int to the scriptX
    public void addScriptX(int dx) {
        this.scriptX += dx;
    }

    //MODIFIES: this
    //EFFECTS: adds the int to the scriptY
    public void addScriptY(int dy) {
        this.scriptY += dy;
    }

    //MODIFIES: this
    //EFFECTS: consumes a Block and adds the Block object to the Pattern
    public void addBlock(Block block) {
        this.blockList.add(block);
    }

    //MODIFIES: this
    //EFFECTS: removes the selected block from the Pattern, if more than one delete the most recent,
    // if does not exist do nothing
    public void removeBlock(Block block) {
        for (int i = this.blockList.size() - 1; i >= 0; i--) {
            if (this.blockList.get(i) == block) {
                this.blockList.remove(i);
                break;
            }
        }
    }

    //EFFECTS: return true if the mouse event point is within the range of the entire script
    public boolean containsRange(Point e) {
        int mouseX = (int) e.getX();
        int mouseY = (int) e.getY();
        return (mouseX >= scriptX) && (mouseX <= scriptX + WIDTH)
                && (mouseY >= scriptY) && (mouseY <= HEIGHT * (blockList.size() + 1) + scriptY);
    }

    //EFFECTS: return the musical string of all the blocks in the script in order
    public String convertScript() {
        String scriptMusic = "";
        for (Block block : blockList) {
            scriptMusic += block.convertBlock() + " ";
        }
        return scriptMusic;
    }

    //EFFECTS: returns the script as a JSONObject
    @Override
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();
        json.put("name", scriptName);
        json.put("scriptX", scriptX);
        json.put("scriptY", scriptY);
        json.put("blockList", blocksToJson());
        return json;
    }

    //EFFECTS: returns the JSONArray of all blocks in blockList
    private JSONArray blocksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Block block : blockList) {
            jsonArray.put(block.convertToJson());
        }

        return jsonArray;
    }


    //MODIFIES: g
    //EFFECTS: Draw the script block inside the Workspace
    public void drawScript(Graphics g) {
        g.setColor(Color.red);
        g.fill3DRect(scriptX, scriptY, WIDTH, HEIGHT, true);
        g.setColor(Color.BLACK);
        g.setFont(new Font("ComicSans", Font.BOLD, 14));
        g.drawString("Script Name: " + this.scriptName, scriptX + 30, scriptY + 30);
        for (Block block : blockList) {
            block.drawBlock(scriptX, scriptY + (blockList.indexOf(block) + 1) * HEIGHT, g);
        }
    }
}

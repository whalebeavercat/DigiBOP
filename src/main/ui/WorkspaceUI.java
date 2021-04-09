package ui;

import model.Script;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

//Represents the UI for the Workspace with all the drawings
public class WorkspaceUI extends JPanel {

    public static final int GRID_WIDTH = 25;
    private SynthEditor synthEditor;

    //EFFECTS: create a Grid Panel creates the workspace for the blocks in it
    public WorkspaceUI(SynthEditor synthEditor) {
        this.synthEditor = synthEditor;
    }

    //MODIFIES: this
    //EFFECTS: paint the workspace based on the contents of the mainChannel
    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        drawGrid(graphic);
        for (Script script : synthEditor.getMainChannel().getScriptList()) {
            script.drawScript(graphic);
        }
    }

    //EFFECTS: draw the grid for the workspace
    private void drawGrid(Graphics graphic) {
        for (int i = 0; i < getWidth(); i += GRID_WIDTH) {
            for (int j = 0; j < getHeight(); j += GRID_WIDTH) {
                graphic.drawRect(i, j, GRID_WIDTH, GRID_WIDTH);
            }
        }
    }

    //EFFECTS: returns the script at the point
    //         otherwise, do nothing
    public Script getScriptAtPoint(Point e) {
        for (Script script : synthEditor.getMainChannel().getScriptList()) {
            if (script.containsRange(e)) {
                return script;
            }
        }
        return null;
    }

}

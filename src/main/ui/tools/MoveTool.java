package ui.tools;

import model.Script;
import ui.SynthEditor;

import java.awt.*;
import java.awt.event.MouseEvent;

//Represents a Tool that can move scripts around in the workspace
public class MoveTool extends Tool {

    private Point startPosition;

    //EFFECTS: construct a MoveTool given a SynthEditor
    public MoveTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    @Override
    public String getLabel() {
        return "Move";
    }


    //MODIFIES: this
    //EFFECTS: if the mouse is pointed on a script, then set the start position and the script, also select the script
    @Override
    public void mousePressedInWorkspace(MouseEvent e) {
        selectedScript = synthEditor.getWorkspace().getScriptAtPoint(e.getPoint());
        if (selectedScript != null) {
            startPosition = e.getPoint();
            synthEditor.setSelectedScript(selectedScript);
        }
    }

    // MODIFIES: this
    // EFFECTS:  unselect the script, and set the script to null
    @Override
    public void mouseReleasedInWorkspace(MouseEvent e) {
        if (selectedScript != null) {
            selectedScript = null;
            isSelected = false;
            synthEditor.getWorkspace().repaint();
        }
    }

    // MODIFIES: this
    // EFFECTS:  set the script x and y according to point
    @Override
    public void mouseDraggedInWorkspace(MouseEvent e) {
        if (selectedScript != null) {
            int dx = (int) (e.getPoint().getX() - startPosition.getX());
            int dy = (int) (e.getPoint().getY() - startPosition.getY());
            startPosition = e.getPoint();
            selectedScript.addScriptX(dx);
            selectedScript.addScriptY(dy);
            synthEditor.getWorkspace().repaint();
        }
    }
}

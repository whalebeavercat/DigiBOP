package ui.tools;

import model.Script;
import ui.SynthEditor;
import ui.WorkspaceUI;

import java.awt.*;
import java.awt.event.MouseEvent;

//Represents a tool that can create a Script on the workspace when pressed
public class ScriptTool extends Tool {

    //EFFECTS: construct a tool that can create a script given a synthEditor
    public ScriptTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    @Override
    public String getLabel() {
        return "Create New Script";
    }

    //MODIFIES: this
    //EFFECTS: creates a new script to the mouse coordinates when pressed on the workspace
    //         only works if ScriptTool is selected
    public void mousePressedInWorkspace(MouseEvent e) {
        if (isSelected) {
            selectedScript = new Script(e.getPoint(), "Script" + synthEditor.getMainChannel().channelSize());
            synthEditor.getMainChannel().addScript(selectedScript);
            synthEditor.setSelectedScript(selectedScript);
            isSelected = false;
        }
        WorkspaceUI workspace = synthEditor.getWorkspace();
        workspace.repaint();
    }
}

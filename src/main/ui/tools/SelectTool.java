package ui.tools;

import model.Script;
import ui.SynthEditor;

import java.awt.event.MouseEvent;

//Represents a tool that can select scripts and sets them in the SynthEditor
public class SelectTool extends Tool {

    //EFFECTS: construct a select tool from a SynthEditor
    public SelectTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    @Override
    public String getLabel() {
        return "Select";
    }

    //MODIFIES: this
    //EFFECTS: if the mouse is pointed on a script, then set the script as the selected script
    @Override
    public void mousePressedInWorkspace(MouseEvent e) {
        selectedScript = synthEditor.getWorkspace().getScriptAtPoint(e.getPoint());
        if (selectedScript != null && isSelected) {
            synthEditor.setSelectedScript(selectedScript);
            isSelected = false;
        }
    }


}

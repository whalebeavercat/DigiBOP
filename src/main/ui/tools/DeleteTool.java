package ui.tools;

import model.Script;
import ui.SynthEditor;

import java.awt.event.MouseEvent;

//Represents a tool that can delete scripts from the workspace
public class DeleteTool extends Tool {

    //EFFECTS: construct a tool that can delete given a SynthEditor
    public DeleteTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    @Override
    public String getLabel() {
        return "Delete";
    }

    //MODIFIES: this
    //EFFECTS: if the mouse is pointed on a script, then delete the selected script
    @Override
    public void mousePressedInWorkspace(MouseEvent e) {
        selectedScript = synthEditor.getWorkspace().getScriptAtPoint(e.getPoint());
        if (selectedScript != null && isSelected) {
            synthEditor.getMainChannel().removeScript(selectedScript);
            synthEditor.setSelectedScript(null);
            isSelected = false;
            synthEditor.getWorkspace().repaint();
        }
    }
}

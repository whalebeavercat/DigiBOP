package ui.tools;

import model.Block;
import model.Script;
import ui.SynthEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

//Represents a tool that can be used to modify the channel
public abstract class Tool {

    protected SynthEditor synthEditor;
    protected JButton button;
    protected boolean isSelected;
    protected Script selectedScript;

    //EFFECTS: construct a Tool given a synthEditor
    public Tool(SynthEditor synthEditor) {
        this.synthEditor = synthEditor;
    }

    //getter
    public abstract String getLabel();

    public boolean isSelected() {
        return isSelected;
    }

    //setter

    public void setSelected(boolean selected) {
        this.isSelected = selected;
    }

    //MODIFIES: this
    //EFFECTS: create a Button of a given label and create selecting capabilities
    public JButton createButton() {
        button = new JButton(getLabel());
        button.addActionListener(e -> actionPerformed());
        return button;
    }

    //MODIFIES: this
    //EFFECTS: make isSelected true and stores the tool in the SynthEditor
    public void actionPerformed() {
        synthEditor.setSelectedTool(this);
    }

    //EFFECTS: abstract tools does nothing with this MouseEvent
    public void mouseClickedInWorkspace(MouseEvent e) {}

    //EFFECTS: abstract tools does nothing with this MouseEvent
    public void mouseDraggedInWorkspace(MouseEvent e) {}

    //EFFECTS: abstract tools does nothing with this MouseEvent
    public void mousePressedInWorkspace(MouseEvent e) {}

    //EFFECTS: abstract tools does nothing with this MouseEvent
    public void mouseReleasedInWorkspace(MouseEvent e) {}

}

package ui;

import model.Block;
import model.Script;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a JPanel and gives the options to change the script and its blocks
public class ScriptEditorUI extends JPanel {

    private SynthEditor synthEditor;
    private Block selectedBlock;
    private BlockEditorUI blockEditor;

    //EFFECTS: construct a ScriptEditorUI given a synthEditor, initialize fields
    public ScriptEditorUI(SynthEditor synthEditor) {
        this.synthEditor = synthEditor;
        setLayout(new FlowLayout());
        blockEditor = new BlockEditorUI(synthEditor);
        updateScript();
    }

    //MODIFIES: this
    //EFFECTS: displays the options available for the given script
    public void updateScript() {
        update();
        Script selectedScript = synthEditor.getSelectedScript();
        if (selectedScript != null) {
            JLabel script = new JLabel("You have selected: " + selectedScript.getScriptName());
            JTextField userScriptName = new JTextField();
            JButton setScriptName = new JButton("Set Name");
            setScriptName.addActionListener(e -> {
                setScriptName(selectedScript, userScriptName);
            });
            JLabel selectBlock = new JLabel("Select a block to edit: ");
            userScriptName.setPreferredSize(new Dimension(100, 20));
            JComboBox selectEditor = new JComboBox(selectedScript.getBlockList().toArray());
            selectEditor.addActionListener(e -> {
                selectBlock(selectEditor);
            });
            add(script);
            add(Box.createHorizontalStrut(50));
            add(userScriptName);
            add(setScriptName);
            add(selectBlock);
            add(selectEditor);
            add(blockEditor);
        }
    }

    //MODIFIES: this
    //EFFECTS: remove all the content of the jPanel and update with the new
    private void update() {
        removeAll();
        revalidate();
        repaint();
    }

    //EFFECTS: select a block from the given JCombo box
    private void selectBlock(JComboBox selectEditor) {
        selectedBlock = (Block) selectEditor.getSelectedItem();
        updateBlock(selectedBlock);
    }

    //EFFECTS: set the name of the script from the JTextField
    private void setScriptName(Script selectedScript, JTextField userScriptName) {
        selectedScript.setScriptName(userScriptName.getText().trim());
        synthEditor.getWorkspace().repaint();
        updateScript();
    }

    //EFFECTS: displays the options available for the given block
    public void updateBlock(Block block) {
        blockEditor.updateBlock(block);
    }


}

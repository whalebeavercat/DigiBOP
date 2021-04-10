package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

//Represents a JPanel and gives the options to change the script and its blocks
public class ScriptEditorUI extends JPanel {

    private SynthEditor synthEditor;
    private Block selectedBlock;
    private BlockEditorUI blockEditor;
    private JComboBox selectEditor;

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
            userScriptName.setPreferredSize(new Dimension(100, 20));
            JButton setScriptName = new JButton("Set Name");
            setScriptName.addActionListener(e -> {
                setScriptName(selectedScript, userScriptName);
            });
            JLabel selectBlock = new JLabel("Select a block to edit: ");
            createBlockSelector(selectedScript);
            add(script);
            add(Box.createHorizontalStrut(300));
            add(userScriptName);
            add(setScriptName);
            add(selectBlock);
            add(selectEditor);
            add(Box.createHorizontalStrut(300));
            add(blockEditor);
        }
    }

    //MODIFIES: this
    //EFFECTS: produce the block selector as a JComboBox
    private void createBlockSelector(Script selectedScript) {
        selectEditor = new JComboBox(displayBlocks(selectedScript.getBlockList()));
        selectEditor.addActionListener(e -> {
            selectBlock(selectEditor);
        });
    }

    //EFFECTS: displays the names of all the blocks in the script as an array
    private String[] displayBlocks(List<Block> blockList) {
        List<String> blockNames = new ArrayList<>();
        for (int i = 0; i < blockList.size(); i++) {
            blockNames.add(getBlockType(blockList.get(i)) + " " + i);
        }
        return (String[]) blockNames.toArray(new String[blockNames.size()]);
    }

    //EFFECTS: display the type of the block
    private String getBlockType(Block block) {
        if (block instanceof RestBlock) {
            return "RestBlock";
        } else if (block instanceof ChordBlock) {
            return "ChordBlock";
        } else if (block instanceof LoopBlock) {
            return "LoopBlock";
        } else {
            return "NoteBlock";
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
        int position = (int) selectEditor.getSelectedIndex();
        Block selectedBlock = synthEditor.getSelectedScript().getBlockList().get(position);
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
        selectEditor.setSelectedItem(block);
        blockEditor.updateBlock(block);
    }


}

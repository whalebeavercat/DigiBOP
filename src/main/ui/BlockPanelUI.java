package ui;

import ui.tools.*;

import javax.swing.*;
import java.awt.*;

//Represents a JPanel and displays the tools and blocks available
public class BlockPanelUI extends JPanel {

    private SynthEditor synthEditor;

    //EFFECTS: creates the blocks GUI for the side panel, contains blocks and script initialization
    public BlockPanelUI(SynthEditor synthEditor) {
        this.synthEditor = synthEditor;
        setLayout(new GridLayout(0,1));
        createBlockTools();
    }

    //MODIFIES: this
    //EFFECTS: produce the block buttons and add them to the panel
    public void createBlockTools() {
        ScriptTool scriptTool = new ScriptTool(synthEditor);
        add(scriptTool.createButton());

        NoteBlockTool noteBlockTool = new NoteBlockTool(synthEditor);
        add(noteBlockTool.createButton());

        LoopBlockTool loopBlockTool = new LoopBlockTool(synthEditor);
        add(loopBlockTool.createButton());

        RestBlockTool restBlockTool = new RestBlockTool(synthEditor);
        add(restBlockTool.createButton());

        ChordBlockTool chordBlockTool = new ChordBlockTool(synthEditor);
        add(chordBlockTool.createButton());

        SelectTool selectTool = new SelectTool(synthEditor);
        add(selectTool.createButton());

        MoveTool moveTool = new MoveTool(synthEditor);
        add(moveTool.createButton());

        DeleteTool deleteTool = new DeleteTool(synthEditor);
        add(deleteTool.createButton());
    }


}

package ui.tools;

import model.Block;
import model.ChordBlock;
import ui.SynthEditor;

//Represents a tool that can add ChordBlocks to a script in a workspace
public class ChordBlockTool extends BlockTool {

    //EFFECTS: create a ChordBlock given a SynthEditor
    public ChordBlockTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    @Override
    public String getLabel() {
        return "Add ChordBlock";
    }

    //EFFECTS: return a default ChordBlock
    @Override
    protected Block makeBlock() {
        return new ChordBlock("Piano", "C4", "q", "maj");
    }
}

package ui.tools;

import model.Block;
import model.NoteBlock;
import ui.SynthEditor;

//Represents a tool that can add NoteBlocks to a script in a workspace
public class NoteBlockTool extends BlockTool {

    //EFFECTS: create a NoteBlockTool given a SynthEditor
    public NoteBlockTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    @Override
    public String getLabel() {
        return "Add NoteBlock";
    }

    //EFFECTS: return a default NoteBlock
    @Override
    protected Block makeBlock() {
        return new NoteBlock("Piano", "C4", "q");
    }
}

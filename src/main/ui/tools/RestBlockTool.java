package ui.tools;

import model.Block;
import model.NoteBlock;
import model.RestBlock;
import ui.SynthEditor;

//Represents a tool that can add RestBlocks to a script in a workspace
public class RestBlockTool extends BlockTool {

    //EFFECTS: construct a RestBlockTool given a SynthEditor
    public RestBlockTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    @Override
    public String getLabel() {
        return "Add RestBlock";
    }

    //EFFECTS: return a default RestBlock
    @Override
    protected Block makeBlock() {
        return new RestBlock("q");
    }
}

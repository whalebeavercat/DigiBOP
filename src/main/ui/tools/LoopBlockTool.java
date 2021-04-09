package ui.tools;

import model.Block;
import model.LoopBlock;
import ui.SynthEditor;

//Represents a Tool that can add a LoopBlock to a script
public class LoopBlockTool extends BlockTool {

    //EFFECTS: create a LoopBlockTool given a SynthEditor
    public LoopBlockTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    @Override
    public String getLabel() {
        return "Add LoopBlock";
    }

    //EFFECTS: return a default LoopBlock
    @Override
    protected Block makeBlock() {
        return new LoopBlock("Piano", "C4", "q", 4);
    }
}

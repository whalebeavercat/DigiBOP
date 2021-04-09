package ui.tools;

import model.Block;
import model.Script;
import ui.SynthEditor;
import ui.WorkspaceUI;

import java.awt.event.*;

//Represents a Tool that can add a Block to a selected script
public abstract class BlockTool extends Tool {

    protected Block block;

    //EFFECTS: create a block tool from a SynthEditor
    public BlockTool(SynthEditor synthEditor) {
        super(synthEditor);
    }

    //MODIFIES: this
    //EFFECTS: if script is null, do nothing
    //         otherwise, create a new block and add it to the script
    protected void createBlock() {
        selectedScript = synthEditor.getSelectedScript();
        block = makeBlock();
        if (selectedScript != null) {
            selectedScript.addBlock(block);
        }
        WorkspaceUI workspace = synthEditor.getWorkspace();
        workspace.repaint();
        synthEditor.getScriptEditor().updateScript();
        synthEditor.getScriptEditor().updateBlock(block);
    }

    @Override
    public void actionPerformed() {
        createBlock();
    }

    //EFFECTS: change the block field of tool
    protected abstract Block makeBlock();


}

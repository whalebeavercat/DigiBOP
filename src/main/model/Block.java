package model;

import persistence.Writable;

import java.awt.*;

//Represents a block that composes musical phrases. Contains Musical Data
public interface Block extends Writable {

    // FUNCTIONS

    //REQUIRES: all string fields are valid
    //EFFECTS: produce the musical Strings for the loop
    public String convertBlock();

    //MODIFIES: this
    //EFFECTS: draws the block with the given fields
    public void drawBlock(int x, int y, Graphics g);


}

package ui;

import model.*;

import javax.swing.*;
import java.awt.*;

//Represents a Block Editor Panel that displays options to change blocks
public class BlockEditorUI extends JPanel {

    public static final String[] PITCHES =
            {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    public static final String[] TONES = {"1", "2", "3", "4", "5", "6", "7", "8"};
    public static final String[] DURATIONS = {"q", "w", "h", "i"};
    public static final String[] INSTRUMENTS = {"Piano", "Banjo", "Violin", "Flute", "Guitar"};
    public static final String[] CHORDS = {"maj", "min", "maj7", "min7", "dom7", "dim7"};

    private JComboBox instruments;
    private JComboBox pitches;
    private JComboBox tones;
    private JComboBox duration;
    private JComboBox loop;
    private JComboBox chordType;

    private SynthEditor synthEditor;

    //EFFECTS: Creates Block Option Panel for the given block
    public BlockEditorUI(SynthEditor synthEditor) {
        this.synthEditor = synthEditor;
        setLayout(new GridLayout(2, 1));
    }

    //MODIFIES: this, userBLock
    //EFFECTS: update the options to the Block Editor and cast block to the type
    public void updateBlock(Block userBlock) {
        removeAll();
        revalidate();
        repaint();
        if (userBlock instanceof LoopBlock) {
            createLoopBlockEditor((LoopBlock) userBlock);
        } else if (userBlock instanceof ChordBlock) {
            createChordBlockEditor((ChordBlock) userBlock);
        } else if (userBlock instanceof RestBlock) {
            createRestBlockEditor((RestBlock) userBlock);
        } else {
            createNoteBlockEditor((NoteBlock) userBlock);
            add(instruments);
            add(pitches);
            add(tones);
            add(duration);
        }
    }

    //MODIFIES: this
    //EFFECTS: updates and repaints the workspace to reflect changes made
    private void updateWorkspace() {
        synthEditor.getWorkspace().repaint();
    }

    //MODIFIES: this
    //EFFECTS: create the editor for the LoopBlock
    private void createLoopBlockEditor(LoopBlock userBlock) {
        createNoteBlockEditor(userBlock);
        loop = new JComboBox(TONES);
        loop.addActionListener(e -> {
            int selected = Integer.parseInt((String) loop.getSelectedItem());
            userBlock.setLoop(selected);
            updateWorkspace();
        });
        loop.setSelectedItem(String.valueOf(userBlock.getLoop()));
        add(instruments);
        add(pitches);
        add(tones);
        add(duration);
        add(loop);
    }

    //MODIFIES: this
    //EFFECTS: create the editor for the ChordBlock
    private void createChordBlockEditor(ChordBlock userBlock) {
        createNoteBlockEditor(userBlock);
        chordType = new JComboBox(CHORDS);
        chordType.addActionListener(e -> {
            String selected = (String) chordType.getSelectedItem();
            userBlock.setChordType(selected);
            updateWorkspace();
        });
        chordType.setSelectedItem(userBlock.getChordType());
        add(instruments);
        add(pitches);
        add(tones);
        add(duration);
        add(chordType);
    }

    //MODIFIES: this
    //EFFECTS: create the editor for the NoteBlock
    private void createNoteBlockEditor(NoteBlock block) {
        instruments = new JComboBox(INSTRUMENTS);
        pitches = new JComboBox(PITCHES);
        tones = new JComboBox(TONES);
        duration = new JComboBox(DURATIONS);
        setSelectedNoteBlock(block);
        instruments.addActionListener(e -> {
            String selected = (String) instruments.getSelectedItem();
            block.setInstrument(selected);
            updateWorkspace();
        });
        pitches.addActionListener(e -> {
            pitchToneNoteBlock(block);
        });
        tones.addActionListener(e -> {
            pitchToneNoteBlock(block);
        });
        duration.addActionListener(e -> {
            String selected = (String) duration.getSelectedItem();
            block.setDuration(selected);
            updateWorkspace();
        });
    }

    //MODIFIES: this
    //EFFECTS: set the options to the fields displayed in the block
    private void setSelectedNoteBlock(NoteBlock block) {
        String pitch = block.getPitch().replaceAll("\\d", "");
        instruments.setSelectedItem(block.getInstrument());
        pitches.setSelectedItem(pitch);
        tones.setSelectedItem(block.getPitch().replaceAll("[^\\d]", ""));
        duration.setSelectedItem(block.getDuration());
    }

    //MODIFIES: this
    //EFFECTS: creates the editor for the RestBlock
    private void createRestBlockEditor(RestBlock block) {
        duration = new JComboBox(DURATIONS);
        duration.addActionListener(e -> {
            String selected = (String) duration.getSelectedItem();
            block.setDuration(selected);
            updateWorkspace();
        });
        duration.setSelectedItem(block.getDuration());
        add(duration);
    }

    //MODIFIES: block
    //EFFECTS: sets the block pitch to the combination of pitch and tone from editor
    private void pitchToneNoteBlock(NoteBlock block) {
        String selectedPitch = (String) pitches.getSelectedItem();
        String selectedTone = (String) tones.getSelectedItem();
        block.setPitch(selectedPitch + selectedTone);
        updateWorkspace();
    }
}

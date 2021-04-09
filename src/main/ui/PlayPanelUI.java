package ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a JPanel and contains the play and stop options and set bpm
public class PlayPanelUI extends JPanel {

    private static final Color PANEL_COLOR = new Color(0xF52BB0A7, true);
    private SynthEditor synthEditor;
    private ImageIcon playIcon = new ImageIcon("./data/PlayButton.png");
    private ImageIcon stopIcon = new ImageIcon("./data/StopButton.png");

    //EFFECTS: create a Play Panel which contains a Play Button, select tools, and bpm text field
    public PlayPanelUI(SynthEditor synthEditor) {
        this.synthEditor = synthEditor;
        setLayout(new FlowLayout());
        createPlayButton();
        createBpmText();
        setBackground(PANEL_COLOR);
    }

    //MODIFIES: this
    //EFFECTS: produces the play button and adds it into the panel
    private void createPlayButton() {
        JButton playButton = new JButton(playIcon);
        playButton.setSize(100,200);
        playButton.addActionListener(e -> synthEditor.playChannel());
        JButton stopButton = new JButton(stopIcon);
        playButton.setSize(100,200);
        stopButton.addActionListener(e -> synthEditor.stopChannel());
        add(playButton);
        add(stopButton);
        add(Box.createHorizontalStrut(200));
    }

    //MODIFIES: this
    //EFFECTS: produces the text field for the bpm text field and add it into the panel
    private void createBpmText() {
        JLabel bpm = new JLabel("Bpm:");
        JTextField bpmText = new JTextField();
        bpmText.setPreferredSize(new Dimension(100,25));
        JButton setBpm = new JButton("Set BPM");
        setBpm.addActionListener(e -> {
            try {
                int userBpm = Math.abs(Integer.parseInt(bpmText.getText()));
                bpm.setText("Bpm: " + userBpm);
                synthEditor.getMainChannel().setBpm(userBpm);
            } catch (IllegalArgumentException exception) {
                //Do nothing
            }
        });
        add(bpm);
        add(bpmText);
        add(setBpm);
    }
}

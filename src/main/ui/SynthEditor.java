package ui;

import model.Channel;
import model.Script;
import org.jfugue.player.Player;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//A GUI application for DigiBOP
public class SynthEditor extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private Channel mainChannel;
    private Player player;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private String destination;
    private boolean isSaved;
    private Tool selectedTool;
    private WorkspaceUI workspace;
    private Script selectedScript;
    private ScriptEditorUI scriptEditor;


    //EFFECTS: initialize the graphics, fields, and listeners of SynthEditor
    public SynthEditor() {
        super("DigiBOP 3.0");
        initGraphics();
        initFields();
        initMouseListener();
    }

    
    //getter

    public Channel getMainChannel() {
        return mainChannel;
    }

    public String getDestination() {
        return destination;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public WorkspaceUI getWorkspace() {
        return workspace;
    }

    public ScriptEditorUI getScriptEditor() {
        return scriptEditor;
    }

    public Script getSelectedScript() {
        return selectedScript;
    }

    //setter

    //MODIFIES: this
    //EFFECTS: set the selected script and update the script in the script editor
    public void setSelectedScript(Script selectedScript) {
        this.selectedScript = selectedScript;
        scriptEditor.updateScript();
    }

    //MODIFIES: this
    //EFFECTS: initialize the graphical interface of the application
    private void initGraphics() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createMenu();
        createPlayPanel();
        createBlockPanel();
        createWorkspace();
        createScriptEditor();
        setVisible(true);
        setResizable(true);
    }

    //MODIFIES: this
    //EFFECTS: instantiates a MenuTool and display options for saving and loading
    private void createMenu() {
        MenuUI menu = new MenuUI(this);
        setJMenuBar(menu);
    }

    //MODIFIES: this
    //EFFECTS: load the Channel to a Json file, catch IOException
    public void loadChannel(String userDestination) {
        try {
            jsonReader = new JsonReader(userDestination);
            mainChannel = jsonReader.read();
            this.destination = userDestination;
            System.out.println("Successfully loaded " + userDestination + " to the main Channel");
            this.isSaved = true;
            workspace.repaint();
        } catch (Exception e) {
            System.out.println("File " + userDestination + " does not exist or invalid");
        }
    }

    //MODIFIES: this
    //EFFECTS: save the Channel to Json file, catch FileNotFoundException
    public void saveChannel(String userDestination) {
        try {
            jsonWriter = new JsonWriter(userDestination);
            jsonWriter.open();
            jsonWriter.write(mainChannel);
            jsonWriter.close();
            this.destination = userDestination;
            System.out.println("Channel has been successfully saved to " + destination);
            this.isSaved = true;
        } catch (Exception e) {
            System.out.println("Unable to write the file to: " + destination);
        }
    }

    //MODIFIES: this
    //EFFECTS: create the play menu panel with bpm selector and Player
    private void createPlayPanel() {
        PlayPanelUI playPanel = new PlayPanelUI(this);
        add(playPanel, BorderLayout.NORTH);
        playPanel.setPreferredSize(new Dimension(WIDTH, 70));
    }

    //MODIFIES: this
    //EFFECTS: plays the main channel with the bpm when the play button is pressed
    public void playChannel() {
        try {
            player = new Player();
            String channelMusic = mainChannel.convertChannel();
            player.delayPlay(20, channelMusic);
        } catch (Exception e) {
            //DO NOTHING
        }
    }

    //MODIFIES: this
    //EFFECTS: stop the channel from playing when the stop button is pressed
    public void stopChannel() {
        try {
            player = new Player();
            player.delayPlay(20, "");
        } catch (Exception e) {
            //DO NOTHING
        }
    }

    //MODIFIES: this
    //EFFECTS: create the block menu panel with blocks available to use
    private void createBlockPanel() {
        BlockPanelUI blockPanel = new BlockPanelUI(this);
        add(blockPanel, BorderLayout.WEST);
        blockPanel.setPreferredSize(new Dimension(200, HEIGHT));
    }

    //MODIFIES: this
    //EFFECTS: create the grid menu panel with workspace
    private void createWorkspace() {
        workspace = new WorkspaceUI(this);
        add(workspace, BorderLayout.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: create the script editor panel with fields to edit
    private void createScriptEditor() {
        scriptEditor = new ScriptEditorUI(this);
        add(scriptEditor, BorderLayout.EAST);
        scriptEditor.setPreferredSize(new Dimension(300, HEIGHT));
    }

    //MODIFIES: this
    //EFFECTS: initializes channel, file, player for sound
    private void initFields() {
        mainChannel = new Channel();
        isSaved = false;
    }

    //MODIFIES: this
    //EFFECTS: reselect the blocks for editing, deselect other tool and replace with current tool
    public void setSelectedTool(Tool tool) {
        if (selectedTool != null) {
            selectedTool.setSelected(false);
        }
        tool.setSelected(true);
        this.selectedTool = tool;
    }

    //MODIFIES: this
    //EFFECTS: Initialize the mouse listener component of the SynthEditor
    private void initMouseListener() {
        WorkspaceMouseListener wml = new WorkspaceMouseListener();
        workspace.addMouseListener(wml);
        workspace.addMouseMotionListener(wml);
    }

    //MODIFIES: this
    //EFFECTS: handle the mouse events for pressed/released/clicked/drag if the active tool is not null
    public void handleMousePressed(MouseEvent e) {
        if (selectedTool != null) {
            selectedTool.mousePressedInWorkspace(e);
        }
    }

    //MODIFIES: this
    //EFFECTS: handle the mouse events for pressed/released/clicked/drag if the active tool is not null
    public void handleMouseClicked(MouseEvent e) {
        if (selectedTool != null) {
            selectedTool.mouseClickedInWorkspace(e);
        }
    }

    //MODIFIES: this
    //EFFECTS: handle the mouse events for pressed/released/clicked/drag if the active tool is not null
    public void handleMouseReleased(MouseEvent e) {
        if (selectedTool != null) {
            selectedTool.mouseReleasedInWorkspace(e);
        }
    }

    //MODIFIES: this
    //EFFECTS: handle the mouse events for pressed/released/clicked/drag if the active tool is not null
    public void handleMouseDragged(MouseEvent e) {
        if (selectedTool != null) {
            selectedTool.mouseDraggedInWorkspace(e);
        }
    }

    //Represents a MouseListener for Workspace
    //CITATION: Modified from SimpleDrawingPlayer
    //          URL: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
    private class WorkspaceMouseListener extends MouseAdapter {

        // EFFECTS: Forward mouse pressed event to the active tool
        public void mousePressed(MouseEvent e) {
            handleMousePressed(translateEvent(e));
        }

        // EFFECTS: Forward mouse released event to the active tool
        public void mouseReleased(MouseEvent e) {
            handleMouseReleased(translateEvent(e));
        }

        // EFFECTS:Forward mouse clicked event to the active tool
        public void mouseClicked(MouseEvent e) {
            handleMouseClicked(translateEvent(e));
        }

        // EFFECTS:Forward mouse dragged event to the active tool
        public void mouseDragged(MouseEvent e) {
            handleMouseDragged(translateEvent(e));
        }

        // EFFECTS: translates the mouse event to current workspace's coordinate system
        private MouseEvent translateEvent(MouseEvent e) {
            return SwingUtilities.convertMouseEvent(e.getComponent(), e, workspace);
        }
    }
}

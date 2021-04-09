package ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

//Represents a MenuUI that gives the options of saving, loading, and save as
public class MenuUI extends JMenuBar {

    private JMenu menu;
    private JMenuItem menuItem;
    private SynthEditor synthEditor;

    //EFFECTS: Constructs a menu bar with a file menu with save, load, and save as
    public MenuUI(SynthEditor synthEditor) {
        this.synthEditor = synthEditor;
        createMenuBar();
    }

    //MODIFIES: this
    //EFFECTS: return the JMenuBar from the MenuBar Object
    public void createMenuBar() {
        addFileMenu();
        addSaveLoadMenuItems();
    }

    //MODIFIES: this
    //EFFECTS: add the file menu to the menu bar
    private void addFileMenu() {
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        add(menu);
    }

    //MODIFIES: this
    //EFFECTS: add the save, load, save as, menu items to the menu bar
    private void addSaveLoadMenuItems() {
        menuItem = new JMenuItem("Save", KeyEvent.VK_T);
        menuItem.addActionListener(e -> {
            if (synthEditor.isSaved()) {
                synthEditor.saveChannel(synthEditor.getDestination());
            } else {
                saveMenu();
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Load", KeyEvent.VK_T);
        menuItem.addActionListener(e -> {
            loadMenu();
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Save As...", KeyEvent.VK_T);
        menuItem.addActionListener(e -> {
            saveMenu();
        });
        menu.add(menuItem);
    }

    //The code is taken and modified from https://mkyong.com/swing/java-swing-jfilechooser-example/
    //MODIFIES: this
    //EFFECTS: display the saveMenu through JFileChooser
    private void saveMenu() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select a json file:  ");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("JSON Files", "json");
        jfc.addChoosableFileFilter(fileFilter);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String currentDestination = selectedFile.getAbsolutePath() + ".json";
            synthEditor.saveChannel(currentDestination);
        }
    }

    //The code is taken and modified from https://mkyong.com/swing/java-swing-jfilechooser-example/
    //MODIFIES: this
    //EFFECTS: display the load Menu through JFileChooser
    private void loadMenu() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select a json file:  ");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("JSON Files", "json");
        jfc.addChoosableFileFilter(fileFilter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String currentDestination = selectedFile.getAbsolutePath();
            synthEditor.loadChannel(currentDestination);
        }
    }
}

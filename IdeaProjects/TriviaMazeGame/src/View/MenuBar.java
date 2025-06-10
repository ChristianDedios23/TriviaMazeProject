package View;

import Model.Maze;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.*;

public class MenuBar extends JMenuBar
{
    private final JFrame myParent;

    private final JMenu myFileMenu;

    private final JMenu myHelpMenu;

    private final JMenuItem mySaveGameItem;

    private final JMenuItem myLoadGameItem;

    private final JMenuItem myExitGameItem;

    private final JMenuItem myGamePlayInstructionItem;

    private final JMenuItem myAboutItem;

    private final JMenuItem myShortCutsItem;

    private final JMenuItem myLegendItem;

    //add shortcuts menu item and add shortcuts to play the whole game without a mouse
    MenuBar(final JFrame theParent)
    {
        super();
        myParent = theParent;
        myFileMenu = new JMenu("File");
        myHelpMenu = new JMenu("Help");
        mySaveGameItem = new JMenuItem("Save Game");
        myLoadGameItem = new JMenuItem("Load Game");
        myExitGameItem = new JMenuItem("Exit Game");
        myGamePlayInstructionItem = new JMenuItem("How-to-play");
        myAboutItem = new JMenuItem("About");
        myShortCutsItem = new JMenuItem("Short-Cuts");
        myLegendItem = new JMenuItem("Legend");
        setUpComponents();
        addListeners();
    }

    private void setUpComponents()
    {
        mySaveGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        myLoadGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        myExitGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        myGamePlayInstructionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        myAboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        myShortCutsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        myLegendItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        //G ctrl-down

        myFileMenu.add(mySaveGameItem);
        myFileMenu.add(myLoadGameItem);
        myFileMenu.add(myExitGameItem);
        myHelpMenu.add(myGamePlayInstructionItem);
        myHelpMenu.add(myAboutItem);
        myHelpMenu.add(myShortCutsItem);
        myHelpMenu.add(myLegendItem);

        this.add(myFileMenu);
        this.add(myHelpMenu);
    }

    private void addListeners()
    {
        myAboutItem.addActionListener(theEvent ->{
            JOptionPane.showMessageDialog(myParent, "Authors :\n     Christian Dedios \n     Jason Dinh " +
                    "\n     Khalid Mohamed \n\nVersion: 1.0   JDK 23.0.1");
        });

        myExitGameItem.addActionListener(theEvent -> {
            myParent.dispatchEvent(new WindowEvent(myParent, WindowEvent.WINDOW_CLOSING));
        });

        myGamePlayInstructionItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(myParent, "The Trivia Maze Game challenges you to navigate a grid-like maze, " +
                    "starting from the top-left corner and \naiming to reach the bottom-right corner. Along the way, each unopened door presents a trivia " +
                    "question. \nAnswer correctly, and the door remains for future travel. But be careful, a wrong answer locks the door \npermanently. " +
                    "Get stuck with no way forward and you lose!\n" +
                    "\nHow to play:\n" +
                    "   1. Click the start game button and choose the type of questions you would like and the difficulty!\n" +
                    "   2. Make your way to the bottom right corner by answering questions, opening or closing doors.\n" +
                    "   3. The game is over one you make it to the end or you trap yourself in!\n" +
                    "   4. At anytime you can save your game and load it back up in the start screen!\n" +
                    "\nHints:\n" +
                    "Hints are gained based on the difficulty you set and your streak of correct answers!\n" +
                    "Easy: Start with one hint and earn based on streaks of three!\n" +
                    "Medium: Only earn hints based on streaks of five!\n" +
                    "Hard: You are given no hints!");
        });

        //add buttons to moving later
        myShortCutsItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(myParent, "New Game - ALT + N\n"
                    + "Load Game - CTRL + L or ALT + L \nSave Game - CTRL + S \nExit Game - CTRL + E \nAbout - CTRL + A\n"
                    + "How-To-Play - CTRL + H \nShortcuts - CTRL + O \nMove Up - ALT + W \nMove Right - ALT + D\n"
                    + "Move Left - ALT + A \nMove Down - ALT + S");
        });

        mySaveGameItem.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL == null){
                JOptionPane.showMessageDialog(myParent, "No current game to save!");
            }
            if((new File("savedGame.ser")).exists()){
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Save file found!\n Do you want to overwrite them?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.NO_OPTION) {
                    return;
                }
            }

            try
            {
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream("savedGame.ser");
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(StartGameFrame.MY_MAZE_MODEL);

                out.close();
                file.close();

                JOptionPane.showMessageDialog(myParent, "Game has been saved!");

            }

            catch(IOException ex)
            {
                System.err.println(ex);
                JOptionPane.showMessageDialog(myParent, "Error while saving game");
            }


        });

        myLoadGameItem.addActionListener(theEvent -> {

        });

        myLegendItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(myParent, "Brown Door - Unanswered door (question will be asked)\nWhite Line - Open" +
                    "door (answered correctly)\nYellow Wall - Inaccessible (no door)");
        });
    }

    public JMenuBar getMenuBar()
    {
        return this;
    }
}
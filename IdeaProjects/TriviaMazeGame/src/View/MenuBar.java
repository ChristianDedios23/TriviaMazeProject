package View;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

        myFileMenu.add(mySaveGameItem);
        myFileMenu.add(myLoadGameItem);
        myFileMenu.add(myExitGameItem);
        myHelpMenu.add(myGamePlayInstructionItem);
        myHelpMenu.add(myAboutItem);
        myHelpMenu.add(myShortCutsItem);

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
            JOptionPane.showMessageDialog(myParent, "This is how to play the game");
        });

        //add buttons to moving later
        myShortCutsItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(myParent, "New Game - ALT + N\n"
                    + "Load Game - CTRL + L \nSave Game - CTRL + S \nExit Game - CTRL + E \nAbout - CTRL + A\n"
                    + "How-To-Play - CTRL + H \nShortcuts - CTRL + O \nMove Up - Alt + U \nMove Right - ALT + P\n"
                    + "Move Left - ALT + L \nMove Down - ALT + D");
        });

        mySaveGameItem.addActionListener(theEvent -> {
            try
            {
                //Saving of object in a file
                FileOutputStream file = new FileOutputStream("savedGame.ser");
                ObjectOutputStream out = new ObjectOutputStream(file);

                // Method for serialization of object
                out.writeObject(StartGameFrame.MY_MAZE_MODEL);

                out.close();
                file.close();

                System.out.println("HAS BEEN SERIALIZED");

            }

            catch(IOException ex)
            {
                System.err.println(ex);
                System.out.println("ERROR WHILE SAVING");
            }


        });

        myLoadGameItem.addActionListener(theEvent -> {

        });
    }

    public JMenuBar getMenuBar()
    {
        return this;
    }
}
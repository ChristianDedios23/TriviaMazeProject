package View;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class MenuBar extends JMenuBar
{
    private JFrame myParent;

    private JMenu myFileMenu;

    private JMenu myHelpMenu;

    private JMenuItem mySaveGameItem;

    private JMenuItem myLoadGameItem;

    private JMenuItem myExitGameItem;

    private JMenuItem myGamePlayInstructionItem;

    private JMenuItem myAboutItem;

    private JMenuItem myShortCutsItem;

    //add shortcuts menu item and add shortcuts to play the whole game without a mouse
    MenuBar(JFrame theParent)
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

        });

        myLoadGameItem.addActionListener(theEvent -> {

        });
    }

    public JMenuBar getMenuBar()
    {
        return this;
    }
}
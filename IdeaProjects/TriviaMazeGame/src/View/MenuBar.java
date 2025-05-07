package View;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar
{
    private JMenu myFileMenu;

    private JMenu myHelpMenu;

    private JMenuItem mySaveGameItem;

    private JMenuItem myLoadGameItem;

    private JMenuItem myExitGameItem;

    private JMenuItem myGamePlayInstructionItem;

    private JMenuItem myAboutItem;

    private JMenuItem myShortCutsItem;

    //add shortcuts menu item and add shortcuts to play the whole game without a mouse
    public MenuBar()
    {
        super();
        myFileMenu = new JMenu("File");
        myHelpMenu = new JMenu("Help");
        mySaveGameItem = new JMenuItem("Save Game");
        myLoadGameItem = new JMenuItem("Load Game");
        myExitGameItem = new JMenuItem("Exit Game");
        myGamePlayInstructionItem = new JMenuItem("How-to-play");
        myAboutItem = new JMenuItem("About");
        myShortCutsItem = new JMenuItem("Short-Cuts");
        setUpComponents();
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

    public JMenuBar getMenuBar()
    {
        return this;
    }

    public JMenuItem getSaveGameItem()
    {
        return mySaveGameItem;
    }

    public JMenuItem getLoadGameItem()
    {
        return myLoadGameItem;
    }

    public JMenuItem getExitGameItem()
    {
        return myExitGameItem;
    }

    public JMenuItem getAboutItem()
    {
        return myAboutItem;
    }

    public JMenuItem getGameInstructions()
    {
        return myGamePlayInstructionItem;
    }

    public JMenuItem getShortCutsItem(){ return myShortCutsItem;}
}
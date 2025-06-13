package View;

import Model.Maze;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.*;

import static View.StartGameFrame.MY_MAZE_MODEL;

/** This class represents a JMenuBar that is used to contain
 * all the JMenu and JMenuItem objects to be accessible to the user.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed
 * @version 1.0
 */
public class MenuBar extends JMenuBar
{
    /** This represents the frame that the MenuBar is going to be attached to.*/
    private final JFrame myParent;

    /** This is used to hold all the JMenuItems in the file menu.*/
    private final JMenu myFileMenu;

    /** This is used to hold all the JMenuItems in the help menu.*/
    private final JMenu myHelpMenu;

    /** This is used to save the game.*/
    private final JMenuItem mySaveGameItem;

    /** This is used to load the game.*/
    private final JMenuItem myLoadGameItem;

    /** This is used to exit the game.*/
    private final JMenuItem myExitGameItem;

    /** This is used to display the how-to-play text.*/
    private final JMenuItem myHowToPlayItem;

    /** This is used to display the About section.*/
    private final JMenuItem myAboutItem;

    /** This is used to display the shortcuts.*/
    private final JMenuItem myShortCutsItem;

    /** This is used to display the legend.*/
    private final JMenuItem myLegendItem;

    /** This constructor initializes all the components used in the JMenuBar.
     *
     * @param theParent the frame this menu bar is being added to.
     */
    MenuBar(final JFrame theParent)
    {
        super();
        myParent = theParent;
        myFileMenu = new JMenu("File");
        myHelpMenu = new JMenu("Help");
        mySaveGameItem = new JMenuItem("Save Game");
        myLoadGameItem = new JMenuItem("Load Game");
        myExitGameItem = new JMenuItem("Exit Game");
        myHowToPlayItem = new JMenuItem("How-to-play");
        myAboutItem = new JMenuItem("About");
        myShortCutsItem = new JMenuItem("Short-Cuts");
        myLegendItem = new JMenuItem("Legend");
        setUpComponents();
        addListeners();
    }

    /** This sets up all the keyboard shortcuts to all the menu items. */
    private void setUpComponents()
    {
        mySaveGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        myLoadGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK));
        myExitGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        myHowToPlayItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        myAboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        myShortCutsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        myLegendItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));

        myFileMenu.add(mySaveGameItem);
        myFileMenu.add(myLoadGameItem);
        myFileMenu.add(myExitGameItem);
        myHelpMenu.add(myHowToPlayItem);
        myHelpMenu.add(myAboutItem);
        myHelpMenu.add(myShortCutsItem);
        myHelpMenu.add(myLegendItem);

        this.add(myFileMenu);
        this.add(myHelpMenu);
    }

    /** This method adds listeners to all the JMenuItems. */
    private void addListeners()
    {
        //Displays the about sections, collaborators and JDK version used.
        myAboutItem.addActionListener(theEvent ->{
            JOptionPane.showMessageDialog(myParent, "Authors :\n     Christian Dedios \n     Jason Dinh " +
                    "\n     Khalid Mohamed \n\nVersion: 1.0   JDK 23.0.1");
        });

        //Shoots window closing event to parent frame
        myExitGameItem.addActionListener(theEvent -> {
            myParent.dispatchEvent(new WindowEvent(myParent, WindowEvent.WINDOW_CLOSING));
        });

        //Displays to user how to play the game
        myHowToPlayItem.addActionListener(theEvent -> {
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

        //Displays the shortcuts for buttons and menu items
        myShortCutsItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(myParent, "New Game - ALT + N\n"
                    + "Load Game - CTRL + L or ALT + L \nSave Game - CTRL + S \nExit Game - CTRL + E \nAbout - CTRL + A\n"
                    + "How-To-Play - CTRL + H \nShortcuts - CTRL + O \nLegend - CTRL + G \nMove Up - ALT + W \nMove Right - ALT + D\n"
                    + "Move Left - ALT + A \nMove Down - ALT + S");
        });

        //Saves the game
        mySaveGameItem.addActionListener(theEvent -> {
            if(MY_MAZE_MODEL == null){
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
                out.writeObject(MY_MAZE_MODEL);

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

        //Loads a previous saved game
        myLoadGameItem.addActionListener(theEvent -> {
            if(GameFrame.getInstance().isVisible()){
                JOptionPane.showMessageDialog(myParent, "Can't load game while current is active!");
                return;
            }
                try {
                    FileInputStream file = new FileInputStream("savedGame.ser");
                    ObjectInputStream in = new ObjectInputStream (file);
                    MY_MAZE_MODEL = (Maze)in.readObject();
                    in.close();
                    file.close();

                }catch (IOException ex) {
                    System.err.println(ex);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                finally {
                    if(MY_MAZE_MODEL != null){
                        GameFrame gameFrame = GameFrame.getInstance();
                        gameFrame.setVisible(true);
                    }
                }

        });

        //Displays the legend
        myLegendItem.addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(myParent, "Brown Door - Unanswered door (question will be asked)\nWhite Line - Open" +
                    "door (answered correctly)\nYellow Wall - Inaccessible (no door)");
        });
    }

    /** This method returns the instance of this class.
     *
     * @return The instance of this class
     */
    public JMenuBar getMenuBar()
    {
        return this;
    }
}
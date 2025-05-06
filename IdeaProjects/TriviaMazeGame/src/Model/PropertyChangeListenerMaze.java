package Model;

import java.beans.PropertyChangeListener;

public interface PropertyChangeListenerMaze {

    String PROPERTY_PLAYER_MOVE_UP = "playerMoveUP";
    String PROPERTY_PLAYER_MOVE_DOWN = "playerMoveDown";
    String PROPERTY_PLAYER_MOVE_LEFT = "playerMoveLeft";
    String PROPERTY_PLAYER_MOVE_RIGHT = "playerMoveRight";
    String PROPERTY_PLAYER_MOVE_OOB = "playerMoveOOB";
    String PROPERTY_QUESTION_WRONG = "questionWrong";
    String PROPERTY_QUESTION_RIGHT = "questionRight";
    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties. The same listener object may be added more than once, and will be
     * called as many times as it is added. If listener is null, no exception is thrown and
     * no action is taken.
     * @param theListener the PropertyChangeListener to be added
     */
    
    void addPropertyChangeListener(PropertyChangeListener theListener);
}

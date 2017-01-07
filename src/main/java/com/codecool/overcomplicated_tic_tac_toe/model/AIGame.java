package com.codecool.overcomplicated_tic_tac_toe.model;

import java.util.List;

public interface AIGame {

    void setInitialState();
    List getState();
    void setUserStep(int place);
    void setComputerStep(int place);
    GameResult getGameResult();

}

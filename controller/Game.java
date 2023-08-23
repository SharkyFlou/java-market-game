package controller;

import model.EventsInfo;
import model.ItemsInfo;
import view.GameWindow;

public class Game {
    public Game() {
        EventsInfo eventsInfo = EventsInfo.getInstance();
        ItemsInfo itemsInfo = ItemsInfo.getInstance();
        GameWindow gameWindow = new GameWindow();

    }

}

package org.example.adapter.presenter;

import org.example.adapter.ui.Screen;
import org.example.domain.entities.models.ResponseModel;

import java.util.ArrayList;
import java.util.List;

public class Presenter {
    private List<Screen> screens = new ArrayList<>();
    
    public void addScreen(Screen screen) {
        screens.add(screen);
    }
    
    public void notify(ResponseModel message) {
        for (Screen screen : screens) {
            screen.notify(message);
        }
    } 
}

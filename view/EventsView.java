package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.EventId;
import model.EventsInfo;
import model.ItemId;


public class EventsView extends JPanel{
    private JPanel eventsPanel;
    private JPanel descPanel;
    private LinkedHashMap<EventId, EventView> events;


    public EventsView() {
        // set size
        int width=480;
        int height=500-80;
        setPreferredSize(new Dimension(width, height));

        setLayout(new BorderLayout());

        events = new LinkedHashMap<EventId, EventView>();

        eventsPanel = new JPanel(new GridBagLayout());
        eventsPanel.setPreferredSize(new Dimension((width/3) * 2, height));
        eventsPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JScrollPane scrollableTextArea = new JScrollPane(eventsPanel);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 

        add(scrollableTextArea, BorderLayout.CENTER);

        descPanel = new JPanel();
        descPanel.setPreferredSize(new Dimension(width/3, height));
        descPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        add(descPanel, BorderLayout.EAST);
    }

    public void update(int day){
        boolean needToRedraw = false;
        List<EventId> eventsToRemove = new ArrayList<EventId>();
        for (EventView eventView : events.values()) {
            eventView.update();
            if(eventView.getDay() == eventView.getDuration()){
                eventsToRemove.add(findIdByView(eventView));
                needToRedraw = true;
            }
        }
        if(needToRedraw){
            for (EventId eventId : eventsToRemove) {
                System.out.println("Remove visualy event : "+eventId);
                events.remove(eventId);
            }
            redrawAllEvents();
        }
    }

    private void printEvt(){
        for (EventId eventId : events.keySet()) {
            System.out.println(eventId+" : "+ events.get(eventId).getDay()+"/"+events.get(eventId).getDuration());
        }
    }

    private EventId findIdByView(EventView eventView){
        for (EventId eventId : events.keySet()) {
            if(events.get(eventId) == eventView)
                return eventId;
        }
        return null;
    }

    public void addEvent(EventId eventId){
        EventView eventView = new EventView(EventsInfo.getInstance().getEvent(eventId));
        events.put(eventId, eventView);
        eventsPanel.add(eventView, findNextSpot());
    }

    private void redrawAllEvents(){
        eventsPanel.removeAll();
        eventsPanel.updateUI();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.gridx = 0;
        gbc.gridy = 0;
        for (EventView event : events.values()) {
            eventsPanel.add(event,gbc);
            gbc.gridx++;
            if (gbc.gridx == 3) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
    }

    private GridBagConstraints findNextSpot(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        while(true){
            if(findItemByCoords(gbc.gridx, gbc.gridy) == null)
                break;
            //if very last item in grid stop
            if(gbc.gridx == 2 && gbc.gridy == (int)ItemId.values().length / 3)
                break;

            gbc.gridx++;
            if(gbc.gridx == 3){
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        return gbc;
    }

    private Component findItemByCoords(int x, int y) {
        Component match = null;
        GridBagLayout layout = (GridBagLayout)eventsPanel.getLayout();
        for (Component comp : eventsPanel.getComponents()) {
            GridBagConstraints gbc = layout.getConstraints(comp);
            if (gbc.gridx == x && gbc.gridy == y) {
                match = comp;
                break;
            }
        }
        return match;
    }
}

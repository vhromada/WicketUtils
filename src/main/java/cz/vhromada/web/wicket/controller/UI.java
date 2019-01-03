package cz.vhromada.web.wicket.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.vhromada.web.wicket.event.PageEvent;

/**
 * A class represents user interface.
 *
 * @author Vladimir Hromada
 */
public class UI {

    /**
     * Events
     */
    private final List<PageEvent> events = new ArrayList<>();

    /**
     * Fire event on UI.
     *
     * @param event event
     * @throws IllegalArgumentException if event is null
     */
    public void fireEvent(final PageEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("Event mustn't be null.");
        }

        events.add(event);
    }

    /**
     * Returns events.
     *
     * @return events
     */
    public List<PageEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }

}

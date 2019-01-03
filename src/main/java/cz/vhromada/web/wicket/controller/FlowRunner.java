package cz.vhromada.web.wicket.controller;

import org.apache.wicket.Component;
import org.apache.wicket.event.Broadcast;

/**
 * A class represents flow runner.
 *
 * @author Vladimir Hromada
 */
public class FlowRunner {

    /**
     * Creates a new instance of FlowRunner.
     *
     * @param source source
     * @param flow   flow
     * @param data   data
     * @param <T>    type of data
     * @throws IllegalArgumentException if source is null
     *                                  or flow is null
     */
    public <T> FlowRunner(final Component source, final Flow flow, final T data) {
        if (source == null) {
            throw new IllegalArgumentException("Source mustn't be null.");
        }
        if (flow == null) {
            throw new IllegalArgumentException("Flow mustn't be null.");
        }

        source.send(source.getPage(), Broadcast.EXACT, new FrontControllerRequest<>(flow, data));
    }

}

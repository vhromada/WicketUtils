package cz.vhromada.web.wicket.controllers;

import org.apache.wicket.Component;
import org.apache.wicket.event.Broadcast;
import org.springframework.util.Assert;

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
        Assert.notNull(source, "Source mustn't be null.");
        Assert.notNull(flow, "Flow mustn't be null.");

        source.send(source.getPage(), Broadcast.EXACT, new FrontControllerRequest<>(flow, data));
    }

}

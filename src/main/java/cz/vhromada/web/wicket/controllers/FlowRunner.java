package cz.vhromada.web.wicket.controllers;

import cz.vhromada.validators.Validators;

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
        Validators.validateArgumentNotNull(source, "Source");
        Validators.validateArgumentNotNull(flow, "Flow");

        source.send(source.getPage(), Broadcast.EXACT, new FrontControllerRequest<>(flow, data));
    }

}

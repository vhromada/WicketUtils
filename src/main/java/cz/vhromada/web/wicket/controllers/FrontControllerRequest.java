package cz.vhromada.web.wicket.controllers;

import org.springframework.util.Assert;

/**
 * A class represents front controller's request.
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public class FrontControllerRequest<T> {

    /**
     * Flow
     */
    private final Flow flow;

    /**
     * Data
     */
    private final T data;

    /**
     * Creates a new instance of FrontControllerRequest.
     *
     * @param flow flow
     * @param data data
     * @throws IllegalArgumentException if flow is null
     */
    public FrontControllerRequest(final Flow flow, final T data) {
        Assert.notNull(flow, "Flow mustn't be null.");

        this.flow = flow;
        this.data = data;
    }

    /**
     * Returns flow.
     *
     * @return flow
     */
    public Flow getFlow() {
        return flow;
    }

    /**
     * Returns data.
     *
     * @return data
     */
    public T getData() {
        return data;
    }

}

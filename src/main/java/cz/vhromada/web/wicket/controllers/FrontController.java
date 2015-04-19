package cz.vhromada.web.wicket.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.events.PageEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents front controller.
 *
 * @author Vladimir Hromada
 */
@Component("frontController")
public class FrontController {

    /**
     * Map: flow -> controller
     */
    private Map<Flow, Controller<?>> flowControllerMap;

    /**
     * Creates a new instance of FrontController.
     *
     * @param controllers controllers
     * @throws IllegalArgumentException                              if controllers are null
     * @throws cz.vhromada.validators.exceptions.ValidationException if controllers contain null value
     */
    @Autowired
    public FrontController(final List<Controller<?>> controllers) {
        Validators.validateArgumentNotNull(controllers, "Controllers");
        Validators.validateCollectionNotContainNull(controllers, "Controllers");

        this.flowControllerMap = new HashMap<>();
        for (final Controller<?> controller : controllers) {
            this.flowControllerMap.put(controller.getFlow(), controller);
        }
    }

    /**
     * Dispatches request to appropriate controller and returns its response.
     *
     * @param request request
     * @return list of page events
     */
    public <T> List<PageEvent> dispatch(final FrontControllerRequest<T> request) {
        final Controller<T> controller = lookup(request.getFlow());
        if (controller == null) {
            throw new RuntimeException("Bad request: no controller available");
        }

        controller.setUi(new UI());
        try {
            controller.handle(request.getData());
            return controller.getUi().getEvents();
        } finally {
            controller.setUi(null);
        }
    }

    /**
     * Returns controller for specified flow.
     *
     * @param flow flow
     * @return controller for specified flo
     */
    @SuppressWarnings("unchecked")
    private <T> Controller<T> lookup(final Flow flow) {
        return (Controller<T>) flowControllerMap.get(flow);
    }

}
package cz.vhromada.web.wicket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import cz.vhromada.web.wicket.event.PageEvent;

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
     * Controllers
     */
    @Autowired
    @SuppressWarnings({ "SpringAutowiredFieldsWarningInspection", "SpringJavaAutowiringInspection" })
    private List<Controller<?>> controllers;

    /**
     * Dispatches request to appropriate controller and returns its response.
     *
     * @param request request
     * @param <T>     type of data
     * @return list of page events
     */
    public <T> List<PageEvent> dispatch(final FrontControllerRequest<T> request) {
        final Controller<T> controller = lookup(request.getFlow());
        if (controller == null) {
            throw new IllegalArgumentException("Bad request: no controller available");
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
     * @param <T>  type of data
     * @return controller for specified flo
     */
    @SuppressWarnings("unchecked")
    private <T> Controller<T> lookup(final Flow flow) {
        return (Controller<T>) flowControllerMap.get(flow);
    }

    /**
     * Initializes map between flow and controller.
     */
    @PostConstruct
    private void init() {
        flowControllerMap = new HashMap<>();
        for (final Controller<?> controller : controllers) {
            flowControllerMap.put(controller.getFlow(), controller);
        }
    }

}

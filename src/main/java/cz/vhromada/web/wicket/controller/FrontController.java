package cz.vhromada.web.wicket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.vhromada.web.wicket.event.PageEvent;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents front controller.
 *
 * @author Vladimir Hromada
 */
@Component("frontController")
public class FrontController implements InitializingBean {

    /**
     * Map: flow -> controller
     */
    private Map<Flow, Controller<?>> flowControllerMap;

    /**
     * Controllers
     */
    @Autowired
    @SuppressWarnings({ "SpringJavaInjectionPointsAutowiringInspection", "SpringJavaAutowiredFieldsWarningInspection" })
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

    @Override
    public void afterPropertiesSet() {
        flowControllerMap = new HashMap<>();
        controllers.forEach(controller -> flowControllerMap.put(controller.getFlow(), controller));
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

}

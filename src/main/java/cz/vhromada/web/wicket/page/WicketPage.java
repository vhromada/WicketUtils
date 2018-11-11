package cz.vhromada.web.wicket.page;

import java.util.List;

import cz.vhromada.web.wicket.ComponentProvider;
import cz.vhromada.web.wicket.EmptyAjaxRequestTarget;
import cz.vhromada.web.wicket.controller.FrontController;
import cz.vhromada.web.wicket.controller.FrontControllerRequest;
import cz.vhromada.web.wicket.event.PageEvent;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * A class represents catalog page.
 *
 * @author Vladimir Hromada
 */
public abstract class WicketPage extends WebPage {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Provider for components
     */
    @SpringBean
    @SuppressWarnings("unused")
    private ComponentProvider componentProvider;

    /**
     * Front controller
     */
    @SpringBean
    @SuppressWarnings("unused")
    private FrontController frontController;

    @Override
    public void onEvent(final IEvent<?> event) {
        super.onEvent(event);

        final Object payload = event.getPayload();

        if (payload instanceof FrontControllerRequest<?>) {
            final List<PageEvent> pageEvents = frontController.dispatch((FrontControllerRequest<?>) payload);
            for (final PageEvent pageEvent : pageEvents) {
                send(this, Broadcast.BREADTH, pageEvent);
            }
        } else if (payload instanceof PageEvent) {
            onPageEvent((PageEvent) payload);
        }
    }

    /**
     * Returns AJAX request target.
     *
     * @return AJAX request target
     */
    protected static AjaxRequestTarget getAjaxRequestTarget() {
        return RequestCycle.get().find(AjaxRequestTarget.class).orElseGet(EmptyAjaxRequestTarget::new);
    }

    /**
     * Returns panel.
     *
     * @param id       Spring ID of panel
     * @param wicketId Wicket ID of panel
     * @param model    model of panel
     * @param <T>      type of model
     * @return panel
     */
    protected <T> GenericPanel<T> getPanel(final String id, final String wicketId, final IModel<T> model) {
        return componentProvider.getPanel(id, wicketId, model);
    }

    /**
     * Process page event.
     *
     * @param event page event
     */
    protected abstract void onPageEvent(PageEvent event);

}

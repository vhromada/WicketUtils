package cz.vhromada.web.wicket.pages;

import java.util.List;

import cz.vhromada.web.wicket.EmptyAjaxRequestTarget;
import cz.vhromada.web.wicket.controllers.FrontController;
import cz.vhromada.web.wicket.controllers.FrontControllerRequest;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
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
        final AjaxRequestTarget target = RequestCycle.get().find(AjaxRequestTarget.class);
        if (target != null) {
            return target;
        } else {
            return new EmptyAjaxRequestTarget();
        }
    }

    /**
     * Process page event.
     *
     * @param event page event
     */
    protected abstract void onPageEvent(final PageEvent event);

}


package cz.vhromada.web.wicket;

import java.util.Collection;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.request.ILogData;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * A class represents empty AJAX request target.
 *
 * @author Vladimir Hromada
 */
public class EmptyAjaxRequestTarget implements AjaxRequestTarget {

    @Override
    public void add(final Component component, final String markupId) {
    }

    @Override
    @SuppressWarnings("OverloadedVarargsMethod")
    public void add(final Component... components) {
    }

    @Override
    public void addChildren(final MarkupContainer parent, final Class<?> childCriteria) {
    }

    @Override
    public void addListener(final IListener listener) {
    }

    @Override
    public void appendJavaScript(final CharSequence javascript) {
    }

    @Override
    public void prependJavaScript(final CharSequence javascript) {
    }

    @Override
    public void registerRespondListener(final ITargetRespondListener listener) {
    }

    @Override
    public Collection<? extends Component> getComponents() {
        return null;
    }

    @Override
    public void focusComponent(final Component component) {
    }

    @Override
    public IHeaderResponse getHeaderResponse() {
        return null;
    }

    @Override
    public String getLastFocusedElementId() {
        return null;
    }

    @Override
    public Page getPage() {
        return null;
    }

    @Override
    public Integer getPageId() {
        return null;
    }

    @Override
    public boolean isPageInstanceCreated() {
        return false;
    }

    @Override
    public Integer getRenderCount() {
        return null;
    }

    @Override
    public ILogData getLogData() {
        return null;
    }

    @Override
    public Class<? extends IRequestablePage> getPageClass() {
        return null;
    }

    @Override
    public PageParameters getPageParameters() {
        return null;
    }

    @Override
    public void respond(final IRequestCycle iRequestCycle) {
    }

    @Override
    public void detach(final IRequestCycle iRequestCycle) {
    }

}

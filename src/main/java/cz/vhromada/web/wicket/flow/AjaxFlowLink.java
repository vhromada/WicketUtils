package cz.vhromada.web.wicket.flow;

import cz.vhromada.web.wicket.controller.Flow;
import cz.vhromada.web.wicket.controller.FlowRunner;

import org.apache.wicket.ajax.AjaxChannel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

/**
 * A class represents AJAX flow link.
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public class AjaxFlowLink<T> extends AjaxLink<T> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * AJAX channel
     */
    private static final String CHANNEL_FLOW_LINK = "channel.flow.link";

    /**
     * Flow
     */
    private final Flow flow;

    /**
     * Creates a new instance of AjaxFlowLink.
     *
     * @param id   ID
     * @param flow flow
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     * @throws IllegalArgumentException                 if flow is null
     */
    public AjaxFlowLink(final String id, final Flow flow) {
        this(id, null, flow);
    }

    /**
     * Creates a new instance of AjaxFlowLink.
     *
     * @param id    ID
     * @param model model
     * @param flow  flow
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     * @throws IllegalArgumentException                 if flow is null
     */
    public AjaxFlowLink(final String id, final IModel<T> model, final Flow flow) {
        super(id, model);

        if (flow == null) {
            throw new IllegalArgumentException("Flow mustn't be null.");
        }

        this.flow = flow;
    }

    @Override
    public void onClick(final AjaxRequestTarget target) {
        new FlowRunner(this, flow, getModel());
    }

    @Override
    protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);

        // allow only one running AJAX request at a time, other AJAX requests in the same channel will be dropped
        attributes.setChannel(new AjaxChannel(CHANNEL_FLOW_LINK, AjaxChannel.Type.ACTIVE));
    }

}

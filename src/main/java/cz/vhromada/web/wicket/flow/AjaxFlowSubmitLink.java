package cz.vhromada.web.wicket.flow;

import cz.vhromada.web.wicket.controller.Flow;
import cz.vhromada.web.wicket.controller.FlowRunner;

import org.apache.wicket.ajax.AjaxChannel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;

/**
 * A class represents AJAX flow submit link.
 *
 * @author Vladimir Hromada
 */
public class AjaxFlowSubmitLink extends AjaxSubmitLink {

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
     * Creates a new instance of AjaxFlowSubmitLink.
     *
     * @param id   ID
     * @param form form
     * @param flow flow
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     * @throws IllegalArgumentException                 if flow is null
     */
    public AjaxFlowSubmitLink(final String id, final Form<?> form, final Flow flow) {
        super(id, form);

        this.flow = flow;
    }

    @Override
    public void onSubmit(final AjaxRequestTarget target) {
        super.onSubmit(target);

        if (flow == null) {
            throw new IllegalArgumentException("Flow mustn't be null.");
        }

        new FlowRunner(this, flow, getForm().getModel());
    }

    @Override
    protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);

        // allow only one running AJAX request at a time, other AJAX requests in the same channel will be dropped
        attributes.setChannel(new AjaxChannel(CHANNEL_FLOW_LINK, AjaxChannel.Type.ACTIVE));
    }

}

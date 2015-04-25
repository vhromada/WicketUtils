package cz.vhromada.web.wicket.panels;

import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.ComponentProvider;

import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * An abstract class represents base panel.
 *
 * @param <T> type of model data
 * @author Vladimir Hromada
 */
public abstract class BasePanel<T> extends GenericPanel<T> {

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
     * Creates a new instance of BasePanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public BasePanel(final String id, final IModel<T> model) {
        super(id, model);
    }

    /**
     * Returns panel.
     *
     * @param id       Spring ID of panel
     * @param wicketId Wicket ID of panel
     * @param model    model of panel
     * @return panel
     * @throws IllegalArgumentException if Spring ID of panel is null
     *                                  or Wicket ID of panel is null
     */
    protected BasePanel getPanel(final String id, final String wicketId, final IModel<?> model) {
        Validators.validateArgumentNotNull(id, "Spring ID");
        Validators.validateArgumentNotNull(wicketId, "Wicket ID");

        return componentProvider.getPanel(id, wicketId, model);
    }

    /**
     * Returns empty panel.
     *
     * @param id Wicket ID of panel
     * @return panel
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    protected EmptyPanel getEmptyPanel(final String id) {
        return new EmptyPanel(id);
    }

}

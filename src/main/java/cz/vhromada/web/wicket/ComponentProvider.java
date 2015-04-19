package cz.vhromada.web.wicket;

import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * A class represents provider for components.
 *
 * @author Vladimir Hromada
 */
@Component("componentProvider")
public class ComponentProvider {

    /**
     * Application context
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Returns panel.
     *
     * @param id       Spring ID of panel
     * @param wicketId Wicket ID of panel
     * @param model    model of panel
     * @return panel
     */
    public BasePanel getPanel(final String id, final String wicketId, final IModel<?> model) {
        return (BasePanel) applicationContext.getBean(id, wicketId, model);
    }

}

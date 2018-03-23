package cz.vhromada.web.wicket;

import org.apache.wicket.markup.html.panel.GenericPanel;
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
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    private ApplicationContext applicationContext;

    /**
     * Returns panel.
     *
     * @param id       Spring ID of panel
     * @param wicketId Wicket ID of panel
     * @param model    model of panel
     * @param <T>      type of model
     * @return panel
     */
    @SuppressWarnings("unchecked")
    public <T> GenericPanel<T> getPanel(final String id, final String wicketId, final IModel<T> model) {
        return (GenericPanel<T>) applicationContext.getBean(id, wicketId, model);
    }

}

package cz.vhromada.web.wicket.controller;

/**
 * An abstract class represents controller.
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public abstract class Controller<T> {

    /**
     * Thread local user interface
     */
    private final ThreadLocal<UI> uiThreadLocal = new ThreadLocal<>();

    /**
     * Handles data.
     *
     * @param data data
     */
    public abstract void handle(T data);

    /**
     * Returns flow.
     *
     * @return flow
     */
    public abstract Flow getFlow();

    /**
     * Returns user interface for scheduling actions.
     *
     * @return user interface for scheduling actions
     */
    public UI getUi() {
        return uiThreadLocal.get();
    }

    /**
     * Set user interface.
     *
     * @param ui new value
     */
    public void setUi(final UI ui) {
        if (ui == null) {
            uiThreadLocal.remove();
        } else {
            uiThreadLocal.set(ui);
        }
    }

}

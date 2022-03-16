package games.rednblack.puremvc;

import games.rednblack.puremvc.interfaces.IMediator;
import games.rednblack.puremvc.interfaces.INotification;
import games.rednblack.puremvc.util.Interests;

public class Mediator<T> extends Notifier implements IMediator {

    private final String name;
    protected T viewComponent;

    public Mediator(String name, T component) {
        viewComponent = component;
        this.name = name;
    }

    @Override
    public final String getName() {
        return name;
    }

    public void setViewComponent(T viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Override
    public void listNotificationInterests(Interests interests) {

    }

    @Override
    public void handleNotification(INotification notification) {

    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }

    @Override
    public T getViewComponent() {
        return viewComponent;
    }
}

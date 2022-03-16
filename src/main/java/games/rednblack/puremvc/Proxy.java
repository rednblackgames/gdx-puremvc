package games.rednblack.puremvc;

import games.rednblack.puremvc.interfaces.IProxy;

public class Proxy<T> extends Notifier implements IProxy<T> {

    private final String name;
    protected T data;

    public Proxy(String name, T data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }
}

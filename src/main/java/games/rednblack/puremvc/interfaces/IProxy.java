package games.rednblack.puremvc.interfaces;

public interface IProxy<T> extends INotifiable {
    String getName();

    T getData();
}

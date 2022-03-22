package games.rednblack.puremvc.interfaces;

public interface IProxy<T> extends IRegistrable {
    String getName();

    T getData();
}

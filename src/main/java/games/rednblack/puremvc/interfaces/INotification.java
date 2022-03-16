package games.rednblack.puremvc.interfaces;

public interface INotification {

    String getName();

    <T> T getBody();

    String getType();

}

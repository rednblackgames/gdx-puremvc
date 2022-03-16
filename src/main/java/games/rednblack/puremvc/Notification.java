package games.rednblack.puremvc;

import com.badlogic.gdx.utils.Pool;

import games.rednblack.puremvc.interfaces.INotification;

public class Notification implements INotification, Pool.Poolable {

    private String name = null;
    private Object body = null;
    private String type = null;

    void setBody(Object body) {
        this.body = body;
    }

    void setName(String name) {
        this.name = name;
    }

    void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Object getBody() {
        return body;
    }

    public Notification copy() {
        Notification copy = new Notification();

        copy.setName(name);
        copy.setType(type);
        copy.setBody(body);

        return copy;
    }

    @Override
    public void reset() {
        this.body = null;
        this.name = null;
        this.type = null;
    }
}

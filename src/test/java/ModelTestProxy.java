//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

import games.rednblack.puremvc.Proxy;

public class ModelTestProxy extends Proxy<String> {

    public static final String NAME = "ModelTestProxy";
    public static final String ON_REGISTER_CALLED = "onRegister Called";
    public static final String ON_REMOVE_CALLED = "onRemove Called";

    public ModelTestProxy() {
        super(NAME, "");
    }

    public void onRegister() {
        setData(ON_REGISTER_CALLED);
    }

    public void onRemove() {
        setData(ON_REMOVE_CALLED);
    }
}
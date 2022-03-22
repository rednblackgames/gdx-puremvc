//
//  PureMVC Java Standard
//
//  Copyright(c) 2019 Saad Shams <saad.shams@puremvc.org>
//  Your reuse is governed by the Creative Commons Attribution 3.0 License
//

import games.rednblack.puremvc.Facade;
import games.rednblack.puremvc.Proxy;
import games.rednblack.puremvc.interfaces.IProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {

    /**
     * Tests the Model Singleton Factory Method
     */
    @Test
    public void testGetInstance() {
        // Test Factory Method
        Facade facade = Facade.getInstance();

        // test assertions
        Assertions.assertNotNull(facade, "Expecting instance not null");
    }

    /**
     * Tests the proxy registration and retrieval methods.
     *
     * <P>Tests <code>registerProxy</code> and <code>retrieveProxy</code> in the same test.
     * These methods cannot currently be tested separately
     * in any meaningful way other than to show that the
     * methods do not throw exception when called. </P>
     */
    @Test
    public void testRegisterAndRetrieveProxy() {
        // register a proxy and retrieve it.
        Facade facade = Facade.getInstance();
        facade.registerProxy(new Proxy<>("colors", new String[]{"red", "green", "blue"}));
        IProxy<String[]> proxy = facade.retrieveProxy("colors");
        String[] data = proxy.getData();

        // test assertions
        Assertions.assertNotNull(data, "Expecting data not null");
        Assertions.assertNotNull((String[])data, "Expecting data type is Array");
        Assertions.assertEquals(3, data.length, "Expecting data.length == 3");
        Assertions.assertSame("red", data[0], "Expecting data[0] == 'red'");
        Assertions.assertSame("green", data[1], "Expecting data[1] == 'green'");
        Assertions.assertSame("blue", data[2], "Expecting data[2] == 'blue'");
    }

    /**
     * Tests the proxy removal method.
     */
    @Test
    public void testRegisterAndRemoveProxy() {
        // register a proxy, remove it, then try to retrieve it
        Facade facade = Facade.getInstance();
        IProxy<String[]> proxy = new Proxy<>("sizes", new String[]{"7", "13", "21"});
        facade.registerProxy(proxy);

        // remove the proxy
        IProxy<String[]> removedProxy = facade.removeProxy("sizes");

        // assert that we removed the appropriate proxy
        Assertions.assertSame("sizes", removedProxy.getName(), "Expecting removedProxy.getProxyName() == 'sizes'");

        // ensure that the proxy is no longer retrievable from the model
        proxy = facade.retrieveProxy("sizes");

        // test assertions
        Assertions.assertNull(proxy, "Expecting proxy is null");
    }

    /**
     * Tests the hasProxy Method
     */
    @Test
    public void testHasProxy() {
        // register a proxy
        Facade facade = Facade.getInstance();
        IProxy<String[]> proxy = new Proxy<>("aces", new String[]{"clubs", "spades", "hearts", "diamonds"});
        facade.registerProxy(proxy);

        // assert that the model.hasProxy method returns true
        // for that proxy name
        Assertions.assertTrue(facade.hasProxy("aces"), "Expecting model.hasProxy('aces') == true");

        // remove the proxy
        facade.removeProxy("aces");

        // assert that the model.hasProxy method returns false
        // for that proxy name
        Assertions.assertFalse(facade.hasProxy("aces"), "Expecting model.hasProxy('aces') == false");
    }

    /**
     * Tests that the Model calls the onRegister and onRemove methods
     */
    @Test
    public void testOnRegisterAndOnRemove() {
        // Get the Singleton View instance
        Facade facade = Facade.getInstance();

        // Create and register the test mediator
        IProxy<String> proxy = new ModelTestProxy();
        facade.registerProxy(proxy);

        // assert that onRegsiter was called, and the proxy responded by setting its data accordingly
        Assertions.assertSame(ModelTestProxy.ON_REGISTER_CALLED, proxy.getData(), "Expecting proxy.getData() == ModelTestProxy.ON_REGISTER_CALLED");

        // Remove the component
        facade.removeProxy(ModelTestProxy.NAME);

        // assert that onRemove was called, and the proxy responded by setting its data accordingly
        Assertions.assertSame(ModelTestProxy.ON_REMOVE_CALLED, proxy.getData(), "Expecting proxy.getData() == ModelTestProxy.ON_REMOVE_CALLED");
    }

}
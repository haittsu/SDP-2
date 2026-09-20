package factorymethod;

public abstract class Logistics {

    public final void planDelivery(String cargo, String destination) {
        Transport transport = createTransport();
        transport.deliver(cargo, destination);
    }

    protected abstract Transport createTransport();
}

package app;

import abstractfactory.Button;
import abstractfactory.Checkbox;
import abstractfactory.GUIFactory;
import factorymethod.Logistics;

import java.util.Objects;

public class DeliveryApplication {
    private static final String CARGO = "laboratory equipment";
    private static final String DESTINATION = "Aktau warehouse";

    private final GUIFactory guiFactory;
    private final Logistics logistics;

    public DeliveryApplication(GUIFactory guiFactory, Logistics logistics) {
        this.guiFactory = Objects.requireNonNull(guiFactory, "guiFactory must not be null");
        this.logistics = Objects.requireNonNull(logistics, "logistics must not be null");
    }

    public void run() {
        renderInterface();
        planDelivery();
    }

    private void renderInterface() {
        Button button = guiFactory.createButton();
        Checkbox checkbox = guiFactory.createCheckbox();
        button.paint();
        checkbox.paint();
    }

    private void planDelivery() {
        logistics.planDelivery(CARGO, DESTINATION);
    }
}
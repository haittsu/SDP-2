package app;

import abstractfactory.GUIFactory;
import abstractfactory.MacOSFactory;
import abstractfactory.WindowsFactory;
import factorymethod.Logistics;
import factorymethod.RoadLogistics;
import factorymethod.SeaLogistics;

import java.util.Scanner;

public final class Main {
    private static final String USAGE =
            "Usage: java -cp out app.Main <ROAD|SEA> <WINDOWS|MACOS>";

    private record RawChoices(String deliveryMode, String uiPlatform) {
    }

    private Main() {
    }

    public static void main(String[] args) {
        try {
            RawChoices raw = readRawChoices(args);
            DeliveryMode mode = ChoiceParser.parse(DeliveryMode.class, "delivery mode", raw.deliveryMode());
            UiPlatform platform = ChoiceParser.parse(UiPlatform.class, "UI platform", raw.uiPlatform());
            start(mode, platform);
        } catch (InvalidChoiceException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static RawChoices readRawChoices(String[] args) throws InvalidChoiceException {
        if (args.length == 0) {
            return promptForChoices();
        }
        if (args.length == 2) {
            return new RawChoices(args[0], args[1]);
        }
        throw new InvalidChoiceException("Expected 2 arguments (delivery mode and UI platform) "
                + "or none for interactive input, but got " + args.length + ". " + USAGE);
    }

    private static RawChoices promptForChoices() {
        Scanner scanner = new Scanner(System.in);
        String mode = prompt(scanner, "Delivery mode (ROAD/SEA): ");
        String platform = prompt(scanner, "UI platform (WINDOWS/MACOS): ");
        return new RawChoices(mode, platform);
    }

    private static String prompt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    private static void start(DeliveryMode mode, UiPlatform platform) {
        Logistics logistics = createLogistics(mode);
        GUIFactory guiFactory = createGuiFactory(platform);

        System.out.println("Delivery mode: " + mode);
        System.out.println("UI platform: " + platform);

        new DeliveryApplication(guiFactory, logistics).run();
    }

    private static Logistics createLogistics(DeliveryMode mode) {
        return switch (mode) {
            case ROAD -> new RoadLogistics();
            case SEA -> new SeaLogistics();
        };
    }

    private static GUIFactory createGuiFactory(UiPlatform platform) {
        return switch (platform) {
            case WINDOWS -> new WindowsFactory();
            case MACOS -> new MacOSFactory();
        };
    }
}

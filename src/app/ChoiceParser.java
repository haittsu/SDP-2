package app;

import java.util.Arrays;
import java.util.Locale;

final class ChoiceParser {

    private ChoiceParser() {
    }

    static <E extends Enum<E>> E parse(Class<E> choiceType, String label, String rawValue)
            throws InvalidChoiceException {
        if (rawValue == null || rawValue.isBlank()) {
            throw new InvalidChoiceException(
                    "Missing " + label + ". Supported values: " + supportedValues(choiceType));
        }
        String trimmed = rawValue.trim();
        try {
            return Enum.valueOf(choiceType, trimmed.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new InvalidChoiceException(
                    "Unsupported " + label + " '" + trimmed + "'. Supported values: "
                            + supportedValues(choiceType));
        }
    }

    private static <E extends Enum<E>> String supportedValues(Class<E> choiceType) {
        return Arrays.toString(choiceType.getEnumConstants());
    }
}
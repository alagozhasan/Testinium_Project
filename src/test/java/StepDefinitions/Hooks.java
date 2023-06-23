package StepDefinitions;

import Utilities.BaseDriver;
import org.junit.jupiter.api.AfterAll;

public class Hooks {
    public static int generateRandomNumber(int size) {
        int randomChoice;
        randomChoice = (int) (Math.random()*size);
        return randomChoice;
    }

    public static float converterFloat(String price){
        String cleanedPrice = price.replaceAll("[^0-9]", "").replaceFirst("(\\d{1,})(\\d{2})$", "$1.$2");
        return Float.parseFloat(cleanedPrice);
    }


}

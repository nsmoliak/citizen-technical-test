package is.citizen.technicaltest.utils;

import is.citizen.technicaltest.model.PersonModel;

import java.util.Objects;

public class ComparativeHelper {
    public static boolean isEmpty(String str) {
        return str == null || Objects.equals(str, "");
    }

    public static boolean countryCodeCondition(PersonModel p1, PersonModel p2) {
        return (isEmpty(p1.getCountryCode()) && !isEmpty(p2.getCountryCode()))
                && (p2.getState().equals(p1.getState())
                || p2.getCity().equals(p1.getCity())
                || p2.getPostcode().equals(p1.getPostcode()));
    }

    public static boolean stateCondition(PersonModel p1, PersonModel p2) {
        return (isEmpty(p1.getState()) && !isEmpty(p2.getState())
                && (p1.getCity().equals(p2.getCity())
                || p1.getPostcode().equals(p2.getPostcode())));
    }

    public static boolean surnameCondition(PersonModel p1, PersonModel p2) {
        return (isEmpty(p1.getSurname()) && !isEmpty(p2.getSurname())
                && (p1.getPostcode().equals(p2.getPostcode())
                || p1.getFirstAddress().equals(p2.getFirstAddress())
                || p1.getFirstAddress().equals(p2.getSecondAddress())
                || p1.getSecondAddress().equals(p2.getSecondAddress())));
    }
}

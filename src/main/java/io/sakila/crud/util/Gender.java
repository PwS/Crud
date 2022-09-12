package io.sakila.crud.util;

import java.util.Arrays;
import java.util.Optional;

public enum Gender {
    M("Male"),
    F("Female");

    private final String genderType;

    Gender(String genderType) {
        this.genderType = genderType;
    }

    static String getGenderType(String genderType) {
        Optional<Gender> optionalGenderTypeEnum = Arrays.stream(Gender.values()).filter(element -> element.genderType.equalsIgnoreCase(genderType)).findFirst();
        return optionalGenderTypeEnum.map(Enum::name).orElseGet(() -> genderType + ConstantValue.UNRECOGNIZED);
    }

}

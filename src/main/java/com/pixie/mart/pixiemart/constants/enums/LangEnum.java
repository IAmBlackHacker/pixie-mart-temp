package com.pixie.mart.pixiemart.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LangEnum {
    EN("English"), HIN("Hindi"), KANNADA("Kannada"), TAMIL("Tamil"), SANSKRIT("Sanskrit");

    private final String language;
}

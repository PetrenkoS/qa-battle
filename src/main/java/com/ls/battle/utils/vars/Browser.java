package com.ls.battle.utils.vars;

public enum Browser {
    FIREFOX("firefox"),
    CHROME("chrome");

    private final String browser;

    Browser(String browser) {
        this.browser = browser;
    }

    public String getValue() {
        return browser;
    }

    @Override
    public String toString() {
        return browser;
    }

    public static Browser toEnum(String value) {
        for (Browser b : values()) {
            if (b.getValue().equalsIgnoreCase(value)) {
                return b;
            }
        }
        return CHROME;
    }

}

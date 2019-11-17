package com.davidrosu.ciucaslapet;

public class Weight {
    public String username;

    public Weight(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        String string = "{";

        string += "\"username\":\"";
        string += username;
        string += "\"";

        string += "}";

        return string;
    }
}

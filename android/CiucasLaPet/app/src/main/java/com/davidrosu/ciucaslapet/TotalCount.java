package com.davidrosu.ciucaslapet;

import java.sql.Timestamp;

public class TotalCount {
    public String username;

    public TotalCount(String username) {
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

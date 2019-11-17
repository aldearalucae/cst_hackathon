package com.davidrosu.ciucaslapet;

import java.sql.Time;
import java.sql.Timestamp;

public class Training {
    public String start_timestamp;
    public String username;
    public int totalCount;
    public int successCount;
    public int calories;

    public Training(String start_timestamp, String username, int totalCount, int successCount, int calories) {
        this.start_timestamp = start_timestamp;
        this.username = username;
        this.totalCount = totalCount;
        this.successCount = successCount;
        this.calories = calories;
    }

    @Override
    public String toString() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String stop_timestamp = String.valueOf(timestamp.getTime());

        String string = "{";

        string += "\"username\":\"";
        string += username;
        string += "\",";

        string += "\"start_timestamp\":\"";
        string += start_timestamp;
        string += "\",";

        string += "\"stop_timestamp\":\"";
        string += stop_timestamp;
        string += "\",";

        string += "\"total_count\":\"";
        string += totalCount;
        string += "\",";

        string += "\"success_count\":\"";
        string += successCount;
        string += "\",";

        string += "\"calories_count\":\"";
        string += calories;
        string += "\"";

        string += "}";

        return string;
    }
}

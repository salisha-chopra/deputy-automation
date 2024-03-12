package org.components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateExecution {
    public String getDueDate(){
        LocalDate currentDate = LocalDate.now();

        // Add 10 days to the current date
        LocalDate futureDate = currentDate.plusDays(10);

        // Format the future date as a string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String futureDateString = futureDate.format(formatter);
        return futureDateString;
    }
}

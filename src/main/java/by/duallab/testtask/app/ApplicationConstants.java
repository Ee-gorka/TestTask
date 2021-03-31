package by.duallab.testtask.app;

import java.time.format.DateTimeFormatter;

public class ApplicationConstants {
    public static final String PATH_TO_OUTPUT_FILE = "src/output.txt";
    public static final int MINUTES_IN_HOUR = 60;
    public static final int HOURS_IN_DAY = 24;
    public static final String TIME_PATTERN = "HH:mm";
    public static final DateTimeFormatter FORMAT_TO_PRINT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    public static final String SERVICE_POSH_NAME = "Posh";
    public static final String SERVICE_GROTTY_NAME = "Grotty";
    public static final String LF = "\n";
    public static final String SPACE = " ";
}

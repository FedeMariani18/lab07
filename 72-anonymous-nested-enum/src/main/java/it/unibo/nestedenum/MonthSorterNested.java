package it.unibo.nestedenum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    enum Month{
        JANUARY("january", 31 ),
        FEBRUARY("february", 28),
        MARCH("march", 31),
        APRIL("april", 30),
        MAY("may", 31),
        JUNE("june", 30),
        JULY("july", 31),
        AUGUST("august", 31),
        SPTEMBER("september", 30),
        OCTOBER("october", 31),
        NOVEMBER("november", 30),
        DECEMBER("december", 31);

        private final String monthName;
        private final int nDays;

        private Month(final String monthName, final int nMonthDays){
            this.monthName = monthName;
            this.nDays = nMonthDays;
        }

        public String getName(){
            return this.monthName;
        }

        public int getnDays() {
            return this.nDays;
        }

        public static Month fromString(final String input) throws IOException {
            var lawerInput = input.toLowerCase();
            Month[] monthes = Month.values();
            int match = 0;
            Month candidate = null;

            while(lawerInput != ""){
                for (var e: monthes) {
                    if (e.getName().contains(lawerInput)) {
                        match++;
                        candidate = e;
                    }
                }

                if (match == 1) {
                    return candidate;
                } else {
                    candidate = null;
                    match = 0;
                    lawerInput = lawerInput.substring(0, lawerInput.length() - 1); //remove the last letter
                }
            }
            throw new IOException("input not valid: ambiguos or undefined");
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {
            public int compare(String o1, String o2) {
                int res = 0;
                try {
                    res = Integer.compare(Month.fromString(o1).getnDays(), Month.fromString(o2).getnDays());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.exit(1); 
                }
                return res;
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {
            public int compare(String o1, String o2) {
                int res = 0;
                try {
                    res = Integer.compare(Month.fromString(o1).ordinal() , Month.fromString(o2).ordinal());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.exit(1); 
                }
                return res;
            }
        };
    }
}

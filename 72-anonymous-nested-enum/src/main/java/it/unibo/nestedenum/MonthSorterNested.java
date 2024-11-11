package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final Comparator<String> BY_DAYS = new SortByDays();
    private static final Comparator<String> BY_ORDER = new SortByMonthOrder();

    @Override
    public Comparator<String> sortByDays() {
        return BY_DAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BY_ORDER;
    }

    enum Month{
        JANUARY(31 ),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int nDays;

        private Month(final int nMonthDays){
            this.nDays = nMonthDays;
        }

        public int getnDays() {
            return this.nDays;
        }

        public static Month fromString(final String name){
             Objects.requireNonNull(name);
            try {
                return valueOf(name);
            } catch (IllegalArgumentException e) {
                // Fallback to manual scan before giving up
                Month match = null;
                for (final Month month: values()) {
                    if (month.toString().toLowerCase(Locale.ROOT).startsWith(name.toLowerCase(Locale.ROOT))) {
                        if (match != null) {
                            throw new IllegalArgumentException(
                                name + " is ambiguous: both " + match + " and " + month + " would be valid matches",
                                e
                            );
                        }
                        match = month;
                    }
                }
                if (match == null) {
                    throw new IllegalArgumentException("No matching months for " + name, e);
                }
                return match;
            }
        }
    }

    private static final class SortByDays implements Comparator<String> {    
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).getnDays(), Month.fromString(o2).getnDays());
        }

    }

    private static final class SortByMonthOrder implements Comparator<String> {
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).ordinal() , Month.fromString(o2).ordinal()); 
        }
    }
}

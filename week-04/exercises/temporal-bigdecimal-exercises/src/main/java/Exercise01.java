import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Exercise01 {

    // LocalDate
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. return today's date
    LocalDate getToday() {
        LocalDate today = LocalDate.now();
        return today;
    }

    // 2. return December 17, 1903, as a LocalDate
    LocalDate getFirstFlightDate() {
        LocalDate before = LocalDate.of(1903, 12, 3);
        return before;
    }

    // 3. if parameter is in the future, return null.
    // Otherwise, add 5 days to the parameter and return the result.
    LocalDate makeFutureNullShiftThePast(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            return null;
        } else {
            date.plusDays(5);
            return date;
        }
    }

    // 4. return the fifth Friday from the parameter date.
    // if the date is Friday, don't count it.
    LocalDate fiveFridaysFromDate(LocalDate date) {
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            return null;
        } else {
            date.plusWeeks(5);
            return date;
        }
    }

    // 5. given a date and a count,
    // return a list of the next `fridayCount` Fridays after the date.
    // if the date is Friday, don't include it.

    List<LocalDate> getNextFridays(LocalDate date, int fridayCount) {
        List <LocalDate> dates = new ArrayList<>();
        if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            return null;
        } else {
            date.plusWeeks(fridayCount);
            return dates;
            }
        }


    // 6. return the absolute value of the days between two dates.
    // one may be before two, two may be before one, but neither will be null
    int getDaysBetween(LocalDate one, LocalDate two) {
        Period difference = Period.between(one,two);
        return Math.abs(difference.getDays());
    }

}

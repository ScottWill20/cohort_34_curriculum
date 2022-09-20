package learn;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * A schedule of MicroLeases where no lease can overlap another.
 */
public class NonOverlappingMicroLeaseSchedule {

    // on success, a MicroLease is stored in leases
    private ArrayList<MicroLease> leases = new ArrayList<>();

    // 1. Complete the add method.

    /**
     * Attempts to add a MicroLease to the Schedule.
     * Rules:
     * - if lease is null, return false
     * - if lease.getStart or lease.getEnd is null, return false
     * - if lease.getStart is later then lease.getEnd, return false
     * - if the lease overlaps any other lease in leases, return false
     * - otherwise, add to leases and return true
     *
     * @param lease - a MicroLease to be added to the schedule.
     * @return true if MicroLease is valid (see rules)
     * false if not valid
     */
    public boolean add(MicroLease lease) {
        if (lease == null)
            return false;
        if (lease.getStart() == null || lease.getEnd() == null) {
            return false;
        }
        int daysBetween = Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(lease.getStart(), lease.getEnd())));
        if (daysBetween < 0){
            return false;
        }

        leases.add(lease);
        if (leases.size() > 1) {
            for (int i = leases.size() - 2; i < leases.size() - 1; i++) {
                int daysBetween2 = Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(leases.get(i).getEnd(), lease.getStart())));
                if (daysBetween2 < 0) {
                    return false;
                }
            }
        }

        return true;
    }
}


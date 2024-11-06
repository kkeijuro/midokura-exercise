package org.midokura.exercise;

/**
 * Represents a group of customers that arrives at the restaurant.
 */
public class CustomerGroup {
    /*
     * The number of people in the group.
     */
    public final int size; //number of people in the group
    /**
     * True if the group is waiting to be seated, false otherwise.
     */
    public boolean is_waiting = false;

    /**
     * Creates a new customer group with the given size.
     */
    CustomerGroup(int size) {
        this.size = size;
    }
}

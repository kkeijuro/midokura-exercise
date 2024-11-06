package org.midokura.exercise;

/**
 * Represents a table in the restaurant.
 */
public class Table {
    /**
     * The number of customers that can be seated at this table.
     */
    public final int size; //number of chairs around this table
    private CustomerGroup group = null;

    /**
     * Creates a new table with the given size.
     */
    Table(int size) {
        this.size = size;
        this.group = null;
    }

    /**
     * Returns the group seated at this table null if no group is seated.
     */
    public CustomerGroup getCostumerGroup() {
        return this.group;
    }

    /**
     * Seats the given group at this table.
     */
    public void seat(CustomerGroup group) {
        this.group = group;
    }

    /**
     * Removes the group seated at this table.
     */
    public void leave() {
        this.group = null;
    }
}
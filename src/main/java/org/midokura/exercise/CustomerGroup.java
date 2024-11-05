package org.midokura.exercise;

public class CustomerGroup {
    public final int size; //number of people in the group
    public boolean is_waiting = false;

    CustomerGroup(int size) {
        this.size = size;
    }
}

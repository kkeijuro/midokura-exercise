package org.midokura.exercise;

public class Table {
    public final int size; //number of chairs around this table
    private CustomerGroup group = null;

    Table(int size) {
        this.size = size;
        this.group = null;
    }

    public CustomerGroup getCostumerGroup() {
        return this.group;
    }

    public void seat(CustomerGroup group) {
        this.group = group;
    }

    public void leave() {
        this.group = null;
    }
}
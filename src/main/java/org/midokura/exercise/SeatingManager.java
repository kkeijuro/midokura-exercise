package org.midokura.exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class SeatingManager {
    TreeMap<Integer, LinkedList<Table>> sorted_tables = new TreeMap<>();
    TreeMap<Integer, LinkedList<Table>> occupied_tables = new TreeMap<>();

    LinkedList<CustomerGroup>  customers_waiting = new LinkedList<>();

    public SeatingManager(List<Table> tables) {
        for(Table table : tables) {
            if (!this.sorted_tables.containsKey(table.size)) {
                this.sorted_tables.put(table.size, new LinkedList<Table>());
                this.sorted_tables.get(table.size).add(table);
            }
            else
                this.sorted_tables.get(table.size).add(table);
        }
    };

    /**
     * Returns the tables available in the restaurant with no customers seated.
     * @return TreeSet<Table> the tables available in the restaurant
     */
    TreeMap<Integer, LinkedList<Table>> getTables() {
        return this.sorted_tables;
    }

    LinkedList<CustomerGroup> getCustomersWaiting() {
        return this.customers_waiting;
    }

    /**
     * Returns the tables available in the restaurant 
     * with a size greater than or equal to the given size.
     * @param size
     * @return
     */
    public SortedMap<Integer, LinkedList<Table>> getFreeTablesFromSize(int size) {
        return this.sorted_tables.tailMap(size);
    }

    /**
     * Returns the tables occupied in the restaurant 
     * with a size greater than or equal to the given size.
     * @param size
     * @return
     */
    public SortedMap<Integer, LinkedList<Table>> getOccupiedTablesFromSize(int size) {
        return this.occupied_tables.tailMap(size);
    }
    /**
     *  Group arrives and wants to be seated. 
     *  @param group the group that arrives.
     * */
    public synchronized boolean arrives(CustomerGroup group) {
        SortedMap<Integer, LinkedList<Table>> tables = getFreeTablesFromSize(group.size);
        for (LinkedList<Table> table_list : tables.values()) {
            if (!table_list.isEmpty()) {
                Table table = table_list.removeFirst();
                putTableAsOccupied(table);
                table.seat(group);
                return true;
            }
        }
        if(!group.is_waiting)
            this.customers_waiting.add(group);
        return false;
    }

    private void putTableAsOccupied(Table table) {
        if (!this.occupied_tables.containsKey(table.size)) {
            this.occupied_tables.put(table.size, new LinkedList<Table>());
            this.occupied_tables.get(table.size).add(table);
        }
        else
            this.occupied_tables.get(table.size).add(table);
    }

    /* Whether seated or not, the group leaves the restaurant. */
    public synchronized void leaves(CustomerGroup group) {
        Table table = locate(group);
        if (table != null) {
            table.leave();
            this.sorted_tables.get(table.size).add(table);
            this.occupied_tables.get(table.size).remove(table);
            if (!this.customers_waiting.isEmpty()) {
                for(CustomerGroup waiting_group : this.customers_waiting) {
                    if(waiting_group.size <= table.size) {
                        if(arrives(waiting_group)) {
                            waiting_group.is_waiting = false;
                            this.customers_waiting.remove(waiting_group);
                        }
                    }
                }
            }
        }
        else {
            this.customers_waiting.remove(group);
            group.is_waiting = false;
        }
    }
    /* Return the table at which the group is seated, or null if
    they are not seated (whether they're waiting or already left). */
    public Table locate(CustomerGroup group) {
        SortedMap<Integer, LinkedList<Table>> tables = getOccupiedTablesFromSize(group.size);
        for(LinkedList<Table> table_list : tables.values()) {
            for(Table table : table_list) {
                if (table.getCostumerGroup() == group)
                    return table;
            }
        }
        return null;
    }
}

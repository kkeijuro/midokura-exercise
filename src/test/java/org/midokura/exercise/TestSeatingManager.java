package org.midokura.exercise;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.SortedMap;
import java.util.LinkedList;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class TestsSeatingManaget {

    @Test
    void givenTableList_whenSeatingManagerCreated_thenTablesAreSorted() {
        Table table1 = new Table(2);
        Table table2 = new Table(4);
        Table table3 = new Table(2);
        Table table4 = new Table(4);
        Table table5 = new Table(3);
        Table table6 = new Table(6);
        Table table7 = new Table(4);
        Table table8 = new Table(6);
        List<Table> tables = List.of(table1, table2, table3, table4, table5, table6, table7, table8);

        SeatingManager seatingManager = new SeatingManager(tables);
        TreeMap<Integer, LinkedList<Table>> sorted_tables = seatingManager.getTables();
        assertEquals(2, sorted_tables.get(2).size());
        assertEquals(1, sorted_tables.get(3).size());
        assertEquals(3, sorted_tables.get(4).size());
        assertEquals(2, sorted_tables.get(6).size());
    }

    @Test
    void givenTableList_whenSeatingManagerCreatedWithTables_GetTablefromXsize() {
        Table table1 = new Table(2);
        Table table2 = new Table(4);
        Table table3 = new Table(2);
        Table table4 = new Table(4);
        Table table5 = new Table(6);
        List<Table> tables = List.of(table1, table2, table3, table4, table5);

        SeatingManager seatingManager = new SeatingManager(tables);
        SortedMap<Integer, LinkedList<Table>> sorted_tables = seatingManager.getFreeTablesFromSize(3);
        assertEquals(2, sorted_tables.get(4).size());
        assertEquals(1, sorted_tables.get(6).size());
        assertEquals(sorted_tables.size(), 2);
    }

    @Test
    void givenTableList_whenSeatingManagerCreatedWithTablesFreeAndCustomerArrives_thenCustomerSeats() {
        Table table1 = new Table(2);
        Table table2 = new Table(4);
        Table table3 = new Table(2);
        Table table4 = new Table(4);
        Table table5 = new Table(6);
        List<Table> tables = List.of(table1, table2, table3, table4, table5);
        CustomerGroup group1 = new CustomerGroup(4);
        SeatingManager seatingManager = new SeatingManager(tables);
        seatingManager.arrives(group1);
        assertEquals(table2.getCostumerGroup(), group1);
        CustomerGroup group2 = new CustomerGroup(4);
        seatingManager.arrives(group2);
        assertEquals(table4.getCostumerGroup(), group2);
        CustomerGroup group3 = new CustomerGroup(4);
        seatingManager.arrives(group3);
        assertEquals(table5.getCostumerGroup(), group3);
    }
    @Test
    void givenTableList_whenSeatingManagerCreatedWithNoTablesFree_ThenCustomerWaits() {
        Table table1 = new Table(2);
        Table table2 = new Table(4);
        Table table3 = new Table(2);
        Table table4 = new Table(4);
        Table table5 = new Table(6);
        List<Table> tables = List.of(table1, table2, table3, table4, table5);
        CustomerGroup group1 = new CustomerGroup(4);
        SeatingManager seatingManager = new SeatingManager(tables);
        seatingManager.arrives(group1);
        CustomerGroup group2 = new CustomerGroup(4);
        seatingManager.arrives(group2);
        CustomerGroup group3 = new CustomerGroup(4);
        seatingManager.arrives(group3);
        CustomerGroup group4 = new CustomerGroup(4);
        seatingManager.arrives(group4);
        assertEquals(table2.getCostumerGroup(), group1);
        assertEquals(table4.getCostumerGroup(), group2);
        assertEquals(table5.getCostumerGroup(), group3);
        assertEquals(seatingManager.customers_waiting.size(), 1);
    }

    @Test
    void givenTableList_whenSeatingManagerCreatedWithNoTablesFreeAndAGroupLeaves_ThenTableIsOccupied() {
        Table table1 = new Table(2);
        Table table2 = new Table(4);
        Table table3 = new Table(2);
        Table table4 = new Table(4);
        Table table5 = new Table(6);
        List<Table> tables = List.of(table1, table2, table3, table4, table5);
        CustomerGroup group1 = new CustomerGroup(4);
        SeatingManager seatingManager = new SeatingManager(tables);
        seatingManager.arrives(group1);
        CustomerGroup group2 = new CustomerGroup(4);
        seatingManager.arrives(group2);
        CustomerGroup group3 = new CustomerGroup(4);
        seatingManager.arrives(group3);
        CustomerGroup group4 = new CustomerGroup(4);
        seatingManager.arrives(group4);
        assertEquals(table2.getCostumerGroup(), group1);
        assertEquals(table4.getCostumerGroup(), group2);
        assertEquals(table5.getCostumerGroup(), group3);
        assertEquals(seatingManager.customers_waiting.size(), 1);
        seatingManager.leaves(group2);
        assertEquals(table4.getCostumerGroup(), group4);
        assertEquals(seatingManager.customers_waiting.size(), 0);
    }
}
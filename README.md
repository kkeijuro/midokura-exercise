## MIDOKURA SKILLS EXERCISE

This is an exercise for midokura made by Cristobal Pio using VS CODE in java/mvn.
Java 17.
mvn 3.8.8.

mvn compile: to compile solution
mvn test: to launch tests

The time spent for solving it was 2h20min (with a terrible headache).

Analysis of the methods:

3 java collections where used:

    TreeMap<Integer, LinkedList<Table>> sorted_tables = new TreeMap<>();
    TreeMap<Integer, LinkedList<Table>> occupied_tables = new TreeMap<>();
    LinkedList<CustomerGroup> customers_waiting = new LinkedList<>();

for: sorted_tables and occupied_tables:
LinkedList was basically selected because it offers a 0(1)
TreeMap was selected to have all the tables ordered by its size

With tree map we could use the tailMap to begin iterating in the middle of the Tree,
this means that we can begin only where CustomerGroup fits.

for customers_waiting I decided using a LinkedList, iterating is O(n) but inserting/removing is O(1)
and random access is not needed.

In detail:

arrive is O(1)
locate is O(n2) because of the double for
leave is O(n2) because of the double for when locating the table.

In memory terms I guess I could be more efficient using the LinkedList since we are 
only iterating in one direction on locate.

I'm not specially proud of the locate method because of the O(n2) complexity


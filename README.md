## MIDOKURA SKILLS EXERCISE

This is an exercise for midokura made by Cristobal Pio using VS CODE in java/mvn.<br>
Java 17.<br>
mvn 3.8.8.<br>
<br>
mvn compile: to compile solution<br>
mvn test: to launch tests<br>

The time spent for solving it was 2h20min (with a terrible headache).<br>

Analysis of the methods:

3 java collections where used:

    TreeMap<Integer, LinkedList<Table>> sorted_tables = new TreeMap<>();<br>
    TreeMap<Integer, LinkedList<Table>> occupied_tables = new TreeMap<>();<br>
    LinkedList<CustomerGroup> customers_waiting = new LinkedList<>();<br>
<br>
for: sorted_tables and occupied_tables:
LinkedList was basically selected because it offers a 0(1) for inseting information.
TreeMap was selected to have all the tables ordered by its size. Then each LinkedList can be
easilly accessed.

With tree map we could use the tailMap to begin iterating in the middle of the Tree,
this means that we can begin only where CustomerGroup fits.

for customers_waiting I decided using a LinkedList, iterating is O(n) but inserting/removing is O(1). Maybe other collections would fit but I decided using them
and random access is not needed.

In detail, if we dismiss the TreeMap accessing, which would be O(log) here are the
algorithm time: <br>
<br>
arrive is O(1) <br>
locate is O(n) <br>
leave is O(n)<br>

In memory terms I guess I could be more efficient using the LinkedList since we are 
only iterating in one direction on locate but at least it only contains the elements
needed (An array list would mean increasing more than the elements needed each time
we run out of space)


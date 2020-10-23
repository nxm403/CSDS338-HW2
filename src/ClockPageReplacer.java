import java.util.*;

public class ClockPageReplacer {
    private Set<Integer> storedPages; //used for efficiently checking if a given page is already stored
    private List<Integer> pageQueue; //used for FIFO operations
    private Map<Integer, Boolean> secondChance; //used to track if pages should have a second chance
    private final int capacity;

    public ClockPageReplacer(int capacity) {
        assert capacity >= 1;

        this.capacity = capacity;
        storedPages = new HashSet<>(capacity);
        pageQueue = new LinkedList<>();
        secondChance = new HashMap<>();
    }

    void addPage(int page) {
        if(-1 < page) {

            //fixing if queue is at full capacity
            if(storedPages.size() == capacity) {//replacing a full queue

                //iterating through queue to find first page with a second chance bit of 0 (false)
                LOOP:
                for(int oldPage : pageQueue) {

                    if(secondChance.get(oldPage)) { //lowering second chance bit of pages with a bit of 1 (true)
                        secondChance.replace(oldPage, false);
                    } else { //removing first with page with second chance bit of 0
                        pageQueue.remove(oldPage);
                        storedPages.remove(oldPage);
                        secondChance.remove(oldPage);
                        break LOOP;

                    }
                }
            }

            if(storedPages.contains(page)) { //setting second chance bit to 1 (true) if it already exists
                secondChance.replace(page, true);
            } else { //adding a page that is not in the queue
                pageQueue.add(page);
                storedPages.add(page);
                secondChance.put(page, false);
            }
        }
    }

    void addPages(int pages[]) {
        for(int page : pages) {
            addPage(page);
        }
    }
}

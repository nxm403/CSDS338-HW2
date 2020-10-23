import java.util.*;

class FIFOPageReplacer {
    private Set<Integer> storedPages; //used for efficiently checking if a given page is already stored
    private Queue<Integer> pageQueue; //stores pages in FIFO manner
    private final int capacity;
    private int numPageFaults;

    public FIFOPageReplacer(int capacity) {
        this.capacity = capacity;
        storedPages = new HashSet<>(capacity);
        pageQueue = new LinkedList<>();
        numPageFaults = 0;
    }

    void addPage(int page) {
        //storing pages that are not already stored
        if(!storedPages.contains(page)) {

            //adding to stored pages if there is space
            if(storedPages.size() < capacity) {
                pageQueue.add(page);
                storedPages.add(page);
            } else { //performing FIFO if there is not any space
                int topPage = pageQueue.peek();

                //removing oldest page and adding current page
                storedPages.remove(topPage);
                storedPages.add(page);

                pageQueue.add(page);
            }
            numPageFaults++;
        }
    }

    void addPages(int pages[]) {
        for(int page : pages) {
            addPage(page);
        }
    }

    public int getNumPageFaults() {
        return numPageFaults;
    }
}
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
        if(-1 < page && !storedPages.contains(page)) {

            if(!(storedPages.size() < capacity)) {
                //removing oldest page
                int topPage = pageQueue.poll();
                storedPages.remove(topPage);
            }

            //adding to stored pages
            pageQueue.add(page);
            storedPages.add(page);
            numPageFaults++;
        }
    }

    boolean request(int page) {
        return storedPages.contains(page);
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
import java.util.*;

class FIFOPageReplacer {
    /**
     * method to count number of page faults
     * 
     * prob needs to be altered
     * @param pages
     * @param n
     * @param capacity
     * @return
     */
    int pageFaults(int pages[], int n, int capacity) {

        Set<Integer> storedPages = new HashSet<>(capacity); //used for efficiently checking if a given page is already stored
        Queue<Integer> pageQueue = new LinkedList<>(); //stores pages in FIFO manner

        int numPageFaults = 0;
        for(int i = 0; i < n; i++) {
            int currentPage = pages[i];

            //storing pages that are not already stored
            if(!storedPages.contains(currentPage)) {

                //adding to stored pages if there is space
                if(storedPages.size() < capacity) {
                    pageQueue.add(currentPage);
                    storedPages.add(currentPage);
                } else { //performing FIFO if there is not any space
                    int topPage = pageQueue.peek();

                    //removing oldest page and adding current page
                    storedPages.remove(topPage);
                    storedPages.add(currentPage);

                    pageQueue.add(currentPage);
                }
                numPageFaults++;
            }
            
        }

        return numPageFaults;
    }
}
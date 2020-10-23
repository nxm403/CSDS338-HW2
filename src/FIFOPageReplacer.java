//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class FIFOPageReplacer implements PageReplacer {
    private Set<Integer> storedPages;
    private Queue<Integer> pageQueue;
    private final int capacity;
    private int numPageFaults;

    public FIFOPageReplacer(int capacity) {
        this.capacity = capacity;
        this.storedPages = new HashSet(capacity);
        this.pageQueue = new LinkedList();
        this.numPageFaults = 0;
    }

    public void addPage(int page) {
        if (-1 < page && !this.storedPages.contains(page)) {
            if (this.storedPages.size() >= this.capacity) {
                int topPage = (Integer)this.pageQueue.poll();
                this.storedPages.remove(topPage);
            }

            this.pageQueue.add(page);
            this.storedPages.add(page);
        }

    }

    public boolean request(int page) {
        if (!this.storedPages.contains(page)) {
            ++this.numPageFaults;
            return false;
        } else {
            return true;
        }
    }

    void addPages(int[] pages) {
        int[] var2 = pages;
        int var3 = pages.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int page = var2[var4];
            this.addPage(page);
        }

    }

    public int getNumPageFaults() {
        return this.numPageFaults;
    }
}

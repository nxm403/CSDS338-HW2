//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClockPageReplacer implements PageReplacer {
    private Set<Integer> storedPages;
    private List<Integer> pageQueue;
    private Map<Integer, Boolean> secondChance;
    private int numPageFaults;
    private final int capacity;

    public ClockPageReplacer(int capacity) {
        assert capacity >= 1;

        this.capacity = capacity;
        this.storedPages = new HashSet(capacity);
        this.pageQueue = new LinkedList();
        this.secondChance = new HashMap();
    }

    public void addPage(int page) {
        if (-1 < page) {
            if (this.storedPages.size() == this.capacity) {
                Iterator var2 = this.pageQueue.iterator();

                while(var2.hasNext()) {
                    int oldPage = (Integer)var2.next();
                    if (!(Boolean)this.secondChance.get(oldPage)) {
                        this.pageQueue.removeIf((n) -> {
                            return n == oldPage;
                        });
                        this.storedPages.remove(oldPage);
                        this.secondChance.remove(oldPage);
                        break;
                    }

                    this.secondChance.replace(oldPage, false);
                }
            }

            if (this.storedPages.contains(page)) {
                this.secondChance.replace(page, true);
            } else {
                this.pageQueue.add(page);
                this.storedPages.add(page);
                this.secondChance.put(page, false);
            }
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

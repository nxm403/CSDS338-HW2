import java.util.*;

public class RequestGenerator {
    
    private final RequestPattern pattern;
    private final Set<Integer> pageValues;

    public enum RequestPattern {
        PATTERN_1, //equiprobable request for any of the n pages
        PATTERN_2, //strongly biased probability for lower-numbered pages to be requested, using an exponential distribution
        PATTERN_3 //very strongly biased probability to request any of 3<k<10 pages, among the n, rest are exponentially distributed
    }

    public RequestGenerator(int[] pages, RequestPattern pattern) {
        this.pattern = pattern;
        this.pageValues = pageValues(pages);
        
    }

    private Set<Integer> pageValues(int[] pages) {
        Set<Integer> tset = new TreeSet<Integer>();
        for(int page: pages) tset.add(page);

        return tset;
    }
    // public int generateRequest() {

    // }
}

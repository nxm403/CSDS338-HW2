import java.util.*;

public class RequestGenerator {
    
    private final RequestPattern pattern;
    private final int[] pageValues;
    private final int minPageValue;
    private final int maxPageValue;

    public enum RequestPattern {
        PATTERN_1, //equiprobable request for any of the n pages
        PATTERN_2, //strongly biased probability for lower-numbered pages to be requested, using an exponential distribution
        PATTERN_3 //very strongly biased probability to request any of 3<k<10 pages, among the n, rest are exponentially distributed
    }

    public RequestGenerator(int[] pages, RequestPattern pattern) {
        this.pattern = pattern;
        this.pageValues = pageValues(pages);
        this.minPageValue = pageValues[0];
        this.maxPageValue = pageValues[pageValues.length-1];
    }

    private int[] pageValues(int[] pages) {
        Set<Integer> tset = new TreeSet<Integer>();
        for(int page: pages) tset.add(page);
        return tset.toArray();
    }

    public int generateRequest() {
        int requestVal = -1;

        switch(pattern) {
            case PATTERN_1:
                requestVal = pagesValues[new Random().nextInt(pageValues.length)];
                break;
            case PATTERN_2:

//                ArrayList<Integer> frequencyList = new ArrayList<>();
//                for(int page : pageValues) {
//                    int frequency = maxPageValue * Math.pow(1.5, -page);
//                    for(int i = 1; i <= frequency; i++) {
//                        frequencyList.add(page);
//                    }
//                }

                int rando = new Random().nextInt(maxPageValue);
                requestVal = (int)(Math.pow(0.75, rando)*maxPageValue)+minPageValue;
        }

        return requestVal;
    }
}

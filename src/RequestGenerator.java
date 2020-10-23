import java.util.*;

public class RequestGenerator {
    
    private final RequestPattern pattern;
    private final Integer[] pageValues;
    private final int minPageValue;
    private final int maxPageValue;

    public enum RequestPattern {
        PATTERN_1, //equiprobable request for any of the n pages
        PATTERN_2, //strongly biased probability for lower-numbered pages to be requested, using an exponential distribution
        PATTERN_3 //very strongly biased probability to request any of 3<k<10 pages, among the n, rest are exponentially distributed
    }

    public RequestGenerator(int[] pages, RequestPattern pattern) {
        this.pageValues = pageValues(pages);
        this.minPageValue = pageValues[0];
        this.maxPageValue = pageValues[pageValues.length-1];

        if(pageValues.length < 10 && pattern.compareTo(RequestPattern.PATTERN_3) == 0) {
            this.pattern = RequestPattern.PATTERN_2;
        } else {
            this.pattern = pattern;
        }
        
        
    }

    private Integer[] pageValues(int[] pages) {
        Set<Integer> tset = new TreeSet<Integer>();
        for(int page: pages) tset.add(page);
        return (Integer[]) tset.toArray();
    }

    public int generateRequest() {
        int requestVal = -1;
        ArrayList<Integer> frequencyList = new ArrayList<>();
        switch(pattern) {
            case PATTERN_1:
                requestVal = pageValues[new Random().nextInt(pageValues.length)];
                break;
            case PATTERN_2:
                for(int page : pageValues) {
                    double frequency = maxPageValue * Math.pow(1.5, -page);
                    for(int i = 1; i <= frequency; i++) {
                        frequencyList.add(page);
                    }
                }

                requestVal = pageValues[new Random().nextInt(frequencyList.size())];
                break;

            case PATTERN_3:
                for(int i = 0; i < pageValues.length; i++) {
                    double frequency = maxPageValue * Math.pow(1.5, -pageValues[i]);

                    if(3 <= i && i <= 10) frequency += maxPageValue;

                    for(int j = 1; j <= frequency; j++) {
                        frequencyList.add(pageValues[i]);
                    }
                }

                requestVal = pageValues[new Random().nextInt(frequencyList.size())];
                break;
        }

        return requestVal;
    }
}

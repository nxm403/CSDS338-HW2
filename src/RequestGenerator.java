//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.Random;

public class RequestGenerator {
    private final RequestGenerator.RequestPattern pattern;
    private final int[] pageValues;
    private final int minPageValue;
    private final int maxPageValue;
    private final ArrayList<Integer> frequencyList;

    public RequestGenerator(int[] pages, RequestGenerator.RequestPattern pattern) {
        this.pageValues = pages;
        this.minPageValue = this.pageValues[0];
        this.maxPageValue = this.pageValues[this.pageValues.length - 1];
        if (this.pageValues.length < 10 && pattern.compareTo(RequestGenerator.RequestPattern.PATTERN_3) == 0) {
            this.pattern = RequestGenerator.RequestPattern.PATTERN_2;
        } else {
            this.pattern = pattern;
        }

        this.frequencyList = new ArrayList();
        this.populateFrequencies();
    }

    public int generateRequest() {
        int requestVal;
        if (this.pattern == RequestGenerator.RequestPattern.PATTERN_1) {
            requestVal = this.pageValues[(new Random()).nextInt(this.pageValues.length)];
        } else {
            requestVal = (Integer)this.frequencyList.get((new Random()).nextInt(this.frequencyList.size()));
        }

        return requestVal;
    }

    private void populateFrequencies() {
        int j;
        if (this.pattern.equals(RequestGenerator.RequestPattern.PATTERN_2)) {
            int[] var1 = this.pageValues;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                j = var1[var3];
                double frequency = (double)this.maxPageValue * Math.pow(1.5D, (double)(-j));

                for(int i = 1; (double)i <= frequency; ++i) {
                    this.frequencyList.add(j);
                }
            }
        }

        if (this.pattern.equals(RequestGenerator.RequestPattern.PATTERN_3)) {
            for(int i = 0; i < this.pageValues.length; ++i) {
                double frequency = (double)this.maxPageValue * Math.pow(1.5D, (double)(-this.pageValues[i]));
                if (2 <= i && i <= 9) {
                    frequency += (double)this.maxPageValue;
                }

                for(j = 0; (double)j <= frequency; ++j) {
                    this.frequencyList.add(this.pageValues[i]);
                }
            }
        }

    }

    public enum RequestPattern {
        PATTERN_1,
        PATTERN_2,
        PATTERN_3;
    }
}

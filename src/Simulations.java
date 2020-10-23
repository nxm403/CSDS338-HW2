//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.Arrays;
import java.util.Random;

public class Simulations {
	private static final int[] possiblePages = pageListGenerator(100);
	private static int capacity = 15;
	private static int numberOfPages = 1000;

	public Simulations() {
	}

	public static void main(String[] args) {
		System.out.println("Given possible pages of  1 - " + possiblePages.length);
		System.out.println("Max Algorithm Capacity of " + capacity);
		System.out.println("And testing with " + numberOfPages + " pages\n");
		simulation(RequestGenerator.RequestPattern.PATTERN_1);
		simulation(RequestGenerator.RequestPattern.PATTERN_2);
		simulation(RequestGenerator.RequestPattern.PATTERN_3);
	}

	private static void randomlyInsertPage(PageReplacer replacer1, PageReplacer replacer2) {
		int randomInt = possiblePages[(new Random()).nextInt(possiblePages.length)];
		replacer1.addPage(randomInt);
		replacer2.addPage(randomInt);
	}

	private static void randomlyRequestPage(PageReplacer replacer1, PageReplacer replacer2, RequestGenerator requester) {
		int chance = (new Random()).nextInt(10) + 1;
		if ((new Random()).nextInt(chance) == 1) {
			int requestedNumber = requester.generateRequest();
			replacer1.request(requestedNumber);
			replacer2.request(requestedNumber);
		}

	}

	private static void simulation(RequestGenerator.RequestPattern pattern) {
		int[] FIFOpageFaults = new int[10];
		int[] CLOCKpageFaults = new int[10];

		for(int i = 0; i < 10; ++i) {
			RequestGenerator requester = new RequestGenerator(possiblePages, pattern);
			PageReplacer FIFOreplacer = new FIFOPageReplacer(capacity);
			PageReplacer CLOCKreplacer = new ClockPageReplacer(capacity);

			for(int j = 0; j < numberOfPages; ++j) {
				randomlyInsertPage(FIFOreplacer, CLOCKreplacer);
				randomlyRequestPage(FIFOreplacer, CLOCKreplacer, requester);
			}

			FIFOpageFaults[i] = FIFOreplacer.getNumPageFaults();
			CLOCKpageFaults[i] = CLOCKreplacer.getNumPageFaults();
		}

		String patternType;
		if (pattern.equals(RequestGenerator.RequestPattern.PATTERN_1)) {
			patternType = "Equiprobable";
		} else if (pattern.equals(RequestGenerator.RequestPattern.PATTERN_2)) {
			patternType = "Exponential";
		} else {
			patternType = "3<k<10 Biased";
		}

		System.out.println(patternType + " Simulation for FIFO algorithm page faults: " + Arrays.toString(FIFOpageFaults) + ", average no. page faults: " + average(FIFOpageFaults));
		System.out.println(patternType + " Simulation for CLOCK algorithm page faults: " + Arrays.toString(CLOCKpageFaults) + ", average no. page faults: " + average(CLOCKpageFaults));
	}

	private static double average(int[] arr) {
		return Arrays.stream(arr).average().orElse(0.0D / 0.0);
	}

	private static int[] pageListGenerator(int maxVal) {
		int[] pageValues = new int[maxVal];

		for(int i = 1; i <= maxVal; pageValues[i - 1] = i++) {
		}

		return pageValues;
	}
}



public class Simulations {
	private static final int[] possiblePages = {1,2,3,4,5,6,7,8,9,10};
	private static int capacity = 5;
	private static int numberOfPages = 100;
	public static void main(String[] args) {
		System.out.println("Given possible pages of "+possiblePages);
		System.out.println("Max Algorithmn Capacity of "+capacity);
		System.out.println("And testing with "+numberOfPages+" pages");
		System.out.print("Equiprobable Simulation for FIFO algorithmn has ");
		System.out.print(equiprobableSimulationFIFO()+ " page faults\n");
		System.out.print("Equiprobable Simulation for Clock algorithmn has ");
		System.out.print(equiprobableSimulationClock()+ " page faults\n");
		System.out.print("Exponential Simulation for FIFO algorithmn has ");
		System.out.print(exponentialSimulationFIFO()+ " page faults\n");
		System.out.print("Exponential Simulation for Clock algorithmn has ");
		System.out.print(exponentialSimulationClock()+ " page faults\n");
		System.out.print("3<k<10 Biase Simulation for FIFO algorithmn has ");
		System.out.print(biasSimulationFIFO()+ " page faults\n");
		System.out.print("3<k<10 Biase Simulation for Clock algorithmn has ");
		System.out.print(biasSimulationClock()+ " page faults\n");
	}

	private static int equiprobableSimulationFIFO() {
		FIFOPageReplacer simulationFIFO = new FIFOPageReplacer(capacity);
		RequestGenerator pageReq = new RequestGenerator(possiblePages , RequestGenerator.RequestPattern.PATTERN_1);
		for(int i = 0; i<numberOfPages; i++) {
			simulationFIFO.addPage(pageReq.generateRequest());
		}
		return simulationFIFO.getNumPageFaults();
	}
	private static int equiprobableSimulationClock() {
		ClockPageReplacer simulationClock = new ClockPageReplacer(capacity);
		RequestGenerator pageReq = new RequestGenerator(possiblePages , RequestGenerator.RequestPattern.PATTERN_1);
		for(int i = 0; i<numberOfPages; i++) {
			simulationClock.addPage(pageReq.generateRequest());
		}
		return simulationClock.getNumPageFaults();
	}
	private static int exponentialSimulationFIFO() {
		FIFOPageReplacer simulationFIFO = new FIFOPageReplacer(capacity);
		RequestGenerator pageReq = new RequestGenerator(possiblePages , RequestGenerator.RequestPattern.PATTERN_2);
		for(int i = 0; i<numberOfPages; i++) {
			simulationFIFO.addPage(pageReq.generateRequest());
		}
		return simulationFIFO.getNumPageFaults();
	}
	private static int exponentialSimulationClock() {
		ClockPageReplacer simulationClock = new ClockPageReplacer(capacity);
		RequestGenerator pageReq = new RequestGenerator(possiblePages , RequestGenerator.RequestPattern.PATTERN_2);
		for(int i = 0; i<numberOfPages; i++) {
			simulationClock.addPage(pageReq.generateRequest());
		}
		return simulationClock.getNumPageFaults();
	}
	private static int biasSimulationFIFO() {
		FIFOPageReplacer simulationFIFO = new FIFOPageReplacer(capacity);
		RequestGenerator pageReq = new RequestGenerator(possiblePages , RequestGenerator.RequestPattern.PATTERN_3);
		for(int i = 0; i<numberOfPages; i++) {
			simulationFIFO.addPage(pageReq.generateRequest());
		}
		return simulationFIFO.getNumPageFaults();
	}
	private static int biasSimulationClock() {
		ClockPageReplacer simulationClock = new ClockPageReplacer(capacity);
		RequestGenerator pageReq = new RequestGenerator(possiblePages , RequestGenerator.RequestPattern.PATTERN_3);
		for(int i = 0; i<numberOfPages; i++) {
			simulationClock.addPage(pageReq.generateRequest());
		}
		return simulationClock.getNumPageFaults();
	}
}

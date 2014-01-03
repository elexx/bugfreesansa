package tuwien.infosys.sla.auctionsimulator;

import java.util.Random;


public class SimulatorMain {

	private static int time = 0;

	public static void main(String[] args) {
		Market market = Market.getInstance();


		//		ReverseEnglishAgent buyer = new ReverseEnglishAgent(10, 100, ProductBuilder.newBuilder().availability(90).backupAvailable(true).cpu(4).build());
		//
		//		ReverseEnglishAgent seller1 = new ReverseEnglishAgent(700, 1000, ProductBuilder.newBuilder().availability(99).backupAvailable(true).cpu(4).build());
		//		ReverseEnglishAgent seller3 = new ReverseEnglishAgent(30, 1000, ProductBuilder.newBuilder().availability(88).backupAvailable(false).cpu(4).build());
		//
		//		market.createReverseEnglish(buyer, SimulatorMain.time + 1);
		//		market.registerInterestReverseEnglish(seller1);
		//		market.registerInterestReverseEnglish(seller3);
		//
		//		market.runReverseEnglishAuction(SimulatorMain.time);
		//		market.runDutchAuction(SimulatorMain.time);
		//		SimulatorMain.time++;
		//		System.out.println("Reverse English at time: " + SimulatorMain.time + ": " + market.getReverseEnglishStatistics());
		//
		//		DutchAgent seller = new DutchAgent(300, 350, ProductBuilder.newBuilder().cpu(2).ipv6Support(true).bandwith(256).build());
		//		DutchAgent buyer1 = new DutchAgent(0, 330, ProductBuilder.newBuilder().cpu(1).ipv6Support(true).bandwith(256).build());
		//		DutchAgent buyer2 = new DutchAgent(0, 320, ProductBuilder.newBuilder().cpu(2).ipv6Support(true).bandwith(128).build());
		//
		//		market.createDutch(seller, SimulatorMain.time);
		//		market.registerInterestDutch(buyer1);
		//		market.registerInterestDutch(buyer2);
		//
		//		market.runReverseEnglishAuction(SimulatorMain.time);
		//		market.runDutchAuction(SimulatorMain.time);
		//		System.out.println("Reverse English at time: " + SimulatorMain.time + ": " + market.getReverseEnglishStatistics());
		//		System.out.println("Dutch at time: " + SimulatorMain.time + ": " + market.getDutchStatistics());
		//
		//		ReverseEnglishAgent seller2 = new ReverseEnglishAgent(50, 1000, ProductBuilder.newBuilder().availability(95).backupAvailable(true).cpu(4).build());
		//		market.registerInterestReverseEnglish(seller2);

		DoubleAgent dseller1 = new DoubleAgent(200, Integer.MAX_VALUE, ProductBuilder.newBuilder().availability(90).backupAvailable(true).cpu(4).build());
		DoubleAgent dseller2 = new DoubleAgent(120, Integer.MAX_VALUE, ProductBuilder.newBuilder().availability(85).ipv6Support(true).cpu(4).build());
		DoubleAgent dbuyer1 = new DoubleAgent(0, 220, ProductBuilder.newBuilder().availability(90).backupAvailable(true).cpu(4).build());

		market.registerInterestDoubleSeller(dseller1);
		market.registerInterestDoubleSeller(dseller2);
		market.registerInterestDoubleBuyer(dbuyer1);
		market.runDoubleAuction();
	}

}

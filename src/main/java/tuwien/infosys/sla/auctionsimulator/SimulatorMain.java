package tuwien.infosys.sla.auctionsimulator;

public class SimulatorMain {

	public static void main(String[] args) {

		Market market = Market.getInstance();

		ReverseEnglishAgent buyer = new ReverseEnglishAgent(10, 100, ProductBuilder.newBuilder().availability(90).backupAvailable(true).cpu(4).build());


		ReverseEnglishAgent seller1 = new ReverseEnglishAgent(70, 1000, ProductBuilder.newBuilder().availability(99).backupAvailable(true).cpu(4).build());
		ReverseEnglishAgent seller3 = new ReverseEnglishAgent(30, 1000, ProductBuilder.newBuilder().availability(88).backupAvailable(false).cpu(4).build());

		market.createReverseEnglish(buyer, 1);
		market.registerInterestReverseEnglish(seller1);

		market.registerInterestReverseEnglish(seller3);

		System.out.println(seller1);

		market.runAuction();
		System.out.println(market.getReverseEnglishStatistics());

		market.runAuction();
		System.out.println(market.getReverseEnglishStatistics());

		ReverseEnglishAgent seller2 = new ReverseEnglishAgent(50, 1000, ProductBuilder.newBuilder().availability(95).backupAvailable(true).cpu(4).build());
		market.registerInterestReverseEnglish(seller2);

	}

}

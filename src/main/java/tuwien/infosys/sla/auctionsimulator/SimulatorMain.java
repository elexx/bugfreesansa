package tuwien.infosys.sla.auctionsimulator;

public class SimulatorMain {

	public static void main(String[] args) {

		Market market = Market.getInstance();

		ReverseEnglishAgent buyer = new ReverseEnglishAgent(10, 100, ProductBuilder.newBuilder().availability(90).backupAvailable(true).cpu(4).build());


		ReverseEnglishAgent seller1 = new ReverseEnglishAgent(700, 1000, ProductBuilder.newBuilder().availability(99).backupAvailable(true).cpu(4).build());
		ReverseEnglishAgent seller3 = new ReverseEnglishAgent(30, 1000, ProductBuilder.newBuilder().availability(88).backupAvailable(false).cpu(4).build());

		market.createReverseEnglish(buyer, 1);
		market.registerInterestReverseEnglish(seller1);

		market.registerInterestReverseEnglish(seller3);

		System.out.println(seller1);

		market.runAuction();
		System.out.println(market.getReverseEnglishStatistics());
		
		DutchAgent seller = new DutchAgent(300, 350, ProductBuilder.newBuilder().cpu(2).ipv6Support(true).bandwith(256).build());
		DutchAgent buyer1 = new DutchAgent(0, 330, ProductBuilder.newBuilder().cpu(1).ipv6Support(true).bandwith(256).build());
		DutchAgent buyer2 = new DutchAgent(0, 320, ProductBuilder.newBuilder().cpu(2).ipv6Support(true).bandwith(128).build());
	
		market.createDutch(seller, 0);
		market.registerInterestDutch(buyer1);
		market.registerInterestDutch(buyer2);

		market.runAuction();
		System.out.println(market.getReverseEnglishStatistics());
		System.out.println(market.getDutchStatistics());
		System.out.println(buyer1);

		ReverseEnglishAgent seller2 = new ReverseEnglishAgent(50, 1000, ProductBuilder.newBuilder().availability(95).backupAvailable(true).cpu(4).build());
		market.registerInterestReverseEnglish(seller2);
		
	}

}

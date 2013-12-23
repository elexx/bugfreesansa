package tuwien.infosys.sla.auctionsimulator;

import java.util.Random;

public class SimulatorMain {

	private static int time = 0;
	private static final Random RANDOM = new Random();

	private static final int[] BANDWIDTH = new int[] { 100, 200, 400, 800, 1600, 3200, 6400, 10000 };
	private static final int[] CPU = new int[] { 1, 2, 4, 8, 16 };
	private static final int[] RAM = new int[] { 1, 2, 4, 8, 16, 32 };
	private static final int[] STORAGE = new int[] { 128, 256, 512, 1024 };
	private static final int[] LATENCY = new int[] { 5, 10, 15, 20, 50, 100, 250 };

	public static void main(String[] args) {
		Market market = Market.getInstance();

		ReverseEnglishAgent buyer = new ReverseEnglishAgent(10, 100, ProductBuilder.newBuilder().availability(90).backupAvailable(true).cpu(4).build());

		ReverseEnglishAgent seller1 = new ReverseEnglishAgent(700, 1000, ProductBuilder.newBuilder().availability(99).backupAvailable(true).cpu(4).build());
		ReverseEnglishAgent seller3 = new ReverseEnglishAgent(30, 1000, ProductBuilder.newBuilder().availability(88).backupAvailable(false).cpu(4).build());

		market.createReverseEnglish(buyer, SimulatorMain.time + 1);
		market.registerInterestReverseEnglish(seller1);
		market.registerInterestReverseEnglish(seller3);

		market.runReverseEnglishAuction(SimulatorMain.time);
		market.runDutchAuction(SimulatorMain.time);
		SimulatorMain.time++;
		System.out.println("Reverse English at time: " + SimulatorMain.time + ": " + market.getReverseEnglishStatistics());

		DutchAgent seller = new DutchAgent(300, 350, ProductBuilder.newBuilder().cpu(2).ipv6Support(true).bandwith(256).build());
		DutchAgent buyer1 = new DutchAgent(0, 330, ProductBuilder.newBuilder().cpu(1).ipv6Support(true).bandwith(256).build());
		DutchAgent buyer2 = new DutchAgent(0, 320, ProductBuilder.newBuilder().cpu(2).ipv6Support(true).bandwith(128).build());

		market.createDutch(seller, SimulatorMain.time);
		market.registerInterestDutch(buyer1);
		market.registerInterestDutch(buyer2);

		market.runReverseEnglishAuction(SimulatorMain.time);
		market.runDutchAuction(SimulatorMain.time);
		System.out.println("Reverse English at time: " + SimulatorMain.time + ": " + market.getReverseEnglishStatistics());
		System.out.println("Dutch at time: " + SimulatorMain.time + ": " + market.getDutchStatistics());

		ReverseEnglishAgent seller2 = new ReverseEnglishAgent(50, 1000, ProductBuilder.newBuilder().availability(95).backupAvailable(true).cpu(4).build());
		market.registerInterestReverseEnglish(seller2);
	}

	private static Product generateRandomProduct(int paramAmount) {
		ProductBuilder builder = ProductBuilder.newBuilder();

		if (paramAmount == 9) {
			return ProductBuilder.newBuilder().availability(SimulatorMain.RANDOM.nextInt(100 - 85) + 85).backupAvailable(SimulatorMain.RANDOM.nextBoolean()).bandwith(SimulatorMain.BANDWIDTH[SimulatorMain.RANDOM.nextInt(SimulatorMain.BANDWIDTH.length)]).cpu(SimulatorMain.CPU[SimulatorMain.RANDOM.nextInt(SimulatorMain.CPU.length)]).ipv6Support(SimulatorMain.RANDOM.nextBoolean()).latency(SimulatorMain.LATENCY[SimulatorMain.RANDOM.nextInt(SimulatorMain.LATENCY.length)]).operatingSystem(OperatingSystems.fromValue(SimulatorMain.RANDOM.nextInt(3) + 1)).ram(SimulatorMain.RAM[SimulatorMain.RANDOM.nextInt(SimulatorMain.RAM.length)]).storage(SimulatorMain.STORAGE[SimulatorMain.RANDOM.nextInt(SimulatorMain.STORAGE.length)]).build();
		}

		while (builder.size() != paramAmount) {
			int param = SimulatorMain.RANDOM.nextInt(9) + 1;
			builder = addParam(builder, param);
		}

		return builder.build();
	}

	private static ProductBuilder addParam(ProductBuilder builder, int value) {
		switch (value) {
		case 1:
			builder = builder.availability(SimulatorMain.RANDOM.nextInt(100 - 85) + 85);
			break;
		case 2:
			builder = builder.backupAvailable(SimulatorMain.RANDOM.nextBoolean());
			break;
		case 3:
			builder = builder.bandwith(SimulatorMain.BANDWIDTH[SimulatorMain.RANDOM.nextInt(SimulatorMain.BANDWIDTH.length)]);
			break;
		case 4:
			builder = builder.cpu(SimulatorMain.CPU[SimulatorMain.RANDOM.nextInt(SimulatorMain.CPU.length)]);
			break;
		case 5:
			builder = builder.ipv6Support(SimulatorMain.RANDOM.nextBoolean());
			break;
		case 6:
			builder = builder.latency(SimulatorMain.LATENCY[SimulatorMain.RANDOM.nextInt(SimulatorMain.LATENCY.length)]);
			break;
		case 7:
			builder = builder.operatingSystem(OperatingSystems.fromValue(SimulatorMain.RANDOM.nextInt(3) + 1));
			break;
		case 8:
			builder = builder.ram(SimulatorMain.RAM[SimulatorMain.RANDOM.nextInt(SimulatorMain.RAM.length)]);
			break;
		case 9:
			builder = builder.storage(SimulatorMain.STORAGE[SimulatorMain.RANDOM.nextInt(SimulatorMain.STORAGE.length)]);
			break;

		default:
			break;
		}
		return builder;
	}

}

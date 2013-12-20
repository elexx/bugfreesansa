package tuwien.infosys.sla.auctionsimulator;

import java.util.Random;

public class SimulatorMain {
	
	private static int time = 0;

	public static void main(String[] args) {


		Market market = Market.getInstance();

		ReverseEnglishAgent buyer = new ReverseEnglishAgent(10, 100, ProductBuilder.newBuilder().availability(90).backupAvailable(true).cpu(4).build());


		ReverseEnglishAgent seller1 = new ReverseEnglishAgent(700, 1000, ProductBuilder.newBuilder().availability(99).backupAvailable(true).cpu(4).build());
		ReverseEnglishAgent seller3 = new ReverseEnglishAgent(30, 1000, ProductBuilder.newBuilder().availability(88).backupAvailable(false).cpu(4).build());

		market.createReverseEnglish(buyer, time+1);
		market.registerInterestReverseEnglish(seller1);
		market.registerInterestReverseEnglish(seller3);

		market.runReverseEnglishAuction(time);
		market.runDutchAuction(time);
		time++;
		System.out.println("Reverse English at time: " + time + ": " + market.getReverseEnglishStatistics());
		
		DutchAgent seller = new DutchAgent(300, 350, ProductBuilder.newBuilder().cpu(2).ipv6Support(true).bandwith(256).build());
		DutchAgent buyer1 = new DutchAgent(0, 330, ProductBuilder.newBuilder().cpu(1).ipv6Support(true).bandwith(256).build());
		DutchAgent buyer2 = new DutchAgent(0, 320, ProductBuilder.newBuilder().cpu(2).ipv6Support(true).bandwith(128).build());
	
		market.createDutch(seller, time);
		market.registerInterestDutch(buyer1);
		market.registerInterestDutch(buyer2);

		market.runReverseEnglishAuction(time);
		market.runDutchAuction(time);
		System.out.println("Reverse English at time: " + time + ": " + market.getReverseEnglishStatistics());
		System.out.println("Dutch at time: " + time + ": " + market.getDutchStatistics());

		ReverseEnglishAgent seller2 = new ReverseEnglishAgent(50, 1000, ProductBuilder.newBuilder().availability(95).backupAvailable(true).cpu(4).build());
		market.registerInterestReverseEnglish(seller2);
		
	}
	
	
	private static Product generateRasndomProduct(int paramAmount){
		 Random random = new Random();  
		 ProductBuilder builder = ProductBuilder.newBuilder();
		
		if(paramAmount==9){
			return ProductBuilder.newBuilder()
					.availability(random.nextInt(100-85)+85)
					.backupAvailable(random.nextBoolean())
					.bandwith(random.nextInt(10000-100)+100)
					.cpu(random.nextInt(16-1)+1)
					.ipv6Support(random.nextBoolean())
					.latency(random.nextInt(250-5)+5)
					.operatingSystem(OperatingSystems.fromValue(random.nextInt(3)+1))
					.ram(random.nextInt(32-1)+1)
					.storage(random.nextInt(1024-128)+128)
					.build();
		}

		while(builder.size()!=paramAmount){
			int param = random.nextInt(9)+1;
			builder = addParam(builder, param);
		}
				
		return builder.build();
	}
	
	private static ProductBuilder addParam(ProductBuilder builder, int value){
		Random random = new Random();
		switch (value) {
		case 1:
			builder = builder.availability(random.nextInt(100-85)+85);
			break;
		case 2:
			builder = builder.backupAvailable(random.nextBoolean());
			break;
		case 3:
			builder = builder.bandwith(random.nextInt(10000-100)+100);
			break;
		case 4:
			builder = builder.cpu(random.nextInt(16-1)+1);
			break;
		case 5:
			builder = builder.ipv6Support(random.nextBoolean());
			break;
		case 6:
			builder = builder.latency(random.nextInt(250-5)+5);
			break;
		case 7:
			builder = builder.operatingSystem(OperatingSystems.fromValue(random.nextInt(3)+1));
			break;
		case 8:
			builder = builder.ram(random.nextInt(32-1)+1);
			break;
		case 9:
			builder = builder.storage(random.nextInt(1024-128)+128);
			break;
			

		default:
			break;
		}
		return builder;
	} 

}

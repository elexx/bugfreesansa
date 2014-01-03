package tuwien.infosys.sla.auctionsimulator;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import tuwien.infosys.sla.auctionsimulator.config.Config;
import tuwien.infosys.sla.auctionsimulator.config.ConfigLoader;


public class SimulatorMain {

	private static Random random = new Random();

	private static final int TIME_BUFFER = 10;

	public static void main(String[] args) throws IOException {
		ConfigLoader configLoader = new ConfigLoader();
		Config config = configLoader.load();

		AgentGenerator agentGenerator = new AgentGenerator(SimulatorMain.random);

		Market market = Market.getInstance();

		Iterator<Integer> englishAmountIterator = config.getEnglishAgents();
		Iterator<Integer> dutchAmountIterator = config.getDutchAgents();
		Iterator<Integer> doubleAmountIterator = config.getDoubleAgents();

		int minParamAmount = config.getMinParamAmount();
		int maxParamAmount = config.getMaxParamAmount();

		for (int timeSlot = 1; timeSlot <= config.getLifespan() + SimulatorMain.TIME_BUFFER; timeSlot++) {

			/*
			 * CREATION
			 */
			int amount, buyer, seller;

			amount = englishAmountIterator.next();
			buyer = (int) (amount * config.getEnglishBuyerRatio());
			seller = amount - buyer;
			for (int i = 0; i < seller; i++) {
				ReverseEnglishAgent agent = agentGenerator.generateReverseEnglishAgent(minParamAmount, maxParamAmount);
				System.out.println(agent);
				market.registerInterestReverseEnglish(agent);
			}
			for(int i = 0; i < buyer; i++) {
				ReverseEnglishAgent agent = agentGenerator.generateReverseEnglishAgent(minParamAmount, maxParamAmount);
				System.out.println(agent);
				market.createReverseEnglish(agent, genTimeSlot(config.getLifespan(), timeSlot));
			}

			amount = dutchAmountIterator.next();
			buyer = (int) (amount * config.getDutchBuyerRatio());
			seller = amount - buyer;
			for(int i = 0; i < buyer; i++) {
				market.registerInterestDutch(agentGenerator.generateDutchAgent(minParamAmount, maxParamAmount));
			}
			for (int i = 0; i < seller; i++) {
				market.createDutch(agentGenerator.generateDutchAgent(minParamAmount, maxParamAmount), genTimeSlot(config.getLifespan(), timeSlot));
			}

			amount = doubleAmountIterator.next();
			buyer = (int) (amount * config.getDoubleBuyerRatio());
			seller = amount - buyer;
			for (int i = 0; i < buyer; i++) {
				market.registerInterestDoubleBuyer(agentGenerator.generateDoubleAgent(minParamAmount, maxParamAmount));
			}
			for (int i = 0; i < seller; i++) {
				market.registerInterestDoubleSeller(agentGenerator.generateDoubleAgent(minParamAmount, maxParamAmount));
			}

			/*
			 * SIMULATION
			 */
			market.runReverseEnglishAuction(timeSlot);
			market.runDutchAuction(timeSlot);
			if (config.getDoubleAuctionInterval() % timeSlot == 0) {
				market.runDoubleAuction();
			}

		}

		System.out.println("RE " + market.getReverseEnglishStatistics());
		System.out.println("DU " + market.getDutchStatistics());
		System.out.println("DO " + market.getDoubleStatistics());
	}

	private static int genTimeSlot(int lifeSpan, int currentTime) {
		return SimulatorMain.random.nextInt(lifeSpan + SimulatorMain.TIME_BUFFER - currentTime) + currentTime;
	}
}

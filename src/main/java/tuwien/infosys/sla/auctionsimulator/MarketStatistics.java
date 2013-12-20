package tuwien.infosys.sla.auctionsimulator;

public class MarketStatistics {

	private int supplyAgentsCount;
	private int demandAgentsCount;
	private int successfulAuctionsCount;
	private int failedAuctionsCount;

	public MarketStatistics() {
		supplyAgentsCount = 0;
		demandAgentsCount = 0;
		successfulAuctionsCount = 0;
		failedAuctionsCount = 0;
	}

	public int getSupplyAgentsCount() {
		return supplyAgentsCount;
	}

	public int getDemandAgentsCount() {
		return demandAgentsCount;
	}

	public int getSuccessfulAuctionsCount() {
		return successfulAuctionsCount;
	}

	public int getFailedAuctionsCount() {
		return failedAuctionsCount;
	}

	public void increaseSupplyAgentsCount() {
		supplyAgentsCount++;
	}

	public void increaseDemandAgentsCount() {
		demandAgentsCount++;
	}

	public void increaseSuccessfulAuctionsCount() {
		successfulAuctionsCount++;
	}

	public void increaseSuccessfulAuctionsCount(int value) {
		successfulAuctionsCount += value;
	}


	public void increaseFailedAuctionsCount() {
		failedAuctionsCount++;
	}

	public int getOverallAuctionsCount() {
		return successfulAuctionsCount + failedAuctionsCount;
	}

	@Override
	public String toString() {
		return "Supply Agents: " + supplyAgentsCount + " Demand Agents: " + demandAgentsCount + " Successful Auctions: " + successfulAuctionsCount + " Failed Auctions: " + failedAuctionsCount;
	}

}

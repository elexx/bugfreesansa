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

	public void increaseSupplyAgentsCount(int value) {
		supplyAgentsCount += value;
	}

	public void increaseSupplyAgentsCount() {
		increaseSupplyAgentsCount(1);
	}

	public void increaseDemandAgentsCount(int value) {
		demandAgentsCount += value;
	}

	public void increaseDemandAgentsCount() {
		increaseDemandAgentsCount(1);
	}

	public void increaseSuccessfulAuctionsCount(int value) {
		successfulAuctionsCount += value;
	}

	public void increaseSuccessfulAuctionsCount() {
		increaseSuccessfulAuctionsCount(1);
	}

	public void increaseFailedAuctionsCount(int value) {
		failedAuctionsCount += value;
	}

	public void increaseFailedAuctionsCount() {
		increaseFailedAuctionsCount(1);
	}

	public int getOverallAuctionsCount() {
		return successfulAuctionsCount + failedAuctionsCount;
	}

	@Override
	public String toString() {
		return "Supply Agents: " + supplyAgentsCount + " Demand Agents: " + demandAgentsCount + " Successful Auctions: " + successfulAuctionsCount + " Failed Auctions: " + failedAuctionsCount;
	}

}

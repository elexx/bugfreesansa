package tuwien.infosys.sla.auctionsimulator;

import com.google.common.base.Objects;

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
		// @formatter:off
		return Objects.toStringHelper(this)
				.add("Supply Agents", supplyAgentsCount)
				.add("Demand Agents", demandAgentsCount)
				.add("Successful Auctions", successfulAuctionsCount)
				.add("Failed Auctions", failedAuctionsCount)
				.toString();
		// @formatter:on
	}

}

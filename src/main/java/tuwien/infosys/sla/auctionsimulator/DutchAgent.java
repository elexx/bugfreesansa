package tuwien.infosys.sla.auctionsimulator;

import java.util.List;

import com.google.common.base.Optional;

public class DutchAgent extends Agent implements SupplyDemandAuctionable {

	public DutchAgent(int minPrice, int maxPrice, Product product) {
		super(minPrice, maxPrice, product);
	}

	public boolean requestConfirm(int currentPrice) {
		return currentPrice <= this.maxPrice;
	}

	@Override
	public Optional<? extends SupplyDemandAuctionable> startAuction(List<? extends SupplyDemandAuctionable> takers) {
		int currentPrice = maxPrice;

		DutchAgent buyer = null;
		while (buyer == null && currentPrice >= minPrice) {
			for (SupplyDemandAuctionable agent : takers) {
				if (((DutchAgent) agent).requestConfirm(currentPrice)) {
					buyer = (DutchAgent) agent;
					break;
				}
			}
			currentPrice -= Agent.BIDDING_STEP;
		}

		return Optional.fromNullable(buyer);
	}
}

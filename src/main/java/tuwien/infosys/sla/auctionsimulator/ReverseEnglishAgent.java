package tuwien.infosys.sla.auctionsimulator;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Optional;

public class ReverseEnglishAgent extends Agent implements SupplyDemandAuctionable {

	public ReverseEnglishAgent(int minPrice, int maxPrice, Product product) {
		super(minPrice, maxPrice, product);
	}

	public Optional<Integer> requestBid(int currentBid) {
		if (currentBid - Agent.BIDDING_STEP >= this.minPrice) {
			return Optional.of(currentBid - Agent.BIDDING_STEP);
		} else {
			return Optional.absent();
		}
	}

	@Override
	public Optional<? extends SupplyDemandAuctionable> startAuction(List<? extends SupplyDemandAuctionable> takers) {
		int currentPrice = maxPrice;

		do {
			Iterator<? extends SupplyDemandAuctionable> agentIterator = takers.iterator();
			while (agentIterator.hasNext()) {
				ReverseEnglishAgent agent = (ReverseEnglishAgent) agentIterator.next();

				Optional<Integer> bid = agent.requestBid(currentPrice);
				if (bid.isPresent() && bid.get() < currentPrice) {
					currentPrice = bid.get();
				} else {
					agentIterator.remove();
				}
			}
		} while (takers.size() > 1);

		if (takers.size() == 0)
			return Optional.absent();

		return Optional.fromNullable(takers.get(0));
	}
}

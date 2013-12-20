package tuwien.infosys.sla.auctionsimulator;

import com.google.common.base.Optional;

public class BuyerAgent extends Agent {

	private BuyerAgent() {
		super();
	}

	public Optional<Integer> requestDutchBid(int currentprice) {
		if (currentprice + BIDDING_STEP <= this.maxPrice) {
			return Optional.of(currentprice + BIDDING_STEP);
		} else {
			return Optional.absent();
		}
	}

}

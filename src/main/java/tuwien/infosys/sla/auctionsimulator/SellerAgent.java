package tuwien.infosys.sla.auctionsimulator;

import com.google.common.base.Optional;

public class SellerAgent extends Agent {

	private SellerAgent() {
		super();
	}

	public Optional<Integer> requestReverseEnglishBid(int currentprice) {
		if (currentprice - BIDDING_STEP >= this.minPrice) {
			return Optional.of(currentprice - BIDDING_STEP);
		} else {
			return Optional.absent();
		}
	}

}

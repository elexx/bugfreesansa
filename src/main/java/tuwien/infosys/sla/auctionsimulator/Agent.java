package tuwien.infosys.sla.auctionsimulator;

import com.google.common.base.Optional;

public abstract class Agent {

	protected static final int BIDDING_STEP = 10;

	protected final int minPrice;
	protected final int maxPrice;
	protected final Product product;

	protected Agent() {
		minPrice = 0;
		maxPrice = 0;
		product = null;
	}

	abstract Optional<Integer> requestBid(int currentBid);

	abstract void startAuction();

	//	public void listOfTakers(UUID auctionID, List<Agent> takers) {
	//		// TODO: implement
	//	}


}

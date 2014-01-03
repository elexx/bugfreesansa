package tuwien.infosys.sla.auctionsimulator;

public class DoubleAgent extends Agent {

	public DoubleAgent(int minPrice, int maxPrice, Product product) {
		super(minPrice, maxPrice, product);
	}

	/**
	 * Buyer specifies the maximum price to pay.
	 * 
	 * @return
	 */
	public int getBuyerBid() {
		return maxPrice;
	}

	/**
	 * Seller wants at least the returned price.
	 * 
	 * @return
	 */
	public int getSellerPrice() {
		return minPrice;
	}

}

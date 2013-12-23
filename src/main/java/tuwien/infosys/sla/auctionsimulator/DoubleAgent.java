package tuwien.infosys.sla.auctionsimulator;

public class DoubleAgent extends Agent {

	public DoubleAgent(int minPrice, int maxPrice, Product product) {
		super(minPrice, maxPrice, product);
	}

	public int getBuyerBid() {
		return minPrice;
	}

	public int getSellerPrice() {
		return maxPrice;
	}

}

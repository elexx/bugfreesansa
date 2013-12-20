package tuwien.infosys.sla.auctionsimulator;


public abstract class Agent {

	protected static final int BIDDING_STEP = 10;

	protected final int minPrice;
	protected final int maxPrice;
	protected final Product product;

	public Agent(int minPrice, int maxPrice, Product product) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

}

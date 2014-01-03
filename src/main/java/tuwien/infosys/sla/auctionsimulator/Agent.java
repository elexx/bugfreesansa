package tuwien.infosys.sla.auctionsimulator;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Objects;

public abstract class Agent {

	protected static final int BIDDING_STEP = 10;

	protected final int minPrice;
	protected final int maxPrice;
	protected final Product product;

	public Agent(int minPrice, int maxPrice, Product product) {
		checkArgument(minPrice <= maxPrice, "minPrice (" + minPrice + ") should be smaller or equal to maxPrice (" + maxPrice + ")");
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	@Override
	public String toString() {
		// @formatter:off
		return Objects.toStringHelper(this)
				.add("minPrice", minPrice)
				.add("maxPrice", maxPrice)
				.add("product", product)
				.toString();
		// @formatter:on
	}
}

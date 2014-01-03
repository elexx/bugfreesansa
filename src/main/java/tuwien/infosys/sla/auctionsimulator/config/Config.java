package tuwien.infosys.sla.auctionsimulator.config;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Config {
	private final List<Integer> englishAgents = Lists.newLinkedList();
	private final List<Integer> dutchAgents = Lists.newLinkedList();
	private final List<Integer> doubleAgents = Lists.newLinkedList();
	private float englishBuyerRatio;
	private float dutchBuyerRatio;
	private float doubleBuyerRatio;
	private int minParamAmount;
	private int maxParamAmount;
	private int doubleAuctionInterval;


	public Iterator<Integer> getEnglishAgents() {
		return new EndlessIterator(englishAgents.iterator(), getLifespan());
	}

	public Iterator<Integer> getDutchAgents() {
		return new EndlessIterator(dutchAgents.iterator(), getLifespan());
	}

	public Iterator<Integer> getDoubleAgents() {
		return new EndlessIterator(doubleAgents.iterator(), getLifespan());
	}

	public Float getEnglishBuyerRatio() {
		return englishBuyerRatio;
	}

	public Float getDutchBuyerRatio() {
		return dutchBuyerRatio;
	}

	public Float getDoubleBuyerRatio() {
		return doubleBuyerRatio;
	}

	public int getMinParamAmount() {
		return minParamAmount;
	}

	public int getMaxParamAmount() {
		return maxParamAmount;
	}

	public int getDoubleAuctionInterval() {
		return doubleAuctionInterval;
	}

	public int getLifespan() {
		return Math.max(Math.max(englishAgents.size(), dutchAgents.size()), doubleAgents.size());
	}

	@Override
	public String toString(){
		// @formatter:off
		return Objects.toStringHelper(this)
				.add("englishAgents", englishAgents)
				.add("dutchAgents", dutchAgents)
				.add("doubleAgents", doubleAgents)
				.add("englishBuyerRatio", englishBuyerRatio)
				.add("dutchBuyerRatio", dutchBuyerRatio)
				.add("doubleBuyerRatio", doubleBuyerRatio)
				.add("minParamAmount", minParamAmount)
				.add("maxParamAmount", maxParamAmount)
				.toString();
		// @formatter:on
	}

	private class EndlessIterator implements Iterator<Integer> {
		private final Iterator<Integer> baseIterator;
		private final int maxElements;
		private int currentElement;

		public EndlessIterator(Iterator<Integer> baseIterator, int maxElements) {
			this.baseIterator = baseIterator;
			this.maxElements = maxElements;
			currentElement = 0;
		}

		@Override
		public boolean hasNext() {
			return currentElement < maxElements;
		}

		@Override
		public Integer next() {
			currentElement++;
			return baseIterator.hasNext() ? baseIterator.next() : 0;
		}

		@Override
		public void remove() {
		}
	}
}

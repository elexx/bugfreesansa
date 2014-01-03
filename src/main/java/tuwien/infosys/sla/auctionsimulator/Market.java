package tuwien.infosys.sla.auctionsimulator;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class Market {

	private static final Market MARKET = new Market();

	private final MarketStatistics reverseEnglishStatistics;
	private final MarketStatistics dutchStatistics;
	private final MarketStatistics doubleStatistics;

	private final Set<ReverseEnglishAgent> reverseEnglishSellerList;
	private final Multimap<Integer, ReverseEnglishAgent> reverseEnglishBuyerList;

	private final Multimap<Integer, DutchAgent> dutchSellerList;
	private final Set<DutchAgent> dutchBuyerList;

	private final Set<DoubleAgent> doubleSellerList;
	private final Set<DoubleAgent> doubleBuyerList;

	private Market() {
		reverseEnglishSellerList = Sets.newLinkedHashSet();
		reverseEnglishBuyerList = HashMultimap.create();
		reverseEnglishStatistics = new MarketStatistics();

		dutchSellerList = HashMultimap.create();
		dutchBuyerList = Sets.newLinkedHashSet();
		dutchStatistics = new MarketStatistics();

		doubleSellerList = Sets.newLinkedHashSet();
		doubleBuyerList = Sets.newLinkedHashSet();
		doubleStatistics = new MarketStatistics();
	}

	public static Market getInstance() {
		return Market.MARKET;
	}

	public void registerInterestReverseEnglish(ReverseEnglishAgent agent) {
		reverseEnglishStatistics.increaseSupplyAgentsCount();
		reverseEnglishSellerList.add(agent);
	}

	public void createReverseEnglish(ReverseEnglishAgent agent, int timeSlot) {
		reverseEnglishStatistics.increaseDemandAgentsCount();
		reverseEnglishBuyerList.put(timeSlot, agent);
	}

	public void registerInterestDutch(DutchAgent agent) {
		dutchStatistics.increaseDemandAgentsCount();
		dutchBuyerList.add(agent);
	}

	public void createDutch(DutchAgent agent, int timeSlot) {
		dutchStatistics.increaseSupplyAgentsCount();
		dutchSellerList.put(timeSlot, agent);
	}

	public void registerInterestDoubleSeller(DoubleAgent agent) {
		doubleStatistics.increaseSupplyAgentsCount();
		doubleSellerList.add(agent);
	}

	public void registerInterestDoubleBuyer(DoubleAgent agent) {
		doubleStatistics.increaseDemandAgentsCount();
		doubleBuyerList.add(agent);
	}

	public void runReverseEnglishAuction(int time) {
		for (ReverseEnglishAgent buyer : reverseEnglishBuyerList.get(time)) {
			List<ReverseEnglishAgent> takers = Lists.newLinkedList();
			for (ReverseEnglishAgent seller : reverseEnglishSellerList) {
				if (buyer.getProduct().isFulfilledBy(seller.getProduct())) {
					takers.add(seller);
				}
			}

			Optional<? extends SupplyDemandAuctionable> winner = buyer.startAuction(takers);
			if (winner.isPresent()) {
				reverseEnglishStatistics.increaseSuccessfulAuctionsCount();
				reverseEnglishSellerList.remove(winner.get());
			} else {
				reverseEnglishStatistics.increaseFailedAuctionsCount();
			}
		}

		reverseEnglishBuyerList.removeAll(time);
	}

	public void runDutchAuction(int time) {
		for (DutchAgent seller : dutchSellerList.get(time)) {
			List<DutchAgent> takers = Lists.newLinkedList();
			for (DutchAgent buyer : dutchBuyerList) {
				if (buyer.getProduct().isFulfilledBy(seller.getProduct())) {
					takers.add(buyer);
				}
			}

			Optional<? extends SupplyDemandAuctionable> winner = seller.startAuction(takers);
			if (winner.isPresent()) {
				dutchStatistics.increaseSuccessfulAuctionsCount();
				dutchBuyerList.remove(winner.get());
			} else {
				dutchStatistics.increaseFailedAuctionsCount();
			}
		}

		dutchSellerList.removeAll(time);
	}

	public void runDoubleAuction() {
		Multimap<Product, DoubleAgent> sellerProductGroups = HashMultimap.create();
		for (DoubleAgent seller : doubleSellerList) {
			sellerProductGroups.put(seller.getProduct(), seller);
		}

		Multimap<Product, DoubleAgent> buyerProductGroups = HashMultimap.create();
		for (DoubleAgent buyer : doubleBuyerList) {
			buyerProductGroups.put(buyer.getProduct(), buyer);
		}

		for (Product p : sellerProductGroups.keySet()) {
			if (buyerProductGroups.containsKey(p)) {
				int price = 0;
				for (DoubleAgent agent : sellerProductGroups.get(p)) {
					price += agent.getSellerPrice();
				}
				for (DoubleAgent agent : buyerProductGroups.get(p)) {
					price += agent.getBuyerBid();
				}
				price /= (sellerProductGroups.get(p).size() + buyerProductGroups.get(p).size());
				Iterator<DoubleAgent> sellerIterator = sellerProductGroups.get(p).iterator();

				List<DoubleAgent> winners = Lists.newLinkedList();

				while (sellerIterator.hasNext()) {
					DoubleAgent agent = sellerIterator.next();
					if (agent.getSellerPrice() > price) {
						sellerIterator.remove();
					}
				}

				Iterator<DoubleAgent> buyerIterator = buyerProductGroups.get(p).iterator();
				int counter = 0;
				while (buyerIterator.hasNext() && counter < sellerProductGroups.get(p).size()) {
					DoubleAgent agent = buyerIterator.next();
					if (agent.getBuyerBid() >= price) {
						winners.add(agent);
						counter++;
					}
				}

				doubleBuyerList.removeAll(winners);
				sellerIterator = sellerProductGroups.get(p).iterator();
				for (int i = 0; i < counter; i++) {
					doubleSellerList.remove(sellerIterator.next());
				}

				doubleStatistics.increaseSuccessfulAuctionsCount(counter);

			}
		}

	}

	public MarketStatistics getReverseEnglishStatistics() {
		return reverseEnglishStatistics;
	}

	public MarketStatistics getDutchStatistics() {
		return dutchStatistics;
	}

	public MarketStatistics getDoubleStatistics() {
		return doubleStatistics;
	}

}

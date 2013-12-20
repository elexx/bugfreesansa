package tuwien.infosys.sla.auctionsimulator;

import java.util.List;
import java.util.Set;
import com.google.common.base.Optional;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class Market {

	private static final Market MARKET = new Market();

	private int currentMarketTimeslot;
	private final MarketStatistics reverseEnglishStatistics;
	private final MarketStatistics dutchStatistics;

	private final Set<ReverseEnglishAgent> reverseEnglishSellerList;
	private final Multimap<Integer, ReverseEnglishAgent> reverseEnglishBuyerList;

	private final Multimap<Integer, DutchAgent> dutchSellerList;
	private final Set<DutchAgent> dutchBuyerList;


	public static Market getInstance() {
		return Market.MARKET;
	}

	private Market() {
		reverseEnglishSellerList = Sets.newLinkedHashSet();
		reverseEnglishBuyerList = HashMultimap.create();
		reverseEnglishStatistics = new MarketStatistics();

		dutchSellerList = HashMultimap.create();
		dutchBuyerList = Sets.newLinkedHashSet();
		dutchStatistics = new MarketStatistics();
	}

	void registerInterestReverseEnglish(ReverseEnglishAgent agent) {
		reverseEnglishStatistics.increaseSupplyAgentsCount();
		reverseEnglishSellerList.add(agent);
	}

	void createReverseEnglish(ReverseEnglishAgent agent, int duration) {
		reverseEnglishStatistics.increaseDemandAgentsCount();
		reverseEnglishBuyerList.put(currentMarketTimeslot + duration, agent);
	}

	void registerInterestDutch(DutchAgent agent) {
		dutchStatistics.increaseDemandAgentsCount();
		dutchBuyerList.add(agent);
	}

	void createDutch(DutchAgent agent, int duration) {
		dutchStatistics.increaseSupplyAgentsCount();
		dutchSellerList.put(currentMarketTimeslot + duration, agent);
	}

	void registerInterestDoubleSeller(Agent self, int price) {
	} /* Eventuell ist "Agent self" unnötig */

	void registerInterestDoubleBuyer(Agent self, int price) {
	} /* Eventuell ist "Agent self" unnötig */

	public void runAuction() {

		for (ReverseEnglishAgent buyer : reverseEnglishBuyerList.get(currentMarketTimeslot)) {
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

		for(DutchAgent seller: dutchSellerList.get(currentMarketTimeslot)){
			List<DutchAgent> takers = Lists.newLinkedList();
			for(DutchAgent buyer : dutchBuyerList){
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

		reverseEnglishBuyerList.removeAll(currentMarketTimeslot);
		dutchSellerList.removeAll(currentMarketTimeslot);

		currentMarketTimeslot++;
	}

	public MarketStatistics getReverseEnglishStatistics() {
		return reverseEnglishStatistics;
	}
	
	public MarketStatistics getDutchStatistics(){
		return dutchStatistics;
	}


}

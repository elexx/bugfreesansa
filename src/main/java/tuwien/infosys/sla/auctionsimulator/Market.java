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
	
	private final MarketStatistics reverseEnglishStatistics;
	private final MarketStatistics dutchStatistics;

	private final Set<ReverseEnglishAgent> reverseEnglishSellerList;
	private final Multimap<Integer, ReverseEnglishAgent> reverseEnglishBuyerList;

	private final Multimap<Integer, DutchAgent> dutchSellerList;
	private final Set<DutchAgent> dutchBuyerList;

	private Market() {
		reverseEnglishSellerList = Sets.newLinkedHashSet();
		reverseEnglishBuyerList = HashMultimap.create();
		reverseEnglishStatistics = new MarketStatistics();

		dutchSellerList = HashMultimap.create();
		dutchBuyerList = Sets.newLinkedHashSet();
		dutchStatistics = new MarketStatistics();
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

	public void registerInterestDoubleSeller(Agent self, int price) {
	} /* Eventuell ist "Agent self" unnötig */

	public void registerInterestDoubleBuyer(Agent self, int price) {
	} /* Eventuell ist "Agent self" unnötig */
	
	
	public void runReverseEnglishAuction(int time){
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
	
	public void runDutchAuction(int time){
		for(DutchAgent seller: dutchSellerList.get(time)){
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

		dutchSellerList.removeAll(time);		
	}

	public void runDoubleAuction(){
		
	}

	public MarketStatistics getReverseEnglishStatistics() {
		return reverseEnglishStatistics;
	}
	
	public MarketStatistics getDutchStatistics(){
		return dutchStatistics;
	}
	
}

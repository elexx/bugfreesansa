package tuwien.infosys.sla.auctionsimulator;

import java.util.UUID;

public abstract class Market {

	abstract void registerInterestReverseEnglish(SellerAgent self, Product product);

	abstract void createReverseEnglish(BuyerAgent self, Product product, int duration);

	abstract void endReverseEnglish(SellerAgent winner, UUID auctionID); /* Eventuell noch Liste der Seller, die nicht gewonnen haben */

	abstract void failedReverseEnglish(UUID auctionID);



	abstract void registerInterestDutch(BuyerAgent self, Product product);

	abstract void createDutch(SellerAgent self, Product product, int duration);

	abstract void endDutch(BuyerAgent winner, UUID auctionID); /* Eventuell noch Liste der Buyer, die nicht gewonnen haben */

	abstract void failedDutch(UUID auctionID);



	abstract void registerInterestDoubleSeller(Agent self, Product product, int price); /* Eventuell ist "Agent self" unnötig */

	abstract void registerInterestDoubleBuyer(Agent self, Product product, int price); /* Eventuell ist "Agent self" unnötig */

}

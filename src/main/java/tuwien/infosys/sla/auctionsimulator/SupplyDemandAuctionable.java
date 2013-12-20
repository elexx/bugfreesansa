package tuwien.infosys.sla.auctionsimulator;

import java.util.List;

import com.google.common.base.Optional;

public interface SupplyDemandAuctionable {

	Optional<? extends SupplyDemandAuctionable> startAuction(List<? extends SupplyDemandAuctionable> takers);
}

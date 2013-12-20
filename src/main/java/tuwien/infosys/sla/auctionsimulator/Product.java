package tuwien.infosys.sla.auctionsimulator;

import com.google.common.base.Optional;

public class Product {

	public final Optional<Integer> availability;
	public final Optional<Integer> bandwith;
	public final Optional<Integer> cpu;
	public final Optional<Integer> ram;
	public final Optional<Integer> storage;
	public final Optional<Integer> latency;
	public final Optional<OperatingSystems> operatingSystem;
	public final Optional<Boolean> backupAvailable;
	public final Optional<Boolean> ipv6Support;

	public Product(Optional<Integer> availability, Optional<Integer> bandwith, Optional<Integer> cpu, Optional<Integer> ram, Optional<Integer> storage, Optional<Integer> latency, Optional<OperatingSystems> operatingSystem, Optional<Boolean> backupAvailable, Optional<Boolean> ipv6Support) {
		this.availability = availability;
		this.bandwith = bandwith;
		this.cpu = cpu;
		this.ram = ram;
		this.storage = storage;
		this.latency = latency;
		this.operatingSystem = operatingSystem;
		this.backupAvailable = backupAvailable;
		this.ipv6Support = ipv6Support;
	}

}

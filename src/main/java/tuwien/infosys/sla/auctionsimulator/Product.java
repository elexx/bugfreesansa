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

	public boolean isFulfilledBy(Product other){

		boolean availabiliy = matchInteger(this.availability, other.availability);
		boolean bandwith = matchInteger(this.bandwith, other.bandwith);
		boolean cpu = matchInteger(this.cpu, other.cpu);
		boolean ram = matchInteger(this.ram, other.ram);
		boolean storage = matchInteger(this.storage, other.storage);
		boolean latency = matchInteger(other.latency, this.latency);
		boolean operatingSystem = matchOperatingSystem(this.operatingSystem, other.operatingSystem);
		boolean backupAvailable = matchBoolean(this.backupAvailable, other.backupAvailable);
		boolean ipv6Support = matchBoolean(this.ipv6Support, other.ipv6Support);

		return availabiliy && bandwith && cpu && ram && storage && latency && operatingSystem && backupAvailable && ipv6Support;
	}

	private static boolean matchInteger(Optional<Integer> p1, Optional<Integer> p2){
		if(p1.isPresent()){
			return p2.isPresent() && p1.get() <= p2.get();
		}

		return true;
	}

	private static boolean matchBoolean(Optional<Boolean> p1, Optional<Boolean> p2){
		if(p1.isPresent() && p1.get()){
			return p2.isPresent() && p2.get();
		}

		return true;
	}

	private static boolean matchOperatingSystem(Optional<OperatingSystems> p1, Optional<OperatingSystems> p2){
		if(p1.isPresent()){
			return p2.isPresent() && p1.get().equals(p2.get());
		}

		return true;
	}

}

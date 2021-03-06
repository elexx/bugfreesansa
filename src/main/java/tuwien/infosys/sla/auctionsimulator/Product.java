package tuwien.infosys.sla.auctionsimulator;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

public class Product {

	public final Optional<Integer> availability;
	public final Optional<Integer> bandwith;
	public final Optional<Integer> cpu;
	public final Optional<Integer> ram;
	public final Optional<Integer> storage;
	public final Optional<Integer> latency;
	public final Optional<OperatingSystem> operatingSystem;
	public final Optional<Boolean> backupAvailable;
	public final Optional<Boolean> ipv6Support;

	public Product(Optional<Integer> availability, Optional<Integer> bandwith, Optional<Integer> cpu, Optional<Integer> ram, Optional<Integer> storage, Optional<Integer> latency, Optional<OperatingSystem> operatingSystem, Optional<Boolean> backupAvailable, Optional<Boolean> ipv6Support) {
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

	public boolean isFulfilledBy(Product other) {
		boolean availabiliy = matchInteger(this.availability, other.availability);
		boolean bandwith = matchInteger(this.bandwith, other.bandwith);
		boolean cpu = matchInteger(this.cpu, other.cpu);
		boolean ram = matchInteger(this.ram, other.ram);
		boolean storage = matchInteger(this.storage, other.storage);
		boolean latency = matchInteger(other.latency, this.latency);
		boolean operatingSystem = matchObject(this.operatingSystem, other.operatingSystem);
		boolean backupAvailable = matchBoolean(this.backupAvailable, other.backupAvailable);
		boolean ipv6Support = matchBoolean(this.ipv6Support, other.ipv6Support);

		return availabiliy && bandwith && cpu && ram && storage && latency && operatingSystem && backupAvailable && ipv6Support;
	}

	public boolean isExactMatch(Product other) {
		return this.isFulfilledBy(other) && other.isFulfilledBy(this);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Product) {
			Product p = (Product) o;
			return isExactMatch(p);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(availability, bandwith, cpu, ram, storage, latency, operatingSystem, backupAvailable, ipv6Support);
	}

	private static boolean matchInteger(Optional<Integer> p1, Optional<Integer> p2) {
		if (p1.isPresent() && p2.isPresent()) {
			return p1.get() <= p2.get();
		}
		return !p1.isPresent() && !p2.isPresent();
	}

	private static boolean matchBoolean(Optional<Boolean> p1, Optional<Boolean> p2) {
		if (p1.isPresent() && p2.isPresent()) {
			return p1.get() == p2.get();
		}
		return !p1.isPresent() && !p2.isPresent();
	}

	private static boolean matchObject(Optional<? extends Object> p1, Optional<? extends Object> p2) {
		if (p1.isPresent() && p2.isPresent()) {
			return p1.get().equals(p2.get());
		}
		return !p1.isPresent() && !p2.isPresent();
	}

	public int getProductGroupID() {
		int id = 0;
		id |= (availability.isPresent() ? 1 << 0 : 0);
		id |= (bandwith.isPresent() ? 1 << 1 : 0);
		id |= (cpu.isPresent() ? 1 << 2 : 0);
		id |= (ram.isPresent() ? 1 << 3 : 0);
		id |= (storage.isPresent() ? 1 << 4 : 0);
		id |= (latency.isPresent() ? 1 << 5 : 0);
		id |= (operatingSystem.isPresent() ? 1 << 6 : 0);
		id |= (backupAvailable.isPresent() ? 1 << 7 : 0);
		id |= (ipv6Support.isPresent() ? 1 << 8 : 0);
		return id;
	}

	@Override
	public String toString() {
		// @formatter:off
		return Objects.toStringHelper(this)
				.add("availability", availability.orNull())
				.add("bandwith", bandwith.orNull())
				.add("cpu", cpu.orNull())
				.add("ram", ram.orNull())
				.add("storage", storage.orNull())
				.add("latency", latency.orNull())
				.add("operatingSystem", operatingSystem.orNull())
				.add("backupAvailable", backupAvailable.orNull())
				.add("ipv6Support", ipv6Support.orNull())
				.toString();
		// @formatter:on
	}

}

package tuwien.infosys.sla.auctionsimulator;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Optional;
import com.google.common.collect.Range;


public class ProductBuilder {
	private static Range<Integer> allowedAvailability = Range.closed(85, 100);
	private static Range<Integer> allowedBandwith = Range.closed(100, 10000);
	private static Range<Integer> allowedCpu = Range.closed(1, 16);
	private static Range<Integer> allowedRam = Range.closed(1, 32);
	private static Range<Integer> allowedStorage = Range.closed(128, 1024);
	private static Range<Integer> allowedLatency = Range.closed(5, 250);

	private Integer availability;
	private Integer bandwith;
	private Integer cpu;
	private Integer ram;
	private Integer storage;
	private Integer latency;
	private OperatingSystems operatingSystem;
	private Boolean backupAvailable;
	private Boolean ipv6Support;

	private ProductBuilder() {
	}

	public static ProductBuilder newBuilder() {
		return new ProductBuilder();
	}


	public ProductBuilder availability(Integer availability) {
		checkArgument(allowedAvailability.contains(availability), "availability should be between %s and %s", allowedAvailability.lowerEndpoint(), allowedAvailability.upperEndpoint());
		this.availability = availability;
		return this;
	}

	public ProductBuilder bandwith(Integer bandwith) {
		checkArgument(allowedBandwith.contains(bandwith), "bandwith should be between %s and %s", allowedBandwith.lowerEndpoint(), allowedBandwith.upperEndpoint());
		this.bandwith = bandwith;
		return this;
	}

	public ProductBuilder cpu(Integer cpu) {
		checkArgument(allowedCpu.contains(cpu), "cpu should be between %s and %s", allowedCpu.lowerEndpoint(), allowedCpu.upperEndpoint());
		this.cpu = cpu;
		return this;
	}

	public ProductBuilder ram(Integer ram) {
		checkArgument(allowedRam.contains(ram), "ram should be between %s and %s", allowedRam.lowerEndpoint(), allowedRam.upperEndpoint());
		this.ram = ram;
		return this;
	}

	public ProductBuilder storage(Integer storage) {
		checkArgument(allowedStorage.contains(storage), "storage should be between %s and %s", allowedStorage.lowerEndpoint(), allowedStorage.upperEndpoint());
		this.storage = storage;
		return this;
	}

	public ProductBuilder latency(Integer latency) {
		checkArgument(allowedLatency.contains(latency), "latency should be between %s and %s", allowedLatency.lowerEndpoint(), allowedLatency.upperEndpoint());
		this.latency = latency;
		return this;
	}

	public ProductBuilder operatingSystem(OperatingSystems operatingSystem) {
		this.operatingSystem = operatingSystem;
		return this;
	}

	public ProductBuilder backupAvailable(Boolean backupAvailable) {
		this.backupAvailable = backupAvailable;
		return this;
	}

	public ProductBuilder ipv6Support(Boolean ipv6Support) {
		this.ipv6Support = ipv6Support;
		return this;
	}
	
	public int size(){
		int count =0;
		if(availability != null)
			count++;
		if(bandwith != null)
			count++;
		if(cpu != null)
			count++;
		if(ram != null)
			count++;
		if(storage != null)
			count++;
		if(operatingSystem != null)
			count++;
		if(backupAvailable != null)
			count++;
		if(ipv6Support != null)
			count++;
		
		return count;
	}

	public Product build() {
		return new Product(Optional.fromNullable(availability), Optional.fromNullable(bandwith), Optional.fromNullable(cpu), Optional.fromNullable(ram), Optional.fromNullable(storage), Optional.fromNullable(latency), Optional.fromNullable(operatingSystem), Optional.fromNullable(backupAvailable), Optional.fromNullable(ipv6Support));
	}
}
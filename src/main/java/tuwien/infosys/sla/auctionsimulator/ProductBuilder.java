package tuwien.infosys.sla.auctionsimulator;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Optional;
import com.google.common.collect.Range;

public class ProductBuilder {
	private static final Range<Integer> ALLOWED_AVAILABILITY = Range.closed(85, 100);
	private static final Range<Integer> ALLOWED_BANDWIDTH = Range.closed(100, 10000);
	private static final Range<Integer> ALLOWED_CPU = Range.closed(1, 16);
	private static final Range<Integer> ALLOWED_RAM = Range.closed(1, 32);
	private static final Range<Integer> ALLOWED_STORAGE = Range.closed(128, 1024);
	private static final Range<Integer> ALLOWED_LATENCY = Range.closed(5, 250);

	private Integer availability;
	private Integer bandwith;
	private Integer cpu;
	private Integer ram;
	private Integer storage;
	private Integer latency;
	private OperatingSystem operatingSystem;
	private Boolean backupAvailable;
	private Boolean ipv6Support;

	private ProductBuilder() {
	}

	public static ProductBuilder newBuilder() {
		return new ProductBuilder();
	}

	public ProductBuilder availability(Integer availability) {
		checkArgument(ProductBuilder.ALLOWED_AVAILABILITY.contains(availability), "availability should be between %s and %s", ProductBuilder.ALLOWED_AVAILABILITY.lowerEndpoint(), ProductBuilder.ALLOWED_AVAILABILITY.upperEndpoint());
		this.availability = availability;
		return this;
	}

	public ProductBuilder bandwith(Integer bandwith) {
		checkArgument(ProductBuilder.ALLOWED_BANDWIDTH.contains(bandwith), "bandwith should be between %s and %s", ProductBuilder.ALLOWED_BANDWIDTH.lowerEndpoint(), ProductBuilder.ALLOWED_BANDWIDTH.upperEndpoint());
		this.bandwith = bandwith;
		return this;
	}

	public ProductBuilder cpu(Integer cpu) {
		checkArgument(ProductBuilder.ALLOWED_CPU.contains(cpu), "cpu should be between %s and %s", ProductBuilder.ALLOWED_CPU.lowerEndpoint(), ProductBuilder.ALLOWED_CPU.upperEndpoint());
		this.cpu = cpu;
		return this;
	}

	public ProductBuilder ram(Integer ram) {
		checkArgument(ProductBuilder.ALLOWED_RAM.contains(ram), "ram should be between %s and %s", ProductBuilder.ALLOWED_RAM.lowerEndpoint(), ProductBuilder.ALLOWED_RAM.upperEndpoint());
		this.ram = ram;
		return this;
	}

	public ProductBuilder storage(Integer storage) {
		checkArgument(ProductBuilder.ALLOWED_STORAGE.contains(storage), "storage should be between %s and %s", ProductBuilder.ALLOWED_STORAGE.lowerEndpoint(), ProductBuilder.ALLOWED_STORAGE.upperEndpoint());
		this.storage = storage;
		return this;
	}

	public ProductBuilder latency(Integer latency) {
		checkArgument(ProductBuilder.ALLOWED_LATENCY.contains(latency), "latency should be between %s and %s", ProductBuilder.ALLOWED_LATENCY.lowerEndpoint(), ProductBuilder.ALLOWED_LATENCY.upperEndpoint());
		this.latency = latency;
		return this;
	}

	public ProductBuilder operatingSystem(OperatingSystem operatingSystem) {
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

	/**
	 * @return the number of already set parameters
	 */
	public int size() {
		int count = 0;
		if (availability != null)
			count++;
		if (bandwith != null)
			count++;
		if (cpu != null)
			count++;
		if (ram != null)
			count++;
		if (storage != null)
			count++;
		if (latency != null)
			count++;
		if (operatingSystem != null)
			count++;
		if (backupAvailable != null)
			count++;
		if (ipv6Support != null)
			count++;

		return count;
	}

	public Product build() {
		return new Product(Optional.fromNullable(availability), Optional.fromNullable(bandwith), Optional.fromNullable(cpu), Optional.fromNullable(ram), Optional.fromNullable(storage), Optional.fromNullable(latency), Optional.fromNullable(operatingSystem), Optional.fromNullable(backupAvailable), Optional.fromNullable(ipv6Support));
	}
}

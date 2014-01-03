package tuwien.infosys.sla.auctionsimulator;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Random;

public class ProductGenerator {
	private static final int[] BANDWIDTH = new int[] { 100, 200, 400, 800, 1600, 3200, 6400, 10000 };
	private static final int[] CPU = new int[] { 1, 2, 4, 8, 16 };
	private static final int[] RAM = new int[] { 1, 2, 4, 8, 16, 32 };
	private static final int[] STORAGE = new int[] { 128, 256, 512, 1024 };
	private static final int[] LATENCY = new int[] { 5, 10, 15, 20, 50, 100, 250 };

	private final Random random;

	public ProductGenerator(Random random) {
		this.random = random;
	}

	/**
	 * Generates a {@link Product}, filled with random parameter values.
	 * 
	 * @param parameterAmount
	 *            the number of set parameters of the returned Product (between
	 *            3 and 9)
	 * 
	 * @return the generated Product
	 */
	public Product generateRandomProduct(int parameterAmount) {
		checkArgument(parameterAmount >= 3 && parameterAmount <= 9, "paramAmount should be between 3 and 9");
		ProductBuilder builder = ProductBuilder.newBuilder();

		if (parameterAmount == 9) {
			// @formatter:off
			return builder
					.availability(genAvailability())
					.bandwith(genBandwith())
					.cpu(genCpu())
					.ram(genRam())
					.storage(genStorage())
					.latency(genLatency())
					.operatingSystem(genOperatingSystem())
					.backupAvailable(genBackupAvailable())
					.ipv6Support(genIpv6Support())
					.build();
			// @formatter:on
		}

		while (builder.size() < parameterAmount) {
			builder = addArbitraryParameter(builder);
		}

		return builder.build();
	}

	private ProductBuilder addArbitraryParameter(ProductBuilder builder) {
		switch (random.nextInt(9)) {
		case 0:
			return builder.availability(genAvailability());
		case 1:
			return builder.bandwith(genBandwith());
		case 2:
			return builder.cpu(genCpu());
		case 3:
			return builder.ram(genRam());
		case 4:
			return builder.storage(genStorage());
		case 5:
			return builder.latency(genLatency());
		case 6:
			return builder.operatingSystem(genOperatingSystem());
		case 7:
			return builder.backupAvailable(genBackupAvailable());
		case 8:
			return builder.ipv6Support(genIpv6Support());
		default:
			throw new Error("Random.nextInt() returned an unexpected number");
		}
	}

	private int genAvailability() {
		return random.nextInt(100 - 85) + 85;
	}

	private int genBandwith() {
		return ProductGenerator.BANDWIDTH[random.nextInt(ProductGenerator.BANDWIDTH.length)];
	}

	private int genCpu() {
		return ProductGenerator.CPU[random.nextInt(ProductGenerator.CPU.length)];
	}

	private int genRam() {
		return ProductGenerator.RAM[random.nextInt(ProductGenerator.RAM.length)];
	}

	private int genStorage() {
		return ProductGenerator.STORAGE[random.nextInt(ProductGenerator.STORAGE.length)];
	}

	private int genLatency() {
		return ProductGenerator.LATENCY[random.nextInt(ProductGenerator.LATENCY.length)];
	}

	private OperatingSystem genOperatingSystem() {
		return OperatingSystem.fromValue(random.nextInt(3) + 1);
	}

	private boolean genBackupAvailable() {
		return random.nextBoolean();
	}

	private boolean genIpv6Support() {
		return random.nextBoolean();
	}

}

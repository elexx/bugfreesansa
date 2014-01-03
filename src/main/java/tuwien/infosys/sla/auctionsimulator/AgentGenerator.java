package tuwien.infosys.sla.auctionsimulator;

import java.util.Random;

public class AgentGenerator {

	private final Random random;
	private final ProductGenerator productGenerator;

	public AgentGenerator(Random random) {
		this.random = random;
		this.productGenerator = new ProductGenerator(random);
	}

	public ReverseEnglishAgent generateReverseEnglishAgent(int minParamAmount, int maxParamAmount) {
		Product product = productGenerator.generateRandomProduct(genParamAmount(minParamAmount, maxParamAmount));
		int[] prices = genPrice(product);
		return new ReverseEnglishAgent(prices[0], prices[1], product);
	}

	public DutchAgent generateDutchAgent(int minParamAmount, int maxParamAmount) {
		Product product = productGenerator.generateRandomProduct(genParamAmount(minParamAmount, maxParamAmount));
		int[] prices = genPrice(product);
		return new DutchAgent(prices[0], prices[1], product);
	}

	public DoubleAgent generateDoubleAgent(int minParamAmount, int maxParamAmount) {
		Product product = productGenerator.generateRandomProduct(genParamAmount(minParamAmount, maxParamAmount));
		int[] prices = genPrice(product);
		return new DoubleAgent(prices[0], prices[1], product);
	}

	private int genParamAmount(int minParamAmount, int maxParamAmount) {
		if (minParamAmount == maxParamAmount) {
			return minParamAmount;
		}
		return random.nextInt(maxParamAmount - minParamAmount) + minParamAmount;
	}

	private int[] genPrice(Product product) {
		double price = 0;

		if (product.availability.isPresent())
			price += genAvailablityPrice(product.availability.get());
		if (product.bandwith.isPresent())
			price += genBandwidthPrice(product.bandwith.get());
		if (product.cpu.isPresent())
			price += genCPUPrice(product.cpu.get());
		if (product.ram.isPresent())
			price += genRAMPrice(product.ram.get());
		if (product.storage.isPresent())
			price += genStoragePrice(product.storage.get());
		if (product.latency.isPresent())
			price += genLatencyPrice(product.latency.get());
		if (product.backupAvailable.isPresent() && product.backupAvailable.get().equals(true))
			price += genBackupAvailablePrice();
		if (product.operatingSystem.isPresent())
			price += genOperatingSystemPrice(product.operatingSystem.get());
		if (product.ipv6Support.isPresent() && product.ipv6Support.get().equals(true))
			price += genIPv6SupportPrice();

		double minPrice = 0, maxPrice = 0;
		minPrice = price * ((random.nextInt(75 - 50) + 50) / 100.0D);
		maxPrice = price * ((random.nextInt(120 - 80) + 80) / 100.0D);
		return new int[] { (int) Math.round(minPrice), (int) Math.round(maxPrice) };
	}

	private double genAvailablityPrice(int availablity) {
		return Math.pow(2, (99 / 10.0D));
	}

	private double genBandwidthPrice(int bandwidth) {
		return bandwidth * 0.01D;
	}

	private double genCPUPrice(int cpu) {
		return cpu * 8.0D;
	}

	private double genRAMPrice(int ram) {
		return ram * 5.0D;
	}

	private double genStoragePrice(int storage) {
		return storage * 0.25D;
	}

	private double genLatencyPrice(int latency) {
		return 1250.0D / latency;
	}

	private double genOperatingSystemPrice(OperatingSystem os) {
		switch (os) {
		case UNKNOWN: {
			return 0;
		}
		case UNIX: {
			return 100;
		}
		case WINDOWS: {
			return 50;
		}
		case LINUX: {
			return 0;
		}
		}

		return 0;
	}

	private double genBackupAvailablePrice() {
		return 0.125;
	}

	private double genIPv6SupportPrice() {
		return 5.0;
	}
}

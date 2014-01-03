package tuwien.infosys.sla.auctionsimulator;

import java.util.Random;

import com.google.common.base.Optional;

public class AgentGenerator {
 
	private final Random random;
	private ProductGenerator productGenerator;
	

	public AgentGenerator() {
		this.random = new Random();
		this. productGenerator = new ProductGenerator(random);
	}
	
	public ReverseEnglishAgent generateReverseEnglishAgent(int minParams, int maxParams){
		Product product = productGenerator.generateRandomProduct(random.nextInt(maxParams-minParams)+minParams);
		int[] prices = genPrice(product);
		return new ReverseEnglishAgent(prices[0], prices[1], product);
	}
	
	public DutchAgent generateDutchAgent(int minParams, int maxParams){
		Product product = productGenerator.generateRandomProduct(random.nextInt(maxParams-minParams)+minParams);
		int[] prices = genPrice(product);
		return new DutchAgent(prices[0], prices[1], product);		
	}
	
	public DoubleAgent generateDoubleAgent(int minParams, int maxParams){
		Product product = productGenerator.generateRandomProduct(random.nextInt(maxParams-minParams)+minParams);
		int[] prices = genPrice(product);
		return new DoubleAgent(prices[0], prices[1], product);			
	}
	
	
	private int[] genPrice(Product product){
		double minPrice =0;
		double maxPrice =0;
		
		if(product.availability.isPresent())
			minPrice += genAvailablityPrice(product.availability.get());
		if(product.bandwith.isPresent())
			minPrice += genBandwidthPrice(product.bandwith.get());
		if(product.cpu.isPresent())
			minPrice += genCPUPrice(product.cpu.get());
		if(product.ram.isPresent())
			minPrice += genRAMPrice(product.ram.get());
		if(product.storage.isPresent())
			minPrice += genStoragePrice(product.storage.get());
		if(product.latency.isPresent())
			minPrice += genLatencyPrice(product.latency.get());
		if(product.backupAvailable.isPresent() && product.backupAvailable.get().equals(true))	
			minPrice += genBackupAvailablePrice();			
		if(product.operatingSystem.isPresent())	
			minPrice += genOperatingSystemPrice(product.operatingSystem.get());
		if(product.ipv6Support.isPresent() && product.backupAvailable.get().equals(true))	
			minPrice += genIPv6SupportPrice();
		
		minPrice = minPrice * ((random.nextInt(75-50)+85)/100.0D);
		maxPrice = minPrice * ((random.nextInt(120-80)+80)/100.0D);
		return new int[]{(int) Math.round(minPrice), (int) Math.round(maxPrice)};
	}
	
	
	private double genAvailablityPrice(int availablity){
		return Math.pow(2, (99/10.0D));
	}
	
	private double genBandwidthPrice(int bandwidth){
		return bandwidth * 0.01D;
	}
	
	private double genCPUPrice(int cpu){
		return cpu * 8.0D;
	}
	
	private double genRAMPrice(int ram){
		return ram * 5.0D;
	}
	
	private double genStoragePrice(int storage){
		return storage * 0.25D;
	}
	
	private double genLatencyPrice(int latency){
		return 1250.0D/latency;
	}
	
	private double genOperatingSystemPrice(OperatingSystem os){
		switch(os){
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
	
	private double genBackupAvailablePrice(){
		return 0.125;
	}
	
	private double genIPv6SupportPrice(){
		return 5.0;
	}
	
	private double getRandomVariation(){
		return ((random.nextInt(120-80)+80)/100.0D);
	}
}
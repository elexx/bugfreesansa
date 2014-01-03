package tuwien.infosys.sla.auctionsimulator;

public enum OperatingSystem {
	UNKNOWN(0), LINUX(1), WINDOWS(2), UNIX(3);

	private int value;

	private OperatingSystem(int value) {
		this.value = value;
	}

	public static OperatingSystem fromValue(int value) {
		for (OperatingSystem os : OperatingSystem.values()) {
			if (os.value == value) {
				return os;
			}
		}
		return OperatingSystem.UNKNOWN;
	}
}

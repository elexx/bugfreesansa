package tuwien.infosys.sla.auctionsimulator;

public enum OperatingSystems {
	UNKNOWN(0), LINUX(1), WINDOWS(2), UNIX(3);

	private int value;

	private OperatingSystems(int value) {
		this.value = value;
	}

	public static OperatingSystems fromValue(int value) {
		for (OperatingSystems os : OperatingSystems.values()) {
			if (os.value == value) {
				return os;
			}
		}
		return OperatingSystems.UNKNOWN;
	}
}

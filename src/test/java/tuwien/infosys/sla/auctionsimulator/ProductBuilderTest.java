package tuwien.infosys.sla.auctionsimulator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ProductBuilderTest {

	@Test
	public void threePresentTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		Product p = builder.availability(85).backupAvailable(false).cpu(16).build();

		assertThat(p.availability.isPresent(), is(true));
		assertThat(p.availability.get(), is(85));

		assertThat(p.backupAvailable.isPresent(), is(true));
		assertThat(p.backupAvailable.get(), is(false));

		assertThat(p.cpu.isPresent(), is(true));
		assertThat(p.cpu.get(), is(16));
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooHighAvailabilityTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.availability(100 + 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLowAvailabilityTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.availability(0 - 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooHighBandwithTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.bandwith(10000 + 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLowBandwithTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.bandwith(0 - 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooHighCpuTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.cpu(16 + 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLowCpuTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.cpu(1 - 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooHighRamTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.ram(32 + 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLowRamTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.ram(1 - 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooHighStorageTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.storage(1024 + 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLowStorageTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.storage(1 - 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooHighLatencyTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.latency(250 + 1).build();

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooLowLatencyTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();
		builder.latency(5 - 1).build();

		fail();
	}

	@Test
	public void countTest() {
		ProductBuilder builder = ProductBuilder.newBuilder();

		builder.availability(100);
		assertThat(builder.size(), is(1));

		builder.bandwith(100);
		assertThat(builder.size(), is(2));

		builder.cpu(1);
		assertThat(builder.size(), is(3));

		builder.ram(1);
		assertThat(builder.size(), is(4));

		builder.storage(1024);
		assertThat(builder.size(), is(5));

		builder.latency(10);
		assertThat(builder.size(), is(6));

		builder.operatingSystem(OperatingSystem.LINUX);
		assertThat(builder.size(), is(7));

		builder.backupAvailable(true);
		assertThat(builder.size(), is(8));

		builder.ipv6Support(false);
		assertThat(builder.size(), is(9));

	}

}

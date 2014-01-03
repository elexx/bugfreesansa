package tuwien.infosys.sla.auctionsimulator.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigLoader {
	private static final String FILENAME = "config.json";
	private final Gson gson ;

	public ConfigLoader() throws IOException {
		this.gson = new GsonBuilder().create();

		File file = new File(ConfigLoader.FILENAME);
		if (file.createNewFile()) {
			try (FileWriter fileWriter = new FileWriter(ConfigLoader.FILENAME)) {
				gson.toJson(new Config(), Config.class, fileWriter);
				fileWriter.flush();
			}
		}
	}

	public Config load() throws IOException {
		try (FileReader fileReader = new FileReader(ConfigLoader.FILENAME)) {
			return gson.fromJson(fileReader, Config.class);
		}
	}

	public void store(Config config) throws IOException {
		try (FileWriter fileWriter = new FileWriter(ConfigLoader.FILENAME)) {
			gson.toJson(config, Config.class, fileWriter);
			fileWriter.flush();
		}
	}
}

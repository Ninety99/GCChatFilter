package me.NinetyNine.chatfilter;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class ChatFilterConfig implements Listener {

	public static void loadConfig() {
		if (ChatFilter.getInstance().getDataFolder().exists())
			return;

		getConfig().options().copyDefaults(true);
		save();
	}

	public static void addWhitelist(String path, String word) {
		List<String> list = getConfig().getStringList(path);

		for (String words : list) {
			for (int i = 0; i < list.size(); i++)
				if (list.get(i).equalsIgnoreCase(words))
					return;
		}

		if (!list.contains(word)) {
			list.add(word);
			set(path, list);
			save();
		} else
			return;
	}

	public static void removeWhitelist(String path, String word) {
		List<String> list = getConfig().getStringList(path);

		list.removeIf(word::equalsIgnoreCase);
		set(path, list);
	}

	public static FileConfiguration getConfig() {
		return ChatFilter.getInstance().getConfig();
	}

	public static void save() {
		ChatFilter.getInstance().saveConfig();
		ChatFilter.getInstance().reloadConfig();
	}

	public static void set(String path, Object object) {
		getConfig().set(path, object);
		save();
	}

	public static String getString(String path) {
		return getConfig().getString(path);
	}

	public static int getInt(String path) {
		return getConfig().getInt(path);
	}
}

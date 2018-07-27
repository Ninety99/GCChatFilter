package me.NinetyNine.chatfilter.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ChatUtils {
	public static String format(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public static void sendConsole(String message) {
		Bukkit.getServer().getLogger().info("[GCChatFilter] " + message);
	}
}
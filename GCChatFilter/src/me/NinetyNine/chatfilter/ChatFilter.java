package me.NinetyNine.chatfilter;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.NinetyNine.chatfilter.commands.ChatFilterCommands;
import me.NinetyNine.chatfilter.utils.ChatUtils;

public class ChatFilter extends JavaPlugin {
	
	@Getter
	private static ChatFilter instance;
	
	@Override
	public void onEnable() {
		instance = this;
	
		registerListeners();
		registerCommand();
		ChatFilterConfig.loadConfig();
		ChatUtils.sendConsole("Enabled!");
	}

	@Override
	public void onDisable() {
		ChatFilterConfig.save();
		ChatUtils.sendConsole("Disabled!");
	}
	
	public void registerCommand() {
		getCommand("gcf").setExecutor(new ChatFilterCommands());
	}
	
	public void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new ChatCapsChecker(), this);
		pm.registerEvents(new ChatFilterCommands(), this);
		pm.registerEvents(new ChatFilterConfig(), this);
	}
}
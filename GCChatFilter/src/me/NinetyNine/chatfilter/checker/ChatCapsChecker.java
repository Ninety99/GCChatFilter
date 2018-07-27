package me.NinetyNine.chatfilter.checker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.NinetyNine.chatfilter.ChatFilterConfig;

public class ChatCapsChecker implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		String msg = e.getMessage().trim();

		int caps = 0;
		String after = "";

		String[] args = msg.split(" ");

		for (String word : args)
			if (ChatFilterConfig.getConfig().getStringList("whitelist").contains(word))
				return;
		
		for (int i = 0; i < msg.length(); i++) {
			if (Character.isUpperCase(msg.charAt(i)))
				caps++;
		}

		if (caps > ChatFilterConfig.getInt("maxcaps"))
			after = ("" + msg.charAt(0)).toUpperCase() + msg.substring(1).toLowerCase();

		if (after.length() > 0)
			e.setMessage(after);
		else
			return;
	}
}
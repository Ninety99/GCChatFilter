package me.NinetyNine.chatfilter.checker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.NinetyNine.chatfilter.ChatFilterConfig;

public class ChatCapsChecker implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		if (e.getPlayer().hasPermission("gcf.bypass"))
			return;
		else {
			String msg = e.getMessage().trim();

			int caps = 0;
			String after = "";

			String[] args = msg.split(" ");

			for (int i = 0; i < msg.length(); i++) {
				if (Character.isUpperCase(msg.charAt(i)))
					caps++;
			}

			for (int i = 0; i < args.length; i++) {
				if (ChatFilterConfig.getConfig().getStringList("whitelist").contains(args[i].toUpperCase()))
					args[i].toUpperCase();
			}

			if (caps > ChatFilterConfig.getInt("maxcaps")) {
				if (Character.isUpperCase(msg.charAt(0))) {
					after = ("" + msg.charAt(0)).toUpperCase() + msg.substring(1).toLowerCase();
				} else
					after = msg.substring(1).toLowerCase();
			}
			
			if (after.length() > 0)
				e.setMessage(after);
			else
				return;
		}
	}
}
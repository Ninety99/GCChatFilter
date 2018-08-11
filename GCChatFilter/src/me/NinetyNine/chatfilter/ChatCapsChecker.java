package me.NinetyNine.chatfilter;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatCapsChecker implements Listener {

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		if (e.getPlayer().hasPermission("gcf.bypass"))
			return;
		else
			third(e);
	}

	public void third(AsyncPlayerChatEvent e) {
		String message = e.getMessage();

		String[] args = message.split(" ");

		int caps = 0;

		String after = "";
		boolean isWhitelisted = false;
		boolean isName = false;

		for (String word : args) {
			System.out.println(word + " is the word");
			if (ChatFilterConfig.getConfig().getStringList("whitelist").contains(word.toUpperCase()))
				isWhitelisted = true;

			for (Player on : Bukkit.getServer().getOnlinePlayers()) {
				if (on.getName().equals(word))
					isName = true;
			}

			if (!(isWhitelisted && isName)) {
				for (int i = 0; i < word.length(); i++) {
					if (Character.isUpperCase(word.charAt(i)))
						caps++;
					System.out.println("caps++");
				}

				if (caps > ChatFilterConfig.getInt("maxcaps")) {
					String newWord = word.toLowerCase();
					if (Character.isUpperCase(word.charAt(0)))
						after = word.substring(0, 1).toUpperCase() + message.replace(word, newWord);
					else
						after = message.replace(word, newWord);

					System.out.println("replace " + word + " to " + newWord);

					System.out.println("'" + after + "' is now the message");

					caps = 0;
				} else
					return;

				e.setMessage(after);
			}
		}
	}

	public void test(AsyncPlayerChatEvent e) {
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
			if (Character.isUpperCase(msg.charAt(0)))
				after = ("" + msg.charAt(0)).toUpperCase() + msg.substring(1).toLowerCase();

			if (after.length() > 0)
				e.setMessage(after);
			else
				return;
		}
	}
}
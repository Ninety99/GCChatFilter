package me.NinetyNine.chatfilter.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.chatfilter.ChatFilterConfig;
import me.NinetyNine.chatfilter.utils.ChatUtils;

public class ChatFilterCommands implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (cmd.getName().equalsIgnoreCase("gcf")) {
				if (args.length == 0) {
					sender.sendMessage(ChatUtils.format("&cUsage: /gcf whitelist (add|remove) <word>"));
					return true;
				}

				if (args[0].equalsIgnoreCase("whitelist")) {
					if (args.length == 1) {
						sender.sendMessage(ChatUtils.format("&cUsage: /gcf whitelist (add|remove) <word>"));
						return true;
					}

					if (args[1].equalsIgnoreCase("add")) {
						if (args.length == 2) {
							sender.sendMessage(ChatUtils.format("&cUsage: /gcf whitelist add <word>"));
							return true;
						} else if (args.length == 3) {
							String word = args[2];
							ChatFilterConfig.addWhitelist("whitelist", word);
							sender.sendMessage(ChatUtils.format("&aSuccesffully added " + word + " to whitelist!"));
							return true;
						}
					}

					if (args[1].equalsIgnoreCase("remove")) {
						if (args.length == 2) {
							sender.sendMessage(ChatUtils.format("&cUsage: /gcf whitelist remove <word>"));
							return true;
						} else if (args.length == 3) {
							String word = args[2];
							ChatFilterConfig.removeWhitelist("whitelist", word);
							sender.sendMessage(ChatUtils.format("&aSuccesffully removed " + word + " from whitelist!"));
							return true;
						}
					}

					if (args[1].equalsIgnoreCase("list")) {
						if (args.length == 2) {
							List<String> list = ChatFilterConfig.getConfig().getStringList("whitelist");
							if (!(list.isEmpty())) {
								sender.sendMessage(ChatUtils.format("&5Whitelisted words:"));
								for (String words : list)
									sender.sendMessage(ChatColor.GRAY + words);
								return true;
							} else {
								sender.sendMessage(ChatUtils.format("&95Nothing whitelisted found!"));
								return true;
							}
						}
					}
				}
				
				if (args[0].equalsIgnoreCase("setmaxcaps")) {
					if (args.length == 1) {
						sender.sendMessage(ChatUtils.format("&cUsage: /gcf setmaxcaps <number>"));
						return true;
					}
					int number = Integer.parseInt(args[1]);
					ChatFilterConfig.set("maxcaps", number);
					sender.sendMessage(ChatUtils.format("&aSuccesffully set max caps to: " + number));
					return true;
				}

				if (args.length >= 4) {
					sender.sendMessage(ChatUtils.format("&cInvalid command."));
					return true;
				}

				if (!(args[0].equalsIgnoreCase("whitelist") && args[0].equalsIgnoreCase("setmaxcaps")
						&& args[1].equalsIgnoreCase("add") && args[1].equals("remove")
						&& args[1].equalsIgnoreCase("list"))) {
					sender.sendMessage(ChatUtils.format("&cInvalid command."));
					return true;
				}

			} else
				return true;
		} else {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("gcf")) {
				if (player.hasPermission("gcf.whitelist")) {
					if (args.length == 0) {
						player.sendMessage(ChatUtils.format("&cUsage: /gcf whitelist (add|remove|list) <word>"));
						return true;
					}

					if (args[0].equalsIgnoreCase("whitelist")) {
						if (args.length == 1) {
							player.sendMessage(ChatUtils.format("&cUsage: /gcf whitelist (add|remove|list) <word>"));
							return true;
						}

						if (args[1].equalsIgnoreCase("add")) {
							if (args.length == 2) {
								player.sendMessage(ChatUtils.format("&cUsage: /gcf whitelist add <word>"));
								return true;
							} else if (args.length == 3) {
								String word = args[2];
								ChatFilterConfig.addWhitelist("whitelist", word);
								player.sendMessage(ChatUtils.format("&aSuccesffully added " + word + " to whitelist!"));
								return true;
							}
						}

						if (args[1].equalsIgnoreCase("remove")) {
							if (args.length == 2) {
								player.sendMessage(ChatUtils.format("&cUsage: /gcf whitelist remove <word>"));
								return true;
							} else if (args.length == 3) {
								String word = args[2];
								ChatFilterConfig.removeWhitelist("whitelist", word);
								player.sendMessage(
										ChatUtils.format("&aSuccesffully removed " + word + " from whitelist!"));
								return true;
							}
						}

						if (args[1].equalsIgnoreCase("list")) {
							if (args.length == 2) {
								List<String> list = ChatFilterConfig.getConfig().getStringList("whitelist");
								if (!(list.isEmpty())) {
									player.sendMessage(ChatUtils.format("&5Whitelisted words:"));
									for (String words : list)
										player.sendMessage(ChatColor.GRAY + words);
									return true;
								} else {
									player.sendMessage(ChatUtils.format("&9Nothing whitelisted found!"));
									return true;
								}
							}
						}
					}

					if (args[0].equalsIgnoreCase("setmaxcaps")) {
						if (args.length == 1) {
							player.sendMessage(ChatUtils.format("&cUsage: /gcf setmaxcaps <number>"));
							return true;
						}
						int number = Integer.parseInt(args[1]);
						ChatFilterConfig.set("maxcaps", number);
						player.sendMessage(ChatUtils.format("&aSuccesffully set max caps to: " + number));
						return true;
					}

					if (args.length >= 4) {
						player.sendMessage(ChatUtils.format("&cInvalid command."));
						return true;
					}

					if (!(args[0].equalsIgnoreCase("whitelist") && args[0].equalsIgnoreCase("setmaxcaps")
							&& args[1].equalsIgnoreCase("add") && args[1].equals("remove")
							&& args[1].equalsIgnoreCase("list"))) {
						player.sendMessage(ChatUtils.format("&cInvalid command."));
						return true;
					}
				} else {
					player.sendMessage(ChatUtils.format("&CYou do not have permissions to use this command!"));
					return true;
				}
			} else
				return true;
		}
		return false;
	}
}

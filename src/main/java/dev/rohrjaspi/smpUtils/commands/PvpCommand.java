package dev.rohrjaspi.smpUtils.commands;

import dev.rohrjaspi.smpUtils.api.pvp.PvpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PvpCommand implements CommandExecutor {

	private PvpManager pvpManager;

	public PvpCommand(PvpManager pvpManager) {
		this.pvpManager = pvpManager;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

		if (!(sender instanceof Player player)) return false;

		if (args.length == 0) {
			player.sendMessage("Usage: /pvp <enable|disable>");
			return false;
		}

		String actions = args[0];

		switch (actions.toLowerCase()) {
			case "enable":
				pvpManager.setPvPEnabled(player.getUniqueId());
				break;
			case "disable":
				pvpManager.disablePvP(player.getUniqueId());
				break;
			default:
				player.sendMessage("Usage: /pvp <enable|disable>");
				break;
		}

		return false;
	}
}

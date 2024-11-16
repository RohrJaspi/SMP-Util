package dev.rohrjaspi.smpUtils.api.pvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PvpManager {

	private final HashMap<UUID, Boolean> pvpStatus = new HashMap<UUID, Boolean>();

	public void setPvPEnabled(UUID uuid) {

		Player player = Bukkit.getPlayer(uuid);

		if (isPvPEnabled(uuid)) {
			player.sendMessage("PvP is already enabled for you.");
			return;
		}

		if (!isPvPEnabled(uuid)) {
			pvpStatus.put(uuid, true);
			player.sendMessage("PvP has been enabled for you.");
			return;
		}
	}

	private boolean getPvPStatus(UUID uuid) {
		return pvpStatus.getOrDefault(uuid, false);
	}

	public boolean isPvPEnabled(UUID uuid) {
		return getPvPStatus(uuid);
	}

	public void disablePvP(UUID uuid) {

		Player player = Bukkit.getPlayer(uuid);

		if (!isPvPEnabled(uuid)) {
			player.sendMessage("PvP is already disabled");
		}

		if (isPvPEnabled(uuid)) {
			pvpStatus.put(uuid, false);
			player.sendMessage("PvP has been disabled");
		}
	}

}

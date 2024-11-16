package dev.rohrjaspi.smpUtils.listener;

import dev.rohrjaspi.smpUtils.api.pvp.PvpManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvPListener implements Listener {

	private final PvpManager pvpManager;

	public PvPListener(PvpManager pvpManager) {
		this.pvpManager = pvpManager;
	}

	@EventHandler
	public void onPvP(EntityDamageByEntityEvent event) {
		// Ensure the damaged entity is a player
		if (!(event.getEntity() instanceof Player)) {
			return; // Allow non-player entities to handle damage normally
		}
		Player damaged = (Player) event.getEntity();

		// Check if the damager is a player
		Entity damager = event.getDamager();
		if (damager instanceof Player) {
			Player damagerPlayer = (Player) damager;

			// Check PvP status of both players
			boolean isDamagedPvPEnabled = pvpManager.isPvPEnabled(damaged.getUniqueId());
			boolean isDamagerPvPEnabled = pvpManager.isPvPEnabled(damagerPlayer.getUniqueId());

			// PvP disabled logic
			if (!isDamagerPvPEnabled) {
				damagerPlayer.sendMessage("You can't attack other players because your PvP is disabled.");
				event.setCancelled(true);
				System.out.println(damagerPlayer.getName() + " attempted to attack but their PvP is disabled.");
				return;
			}

			if (!isDamagedPvPEnabled) {
				damagerPlayer.sendMessage("You can't attack this player because their PvP is disabled.");
				event.setCancelled(true);
				System.out.println(damagerPlayer.getName() + " attempted to attack " + damaged.getName() + " but their PvP is disabled.");
				return;
			}

			// PvP is allowed
			event.setCancelled(false);
			System.out.println("PvP allowed between " + damagerPlayer.getName() + " and " + damaged.getName());
		} else {
			// Allow non-player entities (like zombies) to damage players
			System.out.println(damager.getType() + " is attacking " + damaged.getName());
			event.setCancelled(false);
		}
	}
}

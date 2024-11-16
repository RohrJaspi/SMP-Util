package dev.rohrjaspi.smpUtils;

import dev.rohrjaspi.smpUtils.api.pvp.PvpManager;
import dev.rohrjaspi.smpUtils.commands.PvpCommand;
import dev.rohrjaspi.smpUtils.listener.PvPListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		PvpManager pvpManager = new PvpManager();


		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PvPListener(pvpManager), this);

		getCommand("pvp").setExecutor(new PvpCommand(pvpManager));

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}

package net.mcreator.nexusmc.procedures;

import net.mcreator.nexusmc.network.NexusModVariables;

public class Old8Procedure {
	public static String execute() {
		return NexusModVariables.terminal_chache.get((int) (NexusModVariables.terminal_chache.size() - 8)) instanceof String _s ? _s : "";
	}
}

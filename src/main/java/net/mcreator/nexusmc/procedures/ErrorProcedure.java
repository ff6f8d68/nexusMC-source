package net.mcreator.nexusmc.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.nexusmc.network.NexusModVariables;

public class ErrorProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String ret = "";
		return entity.getData(NexusModVariables.PLAYER_VARIABLES).curse_r;
	}
}

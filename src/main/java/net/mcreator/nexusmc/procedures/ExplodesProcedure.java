package net.mcreator.nexusmc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import net.mcreator.nexusmc.NexusMod;

public class ExplodesProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		for (int index0 = 0; index0 < 400; index0++) {
			NexusMod.queueServerWork(10, () -> {
				if (world instanceof Level _level && !_level.isClientSide())
					_level.explode(null, x, y, z, 10, Level.ExplosionInteraction.NONE);
			});
		}
	}
}

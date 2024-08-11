
package net.mcreator.nexusmc.item.inventory;

import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.client.Minecraft;

import net.mcreator.nexusmc.init.NexusModItems;
import net.mcreator.nexusmc.client.gui.TGUIScreen;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class TerminalInventoryCapability extends ItemStackHandler {
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void onItemDropped(ItemTossEvent event) {
		if (event.getEntity().getItem().getItem() == NexusModItems.TERMINAL.get()) {
			if (Minecraft.getInstance().screen instanceof TGUIScreen) {
				Minecraft.getInstance().player.closeContainer();
			}
		}
	}

	public TerminalInventoryCapability() {
		super(0);
	}

	@Override
	public int getSlotLimit(int slot) {
		return 64;
	}

	@Override
	public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
		return stack.getItem() != NexusModItems.TERMINAL.get();
	}

	@Override
	public void setSize(int size) {
	}
}


/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.nexusmc.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.nexusmc.client.gui.WDGUIScreen;
import net.mcreator.nexusmc.client.gui.StoreunusedScreen;
import net.mcreator.nexusmc.client.gui.RSHScreen;
import net.mcreator.nexusmc.client.gui.IFGUIScreen;
import net.mcreator.nexusmc.client.gui.CTSGUIScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NexusModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(NexusModMenus.CTSGUI.get(), CTSGUIScreen::new);
		event.register(NexusModMenus.RSH.get(), RSHScreen::new);
		event.register(NexusModMenus.IFGUI.get(), IFGUIScreen::new);
		event.register(NexusModMenus.STOREUNUSED.get(), StoreunusedScreen::new);
		event.register(NexusModMenus.WDGUI.get(), WDGUIScreen::new);
	}
}

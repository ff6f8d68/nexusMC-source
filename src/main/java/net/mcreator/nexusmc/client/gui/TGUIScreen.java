package net.mcreator.nexusmc.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.nexusmc.world.inventory.TGUIMenu;
import net.mcreator.nexusmc.network.TGUIButtonMessage;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class TGUIScreen extends AbstractContainerScreen<TGUIMenu> {
	private final static HashMap<String, Object> guistate = TGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private final static HashMap<String, String> textstate = new HashMap<>();
	public static EditBox command;
	Button button_run;

	public TGUIScreen(TGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 167;
	}

	private static final ResourceLocation texture = new ResourceLocation("nexus:textures/screens/tgui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		command.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("nexus:textures/screens/rgt.png"), this.leftPos + 0, this.topPos + 1, 0, 0, 176, 166, 176, 166);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (command.isFocused())
			return command.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_run"), 6, 146, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_old1"), 6, 133, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_old2"), 6, 115, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_old3"), 6, 97, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_old4"), 6, 79, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_old5"), 6, 61, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_old6"), 6, 43, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_old7"), 6, 25, -16711936, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.nexus.tgui.label_old8"), 6, 7, -16711936, false);
	}

	@Override
	public void init() {
		super.init();
		command = new EditBox(this.font, this.leftPos + 43, this.topPos + 143, 118, 18, Component.translatable("gui.nexus.tgui.command"));
		command.setMaxLength(32767);
		guistate.put("text:command", command);
		this.addWidget(this.command);
		button_run = Button.builder(Component.translatable("gui.nexus.tgui.button_run"), e -> {
			if (true) {
				textstate.put("textin:command", command.getValue());
				PacketDistributor.SERVER.noArg().send(new TGUIButtonMessage(0, x, y, z, textstate));
				TGUIButtonMessage.handleButtonAction(entity, 0, x, y, z, textstate);
			}
		}).bounds(this.leftPos + 164, this.topPos + 142, 40, 20).build();
		guistate.put("button:button_run", button_run);
		this.addRenderableWidget(button_run);
	}
}

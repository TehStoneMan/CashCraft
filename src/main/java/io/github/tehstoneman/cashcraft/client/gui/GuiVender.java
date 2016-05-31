package io.github.tehstoneman.cashcraft.client.gui;

import java.util.logging.Logger;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.inventory.ContainerVender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiVender extends GuiContainer
{
	private static final ResourceLocation VENDER_GUI_TEXTURE = new ResourceLocation( ModInfo.MODID, "textures/gui/vender.png" );

	public GuiVender( IInventory inventoryVender )
	{
		super( new ContainerVender( inventoryVender, Minecraft.getMinecraft().thePlayer ) );
		// Set the width and height of the gui. Should match the size of the texture!
		xSize = 176;
		ySize = 222;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY )
	{
		GlStateManager.color( 1.0F, 1.0F, 1.0F, 1.0F );
		mc.getTextureManager().bindTexture( VENDER_GUI_TEXTURE );

		this.drawTexturedModalRect( guiLeft, guiTop, 0, 0, xSize, ySize );

		// TODO Auto-generated method stub
	}

}

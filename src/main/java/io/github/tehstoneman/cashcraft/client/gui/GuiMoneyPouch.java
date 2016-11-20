package io.github.tehstoneman.cashcraft.client.gui;

import java.util.logging.Logger;

import org.lwjgl.opengl.GL11;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.inventory.ContainerMoneyPouch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiMoneyPouch extends GuiContainer
{
	private static final ResourceLocation	GUI_MONEYPOUCH	= new ResourceLocation( ModInfo.MODID, "textures/gui/moneypouch.png" );

	IInventory								inventoryMoneyPouch;
	IInventory								inventoryPlayer;

	public GuiMoneyPouch( IInventory inventoryPlayer, IInventory inventoryMoneyPouch )
	{
		super( new ContainerMoneyPouch( inventoryPlayer, inventoryMoneyPouch, Minecraft.getMinecraft().thePlayer ) );
		this.inventoryMoneyPouch = inventoryMoneyPouch;
		this.inventoryPlayer = inventoryPlayer;
		xSize = 176;
		ySize = 169;
	}

	@Override
	protected void drawGuiContainerForegroundLayer( int mouseX, int mouseY )
	{
		fontRendererObj.drawString( inventoryMoneyPouch.getDisplayName().getUnformattedText(), 8, 6, 4210752 );
		fontRendererObj.drawString( inventoryPlayer.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752 );
	}

	@Override
	protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY )
	{
		GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
		mc.getTextureManager().bindTexture( GUI_MONEYPOUCH );
		this.drawTexturedModalRect( guiLeft, guiTop, 0, 0, xSize, ySize );
	}
}

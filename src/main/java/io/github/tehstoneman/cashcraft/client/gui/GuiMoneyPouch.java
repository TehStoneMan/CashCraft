package io.github.tehstoneman.cashcraft.client.gui;

import java.util.logging.Logger;

import org.lwjgl.opengl.GL11;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.inventory.ContainerMoneyPouch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

class GuiMoneyPouch extends GuiContainer
{
	private static final ResourceLocation	GUI_MONEYPOUCH	= new ResourceLocation( ModInfo.MODID, "textures/gui/moneypouch.png" );

	public ContainerMoneyPouch container;

	GuiMoneyPouch( ContainerMoneyPouch container )
	{
		super( container );
		this.container = container;
		xSize = 176;
		ySize = 169;
	}

	@Override
	protected void drawGuiContainerForegroundLayer( int mouseX, int mouseY )
	{
		fontRenderer.drawString( CashCraft.proxy.localize( "container.cashcraft.money_pouch" ), 8, 6, 0x404040 );
		fontRenderer.drawString( CashCraft.proxy.localize( "container.inventory" ), 8, ySize - 96 + 2, 0x404040 );
	}

	@Override
	protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY )
	{
		GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
		mc.getTextureManager().bindTexture( GUI_MONEYPOUCH );
		this.drawTexturedModalRect( guiLeft, guiTop, 0, 0, xSize, ySize );
	}
}

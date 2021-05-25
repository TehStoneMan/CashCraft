package io.github.tehstoneman.cashcraft.client.gui;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.matrix.MatrixStack;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.inventory.ContainerMoneyPouch;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiMoneyPouch extends ContainerScreen< ContainerMoneyPouch >
{
	private static final ResourceLocation	GUI_MONEYPOUCH	= new ResourceLocation( ModInfo.MOD_ID, "textures/gui/moneypouch.png" );

	//public ContainerMoneyPouch				container;

	public GuiMoneyPouch( ContainerMoneyPouch container, PlayerInventory playerInventory, ITextComponent title )
	{
		super( container, playerInventory, title );
		xSize = 176;
		ySize = 169;
	}

	@Override
	public void render(MatrixStack matrixStack,  int mouseX, int mouseY, float partialTicks )
	{
		renderBackground( matrixStack );
		super.render( matrixStack,mouseX, mouseY, partialTicks );
		renderHoveredTooltip(matrixStack, mouseX, mouseY );
	}

	@Override
	protected void drawGuiContainerBackgroundLayer( MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY )
	{
		GL11.glColor4f( 1.0F, 1.0F, 1.0F, 1.0F );
		minecraft.getTextureManager().bindTexture( GUI_MONEYPOUCH );
		this.blit(matrixStack, guiLeft, guiTop, 0, 0, xSize, ySize );
	}
}

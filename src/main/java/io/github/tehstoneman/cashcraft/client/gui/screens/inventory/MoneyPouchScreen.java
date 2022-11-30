package io.github.tehstoneman.cashcraft.client.gui.screens.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.world.inventory.MoneyPouchContainerMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MoneyPouchScreen extends AbstractContainerScreen< MoneyPouchContainerMenu >
{
	private static final ResourceLocation GUI_MONEYPOUCH = new ResourceLocation( ModInfo.MOD_ID, "textures/gui/moneypouch.png" );

	// public ContainerMoneyPouch container;

	public MoneyPouchScreen( MoneyPouchContainerMenu container, Inventory playerInventory, Component title )
	{
		super( container, playerInventory, title );
		imageWidth	= 176;
		imageHeight	= 169;
	}

	@Override
	public void render( PoseStack matrixStack, int mouseX, int mouseY, float partialTicks )
	{
		renderBackground( matrixStack );
		super.render( matrixStack, mouseX, mouseY, partialTicks );
		renderTooltip( matrixStack, mouseX, mouseY );
	}

	@Override
	protected void renderBg( PoseStack matrixStack, float partialTicks, int mouseX, int mouseY )
	{
		RenderSystem.setShader( GameRenderer::getPositionTexShader );
		RenderSystem.setShaderColor( 1.0F, 1.0F, 1.0F, 1.0F );
		RenderSystem.setShaderTexture( 0, GUI_MONEYPOUCH );

		this.blit( matrixStack, getGuiLeft(), getGuiTop(), 0, 0, getXSize(), getYSize() );
	}
}

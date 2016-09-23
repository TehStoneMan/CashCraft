package io.github.tehstoneman.cashcraft.client.gui;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.api.CashCraftAPI;
import io.github.tehstoneman.cashcraft.api.PriceList;
import io.github.tehstoneman.cashcraft.common.inventory.ContainerVender;
import io.github.tehstoneman.cashcraft.common.tileentity.TileEntityVender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiVender extends GuiContainer
{
	private static final ResourceLocation	VENDER_STOCK_GUI_TEXTURE	= new ResourceLocation( ModInfo.MODID, "textures/gui/vender_stock.png" );
	// private static final ResourceLocation VENDER_CASH_GUI_TEXTURE = new ResourceLocation( ModInfo.MODID, "textures/gui/vender_cash.png" );
	// private static final ResourceLocation VENDER_PRICE_GUI_TEXTURE = new ResourceLocation( ModInfo.MODID, "textures/gui/vender_pricing.png" );
	// private static final ResourceLocation VENDER_CUSTOMER_GUI_TEXTURE = new ResourceLocation( ModInfo.MODID, "textures/gui/vender_customer.png" );
	private final IInventory				inventoryVender, inventoryPlayer;
	public static final InventoryBasic		inventoryBasic				= new InventoryBasic( "priceList", true, 4 );
	public List< ItemStack >				itemList					= Lists.<ItemStack> newArrayList();

	private GuiButton						withdrawBtn;

	public GuiVender( InventoryPlayer inventoryPlayer, IInventory inventoryVender )
	{
		super( new ContainerVender( inventoryPlayer, inventoryVender, Minecraft.getMinecraft().thePlayer ) );

		this.inventoryVender = inventoryVender;
		this.inventoryPlayer = inventoryPlayer;

		updatePriceList();
		scrollTo( 0 );

		// Set the width and height of the gui. Should match the size of the texture!
		xSize = 176;
		ySize = 222;
	}

	private int getBalance( IInventory inventory )
	{
		if( inventory instanceof TileEntityVender )
			return ( (TileEntityVender)inventory ).getBalance();
		return 0;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY )
	{
		GlStateManager.color( 1.0F, 1.0F, 1.0F, 1.0F );
		RenderHelper.enableGUIStandardItemLighting();
		// Draw inventory background
		mc.getTextureManager().bindTexture( VENDER_STOCK_GUI_TEXTURE );

		this.drawTexturedModalRect( guiLeft, guiTop, 0, 0, xSize, ySize );
	}

	@Override
	protected void drawGuiContainerForegroundLayer( int mouseX, int mouseY )
	{
		fontRendererObj.drawString( inventoryVender.getDisplayName().getUnformattedText(), 8, 6, Color.darkGray.getRGB() );
		fontRendererObj.drawString( I18n.format( "economy.cashCraft.balance", new Object[0] ), 8, 96, Color.darkGray.getRGB() );
		final String balance = CashCraftAPI.economy.toString( getBalance( inventoryVender ), false );
		fontRendererObj.drawString( balance, 79 - fontRendererObj.getStringWidth( balance ), 110, Color.darkGray.getRGB() );
		fontRendererObj.drawString( inventoryPlayer.getDisplayName().getUnformattedText(), 8, 128, Color.darkGray.getRGB() );
	}

	@Override
	public void initGui()
	{
		super.initGui();

		// Define gui buttons
		int buttons = 0;
		withdrawBtn = new GuiButton( buttons++, guiLeft + 85, guiTop + 104, 60, 20, I18n.format( "economy.cashCraft.withdraw", new Object[0] ) );
		withdrawBtn.enabled = getBalance( inventoryVender ) > 0;

		// Add buttons to gui
		buttonList.clear();
		buttonList.add( withdrawBtn );
	}

	@Override
	protected void handleMouseClick( Slot slotIn, int slotId, int mouseButton, ClickType type )
	{
		if( slotIn.inventory == inventoryBasic )
			return;
		super.handleMouseClick( slotIn, slotId, mouseButton, type );
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	/*
	 * @Override
	 * public void drawScreen( int mouseX, int mouseY, float partialTicks )
	 * {
	 * final boolean flag = Mouse.isButtonDown( 0 );
	 * 
	 * super.drawScreen( mouseX, mouseY, partialTicks );
	 * 
	 * boolean rendered = false;
	 * for( final GuiTabs guitabs : GuiTabs.GUI_TAB_ARRAY )
	 * {
	 * if( guitabs == null )
	 * continue;
	 * if( renderGuiTabHoveringText( guitabs, mouseX, mouseY ) )
	 * {
	 * rendered = true;
	 * break;
	 * }
	 * }
	 * 
	 * GlStateManager.color( 1.0F, 1.0F, 1.0F, 1.0F );
	 * GlStateManager.disableLighting();
	 * }
	 */

	/*
	 * @Override
	 * protected void mouseClicked( int mouseX, int mouseY, int mouseButton ) throws IOException
	 * {
	 * if( mouseButton == 0 )
	 * for( final GuiTabs guitab : GuiTabs.GUI_TAB_ARRAY )
	 * if( isMouseOverTab( guitab, mouseX, mouseY ) )
	 * return;
	 * 
	 * super.mouseClicked( mouseX, mouseY, mouseButton );
	 * }
	 */

	/**
	 * Called when a mouse button is released.
	 */
	/*
	 * @Override
	 * protected void mouseReleased( int mouseX, int mouseY, int mouseButton )
	 * {
	 * if( mouseButton == 0 )
	 * for( final GuiTabs guitab : GuiTabs.GUI_TAB_ARRAY )
	 * if( isMouseOverTab( guitab, mouseX, mouseY ) )
	 * {
	 * setCurrentGuiTab( guitab );
	 * return;
	 * }
	 * 
	 * super.mouseReleased( mouseX, mouseY, mouseButton );
	 * }
	 */

	/*
	 * ================
	 * --- GUI Tabs ---
	 * ================
	 */

	/**
	 * Checks if the mouse is over the given tab. Returns true if so.
	 */
	/*
	 * protected boolean isMouseOverTab( GuiTabs tab, int mouseX, int mouseY )
	 * {
	 * final boolean isActiveTab = tab.getTabIndex() == selectedTabIndex;
	 * final boolean isFirstColumn = tab.isTabInFirstColumn();
	 * final int tabRow = tab.getTabRow();
	 * final int rectX = isFirstColumn ? -28 : xSize - 2;
	 * final int rectY = tab.getTabRow() * 29 + 3;
	 * 
	 * return isPointInRegion( rectX + 3, rectY + 3, 24, 23, mouseX, mouseY );
	 * }
	 */

	/**
	 * Renders the GUI tab hovering text if mouse is over it. Returns true if did render or false otherwise.
	 * Params: current creative tab to be checked, current mouse x position, current mouse y position.
	 */
	/*
	 * protected boolean renderGuiTabHoveringText( GuiTabs tab, int mouseX, int mouseY )
	 * {
	 * if( isMouseOverTab( tab, mouseX, mouseY ) )
	 * {
	 * drawCreativeTabHoveringText( I18n.format( tab.getTranslatedTabLabel(), new Object[0] ), mouseX, mouseY );
	 * return true;
	 * }
	 * else
	 * return false;
	 * }
	 */

	/**
	 * Draws the given tab and its background, deciding whether to highlight the tab or not based off of the selected
	 * index.
	 */
	/*
	 * protected void drawTab( GuiTabs tab )
	 * {
	 * final boolean isActiveTab = tab.getTabIndex() == selectedTabIndex;
	 * final boolean isFirstColumn = tab.isTabInFirstColumn();
	 * int x = isFirstColumn ? -28 : xSize - 2;
	 * int y = tab.getTabRow() * 29 + 3;
	 * final int u = isFirstColumn ? 192 : 224;
	 * final int v = isActiveTab ? 43 : 127;
	 * 
	 * GlStateManager.disableLighting();
	 * GlStateManager.color( 1F, 1F, 1F ); // Forge: Reset color in case Items change it.
	 * GlStateManager.enableBlend(); // Forge: Make sure blend is enabled else tabs show a white border.
	 * mc.getTextureManager().bindTexture( VENDER_STOCK_GUI_TEXTURE );
	 * this.drawTexturedModalRect( guiLeft + x, guiTop + y, u, v, 32, 28 );
	 * 
	 * // Draw tab icons
	 * zLevel = 100.0F;
	 * itemRender.zLevel = 100.0F;
	 * x += isFirstColumn ? 9 : 7;
	 * y += 6;
	 * GlStateManager.enableLighting();
	 * GlStateManager.enableRescaleNormal();
	 * final ItemStack itemstack = tab.getIconItemStack();
	 * itemRender.renderItemAndEffectIntoGUI( itemstack, guiLeft + x, guiTop + y );
	 * itemRender.renderItemOverlays( fontRendererObj, itemstack, guiLeft + x, guiTop + y );
	 * GlStateManager.disableLighting();
	 * itemRender.zLevel = 0.0F;
	 * zLevel = 0.0F;
	 * }
	 */

	/**
	 * Sets the current GUI tab, restructuring the GUI as needed.
	 */
	/*
	 * private void setCurrentGuiTab( GuiTabs tab )
	 * {
	 * if( tab == null )
	 * return;
	 * final int i = selectedTabIndex;
	 * selectedTabIndex = tab.getTabIndex();
	 * 
	 * if (tab == GuiTabs.VENDER_STOCK) { ((ContainerVender)inventorySlots).openStockTab(); }
	 * if (tab == GuiTabs.VENDER_PRICES) { ((ContainerVender)inventorySlots).openPriceTab( 0 ); }
	 * }
	 */

	/**
	 * Updates the displayed price list.
	 */
	public void updatePriceList()
	{
		final PriceList priceList = ( (TileEntityVender)inventoryVender ).priceList;
		itemList.clear();
		for( int i = 0; i < inventoryVender.getSizeInventory(); i++ )
		{
			final ItemStack itemStack = inventoryVender.getStackInSlot( i );
			if( itemStack != null && !priceList.isItemListed( itemStack ) )
				itemList.add( itemStack );
		}
		if( priceList != null && priceList.getSize() > 0 )
		{
			final Set< ItemStack > sortedList = priceList.getSortedList().keySet();
			for( final ItemStack itemStack : sortedList )
				itemList.add( itemStack );
		}
	}

	/**
	 * Updates the gui slots ItemStack's based on scroll position.
	 */
	public void scrollTo( float scroll )
	{
		final int maxIndex = Math.max( itemList.size() - 5, 0 );
		final int index = (int)( scroll * maxIndex + 0.5D );

		for( int i = 0; i < 4; ++i )
		{
			final int y = index + i;

			if( y >= 0 && y < itemList.size() )
				inventoryBasic.setInventorySlotContents( i, itemList.get( y ) );
			else
				inventoryBasic.setInventorySlotContents( i, (ItemStack)null );
		}
	}
}

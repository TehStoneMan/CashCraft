package io.github.tehstoneman.cashcraft.client.gui;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class GuiTabs
{
	public static GuiTabs[]		GUI_TAB_ARRAY	= new GuiTabs[3];
	public static final GuiTabs	VENDER_STOCK	= new GuiTabs( 0, "venderStock" )
												{
													@Override
													@SideOnly( Side.CLIENT )
													public Item getTabIconItem()
													{
														return Item.getItemFromBlock( Blocks.CHEST );
													}
												}.setBackgroundImageName( "vender_stock.png" );
	public static final GuiTabs	VENDER_PRICES	= new GuiTabs( 1, "venderPrices" )
												{
													@Override
													@SideOnly( Side.CLIENT )
													public Item getTabIconItem()
													{
														return CashCraftItems.itemCoin;
													}
												}.setBackgroundImageName( "vender_pricing.png" );
	public static final GuiTabs	VENDER_CUSTOMER	= new GuiTabs( 2, "venderCustomer" )
												{
													@Override
													@SideOnly( Side.CLIENT )
													public Item getTabIconItem()
													{
														return Items.EMERALD;
													}
												};
	private final int			tabIndex;
	private final String		tabLabel;
	/** Texture to use. */
	private String				theTexture		= "vender_customer.png";
	private boolean				hasScrollbar	= true;
	/** Whether to draw the title in the foreground of the creative GUI */
	private boolean				drawTitle		= true;
	@SideOnly( Side.CLIENT )
	private ItemStack			iconItemStack;

	public GuiTabs( String label )
	{
		this( getNextID(), label );
	}

	public GuiTabs( int index, String label )
	{
		if( index >= GUI_TAB_ARRAY.length )
		{
			final GuiTabs[] tmp = new GuiTabs[index + 1];
			for( int x = 0; x < GUI_TAB_ARRAY.length; x++ )
				tmp[x] = GUI_TAB_ARRAY[x];
			GUI_TAB_ARRAY = tmp;
		}
		tabIndex = index;
		tabLabel = label;
		GUI_TAB_ARRAY[index] = this;
	}

	@SideOnly( Side.CLIENT )
	public int getTabIndex()
	{
		return tabIndex;
	}

	public GuiTabs setBackgroundImageName( String texture )
	{
		theTexture = texture;
		return this;
	}

	@SideOnly( Side.CLIENT )
	public String getTabLabel()
	{
		return tabLabel;
	}

	/**
	 * Gets the translated Label.
	 */
	@SideOnly( Side.CLIENT )
	public String getTranslatedTabLabel()
	{
		return "guiGroup." + ModInfo.MODID + "." + getTabLabel();
	}

	@SideOnly( Side.CLIENT )
	public ItemStack getIconItemStack()
	{
		if( iconItemStack == null )
			iconItemStack = new ItemStack( getTabIconItem(), 1, getIconItemDamage() );

		return iconItemStack;
	}

	@SideOnly( Side.CLIENT )
	public abstract Item getTabIconItem();

	@SideOnly( Side.CLIENT )
	public int getIconItemDamage()
	{
		return 0;
	}

	@SideOnly( Side.CLIENT )
	public String getBackgroundImageName()
	{
		return theTexture;
	}

	@SideOnly( Side.CLIENT )
	public boolean drawInForegroundOfTab()
	{
		return drawTitle;
	}

	public GuiTabs setNoTitle()
	{
		drawTitle = false;
		return this;
	}

	@SideOnly( Side.CLIENT )
	public boolean shouldHidePlayerInventory()
	{
		return hasScrollbar;
	}

	public GuiTabs setNoScrollbar()
	{
		hasScrollbar = false;
		return this;
	}

	/**
	 * returns index % 6
	 */
	@SideOnly( Side.CLIENT )
	public int getTabRow()
	{
		return tabIndex % 12;
	}

	/**
	 * returns tabIndex < 6
	 */
	@SideOnly( Side.CLIENT )
	public boolean isTabInFirstColumn()
	{
		return tabIndex / 12 == 0;
	}

	public int getTabPage()
	{
		if( tabIndex > 11 )
			return ( tabIndex - 12 ) / 10 + 1;
		return 0;
	}

	public static int getNextID()
	{
		return GUI_TAB_ARRAY.length;
	}
}
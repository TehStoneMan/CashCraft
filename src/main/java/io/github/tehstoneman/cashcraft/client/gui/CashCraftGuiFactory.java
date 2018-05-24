package io.github.tehstoneman.cashcraft.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class CashCraftGuiFactory implements IModGuiFactory
{
	@Override
	public void initialize( Minecraft minecraftInstance )
	{}

	@Override
	public boolean hasConfigGui()
	{
		return true;
	}

	@Override
	public GuiScreen createConfigGui( GuiScreen parentScreen )
	{
		return new CashCraftConfigGui( parentScreen );
	}

	@Override
	public Set< RuntimeOptionCategoryElement > runtimeGuiCategories()
	{
		return null;
	}

	private static class CashCraftConfigGui extends GuiConfig
	{
		private CashCraftConfigGui( GuiScreen parentScreen )
		{
			super( parentScreen, getConfigElements(), ModInfo.MODID, false, false, CashCraft.proxy.localize( "gui.cashcraft.config.title" ) );
		}

		private static List< IConfigElement > getConfigElements()
		{
			final List< IConfigElement > listConfigElements = new ArrayList<>();

			final Configuration config = CashCraft.config.getConfig();
			final ConfigElement generalConfig = new ConfigElement( config.getCategory( Configuration.CATEGORY_GENERAL ) );
			final ConfigElement displayConfig = new ConfigElement( config.getCategory( CashCraftConfig.CATEGORY_DISPLAY ) );
			final ConfigElement economyConfig = new ConfigElement( config.getCategory( CashCraftConfig.CATEGORY_ECONOMY ) );

			listConfigElements.addAll( generalConfig.getChildElements() );
			listConfigElements.add( new DummyCategoryElement( "display", "gui.cashcraft.config.display", displayConfig.getChildElements() ) );
			listConfigElements.add( new DummyCategoryElement( "economy", "gui.cashcraft.config.economy", economyConfig.getChildElements() ) );

			return listConfigElements;
		}
	}
}

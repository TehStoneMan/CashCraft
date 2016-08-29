package io.github.tehstoneman.cashcraft.proxies;

import io.github.tehstoneman.cashcraft.ModInfo;
import io.github.tehstoneman.cashcraft.client.creativetab.CashCraftTab;
import io.github.tehstoneman.cashcraft.common.block.CashCraftBlocks;
import io.github.tehstoneman.cashcraft.common.item.CashCraftItems;
import io.github.tehstoneman.cashcraft.common.tileentity.TileEntityVender;
import io.github.tehstoneman.cashcraft.network.UpdateMessage;
import io.github.tehstoneman.cashcraft.util.ModSettings;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy
{
	CashCraftTab	cashCraftTab;
	
	// Networking
	public static SimpleNetworkWrapper simpleNetworkWrapper;
	
	public static final byte MESSAGE_ID_UPDATE = 1;

	public void preInit()
	{
		cashCraftTab = new CashCraftTab( ModInfo.MODID );

		CashCraftItems.RegisterItems();
		CashCraftBlocks.RegisterBlocks();
		GameRegistry.registerTileEntity( TileEntityVender.class, "tileentity_vender" );
		
		simpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel( ModInfo.MODID );
		simpleNetworkWrapper.registerMessage( UpdateMessage.Handler.class, UpdateMessage.class, MESSAGE_ID_UPDATE, Side.SERVER );
		
		//NetworkRegistry.INSTANCE.registerGuiHandler( CashCraft.instance, new GuiHandler() );
	}

	public void Init()
	{
		if( ModSettings.makeChange )
			CashCraftItems.RegisterRecipes();

		CashCraftBlocks.RegisterRecipes();
	}

	public void postInit()
	{
		// TODO Auto-generated method stub

	}

}

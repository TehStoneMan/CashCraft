package io.github.tehstoneman.cashcraft.economy;

import io.github.tehstoneman.cashcraft.CashCraft;
import io.github.tehstoneman.cashcraft.api.ITrade;
import io.github.tehstoneman.cashcraft.config.CashCraftConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Trade implements ITrade
{
	@Override
	public Boolean isEnabled()
	{
		return CashCraftConfig.useEconomy && CashCraftConfig.useTrade;
	}

	@Override
	public void openTradeGui( EntityPlayer playerIn, EnumTradeType tradeType, World worldIn, BlockPos pos )
	{
		playerIn.openGui( CashCraft.instance, tradeType.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ() );
	}
}

package io.github.tehstoneman.cashcraft.api;

import io.github.tehstoneman.cashcraft.economy.PlayerWallet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Class that contains the basic Cash API
 * Modeled after Forge Essentials Economy API
 *
 * @author TehStoneMan
 *
 */
public interface IEcomomy
{
	/**
	 * Get the wallet for a player
	 *
	 * @param player
	 * @return
	 */
	public PlayerWallet getWallet( EntityPlayer player );

	/**
	 * Get the singular or plural term of the currency used
	 *
	 * @param amount
	 * @return
	 */
	public String currency( long amount );

	/**
	 * Returns the highest stack of cash for the given amount
	 *
	 * @param amount
	 * @return
	 */
	public ItemStack getCash( long amount );

	/**
	 * Returns the value of the given stack
	 *
	 * @param itemStack
	 * @return
	 */
	public long getValue( ItemStack itemStack );

	/**
	 * Turns the amount into a string with the currency attached
	 *
	 * @param amount
	 * @return
	 */
	public String toString( long amount );
}

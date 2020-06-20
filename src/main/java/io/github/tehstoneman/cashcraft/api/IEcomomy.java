package io.github.tehstoneman.cashcraft.api;

import net.minecraft.entity.player.PlayerEntity;
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
	 * Check if the economy module is enabled
	 * WARNING:
	 * API calls are not guaranteed to be safe if this is ignored.
	 *
	 * @return TRUE if enabled
	 */
	public Boolean isEnabled();

	/**
	 * Get the wallet for a player
	 *
	 * @param player
	 *            The player to get the wallet of
	 * @return The wallet of the requested player
	 */
	public IPlayerWallet getWallet( PlayerEntity player );

	/**
	 * Get the singular or plural term of the currency used
	 *
	 * @param plural
	 *            TRUE if plural
	 * @param longFormat
	 *            TRUE if long format
	 * @return Untranslated name of currency
	 */
	public String getCurrencyName( boolean plural, boolean longFormat );

	/**
	 * Returns the highest stack of cash for the given amount
	 *
	 * @param amount
	 *            Requested amount
	 * @return ItemStack containing the highest value of cash that fits in the amount
	 */
	public ItemStack getCash( long amount );

	/**
	 * Returns the value of the given stack
	 *
	 * @param itemStack
	 *            The ItemStack to get the value of
	 * @return The value of the given ItemStack
	 */
	public long getValue( ItemStack itemStack );

	/**
	 * Display amount as a string with the long form currency attached
	 *
	 * @param amount
	 *            The amount to display
	 * @return Amount formatted as string
	 */
	public String toString( long amount );

	/**
	 * Display amount as a string with choice of long or short form currency attached
	 *
	 * @param amount
	 *            The amount to display
	 * @param longFormat
	 *            TRUE is long format
	 * @return Amount formatted as string
	 */
	public String toString( long amount, boolean longFormat );
}

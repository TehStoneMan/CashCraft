package io.github.tehstoneman.cashcraft.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModSettings
{
	public static boolean	makeChange;

	public static void init( File configFile )
	{
		final Configuration config = new Configuration( configFile );

		config.load();

		makeChange = config.get( Configuration.CATEGORY_GENERAL, "makeChange", true,
				"Allow coins/notes to be crafted from higher or lower value coins/notes." ).getBoolean();

		config.save();
	}
}

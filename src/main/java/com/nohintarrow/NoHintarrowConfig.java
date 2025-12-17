package com.nohintarrow;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import java.awt.Color;

@ConfigGroup("example")
public interface NoHintarrowConfig extends Config
{

	@ConfigItem(
			keyName = "clearDelaySeconds",
			name = "Clear Delay (seconds)",
			description = "How many seconds before the hint arrow is cleared automatically"
	)
	default int clearDelaySeconds()
	{
		return 0; // default 0 seconds
	}

	@ConfigItem(
			keyName = "gameMessageColor",
			name = "Game Message Color",
			description = "Choose the color for game info messages"
	)
	default Color gameMessageColor()
	{
		return Color.GREEN;
	}

}

package com.nohintarrow;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import java.awt.Color;

@ConfigGroup("noHintArrow")
public interface NoHintArrowConfig extends Config
{
	//boolean ENABLE_DEBUG_MODE = false; // use false in production, set to true when debugging to access debug tools



	@ConfigItem(
			keyName = "clearDelaySeconds",
			name = "Clear Delay (seconds)",
			description = "How many seconds before the hint arrow is cleared automatically",
			position=0
	)
	default int clearDelaySeconds()
	{
		return 0; // default 0 seconds
	}



	//region Substitute Tile Marker Settings
	@ConfigSection(
			name = "Substitute Tile Marker Settings",
			description = "Substitute Tile Marker configuration",
			position = 2,
			closedByDefault = true
	)
	String substituteTileMarkerSection = "substituteTileMarkerSection";


	@ConfigItem(
			keyName = "doSubstituteTileMarker",
			name = "Use a substitute tile marker",
			description = "Show a substitute tile marker for the removed hint arrow",
			section = substituteTileMarkerSection,
			position = 0
	)
	default boolean doSubstituteTileMarker()
	{
		return true; // default will use a substitute tile marker
	}


	@ConfigItem(
			keyName = "substituteTileMarkerDurationSeconds",
			name = "Duration (seconds)",
			description = "How many seconds before the substitute tile marker is cleared",
			section = substituteTileMarkerSection,
			position = 1
	)
	default int substituteTileMarkerDurationSeconds()
	{
		return 60; // default 1 minute
	}


	@ConfigItem(
			keyName = "substituteTileMarkerColor",
			name = "Color",
			description = "Choose the color for the substitute tile marker",
			section = substituteTileMarkerSection,
			position = 2
	)
	default Color substituteTileMarkerColor() {
		return Color.YELLOW; // default yellow
	}


	@ConfigItem(
			keyName = "showSubstituteTileMarkerLabel",
			name = "Show Label",
			description = "Show a text label (\"Hint\") on the substitute tile marker",
			section = substituteTileMarkerSection,
			position = 3
	)
	default boolean showSubstituteTileMarkerLabel()
	{
		return true; // default will show label for substitute marker
	}
	//endregion



	//region Substitute Arrow Settings
	@ConfigSection(
			name = "Substitute Arrow Settings",
			description = "Substitute Arrow configuration",
			position = 3,
			closedByDefault = true
	)
	String substituteArrowSection = "substituteArrowSection";


	@ConfigItem(
			keyName = "doSubstituteArrow",
			name = "Use a substitute arrow",
			description = "Show a substitute arrow for the removed hint arrow",
			section = substituteArrowSection,
			position = 0
	)
	default boolean doSubstituteArrow()
	{
		return true; // default will use a substitute arrow
	}


	@ConfigItem(
			keyName = "substituteArrowDurationSeconds",
			name = "Duration (seconds)",
			description = "How many seconds before the substitute arrow is cleared",
			section = substituteArrowSection,
			position = 1
	)
	default int substituteArrowDurationSeconds()
	{
		return 60; // default 1 minute
	}


	@ConfigItem(
			keyName = "substituteArrowColor",
			name = "Color",
			description = "Choose the color for the substitute arrow",
			section = substituteArrowSection,
			position = 2
	)
	default Color substituteArrowColor() {
		return Color.ORANGE; // default yellow
	}


	@ConfigItem(
			keyName = "substituteArrowClearRadius",
			name = "Clear radius",
			description = "Radius of clear space around player before the arrow starts",
			section = substituteArrowSection,
			position = 3
	)
	default int substituteArrowClearRadius() {
		return 3; // default about 1 tile
	}
	//endregion



	//region debug
	@ConfigSection(
			name = "Debug Options",
			description = "Various options for debugging",
			position = 4,
			closedByDefault = true
	)
	String debugSection = "debugSection";


	@ConfigItem(
			keyName = "doDebugManualHints",
			name = "Manually add Hint-Arrows",
			description = "Enable shift click to manually set hint arrows",
			section = debugSection,
			position = 0
	)
	default boolean doDebugManualHints()
	{
		return false; // default won't show debug menu options
	}


	@ConfigItem(
			keyName = "doDebugMessages",
			name = "Debug Messages",
			description = "Show debug messages in chatbox",
			section = debugSection,
			position = 1
	)
	default DebugMessagesDetailLevel doDebugMessages()
	{
		return DebugMessagesDetailLevel.NONE; // default won't show debug messages
	}


	@ConfigItem(
			keyName = "debugMessageColor",
			name = "Debug Message Color",
			description = "Choose the color for debug text",
			section = debugSection,
			position = 2
	)
	default Color debugMessageColor()
	{
		return new Color(0x7F007F); // default same purple as incoming trade requests
	}
	//endregion
}

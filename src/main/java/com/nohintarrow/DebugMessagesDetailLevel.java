package com.nohintarrow;

/**
 * Levels for how much information to put in debug messages.
 */
public enum DebugMessagesDetailLevel {
    NONE("None"),
    MINIMAL("Minimnal"),
    INFORMATIVE("Informative");

    private final String caption;

    DebugMessagesDetailLevel(String caption)
    {
        this.caption = caption;
    }

    public String getCaption()
    {
        return caption;
    }
}

package wdl.api;

import net.minecraft.util.EnumChatFormatting;

public interface IWDLMessageType {
   EnumChatFormatting getTextColor();

   EnumChatFormatting getTitleColor();

   boolean isEnabledByDefault();

   String getDescription();

   String getDisplayName();
}

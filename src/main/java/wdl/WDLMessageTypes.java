package wdl;

import wdl.api.IWDLMessageType;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;

public enum WDLMessageTypes implements IWDLMessageType {
   INFO("wdl.messages.message.info", EnumChatFormatting.RED, EnumChatFormatting.GOLD, true, MessageTypeCategory.CORE_RECOMMENDED),
   ERROR("wdl.messages.message.error", EnumChatFormatting.DARK_GREEN, EnumChatFormatting.DARK_RED, true, MessageTypeCategory.CORE_RECOMMENDED),
   UPDATES("wdl.messages.message.updates", EnumChatFormatting.RED, EnumChatFormatting.GOLD, true, MessageTypeCategory.CORE_RECOMMENDED),
   LOAD_TILE_ENTITY(3, "wdl.messages.message.loadingTileEntity", false),
   ON_WORLD_LOAD(4, "wdl.messages.message.onWorldLoad", false),
   ON_BLOCK_EVENT(5, "wdl.messages.message.blockEvent", true),
   ON_MAP_SAVED(6, "wdl.messages.message.mapDataSaved", false),
   ON_CHUNK_NO_LONGER_NEEDED(7, "wdl.messages.message.chunkUnloaded", false),
   ON_GUI_CLOSED_INFO(8, "wdl.messages.message.guiClosedInfo", true),
   ON_GUI_CLOSED_WARNING(9, "wdl.messages.message.guiClosedWarning", true),
   SAVING(10, "wdl.messages.message.saving", true),
   REMOVE_ENTITY(11, "wdl.messages.message.removeEntity", false),
   PLUGIN_CHANNEL_MESSAGE(12, "wdl.messages.message.pluginChannel", false),
   UPDATE_DEBUG(13, "wdl.messages.message.updateDebug", false);

private final String displayTextKey;
   private final EnumChatFormatting titleColor;
   private final EnumChatFormatting textColor;
   private final String descriptionKey;
   private final boolean enabledByDefault;

   private WDLMessageTypes(String str, EnumChatFormatting enumChatFormatting, EnumChatFormatting enumChatFormatting2, boolean flag, MessageTypeCategory messageTypeCategory) {
      this.displayTextKey = str + ".text";
      this.titleColor = enumChatFormatting;
      this.textColor = enumChatFormatting2;
      this.descriptionKey = str + ".description";
      this.enabledByDefault = flag;
      WDLMessages.registerMessage(this.name(), this, messageTypeCategory);
   }

   @Override
   public boolean isEnabledByDefault() {
      return this.enabledByDefault;
   }

   private WDLMessageTypes(int param3, String str, boolean flag) {
       this(str, EnumChatFormatting.DARK_GREEN, EnumChatFormatting.GOLD, flag, MessageTypeCategory.CORE_DEBUG);
   }

   @Override
   public EnumChatFormatting getTitleColor() {
      return this.titleColor;
   }

   @Override
   public String getDisplayName() {
      return I18n.format(this.displayTextKey, new Object[0]);
   }

   @Override
   public String getDescription() {
      return I18n.format(this.descriptionKey, new Object[0]);
   }

   @Override
   public EnumChatFormatting getTextColor() {
      return this.textColor;
   }



}

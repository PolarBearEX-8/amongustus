package cc.baritone.utils;

import client.setting.ClientSettingsBlackType;
import net.minecraft.util.StatCollector;

public enum OptionType implements AltListener {
   INFO("wdl.messages.message.info", ClientSettingsBlackType.RED, ClientSettingsBlackType.GOLD, true, CORERECOMMENDEDManager.cORERECOMMENDEDManager2),
   ERROR("wdl.messages.message.error", ClientSettingsBlackType.DARK_GREEN, ClientSettingsBlackType.DARK_RED, true, CORERECOMMENDEDManager.cORERECOMMENDEDManager2),
   UPDATES("wdl.messages.message.updates", ClientSettingsBlackType.RED, ClientSettingsBlackType.GOLD, true, CORERECOMMENDEDManager.cORERECOMMENDEDManager2),
   LOAD_TILE_ENTITY("wdl.messages.message.loadingTileEntity", false),
   ON_WORLD_LOAD("wdl.messages.message.onWorldLoad", false),
   ON_BLOCK_EVENT("wdl.messages.message.blockEvent", true),
   ON_MAP_SAVED("wdl.messages.message.mapDataSaved", false),
   ON_CHUNK_NO_LONGER_NEEDED("wdl.messages.message.chunkUnloaded", false),
   ON_GUI_CLOSED_INFO("wdl.messages.message.guiClosedInfo", true),
   ON_GUI_CLOSED_WARNING("wdl.messages.message.guiClosedWarning", true),
   SAVING("wdl.messages.message.saving", true),
   REMOVE_ENTITY("wdl.messages.message.removeEntity", false),
   PLUGIN_CHANNEL_MESSAGE("wdl.messages.message.pluginChannel", false),
   UPDATE_DEBUG("wdl.messages.message.updateDebug", false);

   public final String labelText;
   public final ClientSettingsBlackType clientSettingsBlackType;
   public final ClientSettingsBlackType clientSettingsBlackType2;
   public final String labelText2;
   public final boolean enabled;

   @Override
   public String getTextAsText() {
      return StatCollector.getText(this.labelText2, new Object[0]);
   }

   @Override
   public String getText() {
      return StatCollector.getText(this.labelText, new Object[0]);
   }

   @Override
   public ClientSettingsBlackType getClientSettingsBlackType() {
      return this.clientSettingsBlackType;
   }

   @Override
   public ClientSettingsBlackType getClientSettingsBlackType2() {
      return this.clientSettingsBlackType2;
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   private OptionType(String text, boolean flag) {
      this(text, ClientSettingsBlackType.DARK_GREEN, ClientSettingsBlackType.GOLD, flag, CORERECOMMENDEDManager.cORERECOMMENDEDManager);
   }

   private OptionType(String text, ClientSettingsBlackType clientSettingsBlackType3, ClientSettingsBlackType clientSettingsBlackType4, boolean flag, CORERECOMMENDEDManager cORERECOMMENDEDManager) {
      this.labelText = text + ".text";
      this.clientSettingsBlackType = clientSettingsBlackType3;
      this.clientSettingsBlackType2 = clientSettingsBlackType4;
      this.labelText2 = text + ".description";
      this.enabled = flag;
      LoggerEntry.applyText(this.name(), this, cORERECOMMENDEDManager);
   }
}

package client.setting;

import client.util.BaseManagerStyleIChatComponentAdapter;
import net.minecraft.util.IChatComponent;

public enum ComplexOptionType {
   ENABLED("enabled"),
   DISABLED("disabled"),
   PROMPT("prompt");

   public final IChatComponent iChatComponent;
   public static final ComplexOptionType[] ENUM_VALUES = values();

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   private ComplexOptionType(String text) {
      this.iChatComponent = new BaseManagerStyleIChatComponentAdapter("addServer.resourcePack." + text, new Object[0]);
   }
}

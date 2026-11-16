package client.util;

import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.IChatComponent;

public class AuxiliaryIChatComponentEntry implements IMetadataSection {
   public final IChatComponent iChatComponent;
   public final int count;

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public int getCount() {
      return this.count;
   }

   public AuxiliaryIChatComponentEntry(IChatComponent iChatComponent2, int count2) {
      this.iChatComponent = iChatComponent2;
      this.count = count2;
   }
}

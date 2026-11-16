package client.util;

import net.minecraft.util.IChatComponent;

public class InternalIChatComponentEntry {
   public final int count;
   public final IChatComponent iChatComponent;
   public final int count2;

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   public InternalIChatComponentEntry(int count3, IChatComponent iChatComponent2, int count4) {
      this.iChatComponent = iChatComponent2;
      this.count = count3;
      this.count2 = count4;
   }
}

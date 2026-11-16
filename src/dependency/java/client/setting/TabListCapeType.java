package client.setting;

import client.util.BaseManagerStyleIChatComponentAdapter;
import net.minecraft.util.IChatComponent;

public enum TabListCapeType {
   CAPE(0, "cape"),
   JACKET(1, "jacket"),
   LEFT_SLEEVE(2, "left_sleeve"),
   RIGHT_SLEEVE(3, "right_sleeve"),
   LEFT_PANTS_LEG(4, "left_pants_leg"),
   RIGHT_PANTS_LEG(5, "right_pants_leg"),
   HAT(6, "hat");

   public final int count2;
   public final int count;
   public final String labelText;
   public final IChatComponent iChatComponent;

   public int getCount2() {
      return this.count2;
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   public int getCount() {
      return this.count;
   }

   public String getLabelText() {
      return this.labelText;
   }

   private TabListCapeType(int count3, String text) {
      this.count2 = count3;
      this.count = 1 << count3;
      this.labelText = text;
      this.iChatComponent = new BaseManagerStyleIChatComponentAdapter("options.modelPart." + text, new Object[0]);
   }
}

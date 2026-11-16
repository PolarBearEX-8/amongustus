package cc.baritone.utils;

import client.gui.TexturesGuiIconsPngManager;
import net.minecraft.client.Minecraft;

public class MinecraftPreUpdateDisp extends MinecraftPreUpdateDispatcher {
   public final String labelText2;
   public final int count2;
   public final int count3;

   @Override
   public boolean checkIndex(int count, int count4, int count5, int count6, int count7, int count8) {
      if (count7 >= 0 && count7 <= this.count2) {
         CouldnOpenHelper.applyText(this.labelText2);
         return true;
      } else {
         return false;
      }
   }

   public MinecraftPreUpdateDisp(Minecraft mc, String text, String text2) {
      super(mc, text, 5592575);
      this.labelText2 = text2;
      this.count2 = mc.fontRendererObj.getIndex(text);
      this.count3 = mc.fontRendererObj.getIndex(text2);
   }

   @Override
   public void applyIndex(int count, int count4, int count5, int count6, int count7, int count8, int count9, boolean flag) {
      if (count5 >= 0) {
         super.applyIndex(count, count4, count5, count6, count7, count8, count9, flag);
         int count10 = count8 - count4;
         int count11 = count9 - count5;
         if (count10 >= 0 && count10 <= this.count2 && count11 >= 0 && count11 <= count7) {
            int count12 = count8 - 2;
            if (count12 + this.count3 + 4 > count6 + count4) {
               count12 = count6 + count4 - (4 + this.count3);
            }

            TexturesGuiIconsPngManager.processAction(
               count12, count9 - 2, count12 + this.count3 + 4, count9 + this.mc.fontRendererObj.indexOffset + 2, Integer.MIN_VALUE
            );
            CouldnOpenHelper.applyTextByText(this.labelText2, count12 + 2, count9, 16777215);
         }
      }
   }
}

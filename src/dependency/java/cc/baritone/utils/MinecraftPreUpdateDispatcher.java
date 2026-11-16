package cc.baritone.utils;

import client.event.PreUpdateDispatcher;
import net.minecraft.client.Minecraft;

public class MinecraftPreUpdateDispatcher implements PreUpdateDispatcher {
   public final String labelText;
   public final int count;
   public final Minecraft mc;

   public MinecraftPreUpdateDispatcher(Minecraft mc2, String text, int count2) {
      this.mc = mc2;
      this.labelText = text;
      this.count = count2;
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7, int count8, boolean flag) {
      if (count4 >= 0) {
         CouldnOpenHelper.applyTextByText(this.labelText, count3, count4 + 1, this.count);
      }
   }

   @Override
   public void applyIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
   }

   @Override
   public void applyIndex(int count2, int count3, int count4) {
   }

   public MinecraftPreUpdateDispatcher(Minecraft mc2, String text) {
      this(mc2, text, 1048575);
   }

   @Override
   public boolean checkIndex(int count2, int count3, int count4, int count5, int count6, int count7) {
      return false;
   }
}

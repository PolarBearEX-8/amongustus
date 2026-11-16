package client.util;

import client.event.PreUpdateDispatcher;
import client.setting.ClientSettingsBlackType;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;

public abstract class ClientManagerTracker extends SplineCalculator {
   public final Minecraft mc2;
   public final List list;

   public List getList() {
      return this.list;
   }

   public RayTraceValidator resolveRayTraceValidator(int count) {
      return (RayTraceValidator)this.getList().get(count);
   }

   @Override
   public int getCount20() {
      return this.count9;
   }

   @Override
   public int getCount22() {
      return this.count11 - 6;
   }

   @Override
   public void applyTessellator(int count, int count2, Tessellator tessellator) {
      String text = ClientSettingsBlackType.UNDERLINE + "" + ClientSettingsBlackType.BOLD + this.getText();
      this.mc2
         .fontRendererObj
         .getIndex(text, count + this.count9 / 2 - this.mc2.fontRendererObj.getIndex(text) / 2, Math.min(this.count10 + 3, count2), 16777215);
   }

   @Override
   public PreUpdateDispatcher get(int count) {
      return this.resolveRayTraceValidator(count);
   }

   @Override
   public int size() {
      return this.getList().size();
   }

   public abstract String getText();

   public ClientManagerTracker(Minecraft mc, int count, int count2, List list2) {
      super(mc, count, count2, 32, count2 - 55 + 4, 36);
      this.mc2 = mc;
      this.list = list2;
      this.enabled5 = false;
      this.applyFlag(true, (int)((float)mc.fontRendererObj.indexOffset * 1.5F));
   }
}

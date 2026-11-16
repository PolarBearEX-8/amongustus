package client.gui;

import augustus.modules.render.Notifications;
import client.Client;
import client.render.RenderUtils;
import client.setting.BeverageNotifyInfoType;
import client.util.MinecraftAccessor;
import client.util.TimeHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ClientNotification implements MinecraftAccessor {
   public final BeverageNotifyInfoType beverageNotifyInfoType;
   public static final int count4 = 25;
   public final int count6;
   public final String labelText2;
   public static final int count3 = 3;
   public int count;
   public static final int count5 = 750;
   public TimeHelper timer;
   public final int count2;
   public final String labelText;
   public final TimeHelper timer2;

   public boolean isActive() {
      TimeHelper timer3 = this.timer2;
      long index = (long)this.getIndex();
      return timer3.hasReached(index);
   }

   private double getValue() {
      long time = this.timer2.getTime();
      double value = Client.getClient().getModuleManager().notifications.settingSpeed.getValue();
      double offset;
      if (time < 750L) {
         offset = Math.tanh((double)time / 750.0 * value) * (double)this.count2;
      } else if (time > (long)this.count6) {
         offset = Math.tanh(value - (double)(time - (long)this.count6) / 750.0 * value) * (double)this.count2;
      } else {
         offset = (double)this.count2;
      }

      return offset;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public void renderElement() {
      this.timer = new TimeHelper();
      this.count++;
   }

   public BeverageNotifyInfoType getBeverageNotifyInfoType() {
      return this.beverageNotifyInfoType;
   }

   public void applyIndex(int count7) {
      Notifications notifications = Client.getClient().getModuleManager().notifications;
      int intItem = notifications.settingPosition.getInt2();
      double value = this.getValue();
      float factor = (float)(3.0 + value - (double)this.count2);
      float factor2 = (float)(3 + count7 * 28);
      ScaledResolution scaledResolution = new ScaledResolution(minecraft);
      GL11.glPushMatrix();
      GL11.glTranslatef(
         intItem != 1 && intItem != 3 ? (float)(scaledResolution.getScaledWidth() - this.count2) - factor : factor,
         intItem < 2 ? factor2 : (float)(scaledResolution.getScaledHeight() - 25) - factor2,
         0.0F
      );
      RenderUtils.processAction(0, 0, this.count2, 25, Integer.MIN_VALUE);
      if (this.count <= 0) {
         String selected = notifications.settingProgressColorMode.getSelected();
         int index = notifications.getIndex(this, selected);
         RenderUtils.processDouble4(
            0.0, 23.0, (double)((float)this.count2 / 100.0F * (float)this.timer2.getTime() * (100.0F / (float)this.getIndex())), 25.0, index
         );
      }

      FontRenderer fontRendererObj = minecraft.fontRendererObj;
      String labelText3 = this.labelText;
      String selected2 = notifications.settingGroupColorMode.getSelected();
      fontRendererObj.getIndex2(labelText3, 2.0F, 2.0F, notifications.getIndex(this, selected2));
      minecraft.fontRendererObj.getIndex2(this.labelText2, 2.0F, (float)(4 + minecraft.fontRendererObj.indexOffset), -1);
      if (this.count > 0) {
         minecraft.fontRendererObj.getIndex2("x" + (this.count + 1), (float)(this.count2 - 2 - minecraft.fontRendererObj.getIndex("x" + (this.count + 1))), 2.0F, -1);
      }

      GL11.glPopMatrix();
   }

   public int getIndex() {
      return 750 + this.count6;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public ClientNotification(String text, String text2, BeverageNotifyInfoType beverageNotifyInfoType2) {
      this.labelText = text;
      this.labelText2 = text2;
      this.beverageNotifyInfoType = beverageNotifyInfoType2;
      int index = minecraft.fontRendererObj.getIndex(text);
      int index2 = minecraft.fontRendererObj.getIndex(text2);
      this.count2 = Math.max(index, index2) + 3;
      this.count6 = 750 + (int)(Client.getClient().getModuleManager().notifications.settingShowDuration.getValue() * 1000.0);
      this.timer2 = new TimeHelper();
   }
}

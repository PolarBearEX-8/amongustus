package client.util;

import client.Client;
import client.config.ClientNetworkConfig;
import client.render.RenderUtils;
import client.setting.EasingFunction;
import java.awt.Color;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

public class InterpolationEngine implements MinecraftAccessor {
   public int count;
   public final TimeHelper timer = new TimeHelper();
   public boolean enabled;
   public final KeybindAnimation keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
   public boolean enabled2;
   public int count2;
   public int count3;
   public final ClientNetworkConfig clientNetworkConfig;
   public int count4;

   public int getCount2() {
      return this.count2;
   }

   public int getCount() {
      return this.count;
   }

   public void applyIndex(int count5, int count6, int count7, int count8, int count9, int count10, boolean flag) {
      this.count3 = count7;
      this.count4 = count8;
      this.count2 = count9;
      this.count = count10;
      this.enabled = flag;
      FontRenderer fontRendererObj = MinecraftAccessor.minecraft.fontRendererObj;
      float factor = (float)count9 / 2.0F;
      float factor2 = (float)count10 / 2.0F;
      float factor3 = (float)count7 + factor;
      float factor4 = (float)count8 + factor2;
      GL11.glPushMatrix();
      byte byteValue = ((byte)((RenderUtils.checkCondition((float)count7, (float)count8, (float)count9, (float)count10, count5, count6)) ? 1 : 0));
      if (this.enabled2 != (byteValue != 0)) {
         this.keybindAnimation.updateEasingFunction(100, EasingFunction.easeInSine, byteValue, false);
      }

      StringBuilder builder;
      String text;
      searchBlock: {
         float factor5 = (float)(1.0 - this.keybindAnimation.getDouble3() * 0.02F);
         GL11.glScaled((double)factor5, (double)factor5, 0.0);
         float factor6 = factor3 / factor5;
         float factor7 = factor4 / factor5;
         this.enabled2 = ((byteValue) != 0);
         RenderUtils.processDouble4(
            (double)(factor6 - factor), (double)(factor7 - factor2), (double)(factor6 + factor), (double)(factor7 + factor2), new Color(0, 0, 0, flag ? 200 : 100).getRGB()
         );
         GL11.glScaled((double)(1.0F / factor5), (double)(1.0F / factor5), 0.0);
         ClientNetworkConfig clientNetworkConfig2 = Client.getClient().getClientNetworkConfig();
         builder = new StringBuilder().append("§7IP: ");
         if (clientNetworkConfig2 != null) {
            ClientNetworkConfig clientNetworkConfig3 = this.clientNetworkConfig;
            if (clientNetworkConfig2.matchesClientNetworkConfig(clientNetworkConfig3)) {
               text = "§a";
               break searchBlock;
            }
         }

         text = this.isActive() ? "§6" : "§f";
      }

      fontRendererObj.computeCountByText(
         builder.append(text).append(this.clientNetworkConfig.getString3()).toString(), (float)count7 + 5.5F, (float)count8 + 3.5F, Color.white.getRGB()
      );
      fontRendererObj.computeCountByText(
         "§7Port: " + (this.isActive() ? "§6" : "§f") + this.clientNetworkConfig.getInt(),
         (float)count7 + 5.5F,
         (float)count8 + 7.0F + (float)fontRendererObj.indexOffset,
         Color.white.getRGB()
      );
      String text2 = "§7Ping: " + (this.isActive() ? "§6" : "§f") + this.clientNetworkConfig.getCount();
      fontRendererObj.computeCountByText(text2, (float)count7 + ((float)(count9 - fontRendererObj.getIndex(text2)) - 5.5F), (float)count8 + 3.5F, Color.white.getRGB());
      GL11.glPopMatrix();
   }

   public boolean isActive() {
      return this.clientNetworkConfig.isActiveAsFlag();
   }

   public InterpolationEngine(ClientNetworkConfig clientNetworkConfig2) {
      this.clientNetworkConfig = clientNetworkConfig2;
   }

   public int getCount3() {
      return this.count3;
   }

   public ClientNetworkConfig getClientNetworkConfig() {
      return this.clientNetworkConfig;
   }

   public void setEnabled(boolean enabled3) {
      this.clientNetworkConfig.setEnabled(enabled3);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public KeybindAnimation getKeybindAnimation() {
      return this.keybindAnimation;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public int getCount4() {
      return this.count4;
   }

   public void applyColorEntry(int count5, int count6, int count7, ColorEntry colorEntry) {
      if (count7 == 0 && RenderUtils.checkCondition((float)this.count3, (float)this.count4, (float)this.count2, (float)this.count, count5, count6)) {
         if (KeyUtils.checkCondition(157) || KeyUtils.checkCondition(29)) {
            boolean flag = !this.isActive();
            this.setEnabled(flag);
            return;
         }

         if (!this.timer.hasReached(250L)) {
            String text = this.clientNetworkConfig.getString3()
               + ":"
               + this.clientNetworkConfig.getInt()
               + (
                  !this.clientNetworkConfig.getText().equals("")
                     ? ":"
                        + this.clientNetworkConfig.getText()
                        + (!this.clientNetworkConfig.getText2().equals("") ? ":" + this.clientNetworkConfig.getText2() : "")
                     : ""
               );
            boolean flag2 = true;
            String text2 = text;
            colorEntry.applyText2(text2, flag2);
         }

         this.timer.reset();
      }
   }

   public TimeHelper getTimer() {
      return this.timer;
   }
}

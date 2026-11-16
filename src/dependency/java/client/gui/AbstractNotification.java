package client.gui;

import client.module.Module;
import client.setting.ShaderOptions;
import client.util.KeybindAnimation;
import client.util.MinecraftAccessor;
import net.minecraft.util.MathHelper;

public class AbstractNotification implements MinecraftAccessor {
   public int width;
   public int height;
   public int colorCode;
   public int xPos;
   public int yPos;
   public int alpha;
   public int headerColor;
   public long startTimeMillis;
   public Module targetModule;
   public int bodyColor;
   public int shadowColor;
   public int accentColor;
   public KeybindAnimation keybindAnimation;

   public int getDuration() {
      return this.bodyColor;
   }

   public void renderNotification(float factor, ShaderOptions shaderOptions) {
      long value = Math.abs(System.currentTimeMillis() - this.startTimeMillis);
      long doubleItem = (long)MathHelper.getDouble7((double)value, 0.0, (double)this.xPos);
      long doubleItem2 = (long)MathHelper.getDouble7((double)value, (double)(this.bodyColor - this.xPos), (double)this.bodyColor);
      int count = this.accentColor - this.alpha;
      if (value <= (long)this.xPos) {
         this.shadowColor = (int)((double)this.alpha + (double)count / (double)this.xPos * (double)doubleItem);
      } else if (value >= (long)(this.bodyColor - this.xPos)) {
         this.shadowColor = (int)((double)(this.alpha + count) - (double)count / (double)this.xPos * (double)(doubleItem2 - (long)(this.bodyColor - this.xPos)));
      } else {
         this.shadowColor = this.accentColor;
      }
   }

   public long getStartTime() {
      return this.startTimeMillis;
   }

   public AbstractNotification(int count, int count2, int count3, int count4, Module module, int count5, int count6) {
      this.accentColor = count;
      this.headerColor = count2;
      this.yPos = count3;
      this.width = count4;
      this.targetModule = module;
      this.startTimeMillis = System.currentTimeMillis();
      this.alpha = count > MinecraftAccessor.minecraft.getFramebuffer().indexOffset2 / 2 ? MinecraftAccessor.minecraft.getFramebuffer().indexOffset2 : -count3;
      this.height = this.colorCode;
      this.colorCode = this.headerColor;
      this.xPos = MathHelper.getInt12(count5, 0, count6 / 2);
      this.bodyColor = count6;
   }
}

package net.augustus.ui.notification;

import net.augustus.settings.BlurShadowSettings;
import net.augustus.utils.animation.AnimationUtil;
import net.augustus.modules.Module;
import net.augustus.utils.interfaces.MC;
import net.minecraft.util.MathHelper;

public class AbstractNotification implements MC {
   public AnimationUtil slideAnimation;
   public int height;
   public int startX;
   public int currentX;
   public int targetY;
   public int animationDuration;
   public int displayDuration;
   public long createdTime;
   public int width;
   public int targetX;
   public int currentY;
   public int previousY;
   public Module module;

   public AbstractNotification(int n, int n2, int n3, int n4, Module module2, int n5, int n6) {
      this.targetX = n;
      this.targetY = n2;
      this.width = n3;
      this.height = n4;
      this.module = module2;
      this.createdTime = System.currentTimeMillis();
      this.startX = n > mc.getFramebuffer().framebufferWidth / 2 ? mc.getFramebuffer().framebufferWidth : -n3;
      this.previousY = this.currentY;
      this.currentY = this.targetY;
      this.animationDuration = MathHelper.clamp_int(n5, 0, n6 / 2);
      this.displayDuration = n6;
   }

   public long getCreatedTime() {
      return this.createdTime;
   }

   public int getDisplayDuration() {
      return this.displayDuration;
   }

   public void render(float f, BlurShadowSettings blurShadowSettings) {
      long l = Math.abs(System.currentTimeMillis() - this.createdTime);
      long l2 = (long)MathHelper.clamp_double((double)l, 0.0, (double)this.animationDuration);
      long l3 = (long)MathHelper.clamp_double((double)l, (double)(this.displayDuration - this.animationDuration), (double)this.displayDuration);
      int n = this.targetX - this.startX;
      if (l <= (long)this.animationDuration) {
         this.currentX = (int)((double)this.startX + (double)n / (double)this.animationDuration * (double)l2);
      } else if (l >= (long)(this.displayDuration - this.animationDuration)) {
         this.currentX = (int)((double)(this.startX + n) - (double)n / (double)this.animationDuration * (double)(l3 - (long)(this.displayDuration - this.animationDuration)));
      } else {
         this.currentX = this.targetX;
      }
   }




}

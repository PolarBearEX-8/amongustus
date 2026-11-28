package net.augustus.render.esp;

import net.augustus.utils.render.ColorUtil;
import net.augustus.utils.interfaces.MC;

public abstract class AbstractEspBox implements MC {
   public float height;
   public float bottomExpand;
   public float green;
   public float y;
   public float red;
   public float alpha;
   public float blue;
   public float x;
   public float z;
   public float topExpand;
   public float halfWidth;
   public float widthExpand;

   public abstract void updateBounds(float f);

   public float getExpandedHalfWidth() {
      return this.halfWidth + this.widthExpand;
   }

   public void setAlpha(float f) {
      this.alpha = f;
   }

   public float getExpandedHeight() {
      return this.height + this.topExpand;
   }

   public void setExpansion(float f, float f2, float f3) {
      this.widthExpand = f;
      this.topExpand = f2;
      this.bottomExpand = f3;
   }

   public void setColor(int n, float f) {
      this.red = ColorUtil.getRedFloat(n);
      this.green = ColorUtil.getGreenFloat(n);
      this.blue = ColorUtil.getBlueFloat(n);
      this.alpha = f;
   }

   public float getBottomExpand() {
      return this.bottomExpand;
   }
}

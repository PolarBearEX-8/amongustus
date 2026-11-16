package augustus.modes.render;

import augustus.modules.render.MoveCircles;
import client.util.KeybindAnimation;
import client.util.RandomUtil;

public class MoveCircleRing {
   public final double coordOffset3;
   public final double coordOffset5;
   public final double coordOffset1;
   public final float scaleFactor4;
   public final float scaleFactor;
   public final KeybindAnimation keybindAnimation;
   public final KeybindAnimation keybindAnimation2;
   public final int count;
   public double coordOffset;
   public int indexOffset;
   public double coordOffset2;
   public final MoveCircles moveCircles;

   public MoveCircleRing(MoveCircles moveCircles2, double posX, double posY, double posZ, float factor, float factor2, int count2) {
      this.moveCircles = moveCircles2;
      this.keybindAnimation = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
      this.keybindAnimation2 = new KeybindAnimation(0.0F, 0.0F, 0.0F, 0.0F);
      this.count = (int)MoveCircles.getNumberSetting(this.moveCircles).getValue();
      this.coordOffset = 0.0;
      this.keybindAnimation.updateEasingFunction(this.count, KeybindAnimation.getEasingFunction(MoveCircles.getModeSetting(moveCircles2).getSelected()), 1, false);
      this.indexOffset = count2;
      this.coordOffset2 = (double)RandomUtil.processAction3(count2);
      this.coordOffset3 = posX;
      this.coordOffset5 = posY;
      this.coordOffset1 = posZ;
      this.scaleFactor4 = factor;
      this.scaleFactor = factor2;
   }
}

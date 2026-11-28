package net.augustus.modules.render;

import net.augustus.utils.animation.AnimationUtil;
import net.augustus.utils.render.ColorUtil;

public class MoveCircleEffect {
   public final double posX;
   public final double posY;
   public final double posZ;
   public final float lineWidth;
   public final float maxRadius;
   public final AnimationUtil expandAnimation;
   public final AnimationUtil fadeOutAnimation;
   public final int expandDurationMs;
   public double lastExpandProgress;
   public int color;
   public double alpha;
   public final MoveCircles moveCircles;

   public MoveCircleEffect(MoveCircles moveCircles2, double d, double d2, double d3, float f, float f2, int n) {
       super();
       this.moveCircles = moveCircles2;
      this.expandAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);
      this.fadeOutAnimation = new AnimationUtil(0.0F, 0.0F, 0.0F, 0.0F);
      this.expandDurationMs = (int)MoveCircles.getAnimationTimeSetting(this.moveCircles).getValue();
      this.lastExpandProgress = 0.0;
      this.expandAnimation.start(this.expandDurationMs, AnimationUtil.parseEasingType(MoveCircles.getCircleAnimationSetting(moveCircles2).getSelectedValue()), 1, false);
      this.color = n;
      this.alpha = (double)ColorUtil.getAlpha(n);
      this.posX = d;
      this.posY = d2;
      this.posZ = d3;
      this.lineWidth = f;
      this.maxRadius = f2;
   }
}

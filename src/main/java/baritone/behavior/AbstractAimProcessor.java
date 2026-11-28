package baritone.behavior;

import baritone.api.behavior.look.ForkableRandom;
import baritone.api.behavior.look.ITickableAimProcessor;
import baritone.Baritone;
import baritone.api.utils.IPlayerContext;
import baritone.api.utils.Rotation;

public abstract class AbstractAimProcessor implements ITickableAimProcessor {
   public final IPlayerContext ctx;
   private final ForkableRandom rand;
   private double randomYawOffset;
   private double randomPitchOffset;

   private float snapToSensitivity(float f, float f2) {
      return f + this.pixelsToDegrees(this.degreesToPixels(f2 - f));
   }

   public abstract Rotation getPrevRotation();

   @Override
   public final void tick() {
      this.randomYawOffset = (this.rand.nextDouble() - 0.5) * (Double)Baritone.settings().randomLooking.value;
      this.randomPitchOffset = (this.rand.nextDouble() - 0.5) * (Double)Baritone.settings().randomLooking.value;
   }

   private float pixelsToDegrees(int n) {
      float f = this.ctx.getMc().gameSettings.mouseSensitivity * 0.6F + 0.2F;
      return (float)n * f * f * f * 8.0F * 0.15F;
   }

   @Override
   public final Rotation peekRotation(Rotation rotation) {
      return rotation;
   }

   public AbstractAimProcessor(AbstractAimProcessor aimProcessor, LookBehavior.InnerEntry obj) {
      this(aimProcessor);
   }

   private int degreesToPixels(float f) {
      return Math.round(f / this.pixelsToDegrees(1));
   }

   @Override
   public final void advance(int n) {
      for (int i = 0; i < n; i++) {
         this.tick();
      }
   }

   @Override
   public Rotation nextRotation(Rotation rotation) {
      Rotation rotation2 = this.peekRotation(rotation);
      this.tick();
      return rotation2;
   }

   @Override
   public final ITickableAimProcessor fork() {
      return new ForkedAimProcessor(this, this);
   }

   public AbstractAimProcessor(IPlayerContext playerContext) {
      this.ctx = playerContext;
      this.rand = new ForkableRandom();
   }

   private float nudgeToLevel(float f) {
      if (f < -20.0F) {
         return f + 1.0F;
      } else {
         return f > 10.0F ? f - 1.0F : f;
      }
   }

   private AbstractAimProcessor(AbstractAimProcessor aimProcessor) {
      this.ctx = aimProcessor.ctx;
      this.rand = aimProcessor.rand.fork();
      this.randomYawOffset = aimProcessor.randomYawOffset;
      this.randomPitchOffset = aimProcessor.randomPitchOffset;
   }
}

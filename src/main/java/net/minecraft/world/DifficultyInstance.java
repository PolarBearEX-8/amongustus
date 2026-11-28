package net.minecraft.world;

import net.minecraft.util.MathHelper;

public class DifficultyInstance {
   private final EnumDifficulty worldDifficulty;
   private final float additionalDifficulty;

   public float getClampedAdditionalDifficulty() {
      return this.additionalDifficulty < 2.0F ? 0.0F : (this.additionalDifficulty > 4.0F ? 1.0F : (this.additionalDifficulty - 2.0F) / 2.0F);
   }

   public float getAdditionalDifficulty() {
      return this.additionalDifficulty;
   }

   public DifficultyInstance(EnumDifficulty enumDifficulty, long l, long l2, float f) {
      this.worldDifficulty = enumDifficulty;
      this.additionalDifficulty = this.calculateAdditionalDifficulty(enumDifficulty, l, l2, f);
   }

   private float calculateAdditionalDifficulty(EnumDifficulty enumDifficulty, long l, long l2, float f) {
      if (enumDifficulty == EnumDifficulty.PEACEFUL) {
         return 0.0F;
      } else {
         boolean flag = enumDifficulty == EnumDifficulty.HARD;
         float f2 = 0.75F;
         float f3 = MathHelper.clamp_float(((float)l + -72000.0F) / 1440000.0F, 0.0F, 1.0F) * 0.25F;
         f2 += f3;
         float f4 = 0.0F;
         f4 += MathHelper.clamp_float((float)l2 / 3600000.0F, 0.0F, 1.0F) * (flag ? 1.0F : 0.75F);
         f4 += MathHelper.clamp_float(f * 0.25F, 0.0F, f3);
         if (enumDifficulty == EnumDifficulty.EASY) {
            f4 *= 0.5F;
         }

         f2 += f4;
         return (float)enumDifficulty.getDifficultyId() * f2;
      }
   }
}

package net.minecraft.item;

import client.setting.GameSettingsPeacefulType;
import net.minecraft.util.MathHelper;

public class GoldenOre {
   public final GameSettingsPeacefulType gameSettingsPeacefulType;
   public final float factor;

   public float getFactor() {
      return this.factor < 2.0F ? 0.0F : (this.factor > 4.0F ? 1.0F : (this.factor - 2.0F) / 2.0F);
   }

   public GoldenOre(GameSettingsPeacefulType gameSettingsPeacefulType2, long value, long value2, float factor2) {
      this.gameSettingsPeacefulType = gameSettingsPeacefulType2;
      this.factor = this.computeFactor(gameSettingsPeacefulType2, value, value2, factor2);
   }

   private float computeFactor(GameSettingsPeacefulType gameSettingsPeacefulType2, long value, long value2, float factor2) {
      if (gameSettingsPeacefulType2 == GameSettingsPeacefulType.PEACEFUL) {
         return 0.0F;
      } else {
         boolean flag = gameSettingsPeacefulType2 == GameSettingsPeacefulType.HARD;
         float factor3 = 0.75F;
         float factor4 = MathHelper.clamp_float(((float)value + -72000.0F) / 1440000.0F, 0.0F, 1.0F) * 0.25F;
         float factor5 = factor3 + factor4;
         float factor6 = 0.0F
            + MathHelper.clamp_float((float)value2 / 3600000.0F, 0.0F, 1.0F) * (flag ? 1.0F : 0.75F)
            + MathHelper.clamp_float(factor2 * 0.25F, 0.0F, factor4);
         if (gameSettingsPeacefulType2 == GameSettingsPeacefulType.EASY) {
            factor6 *= 0.5F;
         }

         float factor7 = factor5 + factor6;
         return (float)gameSettingsPeacefulType2.getIndex() * factor7;
      }
   }

   public float getFactorAsFloat() {
      return this.factor;
   }
}

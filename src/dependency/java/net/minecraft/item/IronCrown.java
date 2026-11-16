package net.minecraft.item;

import client.setting.Setting;
import client.util.AnglePredictor;
import client.util.SmoothReader;
import client.util.SplineDeserializer;

public class IronCrown {
   public final boolean enabled;
   public final AnglePredictor anglePredictor;
   public final boolean enabled5;
   public final boolean enabled3;
   public final boolean enabled6;
   public final SmoothReader smoothReader;
   public final boolean enabled7;
   public final boolean enabled4;
   public final String labelText2;
   public final String labelText;
   public final double value;
   public final SplineDeserializer splineDeserializer;
   public final boolean enabled2;

   public static Setting[] getSettingArray() {
      return null;
   }

   public IronCrown(
      String text,
      String text2,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4,
      boolean flag5,
      boolean flag6,
      boolean flag7,
      AnglePredictor anglePredictor2,
      SmoothReader smoothReader2,
      SplineDeserializer splineDeserializer2,
      double offset
   ) {
      this.labelText = text;
      this.labelText2 = text2;
      this.enabled2 = flag;
      this.enabled4 = flag2;
      this.enabled6 = flag3;
      this.enabled3 = flag4;
      this.enabled7 = flag5;
      this.enabled5 = flag6;
      this.enabled = flag7;
      this.anglePredictor = anglePredictor2;
      this.smoothReader = smoothReader2;
      this.splineDeserializer = splineDeserializer2;
      this.value = offset;
   }
}

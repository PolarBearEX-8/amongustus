package client.util;

import client.event.PostUpdateInterceptor;

public class SpecialFloatEntry {
   public boolean enabled;
   public float factor12;
   public float factor10 = 0.0F;
   public float factor2;
   public float factor3;
   public PostUpdateInterceptor postUpdateInterceptor;
   public float factor11;
   public float factor9;
   public float factor = 0.0F;
   public String labelText;
   public float factor6;
   public float factor5;
   public float factor4;
   public float factor7;
   public boolean enabled2;
   public boolean enabled3;
   public float factor8 = 0.0F;

   public SpecialFloatEntry(SpecialFloatEntry specialFloatEntry) {
      this.enabled3 = true;
      this.enabled2 = true;
      this.enabled = true;
      this.factor3 = specialFloatEntry.factor3;
      this.factor2 = specialFloatEntry.factor2;
      this.factor5 = specialFloatEntry.factor5;
      this.factor11 = specialFloatEntry.factor11;
      this.factor4 = specialFloatEntry.factor4;
      this.factor7 = specialFloatEntry.factor7;
      this.factor9 = specialFloatEntry.factor9;
      this.factor6 = specialFloatEntry.factor6;
      this.factor12 = specialFloatEntry.factor12;
      this.postUpdateInterceptor = specialFloatEntry.postUpdateInterceptor;
      this.labelText = specialFloatEntry.labelText;
   }

   public SpecialFloatEntry(PostUpdateInterceptor postUpdateInterceptor2) {
      this.enabled3 = true;
      this.enabled2 = true;
      this.enabled = true;
      this.factor9 = postUpdateInterceptor2.getFactor8();
      this.factor6 = postUpdateInterceptor2.getFactor6();
      this.factor12 = postUpdateInterceptor2.getFactor3();
      this.factor11 = postUpdateInterceptor2.getFactor4();
      this.factor4 = postUpdateInterceptor2.getFactor10();
      this.factor7 = postUpdateInterceptor2.getFactor11();
      this.factor3 = postUpdateInterceptor2.getFactor9();
      this.factor2 = postUpdateInterceptor2.getFactor();
      this.factor5 = postUpdateInterceptor2.getFactor2();
      this.postUpdateInterceptor = postUpdateInterceptor2;
      this.labelText = postUpdateInterceptor2.getLabelText();
   }

   public SpecialFloatEntry(PostUpdateInterceptor postUpdateInterceptor2, boolean flag) {
      this.enabled3 = true;
      this.enabled2 = true;
      this.enabled = true;
      if (flag) {
         this.factor9 = 0.0F;
         this.factor6 = 0.0F;
         this.factor12 = 0.0F;
      }

      this.factor9 = postUpdateInterceptor2.getFactor8();
      this.factor6 = postUpdateInterceptor2.getFactor6();
      this.factor12 = postUpdateInterceptor2.getFactor3();
      this.factor11 = postUpdateInterceptor2.getFactor4();
      this.factor4 = postUpdateInterceptor2.getFactor10();
      this.factor7 = postUpdateInterceptor2.getFactor11();
      this.factor3 = postUpdateInterceptor2.getFactor9();
      this.factor2 = postUpdateInterceptor2.getFactor();
      this.factor5 = postUpdateInterceptor2.getFactor2();
      this.postUpdateInterceptor = postUpdateInterceptor2;
      this.labelText = postUpdateInterceptor2.getLabelText();
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         SpecialFloatEntry specialFloatEntry = (SpecialFloatEntry)other;
         return this.labelText.equals(specialFloatEntry.labelText);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.labelText.hashCode();
   }
}

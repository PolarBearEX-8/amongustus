package client.util;

import client.event.PostUpdateInterceptor;
import java.util.ArrayList;
import java.util.List;

public class ListenerAdapter23 implements PostUpdateInterceptor {
   public float factor8;
   public Object dataRef;
   public Boolean memberRef;
   public float factor6;
   public float factor3;
   public List list2 = new ArrayList();
   public int count;
   public String labelText;
   public float factor12;
   public float factor4;
   public float factor;
   public float factor11;
   public List list = new ArrayList();
   public ListenerAdapter23 listenerAdapter23;
   public boolean enabled;
   public Double memberRef2;
   public float factor10;
   public float factor2;
   public float factor5;
   public Boolean memberRef4;
   public float factor7;
   public Boolean memberRef3;
   public SpecialFloatEntry specialFloatEntry;
   public float factor9;

   @Override
   public void setFactor6(float factor13) {
      this.factor6 = factor13;
   }

   @Override
   public float getFactor5() {
      return this.factor5;
   }

   @Override
   public void setFactor2(float factor13) {
      this.factor2 = factor13;
   }

   @Override
   public float getFactor11() {
      return this.factor11;
   }

   @Override
   public void setFactor4(float factor13) {
      this.factor4 = factor13;
   }

   @Override
   public float getFactor6() {
      return this.factor6;
   }

   @Override
   public float getFactor2() {
      return this.factor2;
   }

   @Override
   public void setFactor10(float factor13) {
      this.factor10 = factor13;
   }

   @Override
   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   @Override
   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   @Override
   public SpecialFloatEntry getSpecialFloatEntryAsSpecialFloatEntr() {
      return this.specialFloatEntry;
   }

   @Override
   public float getFactor7() {
      return this.factor7;
   }

   @Override
   public void setFactor8(float factor13) {
      this.factor8 = factor13;
   }

   @Override
   public float getFactor12() {
      return this.factor12;
   }

   @Override
   public void setFactor9(float factor13) {
      this.factor9 = factor13;
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void setFactor3(float factor13) {
      this.factor3 = factor13;
   }

   @Override
   public float getFactor10() {
      return this.factor10;
   }

   @Override
   public void setFactor7(float factor13) {
      this.factor7 = factor13;
   }

   @Override
   public void updateState() {
      if (this.specialFloatEntry == null) {
         this.specialFloatEntry = new SpecialFloatEntry(this, true);
      }
   }

   @Override
   public float getFactor8() {
      return this.factor8;
   }

   @Override
   public float getFactor4() {
      return this.factor4;
   }

   @Override
   public void setFactor11(float factor13) {
      this.factor11 = factor13;
   }

   @Override
   public float getFactor3() {
      return this.factor3;
   }

   @Override
   public boolean isEnabled() {
      return this.enabled;
   }

   @Override
   public void setFactor5(float factor13) {
      this.factor5 = factor13;
   }

   @Override
   public float getFactor() {
      return this.factor;
   }

   @Override
   public void setFactor12(float factor13) {
      this.factor12 = factor13;
   }

   @Override
   public void setFactor(float factor13) {
      this.factor = factor13;
   }

   @Override
   public float getFactor9() {
      return this.factor9;
   }

   public ListenerAdapter23() {
      this.count = -1;
      this.factor9 = 1.0F;
      this.factor = 1.0F;
      this.factor2 = 1.0F;
   }
}

package client.event;

import client.util.SpecialFloatEntry;

public interface PostUpdateInterceptor {
   void setFactor6(float factor);

   float getFactor5();

   void setFactor2(float factor);

   float getFactor11();

   void setFactor4(float factor);

   float getFactor6();

   default SpecialFloatEntry getSpecialFloatEntry() {
      return new SpecialFloatEntry(this);
   }

   float getFactor2();

   void setEnabled(boolean enabled);

   void setFactor10(float factor);

   void setLabelText(String labelText);

   float getFactor7();

   SpecialFloatEntry getSpecialFloatEntryAsSpecialFloatEntr();

   void setFactor8(float factor);

   String getLabelText();

   float getFactor12();

   void setFactor9(float factor);

   void setFactor3(float factor);

   float getFactor10();

   void setFactor7(float factor);

   void updateState();

   float getFactor8();

   float getFactor4();

   void setFactor11(float factor);

   void setFactor5(float factor);

   boolean isEnabled();

   float getFactor3();

   float getFactor();

   void setFactor12(float factor);

   void setFactor(float factor);

   float getFactor9();
}

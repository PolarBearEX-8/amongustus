package client.util;

import client.setting.BridgeOptionType;
import client.setting.DirectOptionType;
import client.setting.RenderNumberProperty;

public class CompositeDoubleEntry {
   public double value = 0.0;
   public double value2 = 0.0;
   public int count2;
   public String labelText;
   public BridgeOptionType bridgeOptionType = BridgeOptionType.NONE;
   public DirectOptionType directOptionType;

   public BridgeOptionType getBridgeOptionType() {
      return this.bridgeOptionType;
   }

   public void applyText(String text) {
      RayTraceUtil.applyString("(Debug P" + this.count2 + ") " + text);
   }

   public double getValue2() {
      return this.value2;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public DirectOptionType getDirectOptionType() {
      return this.directOptionType;
   }

   public void applyBridgeOptionType(BridgeOptionType bridgeOptionType2, String text) {
      this.bridgeOptionType = bridgeOptionType2;
      RayTraceUtil.applyString(
         "P"
            + this.count2
            + ": "
            + this.labelText
            + " "
            + text
            + "; Certainty: "
            + bridgeOptionType2.getLabelText2()
            + "; Violation "
            + this.count2
            + "; AllTimeViolation: "
            + this.value
      );
   }

   public void applyDouble(double offset) {
      if (offset > 0.0) {
         this.value += offset;
      }

      this.value2 += offset;
   }

   public void applyRenderNumberProperty(RenderNumberProperty renderNumberProperty) {
      byte byteValue = 0;
      short shortValue = 9460;
      int count = -1084542402;
      String text = "You got Hitbug! Congratulations! " + renderNumberProperty.getLabelText();
      this.applyText(text);
   }

   public void applyBridgeOptionType(BridgeOptionType bridgeOptionType2) {
      this.bridgeOptionType = bridgeOptionType2;
      RayTraceUtil.applyString(
         "P"
            + this.count2
            + ": "
            + this.labelText
            + "; Certainty: "
            + bridgeOptionType2.getLabelText2()
            + "; Violation "
            + this.count2
            + "; AllTimeViolation: "
            + this.value
      );
   }

   public double getValue() {
      return this.value;
   }

   public CompositeDoubleEntry(int count, String text, DirectOptionType directOptionType2) {
      this.count2 = count;
      this.labelText = text;
      this.directOptionType = directOptionType2;
   }
}

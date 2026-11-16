package client.util;

import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

public class ResourceLocationAdapter extends ResourceLocation {
   public final String labelText;

   public String getLabelText() {
      return this.labelText;
   }

   public static String[] resolveTextArrayByText(String text) {
      String[] textArray = new String[]{null, text, null};
      int count = text.indexOf(35);
      String text2 = text;
      if (count >= 0) {
         textArray[2] = text.substring(count + 1);
         if (count > 1) {
            text2 = text.substring(0, count);
         }
      }

      System.arraycopy(ResourceLocation.resolveTextArray(text2), 0, textArray, 0, 2);
      return textArray;
   }

   public ResourceLocationAdapter(int count, String[] textArray) {
      super(0, new String[]{textArray[0], textArray[1]});
      this.labelText = StringUtils.isEmpty(textArray[2]) ? "normal" : textArray[2].toLowerCase();
   }

   public ResourceLocationAdapter(String text, String text2) {
      this(0, resolveTextArrayByText(text + '#' + (text2 == null ? "normal" : text2)));
   }

   public ResourceLocationAdapter(String text) {
      this(0, resolveTextArrayByText(text));
   }

   public ResourceLocationAdapter(ResourceLocation location, String text) {
      this(location.toString(), text);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other instanceof ResourceLocationAdapter && super.equals(other)) {
         ResourceLocationAdapter resourceLocationAdapter = (ResourceLocationAdapter)other;
         return this.labelText.equals(resourceLocationAdapter.labelText);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return super.toString() + '#' + this.labelText;
   }

   @Override
   public int hashCode() {
      return 31 * super.hashCode() + this.labelText.hashCode();
   }
}

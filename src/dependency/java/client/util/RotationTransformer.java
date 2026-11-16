package client.util;

import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class RotationTransformer {
   public String labelText = null;
   public final List list = Lists.newArrayList();

   private void add(String text) {
      this.list.add(0, text);
   }

   public static void applyRotationTransformer(RotationTransformer rotationTransformer, String text) {
      rotationTransformer.add(text);
   }

   @Override
   public String toString() {
      return this.labelText != null
         ? (!this.list.isEmpty() ? this.labelText + " " + this.join() : this.labelText)
         : (!this.list.isEmpty() ? "(Unknown file) " + this.join() : "(Unknown file)");
   }

   public String join() {
      return StringUtils.join(this.list, "->");
   }

   public static String resolveText(RotationTransformer rotationTransformer, String text) {
      return rotationTransformer.labelText = text;
   }

   private RotationTransformer() {
   }

   public RotationTransformer(BezierProvider bezierProvider) {
      this();
   }
}

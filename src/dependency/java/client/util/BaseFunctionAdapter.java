package client.util;

import com.google.common.base.Function;
import java.util.Arrays;
import java.util.List;

public class BaseFunctionAdapter implements Function {
   public List asList(Object[] entryArray) {
      return Arrays.asList(entryArray);
   }

   public BaseFunctionAdapter(RotationParser rotationParser) {
      this();
   }

   private BaseFunctionAdapter() {
   }

   public Object apply(Object entry) {
      return this.asList((Object[])entry);
   }
}

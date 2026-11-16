package client.util;

import client.event.PreUpdateProcessor;
import com.google.common.base.Function;

public class AltFunctionAdapter implements Function {
   public final ReloadingResourceManagerHolder reloadingResourceManagerHolder;

   public String resolveText(PreUpdateProcessor preUpdateProcessor) {
      return preUpdateProcessor.getName();
   }

   public AltFunctionAdapter(ReloadingResourceManagerHolder reloadingResourceManagerHolder2) {
      this.reloadingResourceManagerHolder = reloadingResourceManagerHolder2;
   }

   public Object apply(Object entry) {
      return this.resolveText((PreUpdateProcessor)entry);
   }
}

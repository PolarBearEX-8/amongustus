package client.util;

import com.google.common.collect.Lists;
import java.net.InetAddress;
import java.util.Collections;
import java.util.List;

public class ListContainer {
   public List list = Lists.newArrayList();
   public boolean enabled;

   public synchronized boolean isEnabled() {
      return this.enabled;
   }

   public synchronized void applyText(String text, InetAddress inetAddress) {
      String text2 = CustomValueHandler.resolveTextByText(text);
      String text3 = CustomValueHandler.resolveText(text);
      if (text3 != null) {
         String text4 = inetAddress.getHostAddress() + ":" + text3;
         boolean flag = false;

         for (InterpolationDispatcher interpolationDispatcher : (Iterable<InterpolationDispatcher>)(Object)(this.list)) {
            if (interpolationDispatcher.getLabelText2().equals(text4)) {
               interpolationDispatcher.updateState();
               flag = true;
               break;
            }
         }

         if (!flag) {
            this.list.add(new InterpolationDispatcher(text2, text4));
            this.enabled = true;
         }
      }
   }

   public synchronized List unmodifiableList() {
      return Collections.unmodifiableList(this.list);
   }

   public synchronized void updateState() {
      this.enabled = false;
   }
}

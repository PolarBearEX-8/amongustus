package client.util;

import client.event.PostUpdateInterceptor;

public class RotationObserver {
   public PostUpdateInterceptor postUpdateInterceptor;
   public boolean enabled2;
   public boolean enabled3;
   public boolean enabled;

   public RotationObserver(boolean flag, boolean flag2, boolean flag3, PostUpdateInterceptor postUpdateInterceptor2) {
      this.enabled3 = flag;
      this.enabled2 = flag2;
      this.enabled = flag3;
      this.postUpdateInterceptor = postUpdateInterceptor2;
   }
}

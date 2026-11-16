package cc.baritone.utils;

import java.util.Optional;

public class AStarHandler {
   public DefaultCustom defaultCustom;
   public boolean enabled;

   public AStarHandler() {
      this(null, false);
   }

   public AStarHandler(DefaultCustom defaultCustom2, boolean flag) {
      this.defaultCustom = defaultCustom2;
      this.enabled = flag;
   }

   public final Optional ofNullable() {
      return Optional.ofNullable(this.defaultCustom);
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}

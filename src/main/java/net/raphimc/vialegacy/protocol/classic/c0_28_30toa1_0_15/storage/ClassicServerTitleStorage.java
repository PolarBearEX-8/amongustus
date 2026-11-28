package net.raphimc.vialegacy.protocol.classic.c0_28_30toa1_0_15.storage;

import com.viaversion.viaversion.api.connection.StoredObject;
import com.viaversion.viaversion.api.connection.UserConnection;

public class ClassicServerTitleStorage extends StoredObject {
   private final String title;
   private final String motd;

   public ClassicServerTitleStorage(UserConnection user, String title, String motd) {
      super(user);
      this.title = title;
      this.motd = motd;
   }

   public String getTitle() {
      return this.title;
   }

   public String getMotd() {
      return this.motd;
   }

   public boolean isHaxEnabled() {
      return this.motd.contains("+hax");
   }

   public boolean isHaxDisabled() {
      return this.motd.contains("-hax");
   }

   public boolean isFlyEnabled() {
      return this.motd.contains("+fly");
   }

   public boolean isFlyDisabled() {
      return this.motd.contains("-fly");
   }

   public boolean isFlyEffectivelyEnabled() {
      boolean isOp = ((ClassicOpLevelStorage)this.user().get(ClassicOpLevelStorage.class)).getOpLevel() >= 100;
      return (this.isHaxDisabled() ? this.isFlyEnabled() : !this.isFlyDisabled()) || isOp && this.isOphaxEnabled();
   }

   public boolean isNoclipEnabled() {
      return this.motd.contains("+noclip");
   }

   public boolean isNoclipDisabled() {
      return this.motd.contains("-noclip");
   }

   public boolean isNoclipEffectivelyEnabled() {
      boolean isOp = ((ClassicOpLevelStorage)this.user().get(ClassicOpLevelStorage.class)).getOpLevel() >= 100;
      return (this.isHaxDisabled() ? this.isNoclipEnabled() : !this.isNoclipDisabled()) || isOp && this.isOphaxEnabled();
   }

   public boolean isRespawnEnabled() {
      return this.motd.contains("+respawn");
   }

   public boolean isRespawnDisabled() {
      return this.motd.contains("-respawn");
   }

   public boolean isRespawnEffectivelyEnabled() {
      boolean isOp = ((ClassicOpLevelStorage)this.user().get(ClassicOpLevelStorage.class)).getOpLevel() >= 100;
      return (this.isHaxDisabled() ? this.isRespawnEnabled() : !this.isRespawnDisabled()) || isOp && this.isOphaxEnabled();
   }

   public boolean isSpeedEnabled() {
      return this.motd.contains("+speed");
   }

   public boolean isSpeedDisabled() {
      return this.motd.contains("-speed");
   }

   public boolean isSpeedEffectivelyEnabled() {
      boolean isOp = ((ClassicOpLevelStorage)this.user().get(ClassicOpLevelStorage.class)).getOpLevel() >= 100;
      return (this.isHaxDisabled() ? this.isSpeedEnabled() : !this.isSpeedDisabled()) || isOp && this.isOphaxEnabled();
   }

   public boolean isOphaxEnabled() {
      return this.motd.contains("+ophax");
   }
}

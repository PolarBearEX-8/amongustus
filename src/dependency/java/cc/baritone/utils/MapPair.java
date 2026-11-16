package cc.baritone.utils;

import net.minecraft.util.MovementInput;

public class MapPair extends MovementInput {
   public final MapEntry mapEntry;

   @Override
   public void applyFloat(float factor, float factor2) {
      this.moveStrafe = 0.0F;
      this.moveForward = 0.0F;
      this.state = this.mapEntry.matchesBaseCustom(BaseCustom.JUMP);
      if (this.mapEntry.matchesBaseCustom(BaseCustom.MOVE_FORWARD)) {
         this.moveForward++;
      }

      if (this.mapEntry.matchesBaseCustom(BaseCustom.MOVE_BACK)) {
         this.moveForward--;
      }

      if (this.mapEntry.matchesBaseCustom(BaseCustom.MOVE_LEFT)) {
         this.moveStrafe++;
      }

      if (this.mapEntry.matchesBaseCustom(BaseCustom.MOVE_RIGHT)) {
         this.moveStrafe--;
      }

      if (this.state2 = this.mapEntry.matchesBaseCustom(BaseCustom.SNEAK)) {
         this.moveStrafe = (float)((double)this.moveStrafe * 0.3);
         this.moveForward = (float)((double)this.moveForward * 0.3);
      }
   }

   public MapPair(MapEntry mapEntry2) {
      this.mapEntry = mapEntry2;
   }
}

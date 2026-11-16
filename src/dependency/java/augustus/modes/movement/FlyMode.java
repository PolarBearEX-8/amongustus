package augustus.modes.movement;

import augustus.modules.movement.Fly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.world.World;

public class FlyMode extends EntityEnderdragon {
   public final Fly fly;

   @Override
   public void applyEntity9(Entity entity) {
   }

   @Override
   public boolean isActive7() {
      return !Fly.getBooleanSetting(this.fly).isEnabled();
   }

   @Override
   public void updateState35() {
   }

   public FlyMode(Fly fly2, World world) {
      super(world);
      this.fly = fly2;
   }
}

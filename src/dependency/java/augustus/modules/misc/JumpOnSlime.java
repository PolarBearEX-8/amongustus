package augustus.modules.misc;

import client.event.EventMove;
import client.event.MoveListener;
import client.module.Category;
import client.module.Module;
import client.setting.BooleanSetting;
import client.util.MinecraftAccessor;
import java.awt.Color;
import net.minecraft.block.BlockSlime;
import net.minecraft.util.BlockPos;

public class JumpOnSlime extends Module implements MoveListener {
   public final BooleanSetting settingInInv = new BooleanSetting(this, "InInv", false);

   public JumpOnSlime() {
      super("JumpOnSlime", "SpringeAufSchleim", new Color(35, 52, 45), Category.MOVEMENT);
   }

   @Override
   public void onPreDisable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onEnable() {
      this.registerEvent(21);
   }

   @Override
   public void onMove(EventMove event) {
      if ((MinecraftAccessor.minecraft.currentScreen == null || this.settingInInv.isEnabled())
         && MinecraftAccessor.minecraft.thePlayer.onGround
         && MinecraftAccessor.minecraft
            .theWorld
            .getBlockState(
               new BlockPos(
                  MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY - 0.5, MinecraftAccessor.minecraft.thePlayer.posZ
               )
            )
            .getBlock() instanceof BlockSlime) {
         event.setState(true);
      }
   }
}

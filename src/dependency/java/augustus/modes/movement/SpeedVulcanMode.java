package augustus.modes.movement;

import augustus.modules.movement.FastLadder;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.MinecraftAccessor;
import client.util.PlayerDiggingActionType;
import client.util.SpecialAbstractEventAdapter;
import client.util.VectorSorter;
import net.minecraft.item.SecondaryItemCallback;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class SpeedVulcanMode extends VectorSorter implements SecondaryItemCallback {
   public final NumberSetting settingMotionY;

   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 15;
      this.applyIndex(byteValue);
   }

   @Override
   public void applySpecialAbstractEventAdapte(SpecialAbstractEventAdapter event) {
      double offset = 0.0;

      for (double offset2 = this.settingMotionY.getValue() % 1.0; offset2 <= this.settingMotionY.getValue(); offset = offset2++) {
         if (!this.checkDouble(offset2)) {
            double offset3 = offset2 - 1.0;
            if (!this.checkDouble(offset3)) {
               break;
            }
         }
      }

      event.setValue(offset);
      MinecraftAccessor.minecraft
         .thePlayer
         .netHandlerPlayClient
         .handlePacket(
            new C07PacketPlayerDigging(
               PlayerDiggingActionType.STOP_DESTROY_BLOCK,
               new BlockPos(
                  MinecraftAccessor.minecraft.thePlayer.posX, MinecraftAccessor.minecraft.thePlayer.posY + 1.0, MinecraftAccessor.minecraft.thePlayer.posZ
               ),
               EnumFacing.DOWN
            )
         );
   }

   public SpeedVulcanMode(FastLadder fastLadder, SettingGroup settingGroup) {
      super(fastLadder, settingGroup, "Vulcan");
      NumberSetting setting = new NumberSetting(super.module, "VulcanMotionY", 5.0, 0.2, 10.0, 3, super.settingGroup);
      String text = "MotionY";
      setting = setting.getNumberSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingMotionY = setting.getNumberSetting(booleanSupplier);
   }
}

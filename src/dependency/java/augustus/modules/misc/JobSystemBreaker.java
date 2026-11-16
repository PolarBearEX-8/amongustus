package augustus.modules.misc;

import augustus.modules.fun.MLG;
import client.event.BlockActionListener;
import client.event.EventBlockAction;
import client.event.TickListener;
import client.module.Category;
import client.module.Module;
import client.setting.NumberSetting;
import client.setting.SettingContainer;
import client.setting.SettingProvider;
import client.util.MinecraftAccessor;
import client.util.PlayerDiggingActionType;
import com.google.common.collect.Sets;
import java.awt.Color;
import java.util.Set;
import net.minecraft.block.Blocks;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class JobSystemBreaker extends Module implements BlockActionListener, TickListener {
   public static final Set set = Sets.newConcurrentHashSet();
   public final NumberSetting settingRange = new NumberSetting(this, "Range", 6.0, 4.5, 6.0, 2);

   public JobSystemBreaker() {
      super("JobSystemBreaker", "JobTerminierere", new Color(66, 135, 245), Category.FUN);
   }

   @Override
   public void onPreDisable() {
      set.clear();
      MinecraftAccessor.minecraft.renderGlobal.updateState();
   }

   @Override
   public void onDisable() {
      set.clear();
   }

   @Override
   public void onEnable() {
      this.registerEvent(30);
      this.registerEvent(40);
   }

   @Override
   public void onBlockAction(EventBlockAction event) {
      if (event.getIBlockState() != null && event.getIBlockState().getBlock().equals(Blocks.block121)) {
         BlockPos blockPos = event.getBlockPos();
         if (blockPos == null) {
            return;
         }

         set.add(blockPos);
      }
   }

   @Override
   public void onRunTick() {
      Vec3 vec3 = MinecraftAccessor.minecraft.thePlayer.getVec35(1.0F);
      set.removeIf(pos -> this.matchesVec3(vec3, (BlockPos)pos));
   }

   private boolean matchesVec3(Vec3 vec, BlockPos blockPos) {
      if (Math.sqrt(blockPos.computeValueByDouble(vec.xCoord, vec.yCoord, vec.zCoord)) < Math.max(1.0, this.settingRange.getValue())) {
         NetHandlerPlayClient netHandlerPlayClient = MinecraftAccessor.minecraft.getNetHandlerPlayClient();
         PlayerDiggingActionType playerDiggingActionType = PlayerDiggingActionType.START_DESTROY_BLOCK;
         EnumFacing[] entries = EnumFacing.getEntries();
         SettingProvider settingProvider = SettingContainer.settingProvider;
         int count = EnumFacing.getEntries().length - 1;
         byte byteValue = 0;
         netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(playerDiggingActionType, blockPos, entries[settingProvider.getInt2(byteValue, count)]));
         netHandlerPlayClient = MinecraftAccessor.minecraft.getNetHandlerPlayClient();
         playerDiggingActionType = PlayerDiggingActionType.STOP_DESTROY_BLOCK;
         entries = EnumFacing.getEntries();
         settingProvider = SettingContainer.settingProvider;
         int count2 = EnumFacing.getEntries().length - 1;
         byte byteValue2 = 0;
         netHandlerPlayClient.handlePacket(new C07PacketPlayerDigging(playerDiggingActionType, blockPos, entries[settingProvider.getInt2(byteValue2, count2)]));
         return true;
      } else {
         return false;
      }
   }
}

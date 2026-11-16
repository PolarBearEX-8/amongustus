package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldEntryAdapter extends InternalWorldEntry {
   public boolean enabled3;
   public boolean enabled4;
   public int count5;
   public int count6;

   public WorldEntryAdapter(World world) {
      super(world);
   }

   private void updateState3() {
      if (this.count5 > 100) {
         this.entityPlayerAdapter.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("demo.reminder", new Object[0]));
         this.count5 = 0;
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.count6++;
      long timestamp = this.world.getTimestamp2();
      long value = timestamp / 24000L + 1L;
      if (!this.enabled3 && this.count6 > 20) {
         this.enabled3 = true;
         this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(5, 0.0F));
      }

      this.enabled4 = timestamp > 120500L;
      if (this.enabled4) {
         this.count5++;
      }

      if (timestamp % 24000L == 500L) {
         if (value <= 6L) {
            this.entityPlayerAdapter.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("demo.day." + value, new Object[0]));
         }
      } else if (value == 1L) {
         if (timestamp == 100L) {
            this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(5, 101.0F));
         } else if (timestamp == 175L) {
            this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(5, 102.0F));
         } else if (timestamp == 250L) {
            this.entityPlayerAdapter.auxiliaryListenerAdapter4.applyPacket(new S2BPacketChangeGameState(5, 103.0F));
         }
      } else if (value == 5L && timestamp % 24000L == 22000L) {
         this.entityPlayerAdapter.applyIChatComponent(new BaseManagerStyleIChatComponentAdapter("demo.day.warning", new Object[0]));
      }
   }

   @Override
   public void applyBlockPos(BlockPos blockPos, EnumFacing enumFacing) {
      if (this.enabled4) {
         this.updateState3();
      } else {
         super.applyBlockPos(blockPos, enumFacing);
      }
   }

   @Override
   public boolean matchesBlockPosByBlockPos(BlockPos blockPos) {
      return this.enabled4 ? false : super.matchesBlockPosByBlockPos(blockPos);
   }

   @Override
   public void applyBlockPos(BlockPos blockPos) {
      if (!this.enabled4) {
         super.applyBlockPos(blockPos);
      }
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player, World world, ItemStack stack, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (this.enabled4) {
         this.updateState3();
         return false;
      } else {
         return super.matchesEntityPlayer(player, world, stack, blockPos, enumFacing, posX, posY, posZ);
      }
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player, World world, ItemStack stack) {
      if (this.enabled4) {
         this.updateState3();
         return false;
      } else {
         return super.matchesEntityPlayer(player, world, stack);
      }
   }
}

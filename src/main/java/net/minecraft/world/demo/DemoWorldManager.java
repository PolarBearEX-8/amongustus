package net.minecraft.world.demo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DemoWorldManager extends ItemInWorldManager {
   private boolean displayedIntro;
   private boolean demoTimeExpired;
   private int demoEndedReminder;
   private int gameModeTicks;

   private void sendDemoReminder() {
      if (this.demoEndedReminder > 100) {
         this.thisPlayerMP.addChatMessage(new ChatComponentTranslation("demo.reminder", new Object[0]));
         this.demoEndedReminder = 0;
      }
   }

   public DemoWorldManager(World world) {
      super(world);
   }

   @Override
   public void onBlockClicked(BlockPos pos, EnumFacing facing) {
      if (this.demoTimeExpired) {
         this.sendDemoReminder();
      } else {
         super.onBlockClicked(pos, facing);
      }
   }

   @Override
   public void updateBlockRemoving() {
      super.updateBlockRemoving();
      this.gameModeTicks++;
      long totalWorldTime = this.theWorld.getTotalWorldTime();
      long l = totalWorldTime / 24000L + 1L;
      if (!this.displayedIntro && this.gameModeTicks > 20) {
         this.displayedIntro = true;
         this.thisPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(5, 0.0F));
      }

      this.demoTimeExpired = totalWorldTime > 120500L;
      if (this.demoTimeExpired) {
         this.demoEndedReminder++;
      }

      if (totalWorldTime % 24000L == 500L) {
         if (l <= 6L) {
            this.thisPlayerMP.addChatMessage(new ChatComponentTranslation("demo.day." + l, new Object[0]));
         }
      } else if (l == 1L) {
         if (totalWorldTime == 100L) {
            this.thisPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(5, 101.0F));
         } else if (totalWorldTime == 175L) {
            this.thisPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(5, 102.0F));
         } else if (totalWorldTime == 250L) {
            this.thisPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(5, 103.0F));
         }
      } else if (l == 5L && totalWorldTime % 24000L == 22000L) {
         this.thisPlayerMP.addChatMessage(new ChatComponentTranslation("demo.day.warning", new Object[0]));
      }
   }

   @Override
   public boolean tryUseItem(EntityPlayer player, World world, ItemStack stack) {
      if (this.demoTimeExpired) {
         this.sendDemoReminder();
         return false;
      } else {
         return super.tryUseItem(player, world, stack);
      }
   }

   @Override
   public void blockRemoving(BlockPos pos) {
      if (!this.demoTimeExpired) {
         super.blockRemoving(pos);
      }
   }

   @Override
   public boolean activateBlockOrUseItem(EntityPlayer player, World world, ItemStack stack, BlockPos pos, EnumFacing facing, float f, float f2, float f3) {
      if (this.demoTimeExpired) {
         this.sendDemoReminder();
         return false;
      } else {
         return super.activateBlockOrUseItem(player, world, stack, pos, facing, f, f2, f3);
      }
   }

   @Override
   public boolean tryHarvestBlock(BlockPos pos) {
      return this.demoTimeExpired ? false : super.tryHarvestBlock(pos);
   }
}

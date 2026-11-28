package baritone.utils.player;

import net.minecraft.world.WorldSettings;
import baritone.api.utils.IPlayerController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public final class BaritonePlayerController implements IPlayerController {
   private final Minecraft mc;

   @Override
   public WorldSettings.GameType getGameType() {
      return this.mc.playerController.getCurrentGameType();
   }

   @Override
   public boolean onPlayerDamageBlock(BlockPos pos, EnumFacing facing) {
      return this.mc.playerController.onPlayerDamageBlock(pos, facing);
   }

   @Override
   public ItemStack windowClick(int n, int n2, int n3, int n4, EntityPlayer player) {
      return this.mc.playerController.windowClick(n, n2, n3, n4, player);
   }

   public BaritonePlayerController(Minecraft mc2) {
      this.mc = mc2;
   }

   @Override
   public boolean hasBrokenBlock() {
      return this.mc.playerController.currentBlock.getY() == -1;
   }

   @Override
   public boolean clickBlock(BlockPos pos, EnumFacing facing) {
      return this.mc.playerController.clickBlock(pos, facing);
   }

   @Override
   public boolean processRightClickBlock(EntityPlayerSP player, WorldClient world, ItemStack stack, BlockPos pos, EnumFacing facing, Vec3 vec) {
      return this.mc.playerController.onPlayerRightClick(player, world, stack, pos, facing, vec);
   }

   @Override
   public void syncHeldItem() {
      this.mc.playerController.syncCurrentPlayItem();
   }

   @Override
   public boolean processRightClick(EntityPlayer player, World world, ItemStack stack) {
      return this.mc.playerController.sendUseItem(player, world, stack);
   }

   @Override
   public void setHittingBlock(boolean flag) {
      this.mc.playerController.isHittingBlock = flag;
   }

   @Override
   public void resetBlockRemoving() {
      this.mc.playerController.resetBlockRemoving();
   }
}

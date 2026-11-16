package client.util;

import client.event.PreRenderListener;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemCallbackAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public final class SkullOwnerManager extends ItemCallbackAdapter {
   public boolean enabled = true;

   @Override
   public void applyPreRenderListener(PreRenderListener preRenderListener) {
      if (this.enabled) {
         preRenderListener.getWorld().applyBlockPosByBlockPos(1000, preRenderListener.getBlockPos(), 0);
      } else {
         preRenderListener.getWorld().applyBlockPosByBlockPos(1001, preRenderListener.getBlockPos(), 0);
      }
   }

   @Override
   public ItemStack resolveItemStackByPreRenderListene(PreRenderListener preRenderListener, ItemStack stack) {
      World world = preRenderListener.getWorld();
      EnumFacing enumFacing = BlockDispenser.resolveEnumFacing(preRenderListener.getCount());
      BlockPos blockPos = preRenderListener.getBlockPos().offset(enumFacing);
      BlockSkull blockSkull = Blocks.blockSkull;
      if (!world.isAirBlock(blockPos) || !blockSkull.matchesWorldByWorld(world, blockPos, stack)) {
         this.enabled = false;
      } else if (!world.state) {
         world.matchesBlockPos(blockPos, blockSkull.getDefaultState().resolveIBlockState(BlockSkull.autoWalkPath, EnumFacing.UP), 3);
         TileEntity tileEntity = world.resolveTileEntity(blockPos);
         if (tileEntity instanceof TileEntitySkull) {
            if (stack.getIndexOffset() == 3) {
               GameProfile gameProfile = null;
               if (stack.canExecute()) {
                  NBTTagCompound stackTagCompound = stack.getStackTagCompound();
                  if (stackTagCompound.matchesText("SkullOwner", 10)) {
                     gameProfile = AuxiliaryNameManager.resolveGameProfile(stackTagCompound.getNBTTagCompound("SkullOwner"));
                  } else if (stackTagCompound.matchesText("SkullOwner", 8)) {
                     String text = stackTagCompound.resolveText("SkullOwner");
                     if (!ClientBrandHelper.isEmpty(text)) {
                        gameProfile = new GameProfile((UUID)null, text);
                     }
                  }
               }

               ((TileEntitySkull)tileEntity).applyGameProfile(gameProfile);
            } else {
               ((TileEntitySkull)tileEntity).applyIndex(stack.getIndexOffset());
            }

            ((TileEntitySkull)tileEntity).setCount3(enumFacing.getEnumFacing5().getIndexOffset() * 4);
            Blocks.blockSkull.applyWorld(world, blockPos, (TileEntitySkull)tileEntity);
         }

         stack.animationsToGo--;
      }

      return stack;
   }
}

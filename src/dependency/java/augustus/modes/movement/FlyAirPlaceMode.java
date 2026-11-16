package augustus.modes.movement;

import augustus.modules.movement.Fly;
import client.Client;
import client.event.EventClick;
import client.event.EventMotionUpdate;
import client.event.EventMove;
import client.event.EventMoveEntity;
import client.module.Module;
import client.rotation.RotationManager;
import client.util.MinecraftAccessor;
import client.util.SlotManager;
import net.minecraft.block.BlockCache;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class FlyAirPlaceMode extends FlyModeHandler {
   public int count3 = 0;
   public int count2 = 0;

   @Override
   public void updateState() {
      RotationManager rotationManager = Client.getClient().getRotationManager();
      Fly fly = Client.getClient().getModuleManager().fly;
      byte byteValue = -100;
      float factor = 90.0F;
      rotationManager.applyModule(factor, byteValue, (Module)fly);
   }

   @Override
   public void processAction() {
      int count = this.getCount2();
      if (count != -1) {
         short shortValue = -1000;
         SlotManager.applyModule3((Module)Client.getClient().getModuleManager().fly, shortValue, count);
      }
   }

   private int getCount2() {
      for (int index = 36; index < MinecraftAccessor.minecraft.thePlayer.container.itemList2.size(); index++) {
         ItemStack stack = MinecraftAccessor.minecraft.thePlayer.container.getSlot(index).getStack();
         if (stack != null && stack.getItem() instanceof ItemBlock && stack.animationsToGo > 0 && BlockCache.checkItemStack(stack)) {
            return index - 36;
         }
      }

      return -1;
   }

   @Override
   public void processChar() {
   }

   @Override
   public void updateState2() {
      this.count3 = (int)MinecraftAccessor.minecraft.thePlayer.coordOffset9;
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
   }

   @Override
   public void onMove(EventMove event) {
      if (MinecraftAccessor.minecraft.thePlayer.indexOffset41 == 1) {
         this.count3 = this.count3 + this.count2;
         System.out.println(this.count2);
      }

      this.count2 = 0;
      if (event.isState()) {
         this.count2 = 1;
      } else if (event.isState4()) {
         event.setState4(false);
         this.count2 = -1;
      }

      event.setState(true);
   }

   @Override
   public void onClick(EventClick event) {
      event.setState2(true);
      event.setState3(true);
      if (MinecraftAccessor.minecraft.thePlayer.posY - 0.5 < (double)this.count3
         && !MinecraftAccessor.minecraft.thePlayer.onGround
         && MinecraftAccessor.minecraft.thePlayer.motionY < 0.0) {
         double offset = MinecraftAccessor.minecraft.thePlayer.posX + MinecraftAccessor.minecraft.thePlayer.value13 * 2.0;
         double offset2 = MinecraftAccessor.minecraft.thePlayer.posZ + MinecraftAccessor.minecraft.thePlayer.value5 * 2.0;
         BlockPos blockPos = new BlockPos(offset, (double)(this.count3 - 1), offset2);
         if (MinecraftAccessor.minecraft.theWorld.getBlockState(blockPos).getBlock().getMaterial() == Material.coreItemEntry
            && MinecraftAccessor.minecraft.thePlayer.getHeldItem() != null
            && MinecraftAccessor.minecraft.thePlayer.getHeldItem().getItem() instanceof ItemBlock
            && MinecraftAccessor.minecraft
               .playerController
               .onPlayerRightClick(
                  MinecraftAccessor.minecraft.thePlayer,
                  MinecraftAccessor.minecraft.theWorld,
                  MinecraftAccessor.minecraft.thePlayer.getHeldItem(),
                  blockPos,
                  EnumFacing.UP,
                  new Vec3(offset, (double)blockPos.getY(), offset2)
               )) {
            MinecraftAccessor.minecraft.thePlayer.respawnPlayer();
         }
      }
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }

   public FlyAirPlaceMode(String text, Fly fly) {
      super(text, fly);
   }
}

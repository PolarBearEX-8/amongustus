package net.augustus.modules.misc;

import net.minecraft.util.BlockPos;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.EventClick;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.EventMoveInput;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class FlyDebug extends Module implements ClickListener, TickListener, MoveInputListener {
   private boolean wasInWater;
   private int lastWaterLevel;

   public FlyDebug() {
       super("FlyDebug", "FlyDebug", Color.BLACK, Categorys.MISC);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(21);
   }

   public void onClick(EventClick event) {
      if (mc.gameSettings.keyBindUseItem.isKeyDown()) {
         System.out.println("Right Click " + mc.thePlayer.ticksExisted);
      }
   }

   public void onPreMoveInput(EventMoveInput event) {
   }

   public void onEarlyTick() {
      if (Augustus.getInstance().getModuleManager().fly.isToggled() || !mc.thePlayer.onGround) {
         System.out.println("In Air Ticks: " + mc.thePlayer.airTicks + " " + mc.thePlayer.ticksExisted);
         System.out.println("Is in water: " + mc.thePlayer.isInWater() + " " + mc.thePlayer.ticksExisted);
      }

      this.wasInWater = mc.thePlayer.isInWater();
      this.lastWaterLevel = this.getMinIntersectingWaterLevel();
   }

   public void onLateTick() {
      int n = 2147483647;
      if (!Augustus.getInstance().getModuleManager().fly.isToggled() && (n = this.getMinIntersectingWaterLevel()) > 0 && n < 10 && mc.thePlayer.onGround) {
         Augustus.getInstance().getModuleManager().fly.setToggled(true);
         this.sendChatMessage(mc.thePlayer.motionX + " " + mc.thePlayer.motionY + " " + mc.thePlayer.motionZ);
         this.sendChatMessage((double)n);
      }
   }

   public int getMinIntersectingWaterLevel() {
      AxisAlignedBB entityBoundingBox = mc.thePlayer.getEntityBoundingBox();
      int n = MathHelper.floor_double(entityBoundingBox.minX);
      int n2 = MathHelper.floor_double(entityBoundingBox.maxX + 1.0);
      int n3 = MathHelper.floor_double(entityBoundingBox.minY);
      int n4 = MathHelper.floor_double(entityBoundingBox.maxY + 1.0);
      int n5 = MathHelper.floor_double(entityBoundingBox.minZ);
      int n6 = MathHelper.floor_double(entityBoundingBox.maxZ + 1.0);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
      int n7 = 2147483647;

      for (int i = n; i < n2; i++) {
         for (int j = n3; j < n4; j++) {
            for (int k = n5; k < n6; k++) {
               IBlockState blockState = mc.theWorld.getBlockState(pos.set(i, j, k));
               if (blockState.getBlock().getMaterial() == Material.water) {
                  int value = (Integer)blockState.getValue(BlockLiquid.LEVEL);
                  double d = (double)(j + 1);
                  if (value < 8) {
                     d = (double)(j + 1) - (double)value / 8.0;
                  }

                  if (d >= entityBoundingBox.minY && value <= n7) {
                     n7 = value;
                  }
               }
            }
         }
      }

      return n7;
   }

}

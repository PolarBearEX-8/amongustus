package net.augustus.render;

import net.augustus.render.cape.WaveyCapeConfig;
import net.augustus.render.waveycape.CapeVector2f;
import net.augustus.render.waveycape.StickConstraint;
import net.augustus.render.waveycape.StickPoint;
import net.augustus.render.waveycape.StickSimulation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public interface WaveyCapeHolder {

   default void simulate(EntityPlayer player) {
      StickSimulation simulation = this.getSimulation();
      WaveyCapeConfig capeConfig = this.getCapeConfig();
      if (!simulation.points.isEmpty()) {
         ((StickPoint)simulation.points.get(0)).prevPosition.copy(((StickPoint)simulation.points.get(0)).position);
         double d = player.prevPosX - player.posX;
         double d2 = player.prevPosZ - player.posZ;
         float f = player.prevRenderYawOffset + player.renderYawOffset - player.prevRenderYawOffset;
         double d3 = Math.sin((double)(f * 0.017453292F));
         double d4 = -Math.cos((double)(f * 0.017453292F));
         float heightMultiplier = (float)capeConfig.heightMultiplier;
         double d5 = MathHelper.clamp_double((double)((StickPoint)simulation.points.get(0)).position.y - player.posY * (double)heightMultiplier, 0.0, 1.0);
         CapeVector2f position2 = ((StickPoint)simulation.points.get(0)).position;
         position2.x = (float)((double)position2.x + d * d3 + d2 * d4 + d5);
         ((StickPoint)simulation.points.get(0)).position.y = (float)(player.posY * (double)heightMultiplier + (double)(player.isSneaking() ? -4 : 0));
         simulation.simulate();
      }
   }

   default void updateSimulation(EntityPlayer player, int n) {
      StickSimulation simulation = this.getSimulation();
      boolean flag = false;
      if (simulation.points.size() != n) {
         simulation.points.clear();
         simulation.sticks.clear();

         for (int i = 0; i < n; i++) {
            StickPoint stickPoint = new StickPoint();
            stickPoint.position.y = (float)(-i);
            stickPoint.locked = i == 0;
            simulation.points.add(stickPoint);
            if (i > 0) {
               simulation.sticks.add(new StickConstraint((StickPoint)simulation.points.get(i - 1), stickPoint, 1.0F));
            }
         }

         flag = true;
      }

      if (flag) {
         for (int j = 0; j < 10; j++) {
            this.simulate(player);
         }
      }
   }

   StickSimulation getSimulation();

   WaveyCapeConfig getCapeConfig();
}

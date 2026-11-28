package net.augustus.render.cape;

import net.augustus.render.waveycape.CapeVector2f;
import net.augustus.render.waveycape.StickConstraint;
import net.augustus.render.waveycape.StickPoint;
import net.augustus.render.waveycape.StickSimulation;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public class WaveyCapeSimulationManager {
   private WaveyCapeConfig config;
   private static final WaveyCapeSimulationManager waveyCapeSimulationManager = new WaveyCapeSimulationManager();
   private final Map simulationsByPlayer = new HashMap();

   private void simulate(EntityPlayer player, StickSimulation stickSimulation) {
      if (!stickSimulation.points.isEmpty()) {
         ((StickPoint)stickSimulation.points.get(0)).prevPosition.copy(((StickPoint)stickSimulation.points.get(0)).position);
         double d = player.prevPosX - player.posX;
         double d2 = player.prevPosZ - player.posZ;
         float f = player.prevRenderYawOffset + player.renderYawOffset - player.prevRenderYawOffset;
         double d3 = Math.sin((double)(f * (float) (Math.PI / 180.0)));
         double d4 = -Math.cos((double)(f * (float) (Math.PI / 180.0)));
         float heightMultiplier = (float)this.config.heightMultiplier;
         double d5 = clamp((double)((StickPoint)stickSimulation.points.get(0)).position.y - player.posY * (double)heightMultiplier, 0.0, 1.0);
         CapeVector2f position2 = ((StickPoint)stickSimulation.points.get(0)).position;
         position2.x = (float)((double)position2.x + d * d3 + d2 * d4 + d5);
         ((StickPoint)stickSimulation.points.get(0)).position.y = (float)(player.posY * (double)heightMultiplier + (double)(player.isSneaking() ? -4 : 0));
         stickSimulation.simulate();
      }
   }

   private static double clamp(double value, double min, double max) {
      return value < min ? min : (value > max ? max : value);
   }

   public WaveyCapeConfig getConfig() {
      return this.config;
   }

   private WaveyCapeSimulationManager() {
      this.config = new WaveyCapeConfig();
   }

   public void removePlayer(EntityPlayer player) {
      this.simulationsByPlayer.remove(player.getUniqueID());
   }

   public void clearAll() {
      this.simulationsByPlayer.clear();
   }

   public void updateSimulation(EntityPlayer player, int n) {
      StickSimulation orCreateSimulation = this.getOrCreateSimulation(player);
      boolean flag = false;
      if (orCreateSimulation.points.size() != n) {
         orCreateSimulation.points.clear();
         orCreateSimulation.sticks.clear();

         for (int i = 0; i < n; i++) {
            StickPoint stickPoint = new StickPoint();
            stickPoint.position.y = (float)(-i);
            stickPoint.locked = i == 0;
            orCreateSimulation.points.add(stickPoint);
            if (i > 0) {
               orCreateSimulation.sticks.add(new StickConstraint((StickPoint)orCreateSimulation.points.get(i - 1), stickPoint, 1.0F));
            }
         }

         flag = true;
      }

      if (flag) {
         for (int j = 0; j < 10; j++) {
            this.simulate(player, orCreateSimulation);
         }
      }
   }

   public static WaveyCapeSimulationManager getWaveyCapeSimulationManager() {
      return waveyCapeSimulationManager;
   }

   public void setConfig(WaveyCapeConfig waveyCapeConfig) {
      this.config = waveyCapeConfig;
   }

   public void tickPlayer(EntityPlayer player) {
      this.simulate(player, this.getOrCreateSimulation(player));
   }

   public StickSimulation getOrCreateSimulation(EntityPlayer player) {
      UUID uniqueID = player.getUniqueID();
      return (StickSimulation)(this.simulationsByPlayer.computeIfAbsent(uniqueID, obj -> new StickSimulation(this.config)));
   }
}

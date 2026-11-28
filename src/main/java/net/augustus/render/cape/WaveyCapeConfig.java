package net.augustus.render.cape;

public class WaveyCapeConfig {
   public CapeStyle capeStyle;
   public CapeWindMode windMode;
   public int gravity = 25;
   public CapeMovementMode capeMovement;
   public int heightMultiplier = 6;

   public WaveyCapeConfig() {
      this.capeStyle = CapeStyle.SMOOTH;
      this.capeMovement = CapeMovementMode.BASIC_SIMULATION;
      this.windMode = CapeWindMode.NONE;
   }



}

package client.util;

import augustus.anticheat.checks.AntiCheatCheck;
import augustus.anticheat.checks.AntiCheatManager;
import client.Client;
import client.rotation.Rotation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public abstract class InternalStringEntryHandler extends AntiCheatCheck {
   public Rotation rotation4;
   public Rotation rotation8;
   public Rotation rotation3;
   public Vec3 vec4;
   public Rotation rotation;
   public Rotation rotation2;
   public Rotation rotation5 = new Rotation();
   public Vec3 vec3;
   public Rotation rotation6;
   public Vec3 vec2;
   public Rotation rotation7;

   @Override
   public void updateState2() {
      if (super.antiCheatManager.getEntityLivingBase() != null
         && !(MinecraftAccessor.minecraft.thePlayer.getDistanceToEntity(super.antiCheatManager.getEntityLivingBase()) > 7.0F)) {
         EntityLivingBase entityLivingBase = super.antiCheatManager.getEntityLivingBase();
         this.applyEntityLivingBaseByEntityLivingBase(entityLivingBase);
      } else {
         super.antiCheatManager.updateState();
      }
   }

   private void applyEntityLivingBaseByEntityLivingBase(EntityLivingBase livingEntity) {
      Client.getClient().getClass();
   }

   @Override
   public void handlePacket(Packet packet) {
   }

   public abstract void applyRotation(
      Rotation rotation9, Rotation rotation10, Rotation rotation11, Rotation rotation12, Rotation rotation13, Rotation rotation14, Rotation rotation15, Rotation rotation16, Rotation rotation17
   );

   public InternalStringEntryHandler(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.rotation3 = new Rotation();
      this.rotation = new Rotation();
      this.rotation6 = new Rotation();
      this.rotation8 = new Rotation();
      this.rotation2 = new Rotation();
      this.rotation7 = new Rotation();
      this.rotation4 = new Rotation();
      this.vec4 = new Vec3(0.0, 0.0, 0.0);
      this.vec3 = new Vec3(0.0, 0.0, 0.0);
      this.vec2 = new Vec3(0.0, 0.0, 0.0);
   }
}

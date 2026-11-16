package client.util;

import augustus.anticheat.checks.AntiCheatCheck;
import augustus.anticheat.checks.AntiCheatManager;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public abstract class AuxiliaryStringEntryRegistry extends AntiCheatCheck {
   public EntityLivingBase entityLivingBase;
   public int count;
   public final TimeHelper timer;
   public int count3;
   public final HashMap hashMap = new HashMap();
   public int count2;

   private EntityLivingBase getEntityLivingBase() {
      EntityLivingBase livingEntity = null;
      if (MinecraftAccessor.minecraft.objectMouseOver != null
         && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
         && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) {
         livingEntity = (EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit;
      }

      return ChatUtil.getEntityLivingBase2(livingEntity);
   }

   @Override
   public void updateState2() {
   }

   private void updateState5() {
      this.hashMap.clear();

      for (int index = 0; index < 11; index++) {
         this.hashMap.put(index, new ArrayList());
      }
   }

   @Override
   public void clear() {
      this.updateState5();
   }

   @Override
   public void updateState3() {
      this.entityLivingBase = null;
   }

   public abstract void applyHashMap(HashMap map);

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         EntityLivingBase entityLivingBase2 = this.getEntityLivingBase();
         if (entityLivingBase2 != null) {
            this.entityLivingBase = entityLivingBase2;
            this.timer.reset();
         }

         if (this.timer.hasReached(3000L)) {
            this.entityLivingBase = null;
         }

         if (this.entityLivingBase != null) {
            if (MinecraftAccessor.minecraft.objectMouseOver != null
               && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase
               && this.entityLivingBase.hurtTime != this.count) {
               ((ArrayList)this.hashMap.get(this.entityLivingBase.hurtTime)).add(this.count3);
            }

            this.count = this.entityLivingBase.hurtTime;
         }

         this.count3 = 0;
         if (this.hashMap.isEmpty()) {
            this.updateState5();
         }

         for (int index = 0; index < 11; index++) {
            ArrayList list = (ArrayList)this.hashMap.get(index);

            while (list.size() > this.count2) {
               list.remove(0);
            }
         }

         HashMap hashMap2 = this.hashMap;
         this.applyHashMap(hashMap2);
      } else if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getUseEntityAction() == UseEntityAction.ATTACK) {
         this.count3++;
      }
   }

   public AuxiliaryStringEntryRegistry(String text, AntiCheatManager antiCheatManager, int count4) {
      super(text, antiCheatManager);
      this.timer = new TimeHelper();
      this.count = 0;
      this.count2 = count4;
      super.state = true;
   }
}

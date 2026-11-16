package augustus.anticheat.checks;

import client.util.MinecraftAccessor;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import client.util.TimeHelper;
import client.util.UseEntityAction;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AutoClickerP3Check extends AntiCheatCheck {
   public int count;
   public final TimeHelper timer;
   public EntityLivingBase entityLivingBase;
   public double value = 0.0;
   public final ArrayList arrayList = new ArrayList();

   @Override
   public void updateState2() {
   }

   @Override
   public void clear() {
      this.arrayList.clear();
      this.value = 0.0;
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         boolean flag = MinecraftAccessor.minecraft.objectMouseOver != null
            && MinecraftAccessor.minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
            && MinecraftAccessor.minecraft.objectMouseOver.entityHit instanceof EntityLivingBase;
         if (this.timer.hasReached(3000L)) {
            this.entityLivingBase = null;
         }

         if (flag && this.count > 0) {
            this.timer.reset();
            this.entityLivingBase = (EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit;
         }

         if (flag
            && ((EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit).hurtTime == 0
            && ((EntityLivingBase)MinecraftAccessor.minecraft.objectMouseOver.entityHit).indexOffset15 == 0) {
            if (this.entityLivingBase == null) {
               return;
            }

            this.arrayList.add(this.count);

            while (this.arrayList.size() > 20) {
               this.arrayList.remove(0);
            }

            if (this.arrayList.size() == 20) {
               double coordinate = PlayerUtil.getCoordinate(this.arrayList);
               double offset = PlayerUtil.computeValueByList(this.arrayList);
               int count2 = PlayerUtil.computeCountByList(this.arrayList);
               int count3 = PlayerUtil.computeCount(this.arrayList);
               if (coordinate > 0.1 && offset < 0.25 || count2 > 0 || count3 < 2 && coordinate > 0.88) {
                  this.value++;
                  if (offset == 0.0) {
                     this.value++;
                  }

                  if (count2 > 0) {
                     this.value += 2.0;
                  }

                  RayTraceUtil.applyString("AutoClickerP3 vl: " + this.value + " " + offset);
                  this.arrayList.clear();
               } else {
                  this.value = Math.max(this.value - 0.001, 0.0);
               }

               if (this.value > 10.0) {
                  this.value = 0.0;
                  RayTraceUtil.applyString("Banned I think! Reset Violations");
               }
            }
         }

         this.count = 0;
      }

      if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getUseEntityAction() == UseEntityAction.ATTACK) {
         this.count++;
      }
   }

   public AutoClickerP3Check(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.entityLivingBase = null;
      this.timer = new TimeHelper();
      super.state = true;
   }
}

package augustus.anticheat.checks;

import client.ClientGlobals;
import client.util.ArrayListData;
import client.util.MinecraftAccessor;
import client.util.Packet;
import client.util.PlayerUtil;
import client.util.RayTraceUtil;
import client.util.SimulatedPlayer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class SelectionCheck extends AntiCheatCheck implements MinecraftAccessor {
   public ArrayList arrayList2;
   public ArrayList arrayList;

   private static String[] stringArray;

   public static String[] getStringArray() {
      return null;
   }

   @Override
   public void updateState2() {
   }

   public double computeValue5(Entity entity) {
      return entity instanceof EntityLivingBase
         ? (double)Math.abs(
            SimulatedPlayer.getFloatArray(
               entity.posX, entity.posY, entity.posZ, MinecraftAccessor.minecraft.thePlayer.scaleFactor11, MinecraftAccessor.minecraft.thePlayer.scaleFactor27
            )[0]
         )
         : 1000.0;
   }

   private static double computeValue3(Entity entity) {
      return ClientGlobals.moduleManager.killAura.getDistanceToTarget4(entity);
   }

   private static double computeValueByEntity(Entity entity) {
      return ClientGlobals.moduleManager.killAura.getDistanceToTarget5(entity);
   }

   private void applyList(List list, String text) {
      switch (text) {
         case "FOV":
            list.sort(Comparator.comparingDouble(this::computeValue5));
            break;
         case "Health":
            list.sort(Comparator.comparingDouble(SelectionCheck::computeValue9));
            break;
         case "Distance":
            list.sort(Comparator.comparingDouble(SelectionCheck::computeValue7));
            break;
         case "Single":
            list.sort(Comparator.comparingDouble(SelectionCheck::computeValue3));
            break;
         case "UltimateSwitch":
            list.sort(Comparator.comparingDouble(SelectionCheck::computeValueByEntity));
      }
   }

   private static double computeValue7(Entity entity) {
      return (double)MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(entity);
   }

   private static boolean matchesEntity(double offset, Entity entity) {
      return ClientGlobals.moduleManager.killAura.checkEntity(entity, offset);
   }

   @Override
   public void handlePacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity) {
         if (this.arrayList.isEmpty()) {
            for (String text : ClientGlobals.moduleManager.killAura.settingUltimateSwitch.getOptions()) {
               this.arrayList2.add(new ArrayListData(100));
               this.arrayList.add(text);
            }
         }

         int entityId = ((C02PacketUseEntity)packet).getEntityId();
         double offset = Math.max(
            ClientGlobals.moduleManager.killAura.settingPreHitRange.getDouble2(), ClientGlobals.moduleManager.killAura.settingPreAimRange.getDouble2()
         );
         List list = (List)MinecraftAccessor.minecraft.theWorld.loadedEntityList.stream().filter(entity -> SelectionCheck.matchesEntity(offset, (Entity)entity)).collect(Collectors.toList());
         if (list.size() == 1 && ((Entity)list.get(0)).getEntityId() == entityId) {
            return;
         }

         for (int index = 0; index < this.arrayList.size(); index++) {
            String text2 = (String)this.arrayList.get(index);
            this.applyList(list, text2);
            if (!list.isEmpty()) {
               if (((Entity)list.get(0)).getEntityId() == entityId) {
                  ArrayListData arrayListData = (ArrayListData)this.arrayList2.get(index);
                  double offset2 = 1.0;
                  arrayListData.processAction(offset2);
               } else {
                  ArrayListData arrayListData2 = (ArrayListData)this.arrayList2.get(index);
                  double offset3 = 0.0;
                  arrayListData2.processAction(offset3);
               }
            } else {
               ArrayListData arrayListData3 = (ArrayListData)this.arrayList2.get(index);
               double offset4 = 0.0;
               arrayListData3.processAction(offset4);
            }
         }

         StringBuilder builder = new StringBuilder();

         for (int index2 = 0; index2 < this.arrayList.size(); index2++) {
            double offset5 = ((ArrayListData)this.arrayList2.get(index2)).getDouble7() * 100.0;
            builder.append((String)this.arrayList.get(index2)).append(" - ").append(PlayerUtil.getDouble5(offset5, 1)).append(" ");
         }

         RayTraceUtil.applyString(builder.toString());
      }
   }

   private static double computeValue9(Entity entity) {
      return (double)((EntityLivingBase)entity).getFloat2();
   }

   public SelectionCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.arrayList2 = new ArrayList();
      this.arrayList = new ArrayList();
   }
}

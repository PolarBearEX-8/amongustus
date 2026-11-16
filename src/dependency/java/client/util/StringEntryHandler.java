package client.util;

import client.Client;
import client.event.EventBusManager;
import client.event.TickListener;
import client.network.NetworkPlayerInfo;
import client.setting.ItemListSetting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.GoldenNugget;

public class StringEntryHandler extends AuxiliaryStringEntry implements TickListener {
   public final ItemListSetting itemListSetting = new ItemListSetting(Client.getClient().getModuleManager().clientSettings, "AlertNames", new ArrayList());
   public final GoldenNugget goldenNugget = new GoldenNugget(Client.getClient().getModuleManager().clientSettings, "AlertNumbers", new ArrayList());
   public boolean enabled;
   public final HashSet hashSet;
   public final HashMap hashMap = new HashMap();

   public void updateState2() {
      long value = 0x2ee62f142fL;
      byte byteValue = 85;
      String text = "§7" + this.getString() + " [§cadd§7] [§2Name§7] ([§2Distance§7])";
      this.applyText(text);
   }

   @Override
   public void updateState() {
      String text = "§c" + this.getString() + "§7 (Alerts players nearby)";
      this.applyText(text);
   }

   public void updateState5() {
      long value = 0x32ecfbea72L;
      String text = "§7" + this.getString() + " [§cadd/remove/clear/list§7]";
      this.applyText(text);
   }

   private void updateState6() {
      for (int index = 0; index < this.itemListSetting.getCountAsIndex() && index < this.goldenNugget.getCountAsIndex(); index++) {
         String text = (String)this.itemListSetting.resolveTarget(index);
         Double offset = (Double)this.goldenNugget.resolveTarget(index);
         if (text != null && offset != null) {
            this.hashMap.put(text, offset);
         }
      }

      this.enabled = false;
   }

   private static boolean matchesHashSet(HashSet set, String text) {
      boolean flag = set.contains(text);
      if (!flag) {
         RayTraceUtil.applyString("§aAll-clear: " + text + " is no longer nearby");
      }

      return !flag;
   }

   private void applyText(String text, double offset) {
      if (!this.hashMap.containsKey(text)) {
         this.hashMap.put(text, offset);
         this.itemListSetting.applyTarget(text);
         GoldenNugget goldenNugget2 = this.goldenNugget;
         Double offset2 = offset;
         goldenNugget2.applyTarget(offset2);
      }
   }

   private void applyTextByText(String text) {
      Double offset = (Double)this.hashMap.get(text);
      if (offset != null) {
         this.hashMap.remove(text);
         this.itemListSetting.matchesTarget(text);
         this.goldenNugget.matchesTarget(offset);
      }
   }

   public void updateState3() {
      short shortValue = 4392;
      char character = '\udc61';
      short shortValue2 = 21005;
      String text = "§7" + this.getString() + " [§cremove§7] [§2Name§7]";
      this.applyText(text);
   }

   public boolean containsKey(String text) {
      return this.hashMap.containsKey(text);
   }

   private void updateState4() {
      this.hashMap.clear();
      this.itemListSetting.updateState7();
      this.goldenNugget.updateState7();
   }

   @Override
   public void printStackTrace(String[] textArray, String text) {
      if (textArray.length < 2) {
         this.updateState5();
      } else {
         if (textArray[1].equalsIgnoreCase("add")) {
            if (textArray.length == 3) {
               String text2 = textArray[2];
               double offset = -1.0;
               String text3 = text2;
               this.applyText(text3, offset);
               RayTraceUtil.applyString("Added " + textArray[2] + " successfully");
            } else if (textArray.length == 4) {
               double offset2 = -1.0;

               try {
                  offset2 = Double.parseDouble(textArray[3]);
               } catch (Exception exception) {
               }

               String text4 = textArray[2];
               this.applyText(text4, offset2);
               RayTraceUtil.applyString("Added " + textArray[2] + " successfully");
            } else {
               this.updateState2();
            }
         } else if (textArray[1].equalsIgnoreCase("remove")) {
            if (textArray.length != 3) {
               this.updateState3();
               return;
            }

            if ((Double)this.hashMap.get(textArray[2]) == null) {
               RayTraceUtil.applyString("Unable to find: " + textArray[2]);
               return;
            }

            RayTraceUtil.applyString("Removed " + textArray[2] + " successfully");
            String text5 = textArray[2];
            String text6 = text5;
            this.applyTextByText(text6);
         } else if (textArray[1].equalsIgnoreCase("clear")) {
            if (textArray.length != 2) {
               this.updateState5();
               return;
            }

            RayTraceUtil.applyString("Cleared " + this.hashMap.size() + " alerts");
            this.updateState4();
         } else if (textArray[1].equalsIgnoreCase("list")) {
            if (textArray.length != 2) {
               this.updateState5();
               return;
            }

            if (this.hashMap.isEmpty()) {
               RayTraceUtil.applyString("No names to alert found");
               return;
            }

            RayTraceUtil.applyString("Alerting names: ");

            for (String text7 : (Iterable<String>)(Iterable<?>)this.hashMap.keySet()) {
               Double offset3 = (Double)this.hashMap.get(text7);
               RayTraceUtil.applyString(text7 + (offset3 > 0.0 ? "; " + DecimalFormatter.formatDecimal(offset3, 1) : ""));
            }
         } else {
            this.updateState5();
         }
      }
   }

   @Override
   public void onPreTick() {
      if (this.enabled) {
         this.updateState6();
      }

      if (!this.hashMap.isEmpty()) {
         HashSet set = new HashSet();

         for (EntityPlayer player : (Iterable<EntityPlayer>)(Iterable)(MinecraftAccessor.minecraft.theWorld.itemList7)) {
            if (!(player instanceof EntityPlayerSP) && player.getGameProfile() != null) {
               String name = player.getGameProfile().getName();
               Double offset = (Double)this.hashMap.get(name);
               if (offset != null) {
                  if (offset < 0.0) {
                     if (!this.hashSet.contains(name)) {
                        RayTraceUtil.applyString("§cALERT: Player " + name + " is nearby!");
                        this.hashSet.add(name);
                     }

                     set.add(name);
                  } else if ((double)MinecraftAccessor.minecraft.thePlayer.getDistanceSqToEntity(player) < offset) {
                     if (!this.hashSet.contains(name)) {
                        RayTraceUtil.applyString("§cALERT: Player " + name + " is nearby!");
                        this.hashSet.add(name);
                     }

                     set.add(name);
                  }
               }
            }
         }

         Iterator iterator = MinecraftAccessor.minecraft.getNetHandlerPlayClient().getCollection().iterator();

         while (iterator.hasNext()) {
            String name2 = ((NetworkPlayerInfo)iterator.next()).getGameProfile().getName();
            if (!name2.equals(MinecraftAccessor.minecraft.session.getText1())) {
               Double offset2 = (Double)this.hashMap.get(name2);
               if (offset2 != null && !(offset2 >= 0.0)) {
                  if (!this.hashSet.contains(name2)) {
                     RayTraceUtil.applyString("§cALERT: Player " + name2 + " is nearby!");
                     this.hashSet.add(name2);
                  }

                  set.add(name2);
               }
            }
         }

         this.hashSet.removeIf(name -> matchesHashSet(set, (String)name));
      }
   }

   public StringEntryHandler() {
      super("alert");
      this.hashSet = new HashSet();
      this.enabled = true;
      EventBusManager.getDietrichEvents2().subscribe(40, this);
   }
}

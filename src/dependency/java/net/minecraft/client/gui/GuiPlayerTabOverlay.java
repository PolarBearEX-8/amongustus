package net.minecraft.client.gui;

import augustus.modules.misc.MidClick;
import augustus.modules.misc.MurdererFinder;
import client.ClientGlobals;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Module;
import client.network.NetworkPlayerInfo;
import client.setting.ClientSettingsBlackType;
import client.setting.TabListCapeType;
import client.setting.TabListIntegerType;
import client.setting.WorldSettingsGameType;
import client.util.ScoreboardData;
import client.util.SecondaryComparatorAdapter;
import client.util.StandardResourceLocationEntry;
import client.util.ThePlayerNameManager;
import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.NetHandlerPlayClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class GuiPlayerTabOverlay extends TexturesGuiIconsPngManager {
   public static final Ordering ordering = Ordering.from(new SecondaryComparatorAdapter(null));
   public final Minecraft mc;
   public final StandardResourceLocationEntry standardResourceLocationEntry;
   public IChatComponent iChatComponent2;
   public IChatComponent iChatComponent;
   public long timestamp;
   public boolean enabled;

   public static String getText(NetworkPlayerInfo networkPlayerInfo) {
      String text = networkPlayerInfo.getChatComponent() != null
         ? networkPlayerInfo.getChatComponent().getString2()
         : ScorePlayerTeam.getText(networkPlayerInfo.getScorePlayerTeam(), networkPlayerInfo.getGameProfile().getName());
      MidClick midClick = ClientGlobals.moduleManager.midClick;
      String name = networkPlayerInfo.getGameProfile().getName();
      if (midClick.checkString(name, true) && ((Module)ClientGlobals.moduleManager.midClick).isToggled()) {
         String name2 = networkPlayerInfo.getGameProfile().getName();
         text = text.replaceAll(name2, (ClientGlobals.moduleManager.midClick.settingSimple.isEnabled() ? "§l" : "§a§l") + name2);
         if (!ClientGlobals.moduleManager.midClick.settingSimple.isEnabled()) {
            text = "§7[§2§lFriend§7]§9 " + text;
         }
      }

      MurdererFinder murdererFinder = ClientGlobals.moduleManager.murdererFinder;
      String name3 = networkPlayerInfo.getGameProfile().getName();
      String text2 = murdererFinder.checkString(name3) ? " §7[§4Murderer§7]§9" : "";
      return text + text2;
   }

   public void applyNetworkPlayerInfo(int count, int count2, int count3, NetworkPlayerInfo networkPlayerInfo) {
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      if (((Module)ClientGlobals.moduleManager.tabList).isToggled() && ClientGlobals.moduleManager.tabList.settingPingNumber.isEnabled()) {
         int rgb = Color.green.getRGB();
         if (networkPlayerInfo.getIndexOffset3() < 70) {
            rgb = Color.green.getRGB();
         } else if (networkPlayerInfo.getIndexOffset3() < 150) {
            rgb = Color.yellow.getRGB();
         } else if (networkPlayerInfo.getIndexOffset3() < 250) {
            rgb = Color.orange.getRGB();
         } else if (networkPlayerInfo.getIndexOffset3() >= 250) {
            rgb = Color.red.getRGB();
         }

         this.mc.fontRendererObj.getIndex(networkPlayerInfo.getIndexOffset3() + "", count2 + count - this.mc.fontRendererObj.getIndex(networkPlayerInfo.getIndexOffset3() + ""), count3, rgb);
      } else {
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation2);
         byte byteValue = 0;
         byte byteValue2;
         if (networkPlayerInfo.getIndexOffset3() < 0) {
            byteValue2 = 5;
         } else if (networkPlayerInfo.getIndexOffset3() < 150) {
            byteValue2 = 0;
         } else if (networkPlayerInfo.getIndexOffset3() < 300) {
            byteValue2 = 1;
         } else if (networkPlayerInfo.getIndexOffset3() < 600) {
            byteValue2 = 2;
         } else if (networkPlayerInfo.getIndexOffset3() < 1000) {
            byteValue2 = 3;
         } else {
            byteValue2 = 4;
         }

         this.factor += 100.0F;
         this.processAction(count2 + count - 11, count3, 0 + byteValue * 10, 176 + byteValue2 * 8, 10, 8);
         this.factor -= 100.0F;
      }
   }

   public IChatComponent getChatComponent2() {
      return this.iChatComponent;
   }

   public void setIChatComponent(IChatComponent iChatComponent3) {
      this.iChatComponent = iChatComponent3;
   }

   public void applyThePlayerNameManager(int count, ThePlayerNameManager thePlayerNameManager, ScoreboardData scoreboardData) {
      NetHandlerPlayClient netHandlerPlayClient = this.mc.thePlayer.netHandlerPlayClient;
      List list = ordering.sortedCopy(netHandlerPlayClient.getCollection());
      int count2 = 0;
      int count3 = 0;

      for (Object networkPlayerInfoObj : list) {
         NetworkPlayerInfo networkPlayerInfo = (NetworkPlayerInfo)networkPlayerInfoObj;
         int index = this.mc.fontRendererObj.getIndex(getText(networkPlayerInfo) + " " + networkPlayerInfo.getIndexOffset3());
         count2 = Math.max(count2, index);
         if (scoreboardData != null && scoreboardData.getTabListIntegerType() != TabListIntegerType.HEARTS) {
            int index2 = this.mc.fontRendererObj.getIndex(" " + thePlayerNameManager.getComparatorEntry(networkPlayerInfo.getGameProfile().getName(), scoreboardData).getIndex());
            count3 = Math.max(count3, index2);
         }
      }

      List list2 = list.subList(0, Math.min(list.size(), 80));
      int size = list2.size();
      int count4 = size;

      int count5;
      for (count5 = 1; count4 > 20; count4 = (size + count5 - 1) / count5) {
         count5++;
      }

      boolean flag = this.mc.isGamePaused() || this.mc.getNetHandlerPlayClient().getNetworkManager().canExecute();
      if (scoreboardData != null) {
         if (scoreboardData.getTabListIntegerType() == TabListIntegerType.HEARTS) {
            count3 = 90;
         }
      } else {
         count3 = 0;
      }

      int count6 = Math.min(count5 * ((flag ? 9 : 0) + count2 + count3 + 13), count - 50) / count5;
      int count7 = count / 2 - (count6 * count5 + (count5 - 1) * 5) / 2;
      int count8 = 10;
      int count9 = count6 * count5 + (count5 - 1) * 5;
      List list3 = null;
      List list4 = null;
      if (this.iChatComponent != null) {
         list3 = this.mc.fontRendererObj.getList(this.iChatComponent.getString2(), count - 50);

         for (Object textObj : list3) {
            String text = (String)textObj;
            count9 = Math.max(count9, this.mc.fontRendererObj.getIndex(text));
         }
      }

      if (this.iChatComponent2 != null) {
         list4 = this.mc.fontRendererObj.getList(this.iChatComponent2.getString2(), count - 50);

         for (Object text2Obj : list4) {
            String text2 = (String)text2Obj;
            count9 = Math.max(count9, this.mc.fontRendererObj.getIndex(text2));
         }
      }

      if (list3 != null) {
         processAction(
            count / 2 - count9 / 2 - 1, count8 - 1, count / 2 + count9 / 2 + 1, count8 + list3.size() * this.mc.fontRendererObj.indexOffset, Integer.MIN_VALUE
         );

         for (Object text3Obj : list3) {
            String text3 = (String)text3Obj;
            int index3 = this.mc.fontRendererObj.getIndex(text3);
            this.mc.fontRendererObj.getIndex2(text3, (float)(count / 2 - index3 / 2), (float)count8, -1);
            count8 += this.mc.fontRendererObj.indexOffset;
         }

         count8++;
      }

      processAction(count / 2 - count9 / 2 - 1, count8 - 1, count / 2 + count9 / 2 + 1, count8 + count4 * 9, Integer.MIN_VALUE);

      for (int index4 = 0; index4 < size; index4++) {
         int count10 = index4 / count4;
         int count11 = index4 % count4;
         int count12 = count7 + count10 * count6 + count10 * 5;
         int count13 = count8 + count11 * 9;
         processAction(count12, count13, count12 + count6, count13 + 8, 553648127);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.applyState14();
         GlStateManager.disableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         if (index4 < list2.size()) {
            NetworkPlayerInfo networkPlayerInfo2 = (NetworkPlayerInfo)list2.get(index4);
            Object text4 = getText(networkPlayerInfo2);
            GameProfile gameProfile = networkPlayerInfo2.getGameProfile();
            if (flag) {
               EntityPlayer playerEntityByUUID = this.mc.theWorld.getPlayerEntityByUUID(gameProfile.getId());
               boolean flag2 = playerEntityByUUID != null
                  && playerEntityByUUID.checkTabListCapeType(TabListCapeType.CAPE)
                  && (gameProfile.getName().equals("Dinnerbone") || gameProfile.getName().equals("Grumm"));
               this.mc.getTextureManager().applyResourceLocation3(networkPlayerInfo2.getResourceLocation());
               int count14 = 8 + (flag2 ? 8 : 0);
               int count15 = 8 * (flag2 ? -1 : 1);
               TexturesGuiIconsPngManager.processAction(count12, count13, 8.0F, (float)count14, 8, count15, 8, 8, 64.0F, 64.0F);
               if (playerEntityByUUID != null && playerEntityByUUID.checkTabListCapeType(TabListCapeType.HAT)) {
                  int count16 = 8 + (flag2 ? 8 : 0);
                  int count17 = 8 * (flag2 ? -1 : 1);
                  TexturesGuiIconsPngManager.processAction(count12, count13, 40.0F, (float)count16, 8, count17, 8, 8, 64.0F, 64.0F);
               }

               count12 += 9;
            }

            if (networkPlayerInfo2.getWorldSettingsGameType() == WorldSettingsGameType.SPECTATOR) {
               String text5 = ClientSettingsBlackType.ITALIC + (String)text4;
               this.mc.fontRendererObj.getIndex2(text5, (float)count12, (float)count13, -1862270977);
            } else {
               this.mc.fontRendererObj.getIndex2((String)text4, (float)count12, (float)count13, -1);
            }

            if (scoreboardData != null && networkPlayerInfo2.getWorldSettingsGameType() != WorldSettingsGameType.SPECTATOR) {
               int count18 = count12 + count2 + 1;
               int count19 = count18 + count3;
               if (count19 - count18 > 5) {
                  this.updateCustomItemEntry(scoreboardData, count13, gameProfile.getName(), count18, count19, networkPlayerInfo2);
               }
            }

            this.applyNetworkPlayerInfo(count6, count12 - (flag ? 9 : 0), count13, networkPlayerInfo2);
         }
      }

      if (list4 != null) {
         int count20 = count8 + count4 * 9 + 1;
         processAction(
            count / 2 - count9 / 2 - 1, count20 - 1, count / 2 + count9 / 2 + 1, count20 + list4.size() * this.mc.fontRendererObj.indexOffset, Integer.MIN_VALUE
         );

         for (Object text6Obj : list4) {
            String text6 = (String)text6Obj;
            int index5 = this.mc.fontRendererObj.getIndex(text6);
            this.mc.fontRendererObj.getIndex2(text6, (float)(count / 2 - index5 / 2), (float)count20, -1);
            count20 += this.mc.fontRendererObj.indexOffset;
         }
      }
   }

   public void setIChatComponent2(IChatComponent iChatComponent3) {
      this.iChatComponent2 = iChatComponent3;
   }

   public void updateCustomItemEntry(ScoreboardData scoreboardData, int count, String text, int count2, int count3, NetworkPlayerInfo networkPlayerInfo) {
      int index = scoreboardData.getThePlayerNameManager().getComparatorEntry(text, scoreboardData).getIndex();
      if (scoreboardData.getTabListIntegerType() == TabListIntegerType.HEARTS) {
         this.mc.getTextureManager().applyResourceLocation3(resourceLocation2);
         if (this.timestamp == networkPlayerInfo.getEpochMillis7()) {
            if (index < networkPlayerInfo.getIndexOffset()) {
               networkPlayerInfo.setEpochMillis(Minecraft.getTimestamp());
               networkPlayerInfo.setEpochMillis5((long)(this.standardResourceLocationEntry.getIndex() + 20));
            } else if (index > networkPlayerInfo.getIndexOffset()) {
               networkPlayerInfo.setEpochMillis(Minecraft.getTimestamp());
               networkPlayerInfo.setEpochMillis5((long)(this.standardResourceLocationEntry.getIndex() + 10));
            }
         }

         if (Minecraft.getTimestamp() - networkPlayerInfo.getEpochMillis() > 1000L || this.timestamp != networkPlayerInfo.getEpochMillis7()) {
            networkPlayerInfo.setIndexOffset(index);
            networkPlayerInfo.setIndexOffset1(index);
            networkPlayerInfo.setEpochMillis(Minecraft.getTimestamp());
         }

         networkPlayerInfo.setEpochMillis7(this.timestamp);
         networkPlayerInfo.setIndexOffset(index);
         int count4 = MathHelper.computeCount((float)Math.max(index, networkPlayerInfo.getIndexOffset1()) / 2.0F);
         int count5 = Math.max(MathHelper.computeCount((float)(index / 2)), Math.max(MathHelper.computeCount((float)(networkPlayerInfo.getIndexOffset1() / 2)), 10));
         boolean flag = networkPlayerInfo.getEpochMillis5() > (long)this.standardResourceLocationEntry.getIndex()
            && (networkPlayerInfo.getEpochMillis5() - (long)this.standardResourceLocationEntry.getIndex()) / 3L % 2L == 1L;
         if (count4 > 0) {
            float factor = Math.min((float)(count3 - count2 - 4) / (float)count5, 9.0F);
            if (factor > 3.0F) {
               for (int index2 = count4; index2 < count5; index2++) {
                  this.applyFloat((float)count2 + (float)index2 * factor, (float)count, flag ? 25 : 16, 0, 9, 9);
               }

               for (int index3 = 0; index3 < count4; index3++) {
                  this.applyFloat((float)count2 + (float)index3 * factor, (float)count, flag ? 25 : 16, 0, 9, 9);
                  if (flag) {
                     if (index3 * 2 + 1 < networkPlayerInfo.getIndexOffset1()) {
                        this.applyFloat((float)count2 + (float)index3 * factor, (float)count, 70, 0, 9, 9);
                     }

                     if (index3 * 2 + 1 == networkPlayerInfo.getIndexOffset1()) {
                        this.applyFloat((float)count2 + (float)index3 * factor, (float)count, 79, 0, 9, 9);
                     }
                  }

                  if (index3 * 2 + 1 < index) {
                     this.applyFloat((float)count2 + (float)index3 * factor, (float)count, index3 >= 10 ? 160 : 52, 0, 9, 9);
                  }

                  if (index3 * 2 + 1 == index) {
                     this.applyFloat((float)count2 + (float)index3 * factor, (float)count, index3 >= 10 ? 169 : 61, 0, 9, 9);
                  }
               }
            } else {
               float factor2 = MathHelper.clamp_float((float)index / 20.0F, 0.0F, 1.0F);
               int count6 = (int)((1.0F - factor2) * 255.0F) << 16 | (int)(factor2 * 255.0F) << 8;
               String text2 = "" + (float)index / 2.0F;
               if (count3 - this.mc.fontRendererObj.getIndex(text2 + "hp") >= count2) {
                  text2 = text2 + "hp";
               }

               this.mc.fontRendererObj.getIndex2(text2, (float)((count3 + count2) / 2 - this.mc.fontRendererObj.getIndex(text2) / 2), (float)count, count6);
            }
         }
      } else {
         String text3 = ClientSettingsBlackType.YELLOW + "" + index;
         this.mc.fontRendererObj.getIndex2(text3, (float)(count3 - this.mc.fontRendererObj.getIndex(text3)), (float)count, 16777215);
      }
   }

   public IChatComponent getChatComponent() {
      return this.iChatComponent2;
   }

   public void renderElement() {
      this.iChatComponent = null;
      this.iChatComponent2 = null;
   }

   public void setEnabled(boolean enabled2) {
      if (enabled2 && !this.enabled) {
         this.timestamp = Minecraft.getTimestamp();
      }

      this.enabled = enabled2;
   }

   public GuiPlayerTabOverlay(Minecraft mc2, StandardResourceLocationEntry standardResourceLocationEntry2) {
      this.mc = mc2;
      this.standardResourceLocationEntry = standardResourceLocationEntry2;
   }
}

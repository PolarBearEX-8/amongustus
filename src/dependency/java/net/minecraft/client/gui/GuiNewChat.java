package net.minecraft.client.gui;

import client.Client;
import client.ClientGlobals;
import client.gui.TexturesGuiIconsPngManager;
import client.module.Module;
import client.render.RenderPass;
import client.render.RenderUtils;
import client.setting.GameSettingsFullType;
import client.setting.ShaderOptions;
import client.util.FovWriter;
import client.util.InternalIChatComponentEntry;
import client.util.MovementHandler;
import client.util.RandomUtil;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiNewChat extends TexturesGuiIconsPngManager {
   public static final Logger logger = LogManager.getLogger();
   public final Minecraft mc;
   public final List list2 = Lists.newArrayList();
   public final List list = Lists.newArrayList();
   public final List list3 = Lists.newArrayList();
   public int count;
   public boolean enabled;

   public int getCount2() {
      return this.count;
   }

   public List getList3() {
      return this.list3;
   }

   public void applyIndex(int count2) {
      this.count += count2;
      int size = this.list3.size();
      if (this.count > size - this.getCountAsIndex()) {
         this.count = size - this.getCountAsIndex();
      }

      if (this.count <= 0) {
         this.count = 0;
         this.enabled = false;
      }
   }

   public static int computeCountByFloat(float factor) {
      return MathHelper.floor_float(factor * (float)280 + (float)40);
   }

   public void renderElement() {
      if (ClientGlobals.moduleManager == null
         || ClientGlobals.moduleManager.chat == null
         || ClientGlobals.moduleManager.chat.settingNoChatLimit == null
         || !((Module)ClientGlobals.moduleManager.chat).isToggled()
         || !ClientGlobals.moduleManager.chat.settingNoChatLimit.isEnabled()) {
         this.list3.clear();
         this.list.clear();
         this.list2.clear();
      }
   }

   public IChatComponent resolveIChatComponent(int count2, int count3) {
      if (!this.isActive()) {
         return null;
      } else {
         int scaleFactor = new ScaledResolution(this.mc).getScaleFactor();
         float factor = this.getFactor();
         int count4 = count2 / scaleFactor - 3;
         int count5 = count3 / scaleFactor - 27;
         int count6 = MathHelper.floor_float((float)count4 / factor);
         int count7 = MathHelper.floor_float((float)count5 / factor);
         if (count6 >= 0 && count7 >= 0) {
            int count8 = Math.min(this.getCountAsIndex(), this.list3.size());
            if (count6 <= MathHelper.floor_float((float)this.getCount3() / this.getFactor()) && count7 < this.mc.fontRendererObj.indexOffset * count8 + count8) {
               int count9 = count7 / this.mc.fontRendererObj.indexOffset + this.count;
               if (count9 >= 0 && count9 < this.list3.size()) {
                  InternalIChatComponentEntry internalIChatComponentEntry = (InternalIChatComponentEntry)this.list3.get(count9);
                  int count10 = 0;

                  for (IChatComponent iChatComponent : (java.lang.Iterable<IChatComponent>) (internalIChatComponentEntry.getIChatComponent())) {
                     if (iChatComponent instanceof ChatComponentText) {
                        count10 += this.mc.fontRendererObj.getIndex(FovWriter.resolveText(((ChatComponentText)iChatComponent).getLabelTextAsText(), false));
                        if (count10 > count6) {
                           return iChatComponent;
                        }
                     }
                  }
               }

               return null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public float getFactor() {
      return this.mc.gameSettings.scaleFactor29;
   }

   public void applyFlag(boolean flag, int count2, float factor, int count3, int count4, int count5) {
      int count6 = -1;
      int count7 = 0;
      int count8 = 0;
      int count9 = 0;

      for (int index = 0; index + this.count < this.list3.size() && index < count3; index++) {
         InternalIChatComponentEntry internalIChatComponentEntry = (InternalIChatComponentEntry)this.list3.get(index + this.count);
         if (internalIChatComponentEntry != null) {
            count6++;
            if (ClientGlobals.moduleManager.antiStrike.canExecute12()
               && !internalIChatComponentEntry.getIChatComponent().getString().startsWith("§6[§9" + Client.getClient().getString4() + "§6] ")) {
               count6--;
            } else {
               int count10 = count2 - internalIChatComponentEntry.getCount();
               if (count10 < 200 || flag) {
                  int count11;
                  if (flag) {
                     count11 = 255;
                  } else {
                     double offset = (double)count10 / 200.0;
                     double doubleItem = MathHelper.getDouble7((1.0 - offset) * 10.0, 0.0, 1.0);
                     double offset2 = doubleItem * doubleItem;
                     count11 = (int)(255.0 * offset2);
                  }

                  if ((int)((float)count11 * factor) > 3) {
                     int count12 = -count6 * 9;
                     if (index == 0) {
                        count9 = count12;
                     }

                     count7 = 0 + count4 + 4;
                     count8 = count12 - 9;
                  }
               }
            }
         }
      }

      if (count7 != 0) {
         RenderUtils.processAction(0, count8, count7, count9, count5);
      }
   }

   public static int computeCount(float factor) {
      return MathHelper.floor_float(factor * (float)160 + (float)20);
   }

   public int getCount() {
      return computeCount(this.isActive() ? this.mc.gameSettings.scaleFactor41 : this.mc.gameSettings.scaleFactor8);
   }

   public List getList2() {
      return this.list2;
   }

   private void applyRenderPass(boolean flag, float factor, int count2, int count3, int count4, RenderPass renderPass) {
      int index = this.mc.ingameGUI.getIndex();
      ShaderOptions settingBackGroundColor = ClientGlobals.moduleManager.chat.settingBackGroundColor2;
      int index2 = RandomUtil.getIndex(renderPass, count4);
      this.applyFlag(flag, index, factor, count2, count3, settingBackGroundColor.getIndex(renderPass, index2));
   }

   public int getCount3() {
      return computeCountByFloat(this.mc.gameSettings.scaleFactor137);
   }

   public void renderElement2() {
      this.list3.clear();
      this.renderElement3();

      for (int index = this.list.size() - 1; index >= 0; index--) {
         InternalIChatComponentEntry internalIChatComponentEntry = (InternalIChatComponentEntry)this.list.get(index);
         this.applyIChatComponent(internalIChatComponentEntry.getIChatComponent(), internalIChatComponentEntry.getCount2(), internalIChatComponentEntry.getCount(), true);
      }
   }

   public synchronized void applyIChatComponent(IChatComponent iChatComponent) {
      this.applyIChatComponent(iChatComponent, 0);
   }

   public void renderElement3() {
      this.count = 0;
      this.enabled = false;
   }

   public boolean isActive() {
      return this.mc.currentScreen instanceof GuiChat;
   }

   public int getCountAsIndex() {
      return this.getCount() / 9;
   }

   public void processAction(int count2, boolean flag, int count3) {
      int count4 = count3;
      if (this.mc.gameSettings.gameSettingsFullType != GameSettingsFullType.HIDDEN) {
         int countAsIndex = this.getCountAsIndex();
         int count5 = 0;
         int size = this.list3.size();
         float factor = this.mc.gameSettings.scaleFactor102 * 0.9F + 0.1F;
         if (size > 0) {
            boolean flag2;
            if (this.isActive()) {
               flag2 = true;
            } else {
               flag2 = false;
            }

            float factor2 = this.getFactor();
            int count6 = MathHelper.computeCount((float)this.getCount3() / factor2);
            GlStateManager.pushMatrix();
            GlStateManager.translate(2.0F, 20.0F, 0.0F);
            GlStateManager.translate3f3(factor2, factor2, 1.0F);
            if (flag && ((Module)ClientGlobals.moduleManager.chat).isToggled()) {
               MovementHandler.processBoolean(
                  ClientGlobals.moduleManager.chat.settingBackGroundColor2.canExecute(), ClientGlobals.moduleManager.chat.settingBackGroundColor2.canExecute2()
               );
               if (count3 == Integer.MIN_VALUE) {
                  count4 = 2130706432;
               }

               int resolvedColor = count4;
               MovementHandler.applyRunnableTask(false, renderPass -> this.applyRenderPass(flag2, factor, countAsIndex, count6, resolvedColor, renderPass));
            } else if (flag) {
               this.applyFlag(flag2, this.mc.ingameGUI.getIndex(), factor, countAsIndex, count6, 2130706432);
            }

            int count7 = -1;

            for (int index = 0; index + this.count < this.list3.size() && index < countAsIndex; index++) {
               InternalIChatComponentEntry internalIChatComponentEntry = (InternalIChatComponentEntry)this.list3.get(index + this.count);
               if (internalIChatComponentEntry != null) {
                  count7++;
                  if (ClientGlobals.moduleManager.antiStrike.canExecute12()
                     && !internalIChatComponentEntry.getIChatComponent().getString().startsWith("§6[§9" + Client.getClient().getString4() + "§6] ")) {
                     count7--;
                  } else {
                     int count8 = count2 - internalIChatComponentEntry.getCount();
                     if (count8 < 200 || flag2) {
                        int count9;
                        if (flag2) {
                           count9 = 255;
                        } else {
                           double offset = (double)count8 / 200.0;
                           double doubleItem = MathHelper.getDouble7((1.0 - offset) * 10.0, 0.0, 1.0);
                           double offset2 = doubleItem * doubleItem;
                           count9 = (int)(255.0 * offset2);
                        }

                        count5++;
                        int count10 = (int)((float)count9 * factor);
                        if (count10 > 3) {
                           int count11 = -count7 * 9;
                           String string = internalIChatComponentEntry.getIChatComponent().getString2();
                           GlStateManager.disableBlend();
                           this.mc.fontRendererObj.getIndex(string, 0.0F, (float)(count11 - 8), 16777215 + (count10 << 24), true, false, false);
                           GlStateManager.applyState10();
                           GlStateManager.enableBlend();
                        }
                     }
                  }
               }
            }

            if (flag2) {
               int indexOffset = this.mc.fontRendererObj.indexOffset;
               GlStateManager.translate(-3.0F, 0.0F, 0.0F);
               int count12 = size * indexOffset + size;
               int count13 = count5 * indexOffset + count5;
               int count14 = this.count * count13 / size;
               int count15 = count13 * count13 / count12;
               if (count12 != count13) {
                  int count16 = count14 > 0 ? 170 : 96;
                  int count17 = this.enabled ? 13382451 : 3355562;
                  if (flag) {
                     processAction(0, -count14, 2, -count14 - count15, count17 + (count16 << 24));
                     processAction(2, -count14, 1, -count14 - count15, 13421772 + (count16 << 24));
                  }
               }
            }

            GlStateManager.popMatrix();
         }
      }
   }

   public void applyText(String text) {
      if (this.list2.isEmpty() || !((String)this.list2.get(this.list2.size() - 1)).equals(text)) {
         this.list2.add(text);
      }
   }

   public void applyIndexByIndex(int count2) {
      Iterator iterator = this.list3.iterator();

      while (iterator.hasNext()) {
         if (((InternalIChatComponentEntry)iterator.next()).getCount2() == count2) {
            iterator.remove();
         }
      }

      Iterator iterator2 = this.list.iterator();

      while (iterator2.hasNext()) {
         if (((InternalIChatComponentEntry)iterator2.next()).getCount2() == count2) {
            iterator2.remove();
            break;
         }
      }
   }

   public void applyIChatComponent(IChatComponent iChatComponent, int count2) {
      this.applyIChatComponent(iChatComponent, count2, this.mc.ingameGUI.getIndex(), false);
      logger.info("[CHAT] " + iChatComponent.getString());
   }

   private void applyIChatComponent(IChatComponent iChatComponent, int count2, int count3, boolean flag) {
      if (count2 != 0) {
         this.applyIndexByIndex(count2);
      }

      int count4 = MathHelper.floor_float((float)this.getCount3() / this.getFactor());
      List list4 = FovWriter.resolveList(iChatComponent, count4, this.mc.fontRendererObj, false, false);
      boolean active = this.isActive();

      for (IChatComponent iChatComponent2 : (java.lang.Iterable<IChatComponent>) (list4)) {
         if (active && this.count > 0) {
            this.enabled = true;
            this.applyIndex(1);
         }

         this.list3.add(0, new InternalIChatComponentEntry(count3, iChatComponent2, count2));
      }

      if (!((Module)ClientGlobals.moduleManager.chat).isToggled() || !ClientGlobals.moduleManager.chat.settingNoChatLimit.isEnabled()) {
         while (this.list3.size() > 100) {
            this.list3.remove(this.list3.size() - 1);
         }

         if (!flag) {
            this.list.add(0, new InternalIChatComponentEntry(count3, iChatComponent, count2));

            while (this.list.size() > 100) {
               this.list.remove(this.list.size() - 1);
            }
         }
      }
   }

   public GuiNewChat(Minecraft mc2) {
      this.mc = mc2;
   }
}

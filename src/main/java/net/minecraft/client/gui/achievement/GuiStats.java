package net.minecraft.client.gui.achievement;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.augustus.utils.input.MouseInputUtil;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.EntityList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.StatFileWriter;

public class GuiStats extends GuiScreen implements IProgressMeter {
   public String screenTitle = "Select world";
   private GuiStats.StatsGeneral generalStats;
   private GuiStats.StatsItem itemStats;
   private GuiStats.StatsBlock blockStats;
   private GuiStats.StatsMobsList mobStats;
   private StatFileWriter statFile;
   private GuiSlot displaySlot;
   private boolean doesGuiPauseGame = true;

   private void drawSprite(int n, int n2, int n3, int n4) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(statIcons);
      float f = 0.0078125F;
      float f2 = 0.0078125F;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)(n + 0), (double)(n2 + 18), (double)this.zLevel)
         .tex((double)((float)(n3 + 0) * 0.0078125F), (double)((float)(n4 + 18) * 0.0078125F))
         .endVertex();
      worldRenderer.pos((double)(n + 18), (double)(n2 + 18), (double)this.zLevel)
         .tex((double)((float)(n3 + 18) * 0.0078125F), (double)((float)(n4 + 18) * 0.0078125F))
         .endVertex();
      worldRenderer.pos((double)(n + 18), (double)(n2 + 0), (double)this.zLevel)
         .tex((double)((float)(n3 + 18) * 0.0078125F), (double)((float)(n4 + 0) * 0.0078125F))
         .endVertex();
      worldRenderer.pos((double)(n + 0), (double)(n2 + 0), (double)this.zLevel)
         .tex((double)((float)(n3 + 0) * 0.0078125F), (double)((float)(n4 + 0) * 0.0078125F))
         .endVertex();
      instance.draw();
   }

   public static StatFileWriter accessStatFile(GuiStats guiStats) {
      return guiStats.statFile;
   }

   @Override
   public void doneLoading() {
      if (this.doesGuiPauseGame) {
         this.initLists();
         this.createButtons();
         this.displaySlot = this.generalStats;
         this.doesGuiPauseGame = false;
      }
   }

   public static FontRenderer getFontRendererHeaderTooltip(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   @Override
   public void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 0) {
            GuiScreen parentScreen2 = this.parentScreen;
            this.mc.displayGuiScreen(parentScreen2);
         } else if (button.id == 1) {
            this.displaySlot = this.generalStats;
         } else if (button.id == 3) {
            this.displaySlot = this.itemStats;
         } else if (button.id == 2) {
            this.displaySlot = this.blockStats;
         } else if (button.id == 4) {
            this.displaySlot = this.mobStats;
         } else {
            this.displaySlot.actionPerformed(button);
         }
      }
   }

   public static FontRenderer getFontRendererGeneralValue(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public void initLists() {
      this.generalStats = new GuiStats.StatsGeneral(this, this.mc);
      this.generalStats.registerScrollButtons(1, 1);
      this.itemStats = new GuiStats.StatsItem(this, this.mc);
      this.itemStats.registerScrollButtons(1, 1);
      this.blockStats = new GuiStats.StatsBlock(this, this.mc);
      this.blockStats.registerScrollButtons(1, 1);
      this.mobStats = new GuiStats.StatsMobsList(this, this.mc);
      this.mobStats.registerScrollButtons(1, 1);
   }

   public static void drawHeaderTooltipBackground(GuiStats guiStats, int n, int n2, int n3, int n4, int n5, int n6) {
      guiStats.drawGradientRect(n, n2, n3, n4, n5, n6);
   }

   public GuiStats(GuiScreen screen, StatFileWriter statFileWriter) {
      super(screen);
      this.statFile = statFileWriter;
   }

   public static FontRenderer getFontRendererStatTooltipWidth(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererGeneralName(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererStatValue(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   private void drawButtonBackground(int n, int n2) {
      this.drawSprite(n, n2, 0, 0);
   }

   public static FontRenderer getFontRendererMobKillsHeight(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererStatDash(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   @Override
   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      if (this.displaySlot != null) {
         this.displaySlot.handleMouseInput();
      }
   }

   public static FontRenderer getFontRendererMobName(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static void invokeDrawSprite(GuiStats guiStats, int n, int n2, int n3, int n4) {
      guiStats.drawSprite(n, n2, n3, n4);
   }

   public static FontRenderer getFontRendererMobKills(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererStatTooltipDraw(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static void invokeDrawStatsScreen(GuiStats guiStats, int n, int n2, Item item) {
      guiStats.drawStatsScreen(n, n2, item);
   }

   public static FontRenderer getFontRendererMobSlotHeight(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererMobKilledByHeight(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static void drawStatTooltipBackground(GuiStats guiStats, int n, int n2, int n3, int n4, int n5, int n6) {
      guiStats.drawGradientRect(n, n2, n3, n4, n5, n6);
   }

   private void drawStatsScreen(int n, int n2, Item item) {
      this.drawButtonBackground(n + 1, n2 + 1);
      GlStateManager.enableRescaleNormal();
      RenderHelper.enableGUIStandardItemLighting();
      this.itemRender.renderItemIntoGUI(new ItemStack(item, 1, 0), n + 2, n2 + 2);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
   }

   public static FontRenderer getFontRendererGeneralValueWidth(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererHeaderName(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererStatDashWidth(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererMobKilledBy(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   public static FontRenderer getFontRendererMobContentHeight(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.doesGuiPauseGame) {
         this.drawDefaultBackground();
         drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.downloadingStats", new Object[0]), this.width / 2, this.height / 2, 16777215);
         drawCenteredString(
            this.fontRendererObj,
            lanSearchStates[(int)(Minecraft.getSystemTime() / 150L % (long)lanSearchStates.length)],
            this.width / 2,
            this.height / 2 + this.fontRendererObj.FONT_HEIGHT * 2,
            16777215
         );
      } else {
         this.displaySlot.drawScreen(mouseX, mouseY, partialTicks);
         drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 20, 16777215);
         super.drawScreen(mouseX, mouseY, partialTicks);
      }
   }

   public static FontRenderer getFontRendererStatValueWidth(GuiStats guiStats) {
      return guiStats.fontRendererObj;
   }

   @Override
   public void initGui() {
      this.screenTitle = I18n.format("gui.stats", new Object[0]);
      this.doesGuiPauseGame = true;
      this.mc.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
   }

   public void createButtons() {
      this.buttonList.add(new GuiButton(0, this.width / 2 + 4, this.height - 28, 150, 20, I18n.format("gui.done", new Object[0])));
      this.buttonList.add(new GuiButton(1, this.width / 2 - 160, this.height - 52, 80, 20, I18n.format("stat.generalButton", new Object[0])));
      GuiButton button;
      this.buttonList.add(button = new GuiButton(2, this.width / 2 - 80, this.height - 52, 80, 20, I18n.format("stat.blocksButton", new Object[0])));
      GuiButton button2;
      this.buttonList.add(button2 = new GuiButton(3, this.width / 2, this.height - 52, 80, 20, I18n.format("stat.itemsButton", new Object[0])));
      GuiButton button3;
      this.buttonList.add(button3 = new GuiButton(4, this.width / 2 + 80, this.height - 52, 80, 20, I18n.format("stat.mobsButton", new Object[0])));
      if (this.blockStats.getSize() == 0) {
         button.enabled = false;
      }

      if (this.itemStats.getSize() == 0) {
         button2.enabled = false;
      }

      if (this.mobStats.getSize() == 0) {
         button3.enabled = false;
      }
   }

   @Override
   public boolean doesGuiPauseGame() {
      return !this.doesGuiPauseGame;
   }



   public static abstract class Stats extends GuiSlot {
      public int headerPressed;
      public List statsHolder;
      public Comparator statSorter;
      public int sortColumn;
      public int sortOrder;
      public final GuiStats guiStats;

      @Override
      public void drawBackground() {
         this.guiStats.drawDefaultBackground();
      }

      @Override
      public final int getSize() {
         return this.statsHolder.size();
      }

      public void renderMouseHoverToolTip(StatCrafting statCrafting, int n, int n2) {
         if (statCrafting != null) {
            Item item = statCrafting.getItem();
            String unlocalizedName = new ItemStack(item).getUnlocalizedName();
            String str = ("" + I18n.format(unlocalizedName + ".name", new Object[0])).trim();
            if (str.length() > 0) {
               int n3 = n + 12;
               int n4 = n2 - 12;
               int stringWidth = GuiStats.getFontRendererStatTooltipWidth(this.guiStats).getStringWidth(str);
               GuiStats.drawStatTooltipBackground(this.guiStats, n3 - 3, n4 - 3, n3 + stringWidth + 3, n4 + 8 + 3, -1073741824, -1073741824);
               GuiStats.getFontRendererStatTooltipDraw(this.guiStats).drawStringWithShadow(str, (float)n3, (float)n4, -1);
            }
         }
      }

      @Override
      public void elementClicked(int n, boolean flag, int n2, int n3) {
      }

      public void sortByColumn(int n) {
         if (n != this.sortColumn) {
            this.sortColumn = n;
            this.sortOrder = -1;
         } else if (this.sortOrder == -1) {
            this.sortOrder = 1;
         } else {
            this.sortColumn = -1;
            this.sortOrder = 0;
         }

         Collections.sort(this.statsHolder, this.statSorter);
      }

      public abstract String getHeaderDescriptionId(int n);

      public void renderStat(StatBase statBase, int n, int n2, boolean flag) {
         if (statBase != null) {
            String str = statBase.format(GuiStats.accessStatFile(this.guiStats).readStat(statBase));
            this.guiStats.drawString(GuiStats.getFontRendererStatValue(this.guiStats), str, n - GuiStats.getFontRendererStatValueWidth(this.guiStats).getStringWidth(str), n2 + 5, flag ? 16777215 : 9474192);
         } else {
            String str2 = "-";
            this.guiStats.drawString(GuiStats.getFontRendererStatDash(this.guiStats), str2, n - GuiStats.getFontRendererStatDashWidth(this.guiStats).getStringWidth(str2), n2 + 5, flag ? 16777215 : 9474192);
         }
      }

      public Stats(GuiStats guiStats2, Minecraft mc2) {
         super(mc2, guiStats2.width, guiStats2.height, 32, guiStats2.height - 64, 20);
         this.guiStats = guiStats2;
         this.headerPressed = -1;
         this.sortColumn = -1;
         this.setShowSelectionBox(false);
         this.setHasListHeader(true, 20);
      }

      @Override
      public boolean isSelected(int n) {
         return false;
      }

      @Override
      public void clickedHeader(int n, int n2) {
         this.headerPressed = -1;
         if (n >= 79 && n < 115) {
            this.headerPressed = 0;
         } else if (n >= 129 && n < 165) {
            this.headerPressed = 1;
         } else if (n >= 179 && n < 215) {
            this.headerPressed = 2;
         }

         if (this.headerPressed >= 0) {
            this.sortByColumn(this.headerPressed);
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         }
      }

      public final StatCrafting getSlotStat(int n) {
         return (StatCrafting)this.statsHolder.get(n);
      }

      @Override
      public void renderDecorations(int n, int n2) {
         if (n2 >= this.top && n2 <= this.bottom) {
            int n3 = this.getSlotIndexFromScreenCoords(n, n2);
            int n4 = this.width / 2 - 92 - 16;
            if (n3 >= 0) {
               if (n < n4 + 40 || n > n4 + 40 + 20) {
                  return;
               }

               this.renderMouseHoverToolTip(this.getSlotStat(n3), n, n2);
            } else {
               String str = "";
               if (n >= n4 + 115 - 18 && n <= n4 + 115) {
                  str = this.getHeaderDescriptionId(0);
               } else if (n >= n4 + 165 - 18 && n <= n4 + 165) {
                  str = this.getHeaderDescriptionId(1);
               } else {
                  if (n < n4 + 215 - 18 || n > n4 + 215) {
                     return;
                  }

                  str = this.getHeaderDescriptionId(2);
               }

               str = ("" + I18n.format(str, new Object[0])).trim();
               if (str.length() > 0) {
                  int n5 = n + 12;
                  int n6 = n2 - 12;
                  int stringWidth = GuiStats.getFontRendererHeaderName(this.guiStats).getStringWidth(str);
                  GuiStats.drawHeaderTooltipBackground(this.guiStats, n5 - 3, n6 - 3, n5 + stringWidth + 3, n6 + 8 + 3, -1073741824, -1073741824);
                  GuiStats.getFontRendererHeaderTooltip(this.guiStats).drawStringWithShadow(str, (float)n5, (float)n6, -1);
               }
            }
         }
      }

      @Override
      public void drawListHeader(int n, int n2, Tessellator tessellator) {
         if (!MouseInputUtil.isButtonDown(0)) {
            this.headerPressed = -1;
         }

         if (this.headerPressed == 0) {
            GuiStats.invokeDrawSprite(this.guiStats, n + 115 - 18, n2 + 1, 0, 0);
         } else {
            GuiStats.invokeDrawSprite(this.guiStats, n + 115 - 18, n2 + 1, 0, 18);
         }

         if (this.headerPressed == 1) {
            GuiStats.invokeDrawSprite(this.guiStats, n + 165 - 18, n2 + 1, 0, 0);
         } else {
            GuiStats.invokeDrawSprite(this.guiStats, n + 165 - 18, n2 + 1, 0, 18);
         }

         if (this.headerPressed == 2) {
            GuiStats.invokeDrawSprite(this.guiStats, n + 215 - 18, n2 + 1, 0, 0);
         } else {
            GuiStats.invokeDrawSprite(this.guiStats, n + 215 - 18, n2 + 1, 0, 18);
         }

         if (this.sortColumn != -1) {
            short s = 79;
            byte b = 18;
            if (this.sortColumn == 1) {
               s = 129;
            } else if (this.sortColumn == 2) {
               s = 179;
            }

            if (this.sortOrder == 1) {
               b = 36;
            }

            GuiStats.invokeDrawSprite(this.guiStats, n + s, n2 + 1, b, 0);
         }
      }
   }

   public static class StatsItem extends GuiStats.Stats {
      public final GuiStats parentGuiStats;

      public StatsItem(GuiStats guiStats2, Minecraft mc2) {
         super(guiStats2, mc2);
         this.parentGuiStats = guiStats2;
         this.statsHolder = Lists.newArrayList();

         for (StatCrafting statCrafting : (Iterable<StatCrafting>)(StatList.itemStats)) {
            boolean flag = false;
            int idFromItem = Item.getIdFromItem(statCrafting.getItem());
            if (GuiStats.accessStatFile(guiStats2).readStat(statCrafting) > 0) {
               flag = true;
            } else if (StatList.objectBreakStats[idFromItem] != null && GuiStats.accessStatFile(guiStats2).readStat(StatList.objectBreakStats[idFromItem]) > 0) {
               flag = true;
            } else if (StatList.objectCraftStats[idFromItem] != null && GuiStats.accessStatFile(guiStats2).readStat(StatList.objectCraftStats[idFromItem]) > 0) {
               flag = true;
            }

            if (flag) {
               this.statsHolder.add(statCrafting);
            }
         }

         this.statSorter = new GuiStats.StatsItem.EntryComparator(this, guiStats2);
      }

      @Override
      public void drawListHeader(int n, int n2, Tessellator tessellator) {
         super.drawListHeader(n, n2, tessellator);
         if (this.headerPressed == 0) {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 115 - 18 + 1, n2 + 1 + 1, 72, 18);
         } else {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 115 - 18, n2 + 1, 72, 18);
         }

         if (this.headerPressed == 1) {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 165 - 18 + 1, n2 + 1 + 1, 18, 18);
         } else {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 165 - 18, n2 + 1, 18, 18);
         }

         if (this.headerPressed == 2) {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 215 - 18 + 1, n2 + 1 + 1, 36, 18);
         } else {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 215 - 18, n2 + 1, 36, 18);
         }
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         StatCrafting slotStat = this.getSlotStat(n);
         Item item = slotStat.getItem();
         GuiStats.invokeDrawStatsScreen(this.parentGuiStats, n2 + 40, n3, item);
         int idFromItem = Item.getIdFromItem(item);
         this.renderStat(StatList.objectBreakStats[idFromItem], n2 + 115, n3, n % 2 == 0);
         this.renderStat(StatList.objectCraftStats[idFromItem], n2 + 165, n3, n % 2 == 0);
         this.renderStat(slotStat, n2 + 215, n3, n % 2 == 0);
      }

      @Override
      public String getHeaderDescriptionId(int n) {
         return n == 1 ? "stat.crafted" : (n == 2 ? "stat.used" : "stat.depleted");
      }


      public static class EntryComparator implements Comparator {
         public final GuiStats guiStats;
         public final GuiStats.StatsItem statsItem;

         public EntryComparator(GuiStats.StatsItem statsItem2, GuiStats guiStats2) {
            this.statsItem = statsItem2;
            this.guiStats = guiStats2;
         }

         @Override
         public int compare(Object a, Object b) {
            return this.compare((StatCrafting)a, (StatCrafting)b);
         }

         public int compare(StatCrafting a, StatCrafting b) {
            int idFromItem = Item.getIdFromItem(a.getItem());
            int idFromItem2 = Item.getIdFromItem(b.getItem());
            StatBase statBase = null;
            StatBase statBase2 = null;
            if (this.statsItem.sortColumn == 0) {
               statBase = StatList.objectBreakStats[idFromItem];
               statBase2 = StatList.objectBreakStats[idFromItem2];
            } else if (this.statsItem.sortColumn == 1) {
               statBase = StatList.objectCraftStats[idFromItem];
               statBase2 = StatList.objectCraftStats[idFromItem2];
            } else if (this.statsItem.sortColumn == 2) {
               statBase = StatList.objectUseStats[idFromItem];
               statBase2 = StatList.objectUseStats[idFromItem2];
            }

            if (statBase != null || statBase2 != null) {
               if (statBase == null) {
                  return 1;
               }

               if (statBase2 == null) {
                  return -1;
               }

               int n = GuiStats.accessStatFile(this.statsItem.parentGuiStats).readStat(statBase);
               int n2 = GuiStats.accessStatFile(this.statsItem.parentGuiStats).readStat(statBase2);
               if (n != n2) {
                  return (n - n2) * this.statsItem.sortOrder;
               }
            }

            return idFromItem - idFromItem2;
         }
      }
   }

   public static class StatsBlock extends GuiStats.Stats {
      public final GuiStats parentGuiStats;

      @Override
      public void drawListHeader(int n, int n2, Tessellator tessellator) {
         super.drawListHeader(n, n2, tessellator);
         if (this.headerPressed == 0) {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 115 - 18 + 1, n2 + 1 + 1, 18, 18);
         } else {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 115 - 18, n2 + 1, 18, 18);
         }

         if (this.headerPressed == 1) {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 165 - 18 + 1, n2 + 1 + 1, 36, 18);
         } else {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 165 - 18, n2 + 1, 36, 18);
         }

         if (this.headerPressed == 2) {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 215 - 18 + 1, n2 + 1 + 1, 54, 18);
         } else {
            GuiStats.invokeDrawSprite(this.parentGuiStats, n + 215 - 18, n2 + 1, 54, 18);
         }
      }

      public StatsBlock(GuiStats guiStats2, Minecraft mc2) {
         super(guiStats2, mc2);
         this.parentGuiStats = guiStats2;
         this.statsHolder = Lists.newArrayList();

         for (StatCrafting statCrafting : (Iterable<StatCrafting>)(StatList.objectMineStats)) {
            boolean flag = false;
            int idFromItem = Item.getIdFromItem(statCrafting.getItem());
            if (GuiStats.accessStatFile(guiStats2).readStat(statCrafting) > 0) {
               flag = true;
            } else if (StatList.objectUseStats[idFromItem] != null && GuiStats.accessStatFile(guiStats2).readStat(StatList.objectUseStats[idFromItem]) > 0) {
               flag = true;
            } else if (StatList.objectCraftStats[idFromItem] != null && GuiStats.accessStatFile(guiStats2).readStat(StatList.objectCraftStats[idFromItem]) > 0) {
               flag = true;
            }

            if (flag) {
               this.statsHolder.add(statCrafting);
            }
         }

         this.statSorter = new GuiStats.StatsBlock.EntryComparator(this, guiStats2);
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         StatCrafting slotStat = this.getSlotStat(n);
         Item item = slotStat.getItem();
         GuiStats.invokeDrawStatsScreen(this.parentGuiStats, n2 + 40, n3, item);
         int idFromItem = Item.getIdFromItem(item);
         this.renderStat(StatList.objectCraftStats[idFromItem], n2 + 115, n3, n % 2 == 0);
         this.renderStat(StatList.objectUseStats[idFromItem], n2 + 165, n3, n % 2 == 0);
         this.renderStat(slotStat, n2 + 215, n3, n % 2 == 0);
      }

      @Override
      public String getHeaderDescriptionId(int n) {
         return n == 0 ? "stat.crafted" : (n == 1 ? "stat.used" : "stat.mined");
      }


      public static class EntryComparator implements Comparator {
         public final GuiStats guiStats;
         public final GuiStats.StatsBlock statsBlock;

         public EntryComparator(GuiStats.StatsBlock statsBlock2, GuiStats guiStats2) {
            this.statsBlock = statsBlock2;
            this.guiStats = guiStats2;
         }

         public int compare(StatCrafting a, StatCrafting b) {
            int idFromItem = Item.getIdFromItem(a.getItem());
            int idFromItem2 = Item.getIdFromItem(b.getItem());
            StatBase statBase = null;
            StatBase statBase2 = null;
            if (this.statsBlock.sortColumn == 2) {
               statBase = StatList.mineBlockStatArray[idFromItem];
               statBase2 = StatList.mineBlockStatArray[idFromItem2];
            } else if (this.statsBlock.sortColumn == 0) {
               statBase = StatList.objectCraftStats[idFromItem];
               statBase2 = StatList.objectCraftStats[idFromItem2];
            } else if (this.statsBlock.sortColumn == 1) {
               statBase = StatList.objectUseStats[idFromItem];
               statBase2 = StatList.objectUseStats[idFromItem2];
            }

            if (statBase != null || statBase2 != null) {
               if (statBase == null) {
                  return 1;
               }

               if (statBase2 == null) {
                  return -1;
               }

               int n = GuiStats.accessStatFile(this.statsBlock.parentGuiStats).readStat(statBase);
               int n2 = GuiStats.accessStatFile(this.statsBlock.parentGuiStats).readStat(statBase2);
               if (n != n2) {
                  return (n - n2) * this.statsBlock.sortOrder;
               }
            }

            return idFromItem - idFromItem2;
         }

         @Override
         public int compare(Object a, Object b) {
            return this.compare((StatCrafting)a, (StatCrafting)b);
         }
      }
   }

   public static class StatsGeneral extends GuiSlot {
      public final GuiStats guiStats;

      @Override
      public boolean isSelected(int n) {
         return false;
      }

      public StatsGeneral(GuiStats guiStats2, Minecraft mc2) {
         super(mc2, guiStats2.width, guiStats2.height, 32, guiStats2.height - 64, 10);
         this.guiStats = guiStats2;
         this.setShowSelectionBox(false);
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         StatBase statBase = (StatBase)StatList.generalStats.get(n);
         this.guiStats.drawString(GuiStats.getFontRendererGeneralName(this.guiStats), statBase.getStatName().getUnformattedText(), n2 + 2, n3 + 1, n % 2 == 0 ? 16777215 : 9474192);
         String str = statBase.format(GuiStats.accessStatFile(this.guiStats).readStat(statBase));
         this.guiStats
            .drawString(
               GuiStats.getFontRendererGeneralValue(this.guiStats), str, n2 + 2 + 213 - GuiStats.getFontRendererGeneralValueWidth(this.guiStats).getStringWidth(str), n3 + 1, n % 2 == 0 ? 16777215 : 9474192
            );
      }

      @Override
      public int getSize() {
         return StatList.generalStats.size();
      }

      @Override
      public void drawBackground() {
         this.guiStats.drawDefaultBackground();
      }

      @Override
      public int getContentHeight() {
         return this.getSize() * 10;
      }

      @Override
      public void elementClicked(int n, boolean flag, int n2, int n3) {
      }
   }

   public static class StatsMobsList extends GuiSlot {
      private final List mobsList;
      public final GuiStats guiStats;

      @Override
      public void drawBackground() {
         this.guiStats.drawDefaultBackground();
      }

      @Override
      public void drawSlot(int n, int n2, int n3, int n4, int n5, int n6) {
         EntityList.EntityEggInfo entityEggInfo = (EntityList.EntityEggInfo)this.mobsList.get(n);
         String str = I18n.format("entity." + EntityList.getStringFromID(entityEggInfo.spawnedID) + ".name", new Object[0]);
         int n7 = GuiStats.accessStatFile(this.guiStats).readStat(entityEggInfo.killEntityStat);
         int n8 = GuiStats.accessStatFile(this.guiStats).readStat(entityEggInfo.entityKilledByStat);
         String str2 = I18n.format("stat.entityKills", new Object[]{n7, str});
         String str3 = I18n.format("stat.entityKilledBy", new Object[]{str, n8});
         if (n7 == 0) {
            str2 = I18n.format("stat.entityKills.none", new Object[]{str});
         }

         if (n8 == 0) {
            str3 = I18n.format("stat.entityKilledBy.none", new Object[]{str});
         }

         this.guiStats.drawString(GuiStats.getFontRendererMobName(this.guiStats), str, n2 + 2 - 10, n3 + 1, 16777215);
         this.guiStats.drawString(GuiStats.getFontRendererMobKills(this.guiStats), str2, n2 + 2, n3 + 1 + GuiStats.getFontRendererMobKillsHeight(this.guiStats).FONT_HEIGHT, n7 == 0 ? 6316128 : 9474192);
         this.guiStats.drawString(GuiStats.getFontRendererMobKilledBy(this.guiStats), str3, n2 + 2, n3 + 1 + GuiStats.getFontRendererMobKilledByHeight(this.guiStats).FONT_HEIGHT * 2, n8 == 0 ? 6316128 : 9474192);
      }

      @Override
      public boolean isSelected(int n) {
         return false;
      }

      @Override
      public int getContentHeight() {
         return this.getSize() * GuiStats.getFontRendererMobContentHeight(this.guiStats).FONT_HEIGHT * 4;
      }

      public StatsMobsList(GuiStats guiStats2, Minecraft mc2) {
         super(mc2, guiStats2.width, guiStats2.height, 32, guiStats2.height - 64, GuiStats.getFontRendererMobSlotHeight(guiStats2).FONT_HEIGHT * 4);
         this.guiStats = guiStats2;
         this.mobsList = Lists.newArrayList();
         this.setShowSelectionBox(false);

         for (EntityList.EntityEggInfo entityEggInfo : (Iterable<EntityList.EntityEggInfo>)(EntityList.entityEggs.values())) {
            if (GuiStats.accessStatFile(guiStats2).readStat(entityEggInfo.killEntityStat) > 0 || GuiStats.accessStatFile(guiStats2).readStat(entityEggInfo.entityKilledByStat) > 0) {
               this.mobsList.add(entityEggInfo);
            }
         }
      }

      @Override
      public void elementClicked(int n, boolean flag, int n2, int n3) {
      }

      @Override
      public int getSize() {
         return this.mobsList.size();
      }
   }
}

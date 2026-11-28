package net.augustus.settings.options;

import net.augustus.Augustus;
import net.augustus.settings.ColorSetting;
import net.augustus.settings.SelectableOption;
import net.augustus.settings.SettingVisibilityCondition;
import java.awt.Color;
import net.augustus.modules.render.BlockESP;
import net.augustus.modules.render.ClickGui;
import net.augustus.settings.SettingGroup;

public class BlockEspBlockOption implements SelectableOption {
   public String name;
   public boolean selected;
   public ColorSetting colorSetting;
   public final BlockESP blockESP;

   private BlockEspBlockOption(BlockESP blockESP2, String str, boolean flag) {
      this.blockESP = blockESP2;
      this.name = str;
      this.selected = flag;
   }

   private void initColorSetting() {
      SettingGroup settingGroup = new SettingGroup(this.blockESP, this.name, true);
      SettingVisibilityCondition condition = () -> {
         return this.isSelected() && !BlockESP.getOneColorSetting(this.blockESP).getValue();
      };
      SettingGroup settingGroup2 = settingGroup.visibleWhen(condition);
      BlockESP blockESP2 = this.blockESP;
      String str = "Color_" + this.name;
      Color color = new Color(21, 121, 230, 65);
      this.colorSetting = new ColorSetting(blockESP2, str, color, settingGroup2);
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public boolean isSelected() {
      return this.selected;
   }

   @Override
   public void setSelected(boolean flag) {
      BlockESP.clearTrackedBlocksBridge(Augustus.getInstance().getModuleManager().blockESP);
      this.selected = flag;
      BlockESP.setNeedsRendererReload(this.blockESP, true);
   }

   public BlockEspBlockOption(BlockESP blockESP2, String str, boolean flag, BlockEspBlockOptionSyntheticMarker marker) {
      this(blockESP2, str, flag);
   }

   public static void initOptionColorSetting(BlockEspBlockOption blockEspBlockOption) {
      blockEspBlockOption.initColorSetting();
   }

}

package client.gui;

import client.setting.TabListCapeType;
import client.util.Controller28;
import client.util.GeometryMonitor;

public class TabListCapeTypeAuxiliaryTexturesGuiIc extends AuxiliaryTexturesGuiIconsPngManagerAdapter {
   public final TabListCapeType tabListCapeType;
   public final GeometryMonitor geometryMonitor;

   private TabListCapeTypeAuxiliaryTexturesGuiIc(GeometryMonitor geometryMonitor2, int count, int count2, int count3, int count4, int count5, TabListCapeType tabListCapeType2) {
      super(count, count2, count3, count4, count5, GeometryMonitor.resolveText(geometryMonitor2, tabListCapeType2));
      this.geometryMonitor = geometryMonitor2;
      this.tabListCapeType = tabListCapeType2;
   }

   public static TabListCapeType resolveTabListCapeType(TabListCapeTypeAuxiliaryTexturesGuiIc tabListCapeTypeAuxiliaryTexturesGuiIc) {
      return tabListCapeTypeAuxiliaryTexturesGuiIc.tabListCapeType;
   }

   public TabListCapeTypeAuxiliaryTexturesGuiIc(GeometryMonitor geometryMonitor2, int count, int count2, int count3, int count4, int count5, TabListCapeType tabListCapeType2, Controller28 controller) {
      this(geometryMonitor2, count, count2, count3, count4, count5, tabListCapeType2);
   }
}

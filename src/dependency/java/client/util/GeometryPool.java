package client.util;

import java.util.Collections;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;

public class GeometryPool extends GuiScreen {
   public final GuiScreen parentScreen;

   public GeometryPool(GuiScreen parentScreen) {
      this.parentScreen = parentScreen;
   }

   public List getList() {
      return Collections.emptyList();
   }

   public List getListAsList() {
      return Collections.emptyList();
   }

   public boolean matchesRayTraceValidator(RayTraceValidator validator) {
      return false;
   }

   public List resolveList(RayTraceValidator validator) {
      return Collections.emptyList();
   }

   public void updateState() {
   }
}

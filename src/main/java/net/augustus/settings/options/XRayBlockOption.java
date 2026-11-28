package net.augustus.settings.options;

import net.augustus.settings.SelectableOption;
import net.augustus.modules.render.XRay;
import net.augustus.utils.interfaces.MC;

public class XRayBlockOption implements SelectableOption {
   public String name;
   public boolean selected;
   public final XRay xRay;

   private XRayBlockOption(XRay xRay2, String str, boolean flag) {
      this.xRay = xRay2;
      this.name = str;
      this.selected = flag;
   }

   public String getName() {
      return this.name;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void setSelected(boolean flag) {
      if (flag != this.selected && MC.mc.theWorld != null) {
         XRay.setReloadRenderersPending(this.xRay, true);
      }

      this.selected = flag;
   }

   public XRayBlockOption(XRay xRay2, String str, boolean flag, XRayBlockOptionSyntheticMarker marker) {
      this(xRay2, str, flag);
   }
}

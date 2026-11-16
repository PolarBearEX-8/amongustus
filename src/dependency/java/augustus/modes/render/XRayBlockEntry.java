package augustus.modes.render;

import augustus.modules.render.XRay;
import client.setting.NamedOption;
import client.util.AltClientManager;
import client.util.MinecraftAccessor;

public class XRayBlockEntry implements NamedOption {
   public String labelText;
   public boolean state;
   public final XRay xRay;

   @Override
   public void setActive(boolean active) {
      if (active != this.state && MinecraftAccessor.minecraft.theWorld != null) {
         XRay.checkXRay(this.xRay, true);
      }

      this.state = active;
   }

   @Override
   public String getText() {
      return this.labelText;
   }

   @Override
   public boolean isSelected() {
      return this.state;
   }

   private XRayBlockEntry(XRay xRay2, String text, boolean flag) {
      this.xRay = xRay2;
      this.labelText = text;
      this.state = flag;
   }

   public XRayBlockEntry(XRay xRay2, String text, boolean flag, AltClientManager altClientManager) {
      this(xRay2, text, flag);
   }
}

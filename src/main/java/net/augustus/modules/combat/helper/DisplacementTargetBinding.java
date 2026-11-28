package net.augustus.modules.combat.helper;


import net.augustus.modules.combat.KBDisplacementHotkeyGroup;
public class DisplacementTargetBinding {
   public final KBDisplacementHotkeyGroup kbDisplacementHotkeyGroup;
   public final float degreeOffset;

   public DisplacementTargetBinding(KBDisplacementHotkeyGroup group, float f) {
      this.kbDisplacementHotkeyGroup = group;
      this.degreeOffset = f;
   }
}

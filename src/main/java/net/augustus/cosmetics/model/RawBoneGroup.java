package net.augustus.cosmetics.model;

import java.util.HashMap;
import net.labymod.Bone;

public class RawBoneGroup {
   public Bone selfBone;
   public HashMap children = new HashMap();

   public RawBoneGroup(Bone bone) {
      this.selfBone = bone;
   }
}

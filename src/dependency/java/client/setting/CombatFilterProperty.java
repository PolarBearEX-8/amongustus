package client.setting;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.renderer.OpenGlHelper;

public enum CombatFilterProperty {
    VERTEX("vertex", ".vsh", OpenGlHelper.count27),
    FRAGMENT("fragment", ".fsh", OpenGlHelper.count25);

   public final String labelText;
   public final String labelText2;
   public final int count;
   public final Map map = Maps.newHashMap();
   public static final CombatFilterProperty[] ENUM_VALUES = new CombatFilterProperty[]{VERTEX, FRAGMENT};

   public int getCount() {
      return this.count;
   }

   public Map getMap() {
      return this.map;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   private CombatFilterProperty(String text, String text2, int count2) {
      this.labelText = text;
      this.labelText2 = text2;
      this.count = count2;
   }


}

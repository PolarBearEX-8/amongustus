package net.augustus.modules.render;

import net.augustus.Augustus;
import net.augustus.render.EmojiSprite;
import com.google.common.collect.Maps;
import java.util.Map;

public class EmojiRegistry {
   private final Map sprites = Maps.newLinkedHashMap();

   public int getComboTime() {
      return (int)Augustus.getInstance().getModuleManager().emojify.comboTime.getValue();
   }

   public EmojiSprite getSelectedSprite() {
      return (EmojiSprite)this.sprites.get(Augustus.getInstance().getModuleManager().emojify.modes.getSelectedValue());
   }

   public int getComboHits() {
      return (int)Augustus.getInstance().getModuleManager().emojify.comboHits.getValue();
   }

   public void registerDefaultSprites() {
      this.sprites.clear();
      this.sprites.put("default1", new EmojiSprite("default1.png", 6, 16));
      this.sprites.put("default2", new EmojiSprite("default2.png", 4, 20));
      this.sprites.put("default3", new EmojiSprite("default3.png", 8, 17));
      this.sprites.put("angry", new EmojiSprite("angry.png", 5, 18));
      this.sprites.put("pogchamp", new EmojiSprite("pogchamp.png", 3, 21));
      this.sprites.put("prettygood", new EmojiSprite("prettygood.png", 3, 5, 18));
      this.sprites.put("trashmeme", new EmojiSprite("trashmeme.png", 0, 5, 15));
   }



}

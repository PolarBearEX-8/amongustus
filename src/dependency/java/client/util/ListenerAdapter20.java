package client.util;

import client.event.PostMotionDispatcher;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.util.ResourceLocation;

public class ListenerAdapter20 implements PostMotionDispatcher {
   public final ResourceLocation resourceLocation;
   public final String labelText;
   public final BridgeStringEntry bridgeStringEntry;
   public final SoundHandler soundHandler;

   @Override
   public Object getTarget() {
      return this.getAuxiliaryResourceLocationHol();
   }

   public AuxiliaryResourceLocationHolder getAuxiliaryResourceLocationHol() {
      CommonListEntry object = (CommonListEntry)SoundHandler.resolveAltMapEntry(this.soundHandler).getObject(this.resourceLocation);
      return object == null ? SoundHandler.auxiliaryResourceLocationHolder : object.getAuxiliaryResourceLocationHol();
   }

   public ListenerAdapter20(SoundHandler soundHandler2, String text, BridgeStringEntry bridgeStringEntry2) {
      this.soundHandler = soundHandler2;
      this.labelText = text;
      this.bridgeStringEntry = bridgeStringEntry2;
      this.resourceLocation = new ResourceLocation(this.labelText, this.bridgeStringEntry.getLabelText());
   }

   @Override
   public int getCount() {
      CommonListEntry object = (CommonListEntry)SoundHandler.resolveAltMapEntry(this.soundHandler).getObject(this.resourceLocation);
      return object == null ? 0 : object.getCount();
   }
}

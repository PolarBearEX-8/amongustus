package client.util;

import client.setting.ItemPhysicsNoneType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.item.AuxiliaryItemEntry;
import net.minecraft.item.BridgeItemEntry;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NativeLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public static final Gson gson = new GsonBuilder()
      .registerTypeAdapter(NativeLoggerEntry.class, new JsonDeserializerAdapter())
      .registerTypeAdapter(Vector3fHolder.class, new InternalJsonDeserializerAdapter())
      .registerTypeAdapter(EnumFacingHolder.class, new AdvancedJsonDeserializerAdapter())
      .registerTypeAdapter(TertiaryFloatArrayEntry.class, new SecondaryJsonDeserializerAdapter())
      .registerTypeAdapter(AuxiliaryItemEntry.class, new AuxiliaryJsonDeserializerAdapter())
      .registerTypeAdapter(SpecialItemEntry.class, new BaseJsonDeserializerAdapter())
      .create();
   public final List list;
   public final boolean enabled2;
   public final boolean enabled;
   public SpecialItemEntry specialItemEntry;
   public String labelText = "";
   public final Map map;
   public NativeLoggerEntry nativeLoggerEntry;
   public ResourceLocation resourceLocation;

   public static void applyMap(Map map2) {
      for (NativeLoggerEntry nativeLoggerEntry2 : (Iterable<NativeLoggerEntry>)(map2.values())) {
         try {
            NativeLoggerEntry nativeLoggerEntry3 = nativeLoggerEntry2.nativeLoggerEntry;

            for (NativeLoggerEntry nativeLoggerEntry4 = nativeLoggerEntry3.nativeLoggerEntry; nativeLoggerEntry3 != nativeLoggerEntry4; nativeLoggerEntry4 = nativeLoggerEntry4.nativeLoggerEntry.nativeLoggerEntry) {
               nativeLoggerEntry3 = nativeLoggerEntry3.nativeLoggerEntry;
            }

            throw new TertiaryRuntimeExceptionAdapter();
         } catch (NullPointerException nullPointerException) {
         }
      }
   }

   public boolean isActive() {
      return this.isActiveAsFlag() ? this.nativeLoggerEntry.isActive() : this.enabled;
   }

   public void get(Map map2) {
      if (this.resourceLocation != null) {
         this.nativeLoggerEntry = (NativeLoggerEntry)map2.get(this.resourceLocation);
      }
   }

   public SpecialItemEntry getSpecialItemEntry() {
      AuxiliaryItemEntry auxiliaryItemEntry = this.resolveAuxiliaryItemEntry(ItemPhysicsNoneType.THIRD_PERSON);
      AuxiliaryItemEntry auxiliaryItemEntry2 = this.resolveAuxiliaryItemEntry(ItemPhysicsNoneType.FIRST_PERSON);
      AuxiliaryItemEntry auxiliaryItemEntry3 = this.resolveAuxiliaryItemEntry(ItemPhysicsNoneType.HEAD);
      AuxiliaryItemEntry auxiliaryItemEntry4 = this.resolveAuxiliaryItemEntry(ItemPhysicsNoneType.GUI);
      AuxiliaryItemEntry auxiliaryItemEntry5 = this.resolveAuxiliaryItemEntry(ItemPhysicsNoneType.GROUND);
      AuxiliaryItemEntry auxiliaryItemEntry6 = this.resolveAuxiliaryItemEntry(ItemPhysicsNoneType.FIXED);
      return new SpecialItemEntry(auxiliaryItemEntry, auxiliaryItemEntry2, auxiliaryItemEntry3, auxiliaryItemEntry4, auxiliaryItemEntry5, auxiliaryItemEntry6);
   }

   public String resolveText(String text) {
      String text2 = text;
      if (!this.charAt(text)) {
         text2 = '#' + text;
      }

      return this.resolveText(text2, new BridgeItemEntry(this, null));
   }

   public boolean matchesText(String text) {
      return !"missingno".equals(this.resolveText(text));
   }

   private AuxiliaryItemEntry resolveAuxiliaryItemEntry(ItemPhysicsNoneType itemPhysicsNoneType) {
      return this.nativeLoggerEntry != null && !this.specialItemEntry.matchesItemPhysicsNoneType(itemPhysicsNoneType)
         ? this.nativeLoggerEntry.resolveAuxiliaryItemEntry(itemPhysicsNoneType)
         : this.specialItemEntry.getAuxiliaryItemEntry(itemPhysicsNoneType);
   }

   public boolean isActive2() {
      return this.resourceLocation == null || this.nativeLoggerEntry != null && this.nativeLoggerEntry.isActive2();
   }

   public List getList() {
      return this.isActiveAsFlag() ? this.nativeLoggerEntry.getList() : this.list;
   }

   public static NativeLoggerEntry resolveNativeLoggerEntry(String text) {
      return fromJson(new StringReader(text));
   }

   public ResourceLocation getResourceLocation() {
      return this.resourceLocation;
   }

   public NativeLoggerEntry getNativeLoggerEntry() {
      return this.isActiveAsFlag() ? this.nativeLoggerEntry.getNativeLoggerEntry() : this;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   private boolean charAt(String text) {
      return text.charAt(0) == '#';
   }

   private boolean isActiveAsFlag() {
      return this.nativeLoggerEntry != null;
   }

   public static NativeLoggerEntry fromJson(Reader reader) {
      return (NativeLoggerEntry)gson.fromJson(reader, NativeLoggerEntry.class);
   }

   private String resolveText(String text, BridgeItemEntry bridgeItemEntry) {
      if (this.charAt(text)) {
         if (this == bridgeItemEntry.nativeLoggerEntry) {
            logger.warn("Unable to resolve texture due to upward reference: " + text + " in " + this.labelText);
            return "missingno";
         } else {
            String text2 = (String)this.map.get(text.substring(1));
            if (text2 == null && this.isActiveAsFlag()) {
               text2 = this.nativeLoggerEntry.resolveText(text, bridgeItemEntry);
            }

            bridgeItemEntry.nativeLoggerEntry = this;
            if (text2 != null && this.charAt(text2)) {
               text2 = bridgeItemEntry.nativeLoggerEntry2.resolveText(text2, bridgeItemEntry);
            }

            return text2 != null && !this.charAt(text2) ? text2 : "missingno";
         }
      } else {
         return text;
      }
   }

   public NativeLoggerEntry(ResourceLocation location, Map map2, boolean flag, boolean flag2, SpecialItemEntry specialItemEntry2) {
      this(location, Collections.emptyList(), map2, flag, flag2, specialItemEntry2);
   }

   public NativeLoggerEntry(List list2, Map map2, boolean flag, boolean flag2, SpecialItemEntry specialItemEntry2) {
      this((ResourceLocation)null, list2, map2, flag, flag2, specialItemEntry2);
   }

   private NativeLoggerEntry(ResourceLocation location, List list2, Map map2, boolean flag, boolean flag2, SpecialItemEntry specialItemEntry2) {
      this.list = list2;
      this.enabled = flag;
      this.enabled2 = flag2;
      this.map = map2;
      this.resourceLocation = location;
      this.specialItemEntry = specialItemEntry2;
   }
}

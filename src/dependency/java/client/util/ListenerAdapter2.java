package client.util;

import client.event.GlobalListener;
import client.setting.AdvancedSettingArrayEntry;
import java.util.HashMap;
import java.util.Map;
import net.labymod.Bone;
import net.labymod.Cube;
import net.labymod.ModelProperties;
import org.apache.commons.lang3.ArrayUtils;

public class ListenerAdapter2 implements GlobalListener {
   public static GlobalListener globalListener = new ListenerAdapter2();
   public static Map map = new HashMap();

   public static GlobalListener get(String text) {
      GlobalListener globalListener2 = (GlobalListener)map.get(text);
      return globalListener2 == null ? globalListener : globalListener2;
   }

   @Override
   public ListenerAdapter23 resolveListenerAdapter23(ModelBone modelBone, ModelProperties modelProperties, ListenerAdapter23 listenerAdapter) {
      ListenerAdapter23 listenerAdapter2 = new ListenerAdapter23();
      Bone bone = modelBone.bone;
      StaticFloatEntry staticFloatEntry = AdvancedSettingArrayEntry.resolveStaticFloatEntry(AdvancedSettingArrayEntry.resolveDynamicDoubleEntry(bone.getRotation()));
      StaticFloatEntry staticFloatEntry2 = AdvancedSettingArrayEntry.resolveStaticFloatEntry(AdvancedSettingArrayEntry.resolveDynamicDoubleEntry(bone.getPivot()));
      staticFloatEntry.factor2 *= -1.0F;
      staticFloatEntry.factor3 *= -1.0F;
      listenerAdapter2.memberRef = bone.getMirror();
      listenerAdapter2.memberRef3 = bone.getNeverRender();
      listenerAdapter2.memberRef4 = bone.getReset();
      listenerAdapter2.memberRef2 = bone.getInflate();
      listenerAdapter2.listenerAdapter23 = listenerAdapter;
      String name = bone.getName();
      listenerAdapter2.setLabelText(name);
      float factor = (float)Math.toRadians((double)staticFloatEntry.getFactor2());
      listenerAdapter2.setFactor8(factor);
      float factor2 = (float)Math.toRadians((double)staticFloatEntry.getFactor3());
      listenerAdapter2.setFactor6(factor2);
      float factor3 = (float)Math.toRadians((double)staticFloatEntry.getFactor());
      listenerAdapter2.setFactor3(factor3);
      listenerAdapter2.factor5 = -staticFloatEntry2.getFactor2();
      listenerAdapter2.factor12 = staticFloatEntry2.getFactor3();
      listenerAdapter2.factor7 = staticFloatEntry2.getFactor();
      if (!ArrayUtils.isEmpty(bone.getCubes())) {
         for (Cube cube : bone.getCubes()) {
            Double offset = listenerAdapter2.memberRef2 == null ? null : listenerAdapter2.memberRef2 / 16.0;
            Boolean memberRef = listenerAdapter2.memberRef;
            Double offset2 = offset;
            listenerAdapter2.list.add(ComparableEntryArrayEntryAdapter.resolveComparableEntryArrayEntryAda(cube, modelProperties, offset2, memberRef));
         }
      }

      for (ModelBone modelBone2 : (Iterable<ModelBone>)(Iterable<?>)modelBone.hashMap.values()) {
         listenerAdapter2.list2.add(this.resolveListenerAdapter23(modelBone2, modelProperties, listenerAdapter2));
      }

      return listenerAdapter2;
   }

   public static void put(String text, GlobalListener globalListener2) {
      map.put(text, globalListener2);
   }

   @Override
   public GenericListEntry resolveGenericListEntry(ExtraHashMapEntry extraHashMapEntry) {
      byte byteValue = 0;
      int count = 1474226977;
      char character = '\ubc73';
      GenericListEntry genericListEntry = new GenericListEntry();
      genericListEntry.modelProperties = extraHashMapEntry.modelProperties;

      for (ModelBone modelBone : (Iterable<ModelBone>)(Iterable<?>)extraHashMapEntry.hashMap.values()) {
         ListenerAdapter23 listenerAdapter = null;
         ModelProperties modelProperties = extraHashMapEntry.modelProperties;
         genericListEntry.list.add(this.resolveListenerAdapter23(modelBone, modelProperties, listenerAdapter));
      }

      return genericListEntry;
   }
}

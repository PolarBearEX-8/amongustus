package client.event;

import client.setting.MotionSettingGroup;
import client.util.AuxiliaryModuleAdapter;

public interface PostMotionSubscriber {
   AuxiliaryModuleAdapter getAuxiliaryModuleAdapter();

   MotionSettingGroup getMotionSettingGroup();
}

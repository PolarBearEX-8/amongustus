package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;
import java.util.Enumeration;
import java.util.Hashtable;

public class FactoryRegistry extends AudioDeviceFactory {
   private static FactoryRegistry instance = null;
   public Hashtable factories = new Hashtable();

   public void addFactory(AudioDeviceFactory audioDeviceFactory) {
      this.factories.put(audioDeviceFactory.getClass(), audioDeviceFactory);
   }

   public static synchronized FactoryRegistry systemRegistry() {
      if (instance == null) {
         instance = new FactoryRegistry();
         instance.registerDefaultFactories();
      }

      return instance;
   }

   public AudioDeviceFactory[] getFactoriesPriority() {
      AudioDeviceFactory[] audioDeviceFactorys = null;
      synchronized (this.factories) {
         int size = this.factories.size();
         if (size != 0) {
            audioDeviceFactorys = new AudioDeviceFactory[size];
            int n = 0;
            Enumeration en = this.factories.elements();

            while (en.hasMoreElements()) {
               AudioDeviceFactory audioDeviceFactory = (AudioDeviceFactory)en.nextElement();
               audioDeviceFactorys[n++] = audioDeviceFactory;
            }
         }

         return audioDeviceFactorys;
      }
   }

   public void removeFactory(AudioDeviceFactory audioDeviceFactory) {
      this.factories.remove(audioDeviceFactory.getClass());
   }

   @Override
   public AudioDevice createAudioDevice() throws JavaLayerException {
      AudioDevice audioDevice = null;
      AudioDeviceFactory[] factoriesPriority = this.getFactoriesPriority();
      if (factoriesPriority == null) {
         throw new JavaLayerException(this + ": no factories registered");
      } else {
         JavaLayerException ex = null;

         for (int i = 0; audioDevice == null && i < factoriesPriority.length; i++) {
            try {
               audioDevice = factoriesPriority[i].createAudioDevice();
            } catch (JavaLayerException e) {
               ex = e;
            }
         }

         if (audioDevice == null && ex != null) {
            throw new JavaLayerException("Cannot create AudioDevice", ex);
         } else {
            return audioDevice;
         }
      }
   }

   public void removeFactoryType(Class clazz) {
      this.factories.remove(clazz);
   }

   public void registerDefaultFactories() {
      this.addFactory(new JavaSoundAudioDeviceFactory());
   }
}

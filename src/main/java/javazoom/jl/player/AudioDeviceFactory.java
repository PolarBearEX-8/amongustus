package javazoom.jl.player;



import javazoom.jl.decoder.JavaLayerException;
public abstract class AudioDeviceFactory {
   public AudioDevice instantiate(ClassLoader classLoader, String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
      Object obj = null;
      Class clazz = null;
      if (classLoader == null) {
         clazz = Class.forName(str);
      } else {
         clazz = classLoader.loadClass(str);
      }

      return (AudioDevice)clazz.newInstance();
   }

   public abstract AudioDevice createAudioDevice() throws JavaLayerException;
}

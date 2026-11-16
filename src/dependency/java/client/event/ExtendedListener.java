package client.event;

public interface ExtendedListener {
   boolean isEnabled();

   double computeValue(double offset);

   ExtendedListener getExtendedListener();

   String getLabelText();

   double getValue();
}

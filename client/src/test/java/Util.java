import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Util {
    static String formatDuration(long milliseconds) {
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        long remaining = (milliseconds % 1000);

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.####", otherSymbols);

        if(minutes > 0) {
            double d = Double.parseDouble(String.format("%d.%d%d", minutes, seconds, remaining));
            return df.format(d) + "m";
        }
        else if(seconds > 0) {
            double d = Double.parseDouble(String.format("%d.%d", seconds, remaining));
            return df.format(d) + "s";
        }
        else {
            double d = Double.parseDouble(String.format("%d", remaining));
            return df.format(d) + "ms";
        }
    }
}

package oneroster;

import org.ricone.library.client.core.IResponse;
import org.ricone.library.client.core.Model;

import java.text.DecimalFormat;

public class Util {
    public static String getResponseDetails(IResponse<? extends Model> response) {
        return "status: " + response.getResponseStatus() + " | " +
            "size: " + byteCount(response.getResponseHeaders().getFirst("Content-Length"))  + " | " +
            "count: " + formatNumber(response.getResponseHeaders().getFirst("X-Record-Count"))  + " | " +
            "duration: " + response.getResponseHeaders().getFirst("X-Duration");
    }

    public static String getResponseJson(IResponse<? extends Model> response) {
        return response.getJSON().replaceAll("(?m)^", "\t\t");
    }


    private static String trimUrl(String url) {
        return url.replace("http://localhost:8080/api/ims/oneroster/v1p1","");
    }

    private static String byteCount(String stringBytes) {
        if(stringBytes == null) {
            stringBytes = "0";
        }

        long bytes = Long.parseLong(stringBytes);
        String s = bytes < 0 ? "-" : "";
        long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        return b < 1000L ? bytes + " B"
                : b < 999_950L ? String.format("%s%.1f kB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f MB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f GB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f TB", s, b / 1e3)
                : (b /= 1000) < 999_950L ? String.format("%s%.1f PB", s, b / 1e3)
                : String.format("%s%.1f EB", s, b / 1e6);
    }

    private static String formatNumber(String number) {
        if(number == null) {
            return "N/A";
        }
        double amount = Double.parseDouble(number);
        DecimalFormat formatter = new DecimalFormat("#,###");

        return formatter.format(amount);
    }
}

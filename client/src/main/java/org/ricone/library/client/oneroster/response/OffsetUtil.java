package org.ricone.library.client.oneroster.response;

import org.springframework.util.NumberUtils;

import java.util.LinkedList;

public class OffsetUtil {
    public static int[] getOffsetArray(int limit, int offset, String totalRecordCount) {
        final LinkedList<Integer> list = new LinkedList<>();
        list.add(offset); //Add the first instance of what is being offset

        int totalRecords = NumberUtils.parseNumber(totalRecordCount, Integer.class);
        int currentPage = (int) Math.floor((double)offset / limit);
        int totalPages = (int) Math.ceil((double)totalRecords / limit);
        int last_offset = (totalPages - 1) * limit;

        //System.out.println(totalRecords + " " + currentPage + " " +totalPages + " " + last_offset);

        int next_offset = 0;

        while(last_offset != next_offset) {
            next_offset = (currentPage + 1) * limit;
            list.add(next_offset);
            currentPage++;
        }
        return list.stream().mapToInt(i->i).toArray();
    }
}
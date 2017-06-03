package com.rb;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ruth on 28/04/17.
 */
public class WillFileManager {

    private File willFile;
    private ArrayList<String> willList;

    private File getWillFile() {
        return willFile;
    }

    void setWillFile(File willFile) {
        this.willFile = willFile;
    }

    private ArrayList<String> getList() throws FileNotFoundException {

        if (willList != null) {
            System.out.println("willList has already been generated");
            return willList;
        }

        Scanner s = new Scanner(this.willFile);

        willList = new ArrayList<String>();
        while (s.hasNextLine()) {
            willList.add(s.nextLine());
        }
        s.close();

        return willList;
    }

    int getMaxIndex() throws FileNotFoundException {
        ArrayList<String> willList = getList();
        return willList.size();
    }

    Will getFirstEntry() throws FileNotFoundException {

        ArrayList<String> willList = getList();
        String firstEntry = willList.get(0);

        String name = firstEntry.split("\\t")[0];
        String dateString = firstEntry.split("\\t")[1];
        String reference = firstEntry.split("\\t")[2];

        int date = Integer.parseInt(dateString);

        Will myFirstWill = new Will(1, name, date, reference);
        return myFirstWill;
    }

    Will getEntry(int index) throws Exception {

        ArrayList<String> willList = getList();
        String entry = willList.get(index - 1);

        String name = "";
        String reference = "";
        String dateString = "";

        Pattern pattern = Pattern.compile("\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(entry);

        int count = 0;
        List matches = new ArrayList();
        while (matcher.find()) {
            matches.add(matcher.group());
            count++;
        }



        if (count == 1) {
//        if (matcher.groupCount() == 0) {

            dateString = matches.get(0).toString();
            name = entry.split("\\d\\d\\d\\d")[0];
            reference = entry.split("\\d\\d\\d\\d")[1];
        }
        else {
            throw new Exception("Haven't implemented date ranges yet");
        }


        int date = Integer.parseInt(dateString);
        name = name.trim();
        reference = reference.trim();

        Will will = new Will(index, name, date, reference);
        return will;
    }
}

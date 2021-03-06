package com.rb;

import java.io.*;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by ruth on 13/03/17.
 */
class AnimalFileManager {

    private File animalsFile;

    private File getAnimalsFile() {
        return animalsFile;
    }

    void setAnimalsFile(File animalsFile) {
        this.animalsFile = animalsFile;
    }


    ArrayList<String> getList() throws FileNotFoundException {
        Scanner s = new Scanner(this.animalsFile);
        ArrayList<String> animalsList = new ArrayList<String>();
        while (s.hasNext()) {
            animalsList.add(s.next());
        }
        s.close();

        return animalsList;
    }

    Map<Integer, String> getMap() throws FileNotFoundException {
        Scanner s = new Scanner(this.animalsFile);

        Map<Integer, String> map = new HashMap<>();

        while (s.hasNext()) {
            String fileEntry = s.next();
            String[] splitResult = fileEntry.split(":");
            map.put(Integer.valueOf(splitResult[0]), splitResult[1]);
        }
        s.close();

        return map;
    }

    boolean animalExists(String animal) throws FileNotFoundException {
        Map<Integer, String> animalMap = getMap();

        if (animalMap.containsValue(animal)) {
            return true;
        }

        return false;

    }

    boolean animalExists(Integer index) throws FileNotFoundException {
        Map<Integer, String> animalMap = getMap();

        if (animalMap.containsKey(index)) {
            return true;
        }

        return false;

    }

    String getAnimalNameByIndex(Integer index) throws FileNotFoundException {
        Map<Integer, String> animalMap = getMap();
        return animalMap.get(index);
    }

    Animal getAnimalByIndex(Integer index) throws FileNotFoundException {
        Map<Integer, String> animalMap = getMap();
        return new Animal(index, animalMap.get(index));
    }

    /**
     * Adds an animal to the list.
     *
     * @param newAnimal The animal to be added
     * @return true if animal was successfully added.
     * Returns false if the animal already exists in the list
     */
    int addAnimal(String newAnimal) throws FileNotFoundException {

        if (animalExists(newAnimal)) {
            return 0;
        }

        Map<Integer, String> animalMap = getMap();
        int nextNumber = 1;
        if (animalMap.size() > 0) {
            int highestValue = Collections.max(animalMap.keySet());
            nextNumber = highestValue + 1;
        }

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(this.getAnimalsFile(), true));
            String stringToWrite = nextNumber + ":" + newAnimal;
            bw.write(stringToWrite);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (bw != null) try {
                bw.close();
            } catch (IOException ioe2) {
                ioe2.printStackTrace();
            }
        }

        if (!animalExists(newAnimal)) {
            return 0;
        }

        return nextNumber;

    }

    /**
     * Deletes an animal from the list
     *
     * @param animalToDelete animal to delete
     * @return true if animal was successfully deleted.
     * False otherwise.
     */
    boolean deleteAnimal(String animalToDelete) throws IOException {

        if (!animalExists(animalToDelete)) {
            return false;
        }

        File inputFile = this.animalsFile;
        File tempFile = new File(this.animalsFile.getAbsolutePath() + "_temp");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(!currentLine.contains(animalToDelete)){
                writer.write(currentLine + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
        System.out.println(successful);

        return true;
    }
}

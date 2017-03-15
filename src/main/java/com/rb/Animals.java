package com.rb;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by ruth on 13/03/17.
 */
class Animals {

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

    boolean animalExists(String animal) throws FileNotFoundException {

        ArrayList<String> animalList = getList();
        if (animalList.contains(animal)) {
            return true;
        }

        return false;

    }

    /**
     * Adds an animal to the list.
     *
     * @param newAnimal The animal to be added
     * @return true if animal was successfully added.
     * Returns false if the animal already exists in the list
     */
    boolean addAnimal(String newAnimal) throws FileNotFoundException {

        if (animalExists(newAnimal)) {
            return false;
        }

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(this.getAnimalsFile(), true));
            bw.write(newAnimal);
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

        return animalExists(newAnimal);

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
            if(!currentLine.equalsIgnoreCase(animalToDelete)){
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

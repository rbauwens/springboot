package com.rb;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by ruth on 13/03/17.
 */
public class AnimalsTest {

    private Animals animals = new Animals();
    private final String FILEPATH = "src/test/resources/test_animals";


    @Before
    public void checkFileExists() throws FileNotFoundException {
        File animalsFile = new File(FILEPATH);
        assertThat(animalsFile.exists(), is(true));

        this.animals.setAnimalsFile(animalsFile);
    }

    @After
    public void clearFile() throws FileNotFoundException {

        ArrayList<String>animalsList = this.animals.getList();
        if (!animalsList.isEmpty()) {

            PrintWriter pw = new PrintWriter(FILEPATH);
            pw.close();

            animalsList = this.animals.getList();
            assertThat(animalsList, is(empty()));
        }
    }

    @Test
    public void listAnimals() throws FileNotFoundException {

        this.animals.addAnimal("aardvark");

        ArrayList<String> animalsList = this.animals.getList();

        assertThat(animalsList.get(0), is("aardvark"));
        assertThat(animalsList, not(contains("bear")));

    }

    @Test
    public void animalExists() throws FileNotFoundException {

        String animal = "aardvark";
        this.animals.addAnimal(animal);

        boolean animalExists = this.animals.animalExists(animal);
        assertThat(animalExists, is(true));

        animal = "lemur";
        animalExists = this.animals.animalExists(animal);
        assertThat(animalExists, is(false));
    }

    @Test
    public void addAnimal() throws FileNotFoundException {
        String newAnimal = "aardvark";

        boolean status = this.animals.addAnimal(newAnimal);
        assertThat(status, is(true));

        status = this.animals.addAnimal(newAnimal);
        assertThat(status, is(false));
    }

    @Test
    public void addTwoAnimals() throws FileNotFoundException {
        String newAnimal = "mouse";
        boolean status = this.animals.addAnimal(newAnimal);
        assertThat(status, is(true));

        newAnimal = "zebra";
        status = this.animals.addAnimal(newAnimal);
        assertThat(status, is(true));

        assertThat(this.animals.animalExists("mouse"), is(true));
        assertThat(this.animals.animalExists("zebra"), is(true));
    }

    @Test
    public void deleteAnimal() throws IOException {

        this.animals.addAnimal("aardvark");
        this.animals.addAnimal("horse");

        boolean status = this.animals.deleteAnimal("aardvark");
        assertThat(status, is(true));

        boolean animalExists = this.animals.animalExists("aardvark");
        assertThat(animalExists, is(false));
        animalExists = this.animals.animalExists("horse");
        assertThat(animalExists, is(true));

    }

    @Test
    public void deleteNonExistentAnimal() throws IOException {

        boolean status = this.animals.deleteAnimal("mouse");
        assertThat(status, is(false));

    }

}
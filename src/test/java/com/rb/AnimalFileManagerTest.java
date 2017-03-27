package com.rb;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by ruth on 13/03/17.
 */
public class AnimalFileManagerTest {

    private AnimalFileManager animalFileManager = new AnimalFileManager();
    private final String FILEPATH = "src/test/resources/test_animals";


    @Before
    public void checkFileExists() throws FileNotFoundException {
        File animalsFile = new File(FILEPATH);
        assertThat(animalsFile.exists(), is(true));

        this.animalFileManager.setAnimalsFile(animalsFile);
    }

    @After
    public void clearFile() throws FileNotFoundException {

        ArrayList<String>animalsList = this.animalFileManager.getList();
        if (!animalsList.isEmpty()) {

            PrintWriter pw = new PrintWriter(FILEPATH);
            pw.close();

            animalsList = this.animalFileManager.getList();
            assertThat(animalsList, is(empty()));
        }
    }

    @Test
    public void getAnimalsMap() throws FileNotFoundException {

        this.animalFileManager.addAnimal("aardvark");

        Map<Integer, String> animalsMap = this.animalFileManager.getMap();

        assertThat(animalsMap.size(), is(1));
        assertThat(animalsMap.containsKey(1), is(true));
        assertThat(animalsMap.get(1), is(equalTo("aardvark")));
    }

    @Test
    public void animalExists() throws FileNotFoundException {

        String animal = "aardvark";
        this.animalFileManager.addAnimal(animal);

        boolean animalExists = this.animalFileManager.animalExists(animal);
        assertThat(animalExists, is(true));

        animal = "lemur";
        animalExists = this.animalFileManager.animalExists(animal);
        assertThat(animalExists, is(false));
    }

    @Test
    public void animalExistsByIndex() throws FileNotFoundException {

        String animal = "aardvark";
        this.animalFileManager.addAnimal(animal);

        boolean animalExists = this.animalFileManager.animalExists(animal);
        assertThat(animalExists, is(true));

        animalExists = this.animalFileManager.animalExists(1);
        assertThat(animalExists, is(true));

        animalExists = this.animalFileManager.animalExists(2);
        assertThat(animalExists, is(false));
    }

    @Test
    public void getAnimalByIndex() throws FileNotFoundException {

        String animal = "aardvark";
        this.animalFileManager.addAnimal(animal);

        boolean animalExists = this.animalFileManager.animalExists(animal);
        assertThat(animalExists, is(true));

        animalExists = this.animalFileManager.animalExists(1);
        assertThat(animalExists, is(true));

        String animalInFile = this.animalFileManager.getAnimalByIndex(1);
        assertThat(animalInFile, is("aardvark"));
    }

    @Test
    public void getAnimalByIndex2() throws FileNotFoundException {

        String animal1 = "aardvark";
        this.animalFileManager.addAnimal(animal1);
        String animal2 = "monkey";
        this.animalFileManager.addAnimal(animal2);

        assertThat(this.animalFileManager.animalExists(animal1), is(true));
        assertThat(this.animalFileManager.animalExists(animal2), is(true));

        assertThat(this.animalFileManager.animalExists(1), is(true));
        assertThat(this.animalFileManager.animalExists(2), is(true));

        assertThat(this.animalFileManager.getAnimalByIndex(1), is(animal1));
        assertThat(this.animalFileManager.getAnimalByIndex(2), is(animal2));
    }

    @Test
    public void addAnimal() throws FileNotFoundException {
        String newAnimal = "aardvark";

        boolean status = this.animalFileManager.addAnimal(newAnimal);
        assertThat(status, is(true));

        status = this.animalFileManager.addAnimal(newAnimal);
        assertThat(status, is(false));
    }

    @Test
    public void addTwoAnimals() throws FileNotFoundException {
        String newAnimal = "mouse";
        boolean status = this.animalFileManager.addAnimal(newAnimal);
        assertThat(status, is(true));

        newAnimal = "zebra";
        status = this.animalFileManager.addAnimal(newAnimal);
        assertThat(status, is(true));

        assertThat(this.animalFileManager.animalExists("mouse"), is(true));
        assertThat(this.animalFileManager.animalExists("zebra"), is(true));
    }

    @Test
    public void deleteAnimal() throws IOException {

        this.animalFileManager.addAnimal("aardvark");
        this.animalFileManager.addAnimal("horse");

        boolean status = this.animalFileManager.deleteAnimal("aardvark");
        assertThat(status, is(true));

        boolean animalExists = this.animalFileManager.animalExists("aardvark");
        assertThat(animalExists, is(false));
        animalExists = this.animalFileManager.animalExists("horse");
        assertThat(animalExists, is(true));

    }

    @Test
    public void deleteNonExistentAnimal() throws IOException {

        boolean status = this.animalFileManager.deleteAnimal("mouse");
        assertThat(status, is(false));

    }

}
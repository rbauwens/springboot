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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by ruth on 13/03/17.
 */
public class NorfolkWillsManagerTest {

    private WillFileManager willsManager = new WillFileManager();
    private final String FILEPATH = "src/main/resources/NorfolkWills";


    @Before
    public void checkFileExists() throws FileNotFoundException {
        File willsFile = new File(FILEPATH);
        assertThat(willsFile.exists(), is(true));

        this.willsManager.setWillFile(willsFile);
    }

    @Test
    public void getmaxIndex() throws FileNotFoundException {

        int maxIndex = this.willsManager.getMaxIndex();

        assertThat(maxIndex, is(equalTo(22737)));

    }

    @Test
    public void getFirstEntry() throws FileNotFoundException {

        Will will = this.willsManager.getFirstEntry();

        assertThat(will.name, is(equalTo("Basylly, John, of Hindolveston")));
        assertThat(will.date, is(equalTo(1452)));
        assertThat(will.reference, is(equalTo("DCN 67/11 m 1d")));

    }

    @Test
    public void getEntry2030() throws Exception {

        Will will = this.willsManager.getEntry(2030);

        assertThat(will.name, is(equalTo("Taylor (Talyor), Katherine, widow, of Buxton")));
        assertThat(will.date, is(equalTo(1459)));
        assertThat(will.reference, is(equalTo("NCC will register Brosyard 142")));

    }

    @Test(expected=Exception.class)
    public void getEntry22728() throws Exception {

        Will will = this.willsManager.getEntry(22728);

//        assertThat(will.name, is(equalTo("Willson, Henry, of Guist")));
//        assertThat(will.date, is(equalTo(1529-1536)));
//        assertThat(will.reference, is(equalTo("ANW, will register, Bakon, fo. 398")));

    }



    @Test
    public void testRegex() throws Exception {
        String testString = "Taylor (Talyor), Katherine, widow, of Buxton 1459 NCC will register Brosyard 142";
        String name = "";
        String reference = "";
        String dateString = "";

        Pattern pattern = Pattern.compile("\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(testString);
        if (matcher.find()) {
            if (matcher.groupCount() == 0) {
                dateString = matcher.group(0);
                name = testString.split("\\d\\d\\d\\d")[0];
                reference = testString.split("\\d\\d\\d\\d")[1];

            }
            else {
                throw new Exception("Haven't implemented date ranges yet");
            }
        }
        int date = Integer.parseInt(dateString);
        name = name.trim();
        reference = reference.trim();

        assertThat(name, is(equalTo("Taylor (Talyor), Katherine, widow, of Buxton")));
        assertThat(date, is(equalTo(1459)));
        assertThat(reference, is(equalTo("NCC will register Brosyard 142")));

    }


//    @Test
//    public void animalExists() throws FileNotFoundException {
//
//        String animal = "aardvark";
//        this.willsManager.addAnimal(animal);
//
//        boolean animalExists = this.willsManager.animalExists(animal);
//        assertThat(animalExists, is(true));
//
//        animal = "lemur";
//        animalExists = this.willsManager.animalExists(animal);
//        assertThat(animalExists, is(false));
//    }

//    @Test
//    public void animalExistsByIndex() throws FileNotFoundException {
//
//        String animal = "aardvark";
//        this.willsManager.addAnimal(animal);
//
//        boolean animalExists = this.willsManager.animalExists(animal);
//        assertThat(animalExists, is(true));
//
//        animalExists = this.willsManager.animalExists(1);
//        assertThat(animalExists, is(true));
//
//        animalExists = this.willsManager.animalExists(2);
//        assertThat(animalExists, is(false));
//    }
//
//    @Test
//    public void getAnimalByIndex() throws FileNotFoundException {
//
//        String animal = "aardvark";
//        this.willsManager.addAnimal(animal);
//
//        boolean animalExists = this.willsManager.animalExists(animal);
//        assertThat(animalExists, is(true));
//
//        animalExists = this.willsManager.animalExists(1);
//        assertThat(animalExists, is(true));
//
//        String animalInFile = this.willsManager.getAnimalNameByIndex(1);
//        assertThat(animalInFile, is("aardvark"));
//    }
//
//    @Test
//    public void getAnimalByIndex2() throws FileNotFoundException {
//
//        String animal1 = "aardvark";
//        this.willsManager.addAnimal(animal1);
//        String animal2 = "monkey";
//        this.willsManager.addAnimal(animal2);
//
//        assertThat(this.willsManager.animalExists(animal1), is(true));
//        assertThat(this.willsManager.animalExists(animal2), is(true));
//
//        assertThat(this.willsManager.animalExists(1), is(true));
//        assertThat(this.willsManager.animalExists(2), is(true));
//
//        assertThat(this.willsManager.getAnimalNameByIndex(1), is(animal1));
//        assertThat(this.willsManager.getAnimalNameByIndex(2), is(animal2));
//    }
}
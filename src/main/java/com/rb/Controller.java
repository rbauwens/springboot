package com.rb;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.rb.Constants;

/**
 * Created by ruth on 20/02/17.
 */
@RestController
public class Controller {

    @ApiOperation(value = "getGreeting", nickname = "getGreeting")
    @RequestMapping(method = RequestMethod.GET, path = "/greeting", produces = "application/html")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue = "Ruth")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class)})
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }


    @ApiOperation(value = "list", nickname = "list")
    @RequestMapping(method = RequestMethod.GET, path = "/list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class)})
    public String list() {
        try {
            Animals animals = new Animals();
            File animalsFile = new File(Constants.ANIMALS_LIST);
            animals.setAnimalsFile(animalsFile);
            ArrayList<String> animalsList = animals.getList();
            return animalsList.toString();
        } catch (FileNotFoundException ex) {
            return "Animal File does not exist. Check configuration";
        }

    }

    @ApiOperation(value = "addAnimal", nickname = "addAnimal")
    @RequestMapping(method = RequestMethod.POST, path = "/animals/add")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class)})
    public String addAnimalController(@RequestBody(required = true) AnimalType newAnimal){
        try {
            Animals animals = new Animals();
            File animalsFile = new File(Constants.ANIMALS_LIST);
            animals.setAnimalsFile(animalsFile);

            boolean status = animals.addAnimal(newAnimal.name);

            if (status) {
                return "Success!";
            } else {
                return "Failure!";
            }
        } catch (FileNotFoundException ex) {
            return "Animal File does not exist. Check configuration";
        }
    }

    @ApiOperation(value = "deleteAnimal", nickname = "deleteAnimal")
    @RequestMapping(method = RequestMethod.POST, path = "/animals/delete")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class)})
    public String deleteAnimalController(@RequestBody(required = true) AnimalType animal) throws IOException {
        try {
            Animals animals = new Animals();
            File animalsFile = new File(Constants.ANIMALS_LIST);
            animals.setAnimalsFile(animalsFile);

            boolean status = animals.deleteAnimal(animal.name);

            if (status) {
                return "Success!";
            } else {
                return "Failure!";
            }
        } catch (FileNotFoundException ex) {
            return "Animal File does not exist. Check configuration";
        }
    }

}

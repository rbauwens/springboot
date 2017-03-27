package com.rb;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

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


    @ApiOperation(value = "map", nickname = "map")
    @RequestMapping(method = RequestMethod.GET, path = "/map")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class)})
    public String map() {
        try {
            AnimalFileManager animalFileManager = new AnimalFileManager();
            File animalsFile = new File(Constants.ANIMALS_LIST);
            animalFileManager.setAnimalsFile(animalsFile);
            Map<Integer, String> animalsMap = animalFileManager.getMap();
            String returnString= animalsMap.toString();
            return animalsMap.toString();
        } catch (FileNotFoundException ex) {
            return "Animal File does not exist. Check configuration";
        }
    }

    @ApiOperation(value = "getAnimal", nickname = "getAnimal")
    @RequestMapping(method = RequestMethod.GET, path = "/animals/{index}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class)})
    public String getAnimal(@PathVariable Integer index) {
        try {
            AnimalFileManager animalFileManager = new AnimalFileManager();
            File animalsFile = new File(Constants.ANIMALS_LIST);
            animalFileManager.setAnimalsFile(animalsFile);

            if (!animalFileManager.animalExists(index)) {
             return String.format("No animal with index %d exists in the file", index);
            }
            return animalFileManager.getAnimalByIndex(index);

        } catch (FileNotFoundException ex) {
            return "Animal File does not exist. Check configuration";
        }
    }

    @ApiOperation(value = "addAnimal", nickname = "addAnimal")
    @RequestMapping(method = RequestMethod.POST, path = "/animals/add")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class)})
    public String addAnimalController(@RequestBody(required = true) Animal newAnimal){
        try {
            AnimalFileManager animalFileManager = new AnimalFileManager();
            File animalsFile = new File(Constants.ANIMALS_LIST);
            animalFileManager.setAnimalsFile(animalsFile);

            boolean status = animalFileManager.addAnimal(newAnimal.name);

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
    public String deleteAnimalController(@RequestBody(required = true) Animal animal) throws IOException {
        try {
            AnimalFileManager animalFileManager = new AnimalFileManager();
            File animalsFile = new File(Constants.ANIMALS_LIST);
            animalFileManager.setAnimalsFile(animalsFile);

            boolean status = animalFileManager.deleteAnimal(animal.name);

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

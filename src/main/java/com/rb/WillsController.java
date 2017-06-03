package com.rb;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;


@RestController
public class WillsController {

    @ApiOperation(value = "getWill", nickname = "getWill")
    @RequestMapping(method = RequestMethod.GET, path = "/will/{index}", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = WillResponse.class)})
    public WillResponse getWill(@PathVariable Integer index) {

        WillResponse willResponse = new WillResponse();

        try {
            WillFileManager willFileManager = new WillFileManager();
            File willsFile = new File(Constants.WILLS_FILE);
            willFileManager.setWillFile(willsFile);

            int numberEntries = willFileManager.getMaxIndex();
            if (index > numberEntries) {
                willResponse.error = String.format("Requested index %d exceeds maximum number of records held (%d)", index, numberEntries);
                return willResponse;
            }
            willResponse.will = willFileManager.getEntry(index);
            return willResponse;

        } catch (FileNotFoundException ex) {
            willResponse.error = "Will File does not exist. Check configuration";
            return willResponse;
        }
        catch (Exception ex) {
            willResponse.error = ex.getMessage();
            return willResponse;
        }
    }
}

package utility;

import exceptions.CoordinatesException;
import exceptions.FlatException;
import exceptions.HouseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import task_classes.Coordinates;
import task_classes.Flat;
import task_classes.House;
import task_classes.Transport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * Reads the JSON file and returns ready-made collection
 * **/
public class FileManager {
    private String filePath;
    public FileManager(String filePath){
        this.filePath = filePath;
    }
    /**
     * Returns the array of JSON objects from .json file
     * @return JSONArray
     * **/
    public JSONArray getJSON(){
        JSONParser parser = new JSONParser();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))){
            JSONArray result = (JSONArray) parser.parse(fileReader);
            return result;
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("No such file found");
            return null;
        } catch (ParseException e){
            System.out.println(e.getMessage());
            System.out.println("Failed to parse the file");
            return null;
        }
    }
    /**
     * Reads JSON and returns the collection
     * @return HashMap
     * **/
    public HashMap<Integer, Flat> loadHashMap(){
        HashMap<Integer,Flat> result = new HashMap<Integer,Flat>();
        JSONArray arr = getJSON();
        try{
            if (arr == null) throw new NullPointerException();
        } catch (NullPointerException e){
            System.out.println("Failed to load collection from file");
            return null;
        }
        for (Object objFlat : arr){
            JSONObject JSONFlat = (JSONObject) objFlat;
            try{
                JSONObject JSONCoordinates = (JSONObject) JSONFlat.get("coordinates");
                JSONObject JSONHouse = (JSONObject) JSONFlat.get("house");

                Coordinates coordinates = new Coordinates(
                        (Double)JSONCoordinates.get("x"),
                        (Long)JSONCoordinates.get("y")
                );
                House house = new House(
                        (String)JSONHouse.get("name"),
                        Convertor.longToInteger((Long)JSONHouse.get("year")),
                        Convertor.longToInteger((Long)JSONHouse.get("numberOfFloors")),
                        (long)JSONHouse.get("numberOfFlatsOnFloor"),
                        Convertor.longToInteger((Long)JSONHouse.get("numberOfLifts"))
                );
                Transport transport = Transport.valueOf((String)JSONFlat.get("transport"));
                Flat flat = new Flat(
                        (String)JSONFlat.get("name"),
                        coordinates,
                        (long)JSONFlat.get("area"),
                        Convertor.longToInteger((Long)JSONFlat.get("numberOfRooms")),
                        (Double)JSONFlat.get("price"),
                        Convertor.longToInteger((Long)JSONFlat.get("livingSpace")),
                        transport,
                        house
                );
                result.put(flat.getId(),flat);

            } catch (CoordinatesException | HouseException | FlatException e){
                e.getMessage();
            }

        }
        return result;
    }
}

package com.MatrixEngine.EngineFunctions;

import com.MatrixEngine.DataTypes.SaveData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class DataRegistry {
    private static ArrayList<SaveData> data = new ArrayList<>();

    public static void save(String filePath, String fileName) {
        try {
            File file = new File(filePath + fileName + ".txt");
            Writer writer = new FileWriter(filePath + fileName + ".txt");
            for (SaveData d : data) {
                writer.write(d.save() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Unable to save data.");
            e.printStackTrace();
        }
    }

    public static void load(String filePath, String fileName) {
        try {
            File file = new File(filePath + fileName + ".txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                data.add(new SaveData(line.split("-")[0], line.split("-")[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addData(SaveData Data) {
        data.add(Data);
    }

    public static SaveData getData(String name) {
        for (SaveData d : data) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }
}

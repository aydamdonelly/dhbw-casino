package com.example.casino.model;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class FileHandler {
    public abstract float load_num(String path);


    public void save_num(String path, Float num) {
        try{
            Path scoreFile = Paths.get(path);
            Files.write(scoreFile, new byte[0]);
            if (!Files.exists(scoreFile))
                Files.createFile(scoreFile);

            BufferedWriter meinWriter =
                    Files.newBufferedWriter(scoreFile, StandardOpenOption.APPEND);

            meinWriter.write(Float.toString(num));
            meinWriter.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

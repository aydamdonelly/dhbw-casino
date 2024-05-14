package com.example.casino.model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KontostandSaveHandler extends FileHandler{
    @Override
    public float load_num(String path) {
        try{
            Path scoreFile = Paths.get(path);

            if (!Files.exists(scoreFile))
                Files.createFile(scoreFile);

            BufferedReader meinReader = Files.newBufferedReader(scoreFile);

            String zeile = meinReader.readLine();

            if (zeile != null){
                return Float.parseFloat(zeile);
            }

        } catch (Exception e){
        }
        return 1000f;
    }
}

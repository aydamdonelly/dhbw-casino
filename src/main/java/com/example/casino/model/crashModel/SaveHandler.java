package com.example.casino.model.crashModel;

import com.example.casino.model.FileHandler;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class SaveHandler extends FileHandler{

//    MergeSortDesc sorting = new MergeSortDesc();
//
//    public List<Float> save_score(String path, List<Float> scores){
//        try{
//            Path scoreFile = Paths.get(path);
//            Files.write(scoreFile, new byte[0]);
//            if (!Files.exists(scoreFile))
//                Files.createFile(scoreFile);
//
//
//            BufferedWriter meinWriter =
//                    Files.newBufferedWriter(scoreFile, StandardOpenOption.APPEND);
//
//            scores = sorting.mergeSort(scores);
//            meinWriter.write(Float.toString(scores.getFirst()));
//            meinWriter.close();
//            return scores;
//        } catch (Exception e){
//            System.out.println(e);
//        }
//        return scores;
//    }

//    public void save_num(String path, Float num){
//        try{
//            Path scoreFile = Paths.get(path);
//            Files.write(scoreFile, new byte[0]);
//            if (!Files.exists(scoreFile))
//                Files.createFile(scoreFile);
//
//
//            BufferedWriter meinWriter =
//                    Files.newBufferedWriter(scoreFile, StandardOpenOption.APPEND);
//
//            meinWriter.write(Float.toString(num));
//            meinWriter.close();
//        } catch (Exception e){
//            System.out.println(e);
//        }
//    }

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
        return 0f;
    }

//    @Override
//    public void save_num(String path, Float num) {
//        try{
//            Path scoreFile = Paths.get(path);
//            Files.write(scoreFile, new byte[0]);
//            if (!Files.exists(scoreFile))
//                Files.createFile(scoreFile);
//
//            BufferedWriter meinWriter =
//                    Files.newBufferedWriter(scoreFile, StandardOpenOption.APPEND);
//
//            meinWriter.write(Float.toString(num));
//            meinWriter.close();
//        } catch (Exception e){
//            System.out.println(e);
//        }
//    }

//    public Float load_num(String path){
//        try{
//            Path scoreFile = Paths.get(path);
//
//            if (!Files.exists(scoreFile))
//                Files.createFile(scoreFile);
//
//            BufferedReader meinReader = Files.newBufferedReader(scoreFile);
//
//            String zeile = meinReader.readLine();
//
//            if (zeile != null){
//                return Float.parseFloat(zeile);
//            }
//
//        } catch (Exception e){
//            return null;
//        }
//        return null;
//    }

}
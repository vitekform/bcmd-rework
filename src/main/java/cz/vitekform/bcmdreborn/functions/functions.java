package cz.vitekform.bcmdreborn.functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class functions {

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean isGod(Player p){
        double befHP = p.getHealth();
        p.damage(0.1);
        double nowHP = p.getHealth();
        if (befHP - nowHP == 0D){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isPluginPresent(String plugin_name){
        boolean ispresent;
        if (Bukkit.getPluginManager().getPlugin(plugin_name) != null){
            ispresent = true;
        }
        else {
            ispresent = false;
        }
        return ispresent;
    }

    public static int getNumsInString(String s){
        String[] chars = s.split("(?!^)");
        String finString = "";
        for (int i = 0; i != chars.length; i++){
            if (isInteger(chars[i])){
                finString = finString + chars[i];
            }
        }
        return Integer.parseInt(finString);
    }

    public static String getValueFromFile(File f, String query){
        Scanner scn;
        try {
            scn = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scn.hasNextLine()){
            String line = scn.nextLine();
            if (line.contains(query)){
                return line.replace(query + ": ", "");
            }
        }
        return null;
    }

    public static String getAllLinesFromFile(File f){
        String lines = "";
        Scanner scn;
        try {
            scn = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scn.hasNextLine()){
            lines = lines + "\n" + scn.nextLine();
        }
        return lines;
    }
}

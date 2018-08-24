package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Locations locations = new Locations();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> directionMap = new HashMap<>();
        directionMap.put("WEST", "W");
        directionMap.put("NORTH", "N");
        directionMap.put("EAST", "E");
        directionMap.put("SOUTH", "S");
        directionMap.put("QUIT", "Q");

        Location currentLocation = locations.getLocation(1);
        while (true) {
            System.out.println(currentLocation.getDescription());
            if (currentLocation.getLocationID() == 0) {
                break;
            }
            Map<String, Integer> exits = currentLocation.getExits();
            System.out.print("Available exits are ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();
            String direction = scanner.nextLine().toUpperCase();
            if (direction.length() > 1) {
                String[] keyWord = direction.split(" ");
                for (String word : keyWord) {
                    if (exits.containsKey(directionMap.get(word))) {
                        direction = directionMap.get(word);
                        break;
                    }
                }
            }
            if (exits.containsKey(direction)) {
                currentLocation = locations.getLocation(exits.get(direction));
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
        locations.close();
    }
}


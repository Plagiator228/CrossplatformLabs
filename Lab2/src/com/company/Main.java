package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DecorationManager manager = new DecorationManager();
        manager.initializeList();
        manager.outputDecorationsList();
        Scanner scanner = new Scanner(System.in);
        char job, rule, sort_order;
        String typesOfDecorations;
        while (true)
        {
            System.out.println("What do you want to do? (s - sorting, f - search, q - quit)");
            job = scanner.next().charAt(0);
            if (job =='s')
            {
                System.out.println("Which is rule of sorting? (c - color, p - price)");
                rule = scanner.next().charAt(0);
                System.out.println("Which is order of sorting? (a - ascending, d - descending)");
                sort_order = scanner.next().charAt(0);
                if (rule == 'c') {
                    if (sort_order == 'a') {
                        manager.sortByColorAscending();
                    } else if (sort_order == 'd') {
                        manager.sortByColorDescending();
                    } else {
                        System.out.println("Wrong letter try again");
                        continue;
                    }
                } else if (rule == 'p') {
                    if (sort_order == 'a') {
                        manager.sortByPriceAscending();
                    } else if (sort_order == 'd') {
                        manager.sortByPriceDescending();
                    } else {
                        System.out.println("Wrong letter try again");
                        continue;
                    }
                } else {
                    System.out.println("Wrong letter try again");
                    continue;
                }
            } else if ( job == 'f') {
                System.out.println("Which is type of decoration you want to see? (For_Tree_Inside, For_Tree_Outside, For_Apartment)");
                scanner.nextLine();
                typesOfDecorations = scanner.nextLine();
                manager.findCertainTypeDecoration(typesOfDecorations);
            } else if ( job == 'q') {
                break;
            } else {
                System.out.println("Wrong letter try again");
            }
        }
    }
}

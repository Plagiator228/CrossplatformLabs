package com.company;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        LinkedList<Employers> employers = new LinkedList<Employers>();
        ReadFromFile(employers, "Employers.txt");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Which task to do(1,2,3,4):");
            int menu = scanner.nextInt();
            switch (menu) {
                case (1):
                    Map<String, List<Employers>> map = new LinkedHashMap<String, List<Employers>>();
                    for (Employers i : employers) {
                        if (!map.containsKey(i.getPosition()))
                            map.put(i.getPosition(), new LinkedList<Employers>());
                        map.get(i.getPosition()).add(i);
                    }
                    Map<String, List<Employers>> latest = new LinkedHashMap<String, List<Employers>>();
                    Map<String, List<Employers>> earliest = new LinkedHashMap<String, List<Employers>>();
                    List<Employers> tmp;
                    for (Map.Entry<String, List<Employers>> entry : map.entrySet()) {
                        if (!earliest.containsKey(entry.getKey()) || !latest.containsKey(entry.getKey())) {
                            latest.put(entry.getKey(), new LinkedList<Employers>());
                            earliest.put(entry.getKey(), new LinkedList<Employers>());
                        }
                        tmp = new LinkedList<Employers>(entry.getValue());
                        Employers latestBirthDate = Collections.max(tmp, new Employers.AgeComparator());
                        Employers earliestBirthDate = Collections.min(tmp, new Employers.AgeComparator());
                        for (Employers i : tmp) {
                            if (i.getBirthDate().compareTo(latestBirthDate.getBirthDate()) == 0)
                                latest.get(i.getPosition()).add(i);
                            if (i.getBirthDate().compareTo(earliestBirthDate.getBirthDate()) == 0)
                                earliest.get(i.getPosition()).add(i);
                        }
                    }
                    tmp = null;
                    System.out.println("Youngest employers:");
                    ResultOutput(latest, 1);
                    latest.clear();
                    System.out.println("Oldest employers:");
                    ResultOutput(earliest, 1);
                    earliest.clear();
                    map.clear();
                    break;

                case (2):
                    double minSalary = Collections.min(employers, new Employers.SalaryComparator()).getSalary();
                    double maxSalary = Collections.max(employers, new Employers.SalaryComparator()).getSalary();
                    System.out.println("Smallest salary: " + minSalary);
                    System.out.println("Biggest salary: " + maxSalary);
                    map = new LinkedHashMap<String, List<Employers>>();
                    String key;
                    for (Employers i : employers) {
                        if (i.getSalary() < 10000)
                            key = "low";
                        else if (i.getSalary() < 20000)
                            key = "average";
                        else
                            key = "high";
                        if (!map.containsKey(key)) {
                            map.put(key, new LinkedList<Employers>());
                        }
                        map.get(key).add(i);
                    }
                    ResultOutput(map, 2);
                    map.clear();
                    break;

                case (3):
                    employers = new LinkedList<Employers>();
                    List<Employers> employers2 = new LinkedList<Employers>();
                    ReadFromFile(employers, "Employers.txt");
                    ReadFromFile(employers2, "Employers2.txt");
                    Set<Employers> set = new LinkedHashSet<Employers>();
                    set.addAll(employers);
                    set.addAll(employers2);
                    ResultOutput(set);
                    System.out.print("Input year of birth: ");
                    int yearOfBirth = scanner.nextInt();
                    tmp = new LinkedList<Employers>();
                    for (Employers i : set) {
                        if (i.getBirthDate().getYear() + 1900 < yearOfBirth)
                            tmp.add(i);
                    }
                    set.removeAll(tmp);
                    tmp.clear();
                    ResultOutput(set);
                    break;
                case (4):
                    return;
            }
        }
    }

    private static void HeadLineOutput(){
        LineOutput();
        System.out.format("| %20s | %10s | %20s | %10s |\n", "Last Name", "Position",
                "Birth Date", "Salary");
        LineOutput();
    }

    private static void LineOutput(){
        for(int i = 0; i < 73; i++)
            System.out.print("-");
        System.out.print("\n");
    }

    private static void ResultOutput(Set<Employers> result){
        HeadLineOutput();
        List<Employers> tmp;
        for(Employers i : result) {
            i.Output();
            LineOutput();
        }
    }

    private static void ResultOutput(Map<String, List<Employers>> result, int task){
        HeadLineOutput();
        List<Employers> tmp;
        for (Map.Entry<String, List<Employers>> entry : result.entrySet()) {
            if(task == 1)
                System.out.format("| %20s | %10s | %20s | %10s |\n", "", entry.getKey(), "", "");
            else
                System.out.format("| %20s | %10s | %20s | %10s |\n", "", "", "", entry.getKey());
            LineOutput();
            tmp = entry.getValue();
            for(Employers i : tmp) {
                i.Output();
                LineOutput();
            }
        }
    }

    private static void ReadFromFile(List<Employers> inputList, String fileName)
    {
        try {
            File myFile = new File(fileName);
            Scanner scanner = new Scanner(myFile);
            Employers input;
            while (scanner.hasNextLine()) {
                input = new Employers();
                input.setLastName(scanner.next());
                input.setPosition(scanner.next());
                input.setBirthDate(new Date(
                        scanner.nextInt(),
                        scanner.nextInt(),
                        scanner.nextInt()
                ));
                input.setSalary(scanner.nextDouble());
                inputList.add(input);
                if (scanner.hasNextLine())
                    scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File error");
            e.printStackTrace();
        }
    }
}

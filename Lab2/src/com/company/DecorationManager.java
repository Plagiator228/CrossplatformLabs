package com.company;

import java.util.*;

public class DecorationManager {
    private List<ChristmasDecoration> decorations;

    public static class PriceComparator implements Comparator<ChristmasDecoration> {
        public int compare(ChristmasDecoration decoration1, ChristmasDecoration decoration2) {
            return Double.compare(decoration1.getPrice(), decoration2.getPrice());
        }
    }

    public class ColorComparator implements Comparator<ChristmasDecoration> {
        String[] colorArr = new String[] {"Multicolored", "White", "Red", "Orange", "Yellow", "Green", "Blue", "Purple"};
        public int compare(ChristmasDecoration decoration1, ChristmasDecoration decoration2) {
            int index1 = 0, index2 = 0, tmp = 0;
            for(int i = 0; i < colorArr.length && tmp < 2; i++) {
                if(decoration1.getColor() == colorArr[i]) {
                    index1 = i;
                    tmp++;
                }
                if(decoration2.getColor() == colorArr[i])
                {
                    index2 = i;
                    tmp++;
                }
            }
            if(index1 > index2) {
                return 1;
            } else if(index1 == index2) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    public void initializeList() {
        decorations = new ArrayList<ChristmasDecoration>();
        decorations.add(new Tinsel("Tinsel1", "For_Apartment", "Red", 22.9,
                2, "Radial"));
        decorations.add(new Garland("Garland1", "For_Tree_Outside", "Multicolored", 759,
                20, 5));
        decorations.add(new ChristmasTreeToy("TreePike1", "For_Tree_Inside", "Yellow", 41.5,
                "Glass", 0.2));
        decorations.add(new ChristmasTreeToy("ChristmasBall1", "For_Tree_Inside", "Green", 15,
                "Glass", 0.1));
        decorations.add(new ChristmasTreeToy("ChristmasBall2", "For_Tree_Inside", "Purple", 10,
                "Plastic", 0.05));
        decorations.add(new Tinsel("Tinsel2", "For_Tree_Inside", "Multicolored", 49.9,
                4, "Radial"));
        decorations.add(new Garland("Garland2", "For_Apartment", "Blue", 210,
                2, 3));
        decorations.add(new ChristmasTreeToy("TreePike2", "For_Tree_Outside", "Red", 300,
                "Glass", 0.7));
        decorations.add(new ChristmasTreeToy("ChristmasBall3", "For_Tree_Outside", "White", 100,
                "Plastic", 0.3));
        decorations.add(new Tinsel("Tinsel3", "For_Apartment", "Orange", 31.8,
                2, "Linear"));
    }

    public void findCertainTypeDecoration(String types) {
        List<ChristmasDecoration> result = new ArrayList<ChristmasDecoration>();
        String[] typeOfDecoration = types.split("[ ,]");
        for (ChristmasDecoration decoration : decorations) {
            for(int i = 0; i < typeOfDecoration.length; i++) {
                if(typeOfDecoration[i].equals(decoration.getDecorationType()))
                {
                    result.add(decoration);
                    break;
                }
            }
        }
        DecorationManager.outputDecorationsList(result);
    }

    public void sortByPriceAscending() {
        List<ChristmasDecoration> result = new ArrayList<ChristmasDecoration>(decorations);
        result.sort(new PriceComparator());
        DecorationManager.outputDecorationsList(result);
    }

    public void sortByPriceDescending() {
        List<ChristmasDecoration> result = new ArrayList<ChristmasDecoration>(decorations);
        result.sort(Collections.reverseOrder(new Comparator<>() {
            public int compare(ChristmasDecoration decoration1, ChristmasDecoration decoration2) {
                return Double.compare(decoration1.getPrice(), decoration2.getPrice());
            }
        }));
        DecorationManager.outputDecorationsList(result);
    }

    public void sortByColorAscending() {
        List<ChristmasDecoration> result = new ArrayList<ChristmasDecoration>(decorations);
        result.sort(new ColorComparator());
        DecorationManager.outputDecorationsList(result);
    }

    public void sortByColorDescending() {
        List<ChristmasDecoration> result = new ArrayList<ChristmasDecoration>(decorations);
        String[] colorArr = new String[] {"Multicolored" ,"White", "Red", "Orange", "Yellow", "Green", "Blue", "Purple"};
        result.sort(Collections.reverseOrder((decoration1, decoration2) -> {
                int index1 = 0, index2 = 0, tmp = 0;
                for(int i = 0; i < colorArr.length && tmp < 2; i++) {
                    if(decoration1.getColor() == colorArr[i]) {
                        index1 = i;
                        tmp++;
                    }
                    if(decoration2.getColor() == colorArr[i])
                    {
                        index2 = i;
                        tmp++;
                    }
                }
                if(index1 > index2) {
                    return 1;
                } else if(index1 == index2) {
                    return 0;
                } else {
                    return -1;
                }
        }));
        DecorationManager.outputDecorationsList(result);
    }

    public void outputDecorationsList() {
        if(decorations.isEmpty())
            return;
        ChristmasDecoration.printLine();
        System.out.format("| %20s | %17s | %20s | %15s | %11s |\n", "ClassName", "Decoration Name",
                "Decoration Type", "Color", "Price");
        ChristmasDecoration.printLine();
        for (ChristmasDecoration decoration : decorations) {
            decoration.outputInfo();
        }
    }

    static private void outputDecorationsList(List<ChristmasDecoration> decorations) {
        if(decorations.isEmpty())
            return;
        ChristmasDecoration.printLine();
        System.out.format("| %20s | %17s | %20s | %15s | %11s |\n", "ClassName", "Decoration Name",
                "Decoration Type", "Color", "Price");
        ChristmasDecoration.printLine();
        for (ChristmasDecoration decoration : decorations) {
            decoration.outputInfo();
        }
    }
}

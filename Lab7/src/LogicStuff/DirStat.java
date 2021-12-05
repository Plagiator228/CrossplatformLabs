package LogicStuff;

import java.io.File;
import java.util.Arrays;

public class DirStat {
    String DirPath;
    String SearchPatter;
    File[] FileList;
    long FileSize;

    public DirStat(String DirPath, String SearchPattern, long FileSize){
        this.DirPath = DirPath;
        this.SearchPatter = SearchPattern;
        this.FileSize = FileSize;
        File fileName = new File(DirPath);
        FileList = fileName.listFiles();
        if(FileList==null){
            FileList = new File[0];
        }
    }

    public String[] getDirectories(){
        return Arrays.stream(FileList).filter(e->e.isDirectory()).map(e->e.getAbsolutePath()).toArray(String[]::new);
    }

    public String getStatistics(){
        int TotalFileCount = Arrays.stream(FileList).filter(e->e.isFile()).toArray().length;
        int CorrectFileSizeCount =  Arrays.stream(FileList).filter(e->(e.isFile() && e.length()>FileSize)).toArray().length;
        int SubDirsCount = getDirectories().length;
        int MatchingPatternCount = Arrays.stream(FileList).filter(e->(e.isFile() && e.getName().contains(SearchPatter))).toArray().length;
        return String.format("DIRECTORY PATH: %30s   \n" +
                "STATISTICS: \n" +
                "Total files count: %3d, \n" +
                "Total subdirectories count: %3d, \n" +
                "Number of files with size larger than set: %3d, \n" +
                "Number of files that match the search pattern: %3d\n\n", DirPath, TotalFileCount, SubDirsCount, CorrectFileSizeCount, MatchingPatternCount);
    }
}

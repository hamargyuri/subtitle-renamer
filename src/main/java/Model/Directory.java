package Model;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hamargyuri on 2017. 01. 26..
 */
public class Directory {
    private String path;
    private List<File> files;
    private String movieTitle;

    public Directory(String path) throws NoFilesFoundException, NoMkvException {
        this.path = path;
        this.files = Arrays.asList(new File(this.path).listFiles());
        if (this.files.size() < 1) {
            // there's always .DS_Store on mac, so this is kind of obsolete...
            throw new NoFilesFoundException();
        }
        this.movieTitle = setTitle();
    }

    private String setTitle() throws NoMkvException {
        for (File file : this.files) {
            if (file.getName().endsWith(".mkv")) {
                return file.getName().replace(".mkv", "");
            }
        }
        throw new NoMkvException();
    }

    public String getMovieTitle() { return this.movieTitle; }

    public void renameSrt() throws NoSrtException {
        File srt = null;
        for (File file : this.files) {
            if (file.getName().endsWith(".srt")) {
                srt = file;
            }
        }
        if (srt == null) { throw new NoSrtException(); }
        srt.renameTo(new File(this.path + "/" + this.movieTitle + ".srt"));
    }

}

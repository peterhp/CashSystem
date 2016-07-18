package data;

/**
 * Created by Sora on 2016/7/18.
 */
public class ResFile {

    public static String getPath(String fileName) {
        return ResFile.class.getClassLoader()
                .getResource(fileName).getPath();
    }
}

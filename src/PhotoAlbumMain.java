
import controller.CommandFileReader;
import controller.ICommandReader;


public class PhotoAlbumMain {
  public static void main(String[] args) {
    ICommandReader reader = new CommandFileReader();
    reader.start(args);
  }
}
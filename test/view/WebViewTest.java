package view;

import org.junit.Test;

import java.io.IOException;

import controller.WebAlbumController;
import model.Album;
import model.IAlbum;
import model.Snapshot;

import static org.junit.Assert.*;

/**
 * The type Web view test.
 * You need test.txt to run this test completely.
 */
public class WebViewTest {

  /**
   * Normal web.
   */
  @Test
  public void normalWeb() {
    IAlbum album = new Album();
    album.addShape("rectangle", "one", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.takeSnapshot("1");
    album.addShape("rectangle", "two", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.addShape("oval", "three", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.takeSnapshot("2");
    album.addShape("oval", "four", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.move("three", 200, 100);
    album.takeSnapshot("3");
    WebView view = new WebView("test", 1000, 1000);
    for (Snapshot each : album.getSnapshots()) {
      view.updateShapes(each);
    }
    // replace all the time stamps to test
    assertEquals(view.toString().replaceAll("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+", ""),
            """
                    <!DOCTYPE html>
                    <html>
                    <svg width="1000" height="1000" style=background-color:aqua>;
                    <rect width="1000" height="1000" style="fill: none;stroke-width:10;stroke:red"/>;
                    <text x="10" y="40" font-size="25px"text-anchor="left" fill="black" font-weight="bold"></text>
                    <text x="10" y="70" font-size="18px"text-anchor="left" fill="black" > Description: 1</text>
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    </svg>
                    <svg width="1000" height="1"></svg>
                    </html><svg width="1000" height="1000" style=background-color:aqua>;
                    <rect width="1000" height="1000" style="fill: none;stroke-width:10;stroke:red"/>;
                    <text x="10" y="40" font-size="25px"text-anchor="left" fill="black" font-weight="bold"></text>
                    <text x="10" y="70" font-size="18px"text-anchor="left" fill="black" > Description: 2</text>
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <ellipse rx="200.000000" ry="200.000000" cx="105.000000" cy="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    </svg>
                    <svg width="1000" height="1"></svg>
                    </html><svg width="1000" height="1000" style=background-color:aqua>;
                    <rect width="1000" height="1000" style="fill: none;stroke-width:10;stroke:red"/>;
                    <text x="10" y="40" font-size="25px"text-anchor="left" fill="black" font-weight="bold"></text>
                    <text x="10" y="70" font-size="18px"text-anchor="left" fill="black" > Description: 3</text>
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <ellipse rx="200.000000" ry="200.000000" cx="205.000000" cy="200.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <ellipse rx="200.000000" ry="200.000000" cx="105.000000" cy="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    </svg>
                    <svg width="1000" height="1"></svg>
                    </html>""");

  }

  /**
   * No description web.
   */
  @Test
  public void noDescriptionWeb() {
    IAlbum album = new Album();
    album.addShape("rectangle", "one", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.takeSnapshot(null);
    album.addShape("rectangle", "two", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.addShape("oval", "three", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.takeSnapshot(null);
    album.addShape("oval", "four", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.move("three", 200, 100);
    album.takeSnapshot(null);
    WebView view = new WebView("test", 1000, 1000);
    for (Snapshot each : album.getSnapshots()) {
      view.updateShapes(each);
    }
    // replace all the time stamps to test
    assertEquals(view.toString().replaceAll("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+", ""),
            """
                    <!DOCTYPE html>
                    <html>
                    <svg width="1000" height="1000" style=background-color:aqua>;
                    <rect width="1000" height="1000" style="fill: none;stroke-width:10;stroke:red"/>;
                    <text x="10" y="40" font-size="25px"text-anchor="left" fill="black" font-weight="bold"></text>
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    </svg>
                    <svg width="1000" height="1"></svg>
                    </html><svg width="1000" height="1000" style=background-color:aqua>;
                    <rect width="1000" height="1000" style="fill: none;stroke-width:10;stroke:red"/>;
                    <text x="10" y="40" font-size="25px"text-anchor="left" fill="black" font-weight="bold"></text>
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <ellipse rx="200.000000" ry="200.000000" cx="105.000000" cy="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    </svg>
                    <svg width="1000" height="1"></svg>
                    </html><svg width="1000" height="1000" style=background-color:aqua>;
                    <rect width="1000" height="1000" style="fill: none;stroke-width:10;stroke:red"/>;
                    <text x="10" y="40" font-size="25px"text-anchor="left" fill="black" font-weight="bold"></text>
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <rect width="200.000000" height="200.000000" x="105.000000" y="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <ellipse rx="200.000000" ry="200.000000" cx="205.000000" cy="200.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    <ellipse rx="200.000000" ry="200.000000" cx="105.000000" cy="300.000000" style="fill:rgb(255.000000,0.000000,0.000000)"/>;
                    </svg>
                    <svg width="1000" height="1"></svg>
                    </html>""");

  }

  /**
   * No snapshot web.
   */
  @Test
  public void noSnapshotWeb() {
    IAlbum album = new Album();
    album.addShape("rectangle", "one", 100, 200,
            200, 200, 255.0, 0.0, 0.0);

    album.addShape("rectangle", "two", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.addShape("oval", "three", 100, 200,
            200, 200, 255.0, 0.0, 0.0);

    album.addShape("oval", "four", 100, 200,
            200, 200, 255.0, 0.0, 0.0);
    album.move("three", 200, 100);

    WebView view = new WebView("test", 1000, 1000);
    for (Snapshot each : album.getSnapshots()) {
      view.updateShapes(each);
    }
    // replace all the time stamps to test
    // in this case the html file won't be printed
    assertEquals(view.toString().replaceAll("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+", ""),
            """
                    <!DOCTYPE html>
                    <html>
                    """);

    WebAlbumController controller = new WebAlbumController("test.txt", view);
    //controller will throw error, which is to be caught by try-catch in the command reader
    assertThrows(IOException.class, controller::go);

  }


}
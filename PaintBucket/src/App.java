import java.util.Queue;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class App {
    class Canvas {
        String canvas[][];

        void createCanvas(int size, String defaultColor) {
            canvas = new String[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    canvas[i][j] = defaultColor;
                }
            }
        }

        public String[][] getCanvas() {
            return canvas;
        }
    }

    public void paint(String grid[][], int i, int j, String newColor) {
        if (i > grid.length - 1 || i < 0 || j > grid[0].length - 1 || j < 0) {
            System.out.println("Invalid Placement! Please check grid bounds.");
        } else {
            grid[i][j] = newColor;
        }
    }

    public void flood_fill(String grid[][], int i, int j, String newColor) {
        int n = grid.length;
        int m = grid[0].length;
        String oldColor = grid[i][j];

        if (oldColor.equals(newColor)) {
            return;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { i, j });

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            if (x < 0 || x >= n || y < 0 || y >= m || !grid[x][y].equals(oldColor)) {
                continue;
            }

            grid[x][y] = newColor;

            q.add(new int[] { x + 1, y });
            q.add(new int[] { x - 1, y });
            q.add(new int[] { x, y + 1 });
            q.add(new int[] { x, y - 1 });
        }
    }

    public void printCanvas(String grid[][]) {
        for (String[] row : grid) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
    public void writeCanvas(String grid[][]) {
        System.out.println("Writing output to file...");
        try (PrintWriter writer = new PrintWriter("output.txt", StandardCharsets.UTF_8)) { // Initially had issues with Emojis printing in terminal.
            for (String[] row : grid) {     
                for (String cell : row) {
                    writer.print(cell + " ");
                }
                writer.println();
            }
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        // all blue
        {
            App app = new App();

            Canvas paintGrid = app.new Canvas(); 
            paintGrid.createCanvas(4, "⬜");
            String canvas[][] = paintGrid.getCanvas();

            System.out.println("Initial Canvas:");
            app.printCanvas(canvas);

            app.flood_fill(canvas, 2, 2, "🟦");

            System.out.println("Final Canvas:");
            app.printCanvas(canvas);
            app.writeCanvas(canvas);
        }

        // Happy and Sad
        {
            App app = new App();

            Canvas paintGrid = app.new Canvas(); 
            paintGrid.createCanvas(5, "⬜");
            String canvas[][] = paintGrid.getCanvas();

            System.out.println("Initial Canvas:");
            app.printCanvas(canvas);

            app.paint(canvas, 1, 2, "⬛");
            app.paint(canvas, 2, 2, "⬛");
            app.paint(canvas, 3, 2, "⬛");
            app.paint(canvas, 4, 2, "⬛");
            app.paint(canvas, 0, 2, "⬛");
            app.flood_fill(canvas, 0, 0, "😀");
            app.flood_fill(canvas, 0, 3, "🥲");

            System.out.println("Final Canvas:");
            app.printCanvas(canvas);
            app.writeCanvas(canvas);
        }

        //Creeper
        {
        App app = new App();

        Canvas paintGrid = app.new Canvas(); 
        paintGrid.createCanvas(8, "⬜");
        String canvas[][] = paintGrid.getCanvas();

        System.out.println("Initial Canvas:");
        app.printCanvas(canvas);

        app.paint(canvas, 3, 1, "⬛");
        app.paint(canvas, 3, 2, "⬛");
        app.paint(canvas, 2, 1, "⬛");
        app.paint(canvas, 2, 2, "⬛");
        app.paint(canvas, 3, 5, "⬛");
        app.paint(canvas, 3, 6, "⬛");
        app.paint(canvas, 2, 5, "⬛");
        app.paint(canvas, 2, 6, "⬛");
        app.paint(canvas, 4, 3, "⬛");
        app.paint(canvas, 4, 4, "⬛");
        app.paint(canvas, 5, 3, "⬛");
        app.paint(canvas, 5, 4, "⬛");
        app.paint(canvas, 5, 2, "⬛");
        app.paint(canvas, 5, 5, "⬛");
        app.paint(canvas, 6, 3, "⬛");
        app.paint(canvas, 6, 4, "⬛");
        app.paint(canvas, 6, 2, "⬛");
        app.paint(canvas, 6, 5, "⬛");
        app.paint(canvas, 7, 2, "⬛");
        app.paint(canvas, 7, 5, "⬛");

        app.flood_fill(canvas, 0, 0, "🟩");
        app.flood_fill(canvas, 7, 3, "🟩");

        System.out.println("Final Canvas:");
        app.printCanvas(canvas);

        app.writeCanvas(canvas);
        }   
    }
}
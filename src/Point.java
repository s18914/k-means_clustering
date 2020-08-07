import java.util.ArrayList;

public class Point {
    ArrayList<Float> dimensions;
    String name;
    static int number=0;
    Group g;

    public Point(ArrayList dim){
        this.dimensions = dim;
        number++;
        this.name = "Point" + number;
    }

    public float findDistance(float[] d2) {
        float sum = 0;
        for(int i = 0; i < dimensions.size(); i++) {
            sum += Math.pow(dimensions.get(i) - d2[i], 2);
        }
        return (float)Math.sqrt(sum);
    }

    public void setGroup(Group g){
        this.g = g;
    }
}

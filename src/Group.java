import java.util.ArrayList;

public class Group {
    int Id;
    float[] centroid;
    static int i=0;
     ArrayList<Point> points;
     float E;

    public Group(){
        this.Id=i;
        i++;
        points = new ArrayList<>();
        centroid = new float[FileService.ileWymiarow];
        this.E=0;
    }

    public void addPoint(Point p){
        this.points.add(p);
    }

    public void calculateCentroid(){
        if(points.size()==0){
            System.out.println("Ta grupa nie ma żadnego punktu!!!");
            centroid = null;
            System.exit(1);
        }

        for(int i=0; i<centroid.length; i++){
            float suma = 0;
            for(Point p : points){
                suma += p.dimensions.get(i);
            }
            centroid[i] = suma/points.size();
        }
        System.out.println("Dla " +this.toString() + " centroid wygląda tak: " + showCentroid());
    }


    public String showGroup(){
        String ans = "W grupie nr " + Id + " są : ";
        for(Point p : points){
            ans += " " + p.name;
        }
        return ans;
    }

    public float findE(){
        float sum=0;
        for(Point p : points){
            sum+=p.findDistance(this.centroid)*p.findDistance(this.centroid);
        }
        return Math.round(sum);
    }

    public String toString(){
        return "Grupa " + Id;
    }

    public String showCentroid(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(float f : centroid){
            sb.append(f);
            sb.append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }

}

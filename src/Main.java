import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int k;
    private static ArrayList<Point> points = new ArrayList<>();
    private static ArrayList<Group> groups = new ArrayList<>();


    public static void main(String[] args) {
        //wczytanie parametrów
        Scanner skaner = new Scanner(System.in);
        System.out.println("Podaj parametr k :  ");
        k = Integer.parseInt(skaner.nextLine());

        points = FileService.czytajPlik("Punkty.csv");

        //tworze grupy
        for(int i=0; i<k; i++){
            Group g = new Group();
            groups.add(g);
        }

        // grupowanie losowe
        Random number = new Random();

        for(Point p : points){
            int los = number.nextInt(k);
            Group g = groups.get(los);
            p.setGroup(g);
            groups.get(los).addPoint(p);
        }





        boolean isOk=false;

            while(!isOk){
                //obliczanie centrum
                System.out.println("==================== CENTROIDY ====================");
                for(Group g : groups){
                    g.calculateCentroid();
                }

                //sprawdzam punkty - odleglosc od centroidow

                isOk = true;
                for(Point p : points){
                    float minDistance = p.findDistance(p.g.centroid);

                    float dist;

                    for(Group g : groups){
                        dist = p.findDistance(g.centroid);
                        if(dist<minDistance){
                            minDistance=dist;
                            p.setGroup(g);
                            System.out.println("zmieniam grupę");
                            isOk = false;
                        }
                    }
                }

                //wyczyszczenie grup
                for(Group g : groups){
                    g.points.clear();
                }

                //dodanie pktów do grup
                for(Point p : points){
                    int index = p.g.Id;
                    groups.get(index).addPoint(p);
                }

                //wyświetlenie
                for(Group g : groups){
                    System.out.println(g.showGroup());
                    System.out.println("E dla tej grupy: " + g.findE() + "\n");

                }


        }

    }


}

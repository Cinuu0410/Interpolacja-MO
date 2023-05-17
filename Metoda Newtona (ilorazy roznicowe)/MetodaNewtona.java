import java.util.ArrayList;
public class MetodaNewtona
{
    ArrayList<Punkt> Punkty;
    double x;
    MetodaNewtona(ArrayList<Punkt> Punkty, double x)
    {
        this.Punkty = Punkty;
        this.x = x;
    }

    void Licz() {
        double wielomian = Punkty.get(0).y;
        ArrayList<ArrayList<Double>> Listy = new ArrayList<>();
        int integer = 1;
        for (int dlugosc2 = Punkty.size()-1; dlugosc2 > 0 ; dlugosc2--) {
            if(Listy.isEmpty()) {
                ArrayList<Double> Wartosci = new ArrayList<>();
                for (int i = 0; i < dlugosc2; i++) {
                    Wartosci.add((Punkty.get(i + 1).y - Punkty.get(i).y) / (Punkty.get(i+ integer).x - Punkty.get(i).x));
                    //System.out.println(Wartosci.get(i));
                }
                Listy.add(Wartosci);
            }else{
                ArrayList<Double> Wartosci = Listy.get(Listy.size()-1);
                ArrayList<Double> KolejnyRzad = new ArrayList<>();
                for (int i = 0; i < Wartosci.size() - 1; i++) {
                        KolejnyRzad.add((Wartosci.get(i + 1) - Wartosci.get(i)) / (Punkty.get(i + integer).x - Punkty.get(i).x));
                        //System.out.println(Wartosci);
                }
                Listy.add(KolejnyRzad);
            }
            integer++;
        }

        ArrayList<Double> PierwszeElementy = new ArrayList<>();
        for (ArrayList<Double> doubles : Listy) {
            for (Double aDouble : doubles) {
                PierwszeElementy.add(aDouble);
                break;
            }
        }

        for (int i = 0; i < PierwszeElementy.size(); i++) {
            double iloczyn = 1;
            for (int j = 0; j <= i; j++) {
                iloczyn *= (x - Punkty.get(j).x);
            }
            wielomian += PierwszeElementy.get(i) * iloczyn;
        }
        System.out.println("Rozwiązanie metodą Newtona z ilorazami różnicowymi: "+ wielomian);
    }

    public static void main(String[] args) {
        ArrayList<Punkt> Punkty = new ArrayList<>();

        //Punkty z PDF
//        Punkt p1 = new Punkt(0,1);
//        Punkt p2 = new Punkt(2,3);
//        Punkt p3 = new Punkt(3, 2);
//        Punkt p4 = new Punkt(4, 5);
//        Punkt p5 = new Punkt(6,7);

        //Punkty z karteczki
        Punkt p1 = new Punkt(-4,180);
        Punkt p2 = new Punkt(-2,12);
        Punkt p3 = new Punkt(0, 4);
        Punkt p4 = new Punkt(2, -36);
        Punkt p5 = new Punkt(4,-300);

        Punkty.add(p1);
        Punkty.add(p2);
        Punkty.add(p3);
        Punkty.add(p4);
        Punkty.add(p5);

        MetodaNewtona m1 = new MetodaNewtona(Punkty, 3);
        m1.Licz();
    }
}
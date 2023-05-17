import java.util.ArrayList;
public class MetodaNewtonaProgresywnie
{
    ArrayList<Punkt> Punkty;
    double x;

    MetodaNewtonaProgresywnie(ArrayList<Punkt> Punkty, double x)
    {
        this.Punkty = Punkty;
        this.x = x;
    }

    void Licz() {
        double h = Punkty.get(1).x - Punkty.get(0).x;
        double wielomian = Punkty.get(0).y;
        ArrayList<ArrayList<Double>> Listy = new ArrayList<>();
        for (int dlugosc2 = Punkty.size()-1; dlugosc2 > 0 ; dlugosc2--) {
            if(Listy.isEmpty()) {
                ArrayList<Double> Wartosci = new ArrayList<>();
                for (int i = 0; i < dlugosc2; i++) {
                    Wartosci.add((Punkty.get(i + 1).y - Punkty.get(i).y));
                    //System.out.println(Wartosci.get(i));
                }
                Listy.add(Wartosci);
            }else{
                ArrayList<Double> Wartosci = Listy.get(Listy.size()-1);
                ArrayList<Double> KolejnyRzad = new ArrayList<>();
                for (int i = 0; i < Wartosci.size() - 1; i++) {
                    KolejnyRzad.add((Wartosci.get(i + 1) - Wartosci.get(i)));
                    //System.out.println(Wartosci);
                }
                Listy.add(KolejnyRzad);
            }
        }
        //System.out.println(Listy);

        ArrayList<Double> PierwszeElementy = new ArrayList<>();
        for (ArrayList<Double> doubles : Listy) {
            for (Double aDouble : doubles) {
                PierwszeElementy.add(aDouble);
                break;
            }
        }

        //System.out.println(PierwszeElementy);
        int l_silnia = 1;
        int h_potega = 1;
        for (int i = 0; i < PierwszeElementy.size(); i++) {
            double iloczyn = 1;
            for (int j = 0; j <= i; j++) {
                iloczyn *= (x - Punkty.get(j).x);
            }
            wielomian += (PierwszeElementy.get(i)/(silnia(l_silnia) * Math.pow(h, h_potega))) * iloczyn;
            l_silnia++;
            h_potega++;
        }
        System.out.println("Wielomian = "+wielomian);
    }

    double silnia(int n)
    {
        int i, fact=1;
        for(i = 1; i <= n; i++){
            fact = fact * i;
        }
        return fact;
    }

    public static void main(String[] args) {
        ArrayList<Punkt> Punkty = new ArrayList<>();

        Punkt p1 = new Punkt(-4,180);
        Punkt p2 = new Punkt(-2,12);
        Punkt p3 = new Punkt(0, 4);
        Punkt p4 = new Punkt(2, -36);
        Punkt p5 = new Punkt(4, -300);

        Punkty.add(p1);
        Punkty.add(p2);
        Punkty.add(p3);
        Punkty.add(p4);
        Punkty.add(p5);

        MetodaNewtonaProgresywnie m1 = new MetodaNewtonaProgresywnie(Punkty, 3);
        m1.Licz();
    }
}
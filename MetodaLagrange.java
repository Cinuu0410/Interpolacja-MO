import java.util.ArrayList;
import java.util.Scanner;
public class MetodaLagrange {
    ArrayList<Punkt> Punkty;
    MetodaLagrange(ArrayList<Punkt> Punkty)
    {
        this.Punkty = Punkty;
    }

    double InterpolacjaMetodaLagrange(double x)
    {
        int rozmiarListy = Punkty.size();
        double iloczyn_pi = 1;
        double W = 0;
        for(int i = 0; i < rozmiarListy; i++)
        {
            for(int j=0; j < rozmiarListy; j++)
                if(i != j)
                {
                    iloczyn_pi = iloczyn_pi * (x - Punkty.get(j).x) / (Punkty.get(i).x - Punkty.get(j).x);
                }
            W = W + Punkty.get(i).y * iloczyn_pi;
            iloczyn_pi = 1;
        }
        return W;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Punkt> Punkty = new ArrayList<>();
        MetodaLagrange ml = new MetodaLagrange(Punkty);
        //Punkty z PDF
//        Punkt pkt1 = new Punkt(-5, 975);
//        Punkt pkt2 = new Punkt(-4, 433);
//        Punkt pkt3 = new Punkt(-1, 7);
//        Punkt pkt4 = new Punkt(3, -1);
//        Punkt pkt5 = new Punkt(5, 235);

        //Punkty z karteczki
        Punkt pkt1 = new Punkt(-4, 180);
        Punkt pkt2 = new Punkt(-2, 12);
        Punkt pkt3 = new Punkt(0, 4);
        Punkt pkt4 = new Punkt(2, -36);
        Punkt pkt5 = new Punkt(4, -300);

        Punkty.add(pkt1);
        Punkty.add(pkt2);
        Punkty.add(pkt3);
        Punkty.add(pkt4);
        Punkty.add(pkt5);

        System.out.println("Podaj punkt x w ktorym przyblizamy wartosc funkcji: ");
        double x = scan.nextInt();
        System.out.println("Rozwiazanie metoda Lagrange: \nWynik: " + ml.InterpolacjaMetodaLagrange(x));
    }
}
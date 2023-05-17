import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class FunkcjaSklejana {
    ArrayList<Punkt> Punkty;
    ArrayList<Punkt> Pochodne;
    double[] wspolczynniki;
    double[] rozwiazania;
    ArrayList<double[]> gausstemp;
    double[][] gausstab;

    FunkcjaSklejana(ArrayList<Punkt> Punkty, ArrayList<Punkt> Pochodne) {
        this.Punkty = Punkty;
        this.Pochodne = Pochodne;
        wspolczynniki = new double[4 + Punkty.size() - 2];
        rozwiazania = new double[4 + Punkty.size() - 2];
        gausstemp = new ArrayList<>();
        gausstab = new double[4 + Punkty.size() - 2][4 + Punkty.size() - 2];
    }

    void liczPochodna(double x) {
        wspolczynniki[0] = 0;
        wspolczynniki[1] = 1;
        wspolczynniki[2] = 2 * x;
        wspolczynniki[3] = 3 * x * x;
        for (int i = 1; i < Punkty.size() - 1; i++) {
            if (x != Punkty.get(0).x)
                wspolczynniki[3 + i] = 3 * Math.pow((x - Punkty.get(i).x), 2);
            else {
                wspolczynniki[3 + i] = 0;
            }
        }
    }

    void dodaj(double dl, double x) {
        for (int i = 1; i < dl; i++) {
            wspolczynniki[3 + i] = Math.pow((x - Punkty.get(i).x), 3);
        }
    }

    void sklej() {
        int counter = 0;
        wielomian_W3x(Punkty.get(0).x);
        gausstemp.add(wspolczynniki);
        rozwiazania[0] = Punkty.get(0).y;
        for (int i = 0; i < gausstab.length; i++) {
            gausstab[0][i] = gausstemp.get(0)[i];
        }
        for (int i = 1; i < 4 + Punkty.size() - 2; i++) {
            if (i < Punkty.size()) {
                wielomian_W3x(Punkty.get(i).x);
                dodaj(i, Punkty.get(i).x);
                gausstemp.add(wspolczynniki);
                rozwiazania[i] = Punkty.get(i).y;
            } else {
                liczPochodna(Pochodne.get(counter).x);
                rozwiazania[i] = Pochodne.get(counter).y;
                gausstemp.add(wspolczynniki);
                counter++;
            }
            for (int j = 0; j < gausstab.length; j++) {
                gausstab[i][j] = gausstemp.get(i)[j];
            }
        }
    }

    void wielomian_W3x(double x) {
        for (int i = 0; i <= 3; i++) {
            wspolczynniki[i] = Math.pow(x, i);
        }
    }

    double liczSklejane(double x) {
        sklej();
        double S = 0;
        double[] gausswyniki = Gauss.rozwiaz(gausstab, rozwiazania);
        S = gausswyniki[0] + gausswyniki[1] * x + gausswyniki[2] * x * x + gausswyniki[3] * x * x * x;
        if (x > Punkty.get(1).x) {
            int licznik = 1;
            for (int i = 4; i < gausswyniki.length; i++) {
                S += gausswyniki[i] * Math.pow((x - Punkty.get(licznik).x), 3);
                licznik++;
            }
        }
        return S;
    }


    public static void main(String[] args) {
        ArrayList<Punkt> Punkty = new ArrayList<>();
        ArrayList<Punkt> Pochodne = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        //Punkty z PDF
//        Punkt p1 = new Punkt(1, 1);
//        Punkt p2 = new Punkt(3, 8);
//        Punkt p3 = new Punkt(5, 9);
//        Punkt p4 = new Punkt(7, 17);

//        Punkt po1 = new Punkt(1, 1);
//        Punkt po2 = new Punkt(7, 1);

        //Punkty z karteczki
        Punkt p1 = new Punkt(-4,180);
        Punkt p2 = new Punkt(-2,12);
        Punkt p3 = new Punkt(0, 4);
        Punkt p4 = new Punkt(2, -36);
        Punkt p5 = new Punkt(4,-300);

        Punkt po1 = new Punkt(-4, -156);
        Punkt po2 = new Punkt(4, -220);

        Punkty.add(p1);
        Punkty.add(p2);
        Punkty.add(p3);
        Punkty.add(p4);
        Punkty.add(p5);

        Pochodne.add(po1);
        Pochodne.add(po2);

        System.out.println("Wprowadz szukany x: ");
        double szukane = scan.nextDouble();
        FunkcjaSklejana fs1 = new FunkcjaSklejana(Punkty, Pochodne);
        System.out.println("Wynik to: " + fs1.liczSklejane(szukane));
    }
}
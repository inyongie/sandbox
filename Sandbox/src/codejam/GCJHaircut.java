package codejam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ichung on 4/17/2015.
 * GCJHaircut
 *
 * Passed Easy Case, failed Hard
 */
public class GCJHaircut {
    public static class Barber {
        final int rate;
        final int order;
        int counter = 0;

        public Barber(int rate, int order) {
            this.rate = rate;
            this.order = order;
        }

        private void resetCounter() {
            this.counter = rate;
        }

        public void recordTime() {
            counter--;
        }

        public boolean isReady() {
            if(counter == 0) {
                resetCounter();
                return true;
            }
            else {
                return false;
            }
        }

        public int getOrder() {
            return order;
        }
    }

    public static void main(String[] args) {
        BufferedReader in;

        try {
//            in = new BufferedReader(new FileReader("F:\\code\\haircut_test.txt"));
//            in = new BufferedReader(new FileReader("F:\\code\\B-small-attempt1.in"));
            in = new BufferedReader(new FileReader("F:\\code\\B-large.in"));
            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);
            for(int i = 1; i<=cases; i++) {
                String[] line1 = in.readLine().split(" ");
                // # of barbers
                int b = Integer.parseInt(line1[0]);
                // your turn
                long n = Long.parseLong(line1[1]);

                String[] line2 = in.readLine().split(" ");
                ArrayList<Barber> barbers = new ArrayList<Barber>();
                for (int j = 0; j < b; j++) {
                    barbers.add(new Barber(Integer.parseInt(line2[j]), j + 1));
                }

                // forget lcd for now, we're going just cd haha
                long cd = 1;
                for (int j = 0; j < b; j++) {
                    cd *= Long.parseLong(line2[j]);
                }

                long customersInCd = 0;
                for (int j = 0; j < b; j++) {
                    customersInCd += cd / Long.parseLong(line2[j]);
                }

                long adjustedN = n % customersInCd;

                long customerNum = 1;
                int yourBarber = 0;
                if (adjustedN == 0) adjustedN = customersInCd;
                while (customerNum <= adjustedN) {
                    for (int j = 0; j < b; j++) {
                        Barber barb = barbers.get(j);
                        if (barb.isReady()) {
                            if (customerNum == adjustedN) {
                                yourBarber = barb.getOrder();
                                customerNum++;
                                break;
                            }
                            customerNum++;
                        }
                        barb.recordTime();
                    }
                }

                System.out.println("Case #" + i + ": " + yourBarber);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

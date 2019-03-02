import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Eeekarp {

        private static int calculate(int j) {
            int seqLen = 1;
            while (j != 1) {
                j = j % 2 == 0 ? j / 2 : j * 3 + 1;
                seqLen++;
            }
            return seqLen;
        }

        public static void out(int a, int b) {
            int max = 0;
            if(a<b) {
                for (int i = a; i <= b; i++) {
                    int currentVal = calculate(i);
                    if (currentVal > max) {
                        max = currentVal;
                    }
                }
            }else {
                for (int i = b; i <= a; i++) {
                    int currentVal = calculate(i);
                    if (currentVal > max) {
                        max = currentVal;
                    }
                }
            }
            System.out.println(a + " " + b + " " + max);
        }

        public static void main(String[] args) throws IOException {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String input = br.readLine();
            while (input!=null) {
                String [] enter = input.split("");
                int a = Integer.parseInt(enter[0]);
                int b = Integer.parseInt(enter[1]);
                out(a, b);
                input = br.readLine();
            }
        }
    }
package interest_rate_check;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //dane
        JTextField rateField = new JTextField(5);
        JTextField monthsField = new JTextField(5);
        JTextField goldField = new JTextField(5);

        JPanel myPanel = new JPanel();

        myPanel.add(new JLabel("Oprocentowanie:"));
        myPanel.add(rateField);
        myPanel.add(Box.createHorizontalStrut(5)); // a spacer
        myPanel.add(new JLabel("Czas w miesiącach:"));
        myPanel.add(monthsField);
        myPanel.add(Box.createHorizontalStrut(5)); // a spacer
        myPanel.add(new JLabel("Oczekiwana kwota:"));
        myPanel.add(goldField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Kalkulator planu inwestycyjnego", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            double percentage = 19;
            double bonus = Double.parseDouble(rateField.getText());
            int n = Integer.parseInt(monthsField.getText());
            double k = Double.parseDouble(goldField.getText());
            double r = (bonus/100)/12;
            double p = percentage/100;

            //wyliczenie 's' ze zmienionego wzoru
            double up = k*r*(1-p);
            double pow = Math.pow(1 + r * (1 - p), n);
            double down = pow-1;
            double side = (1+r*(1-p));

            double s = up/(down*side); // wyliczenie 's'
            int sValue = (int) Math.round(s);// wyliczone 's'

            //wyliczenie 'k' podstawiając dane do pierwotnego wzoru znając 's'
            double up2 = sValue* pow -sValue;
            double down2 = r*(1-p);
            double side2 = (1+r*(1-p));

            double k2 = Math.floor(up2/down2)*side2; // 'k' uzyskane w wyniku naliczania s przez 8 miesięcy
            int kValue = (int) Math.round(k2);

            if(n == 1) {
                JOptionPane.showMessageDialog(null,"Cel: " + Math.round(k) + " zł" + " \nOkres: " + n + " miesiąc " + "\nWysokość wpłaty: " + sValue + " zł" + "\n\nPrzewidywane środki: " + kValue + " zł");
            }else if (n==2 || n==3 || n==4) {
                JOptionPane.showMessageDialog(null,"Cel: " + Math.round(k) + " zł" + " \nOkres: " + n + " miesiące " + "\nWysokość wpłaty: " + sValue + " zł" + "\n\nPrzewidywane środki: " + kValue + " zł");
            } else{
                JOptionPane.showMessageDialog(null,"Cel: " + Math.round(k)+ " zł" + " \nOkres: " + n + " miesięcy " + "\nWysokość wpłaty: " + sValue + " zł" + "\n\nPrzewidywane środki: " + kValue + " zł");
            }
        }else if (result == JOptionPane.CANCEL_OPTION)
        {
            JOptionPane.showMessageDialog(null,"Anulowano użycie programu.");
        }else {
            JOptionPane.showMessageDialog(null,"Zamknięto kalkulator.");
        }

    }
}


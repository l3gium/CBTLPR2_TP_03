import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculadora extends JFrame implements ActionListener {
    //Desenvolvido por Beatriz Bastos Borges e Miguel Luizatto Alves

    private JTextField display;
    private String operador;
    private double numero1, numero2, resultado;
    private boolean novaEntrada = true;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 4, 5, 5));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                ".", "0", "=", "+",
                "C"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.PLAIN, 20));
            botao.addActionListener(this);
            painelBotoes.add(botao);
        }

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equals("C")) {
                display.setText("");
                operador = "";
                numero1 = numero2 = resultado = 0;
                novaEntrada = true;
            } else if (comando.equals("=")) {
                numero2 = Double.parseDouble(display.getText());
                switch (operador) {
                    case "+" : 
                        resultado = numero1 + numero2;
                        break;
                    case "-" : 
                        resultado = numero1 - numero2;
                        break;
                    case "*" : 
                        resultado = numero1 * numero2;
                        break;
                    case "/" : 
                        if (numero2 == 0) 
                            throw new ArithmeticException("Divisão por zero não permitida");
                    
                        resultado = numero1 / numero2;
                }
                display.setText(String.valueOf(resultado));
                operador = "";
                novaEntrada = true;
            } else if ("+-*/".contains(comando)) {
                operador = comando;
                numero1 = Double.parseDouble(display.getText());
                novaEntrada = true;
            } else {
                if (novaEntrada) {
                    display.setText("");
                    novaEntrada = false;
                }
                if (comando.equals(".") && display.getText().contains(".")) {
                    return;
                }
                display.setText(display.getText() + comando);
            }
        } catch (NumberFormatException ex) {
            display.setText("Erro"); 
        } catch (ArithmeticException ex) {
            display.setText("Erro"); 
        } finally {
            
        }
    }

    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        calculadora.setVisible(true);
    }
}
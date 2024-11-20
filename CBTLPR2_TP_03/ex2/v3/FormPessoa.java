import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FormPessoa extends JFrame {
    //Desenvolvido por Beatriz Bastos Borges e Miguel Luizatto Alves

    private static Pessoa[] UmaPessoa = new Pessoa[100]; 
    private static int totalPessoas = 0; 

    public static void main(String[] args) {
        JFrame frame = new JFrame("Semana 06 - Exercicio 01");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 5, 5));

        JLabel labelNumero = new JLabel("Numero:");
        JTextField textNumero = new JTextField();
        textNumero.setEditable(false);

        JLabel labelNome = new JLabel("Nome:");
        JTextField textNome = new JTextField();

        JLabel labelSexo = new JLabel("Sexo:");

        JRadioButton radioMasculino = new JRadioButton("M");
        JRadioButton radioFeminino = new JRadioButton("F");
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(radioMasculino);
        grupoSexo.add(radioFeminino);

        JPanel panelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSexo.add(radioMasculino);
        panelSexo.add(radioFeminino);

        JLabel labelIdade = new JLabel("Idade:");
        JTextField textIdade = new JTextField();

        panelCampos.add(labelNumero);
        panelCampos.add(textNumero);
        panelCampos.add(labelNome);
        panelCampos.add(textNome);
        panelCampos.add(labelSexo);
        panelCampos.add(panelSexo);
        panelCampos.add(labelIdade);
        panelCampos.add(textIdade);

        JPanel panelBotoes = new JPanel(new GridLayout(1, 4, 0, 0));

        JButton btnOk = new JButton("OK");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");

        panelBotoes.add(btnOk);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnMostrar);
        panelBotoes.add(btnSair);

        frame.add(panelCampos, BorderLayout.CENTER);
        frame.add(panelBotoes, BorderLayout.SOUTH);

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (totalPessoas >= 100) {
                        throw new IllegalStateException("Capacidade maxima de pessoas atingida.");
                    }

                    String nome = textNome.getText().trim();
                    if (nome.isEmpty()) {
                        throw new IllegalArgumentException("Nome obrigatorio.");
                    }

                    char sexo;
                    if (radioMasculino.isSelected()) {
                        sexo = 'M';
                    } else if (radioFeminino.isSelected()) {
                        sexo = 'F';
                    } else {
                        throw new IllegalArgumentException("E necessário selecionar o sexo.");
                    }

                    int idade = Integer.parseInt(textIdade.getText().trim());
                    if (idade <= 0) {
                        throw new IllegalArgumentException("Idade deve ser maior que zero.");
                    }

                    Pessoa novaPessoa = new Pessoa(nome, sexo, idade);
                    UmaPessoa[totalPessoas] = novaPessoa;
                    totalPessoas++;

                    textNumero.setText(String.valueOf(Pessoa.getKp()));
                    JOptionPane.showMessageDialog(frame, "Dados cadastrados com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: Idade deve ser um numero inteiro valido.");
                } catch (IllegalArgumentException | IllegalStateException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar: " + ex.getMessage());
                } finally {
                    textNome.setText("");
                    grupoSexo.clearSelection();
                    textIdade.setText("");
                }
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (totalPessoas == 0) {
                    JOptionPane.showMessageDialog(frame, "Nenhuma pessoa cadastrada.");
                    return;
                }

                StringBuilder dados = new StringBuilder("Lista de Pessoas:\n");
                for (int i = 0; i < totalPessoas; i++) {
                    Pessoa p = UmaPessoa[i];
                    dados.append("Nome: ").append(p.getNome())
                         .append(", Sexo: ").append(p.getSexo())
                         .append(", Idade: ").append(p.getIdade())
                         .append("\n");
                }
                dados.append("Total de pessoas cadastradas: ").append(Pessoa.getKp());

                JOptionPane.showMessageDialog(frame, dados.toString());
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textNome.setText("");
                grupoSexo.clearSelection(); 
                textIdade.setText("");
            }
        });

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}

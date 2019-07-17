import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.text.NumberFormat;
import javax.swing.JComboBox;
import java.util.*;

public class JanelaValores extends JFrame {
	Valores val;
	JTextField caixaCapital;
	JTextField caixaTaxa;
	JTextField caixaTempo;
	JButton botaoSimular;
	JButton botaoVoltar;
	JButton botaoDetalhar;
	JLabel labelMontante;
	JLabel labelValorMontante;
	JLabel labelJuros;
	JLabel labelValorJuros;
	JComboBox comboBoxTaxa;
	JComboBox comboBoxTempo;
	String[] stringsTaxa = {"a.m.", "a.b.", "a.t.", "a.s.", "a.a."};
	String[] stringsTempo = {"mês", "bimestre", "trimestre", "semestre", "ano"};

	public JanelaValores(){
		this.setSize(700, 350);
		this.setVisible(true);
		Container painelPrincipal = super.getContentPane();
		JLabel labelCapital = new JLabel("Capital (R$) :");
		JLabel labelTaxa = new JLabel("Taxa (%):");
		JLabel labelTempo = new JLabel("Tempo:");
		labelJuros = new JLabel("Juros:");
		labelValorJuros = new JLabel("R$ 0,00");
		labelMontante = new JLabel("Montante:");
		labelValorMontante = new JLabel("R$ 0,00");
		botaoSimular = new JButton("Simular");
		botaoVoltar = new JButton("Voltar");
		botaoDetalhar = new JButton("Detalhar");
		Container pp = new JPanel();
		Container painelNorth = new JPanel();
		Container painelSouth = new JPanel();
		Container painelWest = new JPanel();
		Container painelEast = new JPanel();
		painelSouth.setLayout(new GridLayout(3,7));
		painelWest.setLayout(new GridLayout(10,1));
		painelEast.setLayout(new GridLayout(10,1));
		painelNorth.setLayout(new GridLayout(1,1));
		pp.setLayout(new GridLayout(10,1));
		comboBoxTaxa = new JComboBox(stringsTaxa);
		comboBoxTempo = new JComboBox(stringsTempo);
		val = new Valores();
		caixaCapital = new JTextField();
		caixaTaxa = new JTextField();
		caixaTempo = new JTextField();

		painelPrincipal.setLayout(new BorderLayout());
		painelPrincipal.add(pp, BorderLayout.CENTER);
		painelPrincipal.add(painelSouth, BorderLayout.SOUTH);
		painelPrincipal.add(painelWest, BorderLayout.WEST);
		painelPrincipal.add(painelEast, BorderLayout.EAST);
		painelPrincipal.add(painelNorth, BorderLayout.NORTH);

		//NORTH
		painelNorth.add(new JLabel(""));
		//NORTH

		//CENTER
		pp.add(caixaCapital);
		pp.add(new JLabel(""));

		pp.add(caixaTaxa);
		pp.add(new JLabel(""));

		pp.add(caixaTempo);
		pp.add(new JLabel(""));

		pp.add(labelValorJuros);
		pp.add(new JLabel(""));

		pp.add(labelValorMontante);
		pp.add(new JLabel(""));
		//CENTER

		//WEST
		painelWest.add(labelCapital);
		painelWest.add(new JLabel());

		painelWest.add(labelTaxa);
		painelWest.add(new JLabel());

		painelWest.add(labelTempo);
		painelWest.add(new JLabel());

		painelWest.add(labelJuros);
		painelWest.add(new JLabel());

		painelWest.add(labelMontante);
		painelWest.add(new JLabel());

		//WEST

		//EAST
		painelEast.add(new JLabel(""));
		painelEast.add(new JLabel(""));

		painelEast.add(comboBoxTaxa);
		painelEast.add(new JLabel(""));

		painelEast.add(comboBoxTempo);
		painelEast.add(new JLabel(""));

		painelEast.add(new JLabel(""));
		painelEast.add(new JLabel(""));

		painelEast.add(new JLabel(""));
		painelEast.add(new JLabel(""));
		//EAST

		//SOUTH
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));

		painelSouth.add(new JLabel(""));
		painelSouth.add(botaoVoltar);
		painelSouth.add(new JLabel(""));
		painelSouth.add(botaoDetalhar);
		painelSouth.add(new JLabel(""));
		painelSouth.add(botaoSimular);
		painelSouth.add(new JLabel(""));
		
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		painelSouth.add(new JLabel(""));
		//SOUTH

		botaoVoltar.addActionListener(new ControladorValores(botaoVoltar));
		botaoSimular.addActionListener(new ControladorValores(botaoSimular));
		botaoDetalhar.addActionListener(new ControladorValores(botaoDetalhar));
	}

	public void atualizarDisplay(){
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		String m = formatter.format(val.calcularJuros());
		labelValorJuros.setText(m);
		m = formatter.format(val.getMontante());
		labelValorMontante.setText(m);
	}

	public class ControladorValores implements ActionListener{
		JButton botao;
		public ControladorValores(JButton botaoClicado){
			botao = botaoClicado;
		}

		public void actionPerformed(ActionEvent e){
		    if (botao == botaoSimular){
				val.setCapital(Double.parseDouble(caixaCapital.getText()));
				val.converterParaMeses(comboBoxTaxa.getSelectedItem().toString(), comboBoxTempo.getSelectedItem().toString(), caixaTaxa.getText(), caixaTempo.getText());
				val.setMontante(val.calcularMontante());
		    } else if (botao == botaoVoltar){
		        setVisible(false);
		    } else if (botao == botaoDetalhar){
				JFrame janelaDetalhar = new JFrame();
				janelaDetalhar.setTitle("Detalhes do Investimento");
				janelaDetalhar.setSize(300, 30*Integer.parseInt(caixaTempo.getText()));
				janelaDetalhar.setVisible(true);
				ArrayList<Double> listaDeValores = new ArrayList<Double>();
				listaDeValores = val.detalhar(comboBoxTempo.getSelectedItem().toString());

				int j = Integer.parseInt(caixaTempo.getText());			
				
				Container detPainelCenter = new JPanel();
				detPainelCenter = janelaDetalhar.getContentPane();
				detPainelCenter.setLayout(new GridLayout(j, 2));

				ArrayList<JLabel> listaJLabelL = new ArrayList<JLabel>();
				ArrayList<JLabel> listaJLabelV = new ArrayList<JLabel>();
				
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				
				
				for (int i = 1; i <= j; i++){
					listaJLabelL.add(new JLabel("    "+i+":"));
					listaJLabelV.add(new JLabel(formatter.format(listaDeValores.get(i-1))));
				}

				for (int i = 1; i <= j; i++){
					detPainelCenter.add(listaJLabelL.get(i-1));
					detPainelCenter.add(listaJLabelV.get(i-1));
				}


				
			}
			atualizarDisplay();
		}
	}
}

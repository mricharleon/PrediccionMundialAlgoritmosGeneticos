package app_mundial_agentes_geneticos;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.AbstractListModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import jdk.nashorn.internal.runtime.ListAdapter;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.sun.java.swing.plaf.windows.resources.windows;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import app_mundial_agentes_geneticos.EFitnessFunction;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;

import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Inicio {
	int cont=0;
	public JFrame frmMundial;
	
	private Configuration conf;
    private SwappingMutationOperator swapper;
    private EFitnessFunction fitnessFunction = null;
    final List<Equipo> equipos = new ArrayList<Equipo>();
    public List ranking = new ArrayList();
    private static final int MAX_ALLOWED_EVOLUTIONS = 250;
    private Chromosome equipoChromosome = null;
    
    JTextArea textArea = new JTextArea();
    JLabel lblFitness = new JLabel("");
    private JTextArea txtareaproceso = new JTextArea();
    private JComboBox lblPartido1;
    private JComboBox lblPartido2;
    private JComboBox lblPartido3;
    private JComboBox lblGrupo;
    private JComboBox lblRanking;
    private JComboBox lblCopas;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frmMundial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Inicio() throws Exception {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		////////////////////////////////////////////////////////////////////////////////
		//                             Empieza modo gráfico                           //
		////////////////////////////////////////////////////////////////////////////////
		frmMundial = new JFrame();
		frmMundial.getContentPane().setBackground(UIManager.getColor("CheckBox.background"));
		frmMundial.setForeground(Color.WHITE);
		frmMundial.setResizable(false);
		frmMundial.setSize(1183, 592);
		frmMundial.setLocationRelativeTo(null);
		frmMundial.setTitle("Mundial 2014 - Predicción con algoritmos genéticos");
		frmMundial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		// se colocan etiquetas en la vista
		//
		final JLabel lblEquipo = new JLabel("Ecuador");
		lblEquipo.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblGrup = new JLabel("Grupo:");
		
		JLabel lblRankin = new JLabel("Ranking:");
		
		JLabel lblCopa = new JLabel("Copas:");
		
		JLabel lblPartid1 = new JLabel("Partido 1:");
		JLabel labelPartid2 = new JLabel("Partido 2:");
		
		JLabel lblPartido = new JLabel("Partido 3:");
		
		JLabel lblListaDeEquipos = new JLabel("Lista de Equipos");
		final JList list = new JList();
		
		
		JButton btnPredecir = new JButton("Predecir");
		
		final JLabel lblImagen = new JLabel("");
		
		lblFitness.setFont(new Font("Dialog", Font.BOLD, 12));
		
		
		//
		// Se lee el archivo csv
		//
		try {
	         // en esta linea modificar la ruta al archivo
	        CsvReader equipos_import = new CsvReader("/home/usuario/mundial.csv");
	        equipos_import.readHeaders();
	         
	        while (equipos_import.readRecord())
	        {
	            String pais = equipos_import.get(0);
	            String ranking = equipos_import.get(1);
	            String grupo = equipos_import.get(2);
	            String copas = equipos_import.get(3);
	            String partido1 = equipos_import.get(4);
	            String partido2 = equipos_import.get(5);
	            String partido3 = equipos_import.get(6);
	             
	            equipos.add(new Equipo(pais, ranking, grupo, copas, partido1, partido2, partido3));   
	        }
	        
	        equipos_import.close();
	        
	        DefaultListModel modelo = new DefaultListModel(); 
	        
	        int cont = 1;
	        for(Equipo eq : equipos){
	         
	            System.out.println(cont + "" +eq.getPais() + " | " + eq.getGrupo() + " | "
	            + eq.getRanking() + " | " + eq.getCopas() + " | " + eq.getPartido1() + " | " + eq.getPartido2() + " | " + eq.getPartido3());
	            cont++;
	            modelo.addElement(eq.getPais());
	        }
	        list.setModel(modelo);
	        
	        
	        
	        
	        
	        
	        //
	        // Agregar los datos de 'equipos' a la lista 
	        //
	        list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					
					lblImagen.setIcon(new ImageIcon("/home/mricharleon/workspace/app_mundial_agentes_geneticos/src/app_mundial_agentes_geneticos/img/"+
														list.getSelectedValue()+".png"));
					lblEquipo.setText(""+list.getSelectedValue());
					lblGrupo.setSelectedItem(equipos.get(list.getSelectedIndex()).getGrupo());
					lblRanking.setSelectedItem(equipos.get(list.getSelectedIndex()).getRanking()); 
					
					lblCopas.setSelectedItem(equipos.get(list.getSelectedIndex()).getCopas()); 
					
					if(equipos.get(list.getSelectedIndex()).getPartido1().equals("1")){
	        			lblPartido1.setSelectedIndex(0); 
					}else if(equipos.get(list.getSelectedIndex()).getPartido1().equals("0")){
						lblPartido1.setSelectedIndex(1); 
					}else if(equipos.get(list.getSelectedIndex()).getPartido1().equals("-1")){
						lblPartido1.setSelectedIndex(2); 
					}
					
					if(equipos.get(list.getSelectedIndex()).getPartido2().equals("1")){
						lblPartido2.setSelectedIndex(0); 
					}else if(equipos.get(list.getSelectedIndex()).getPartido2().equals("0")){
						lblPartido2.setSelectedIndex(1);
					}else if(equipos.get(list.getSelectedIndex()).getPartido2().equals("-1")){
						lblPartido2.setSelectedIndex(2);
					} 
					
					if(equipos.get(list.getSelectedIndex()).getPartido3().equals("1")){
						lblPartido3.setSelectedIndex(0);
					}else if(equipos.get(list.getSelectedIndex()).getPartido3().equals("0")){
						lblPartido3.setSelectedIndex(1);
					}else if(equipos.get(list.getSelectedIndex()).getPartido3().equals("-1")){
						lblPartido3.setSelectedIndex(2);
					} 
					
				}
			});
	        
	        
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setViewportView(list);
		
		//
		// accion del btn predecir
		//
		btnPredecir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					testSelectFittestEquipos();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		//
		// acciones para editar
		//
		lblPartido1 = new JComboBox();
		lblPartido1.setModel(new DefaultComboBoxModel(new String[] {"Ganado", "Empate", "Perdido"}));
		
		lblPartido2 = new JComboBox();
		lblPartido2.setModel(new DefaultComboBoxModel(new String[] {"Ganado", "Empate", "Perdido"}));
		
		lblPartido3 = new JComboBox();
		lblPartido3.setModel(new DefaultComboBoxModel(new String[] {"Ganado", "Empate", "Perdido"}));
		
		lblGrupo = new JComboBox();
		lblGrupo.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H"}));
		
		lblRanking = new JComboBox();
		lblRanking.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100"}));
		
		lblCopas = new JComboBox();
		lblCopas.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		
		JButton btnEditar = new JButton("Actualizar Datos");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p1 = "";
				String p2 = "";
				String p3 = "";
				if(lblPartido1.getSelectedItem().equals("Ganado") ){
					p1="1";
				}else if(lblPartido1.getSelectedItem().equals("Empate")){
					p1="0";
				}else if(lblPartido1.getSelectedItem().equals("Perdido")){
					p1="-1";
				}
				
				if(lblPartido2.getSelectedItem().equals("Ganado")){
					p2="1";
				}else if(lblPartido2.getSelectedItem().equals("Empate")){
					p2="0";
				}else if(lblPartido2.getSelectedItem().equals("Perdido")){
					p2="-1";
				}
				if(lblPartido3.getSelectedItem().equals("Ganado")){
					p3="1";
				}else if(lblPartido3.getSelectedItem().equals("Empate")){
					p3="0";
				}else if(lblPartido3.getSelectedItem().equals("Perdido")){
					p3="-1";
				}
				
				equipos.add(list.getSelectedIndex(), new Equipo(equipos.get(list.getSelectedIndex()).getPais(),
						lblRanking.getSelectedItem().toString(),
						lblGrupo.getSelectedItem().toString(),
						lblCopas.getSelectedItem().toString(),
						p1,
						p2, 
						p3
						));
				equipos.remove(list.getSelectedIndex()+1);
				
				String outputFile = "/home/mricharleon/mundial.csv";
		        boolean alreadyExists = new File(outputFile).exists();
		        System.out.println(alreadyExists);
		        
		        if(alreadyExists){
		        	File ficheroUsuarios = new File(outputFile);
		            ficheroUsuarios.delete();
		        } 
		        
		        try {
		            
		            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
		             
		            csvOutput.write(",");
		            csvOutput.write(",");
		            csvOutput.write(",");
		            csvOutput.write(",");
		            csvOutput.write(",");
		            csvOutput.write(",");
		            csvOutput.endRecord();
		             
		            for(Equipo us : equipos){
		                 
		                csvOutput.write(us.getPais());
		                csvOutput.write(us.getRanking());
		                csvOutput.write(us.getGrupo());
		                csvOutput.write(us.getCopas());
		                csvOutput.write(us.getPartido1());
		                csvOutput.write(us.getPartido2());
		                csvOutput.write(us.getPartido3());
		                
		                csvOutput.endRecord();                  
		            }
		             
		            csvOutput.close();
		         
		        } catch (IOException ee) {
		            ee.printStackTrace();
		        }
		        
		        DefaultListModel modelo2 = new DefaultListModel(); 
		        for(Equipo eq : equipos){
		            modelo2.addElement(eq.getPais());
		        }
		    	JOptionPane.showMessageDialog(null,"Datos de "+list.getSelectedValue()+": Actualizados");
		        list.setModel(modelo2);
		        
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		
		
		//
		// datos por defecto por la creacion de la GUI
		//
		GroupLayout groupLayout = new GroupLayout(frmMundial.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPredecir, Alignment.TRAILING)
								.addComponent(textArea, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblPartid1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblPartido1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(labelPartid2)
												.addComponent(lblPartido))
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPartido3, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPartido2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))))
									.addGap(72)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCopa)
										.addComponent(lblRankin, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGrup))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblCopas, 0, 0, Short.MAX_VALUE)
												.addComponent(lblRanking, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblFitness)
												.addComponent(btnEditar))
											.addGap(266))
										.addComponent(lblGrupo, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblEquipo)
								.addComponent(lblImagen, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 940, GroupLayout.PREFERRED_SIZE))
							.addGap(31))
						.addComponent(lblListaDeEquipos))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(lblListaDeEquipos)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblImagen, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(lblEquipo)
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPartido1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPartid1)
								.addComponent(lblGrup)
								.addComponent(lblGrupo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelPartid2)
								.addComponent(lblPartido2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRankin)
								.addComponent(lblRanking, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEditar))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPartido)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblPartido3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCopa)
									.addComponent(lblCopas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPredecir)
								.addComponent(lblFitness))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		scrollPane_1.setViewportView(txtareaproceso);
		
		
		txtareaproceso.setForeground(Color.GREEN);
		txtareaproceso.setFont(new Font("Dialog", Font.PLAIN, 11));
		txtareaproceso.setBackground(Color.BLACK);
		textArea.setFont(new Font("Dialog", Font.PLAIN, 13));
		textArea.setForeground(Color.DARK_GRAY);
		textArea.setBackground(SystemColor.control);
		textArea.setEditable(false);
		frmMundial.getContentPane().setLayout(groupLayout);
		
		// 
		// Empieza configuracion del algoritmo genetico
		//
		
        // arranque
        conf = new DefaultConfiguration();
        Configuration.resetProperty(Configuration.PROPERTY_FITEVAL_INST);
        conf.setFitnessEvaluator(new DefaultFitnessEvaluator());
        conf.getGeneticOperators().clear();

        // operador de intercambio de mutacion
        swapper = new SwappingMutationOperator(conf);
        conf.addGeneticOperator(swapper);
        //determina si se desa guardar el individuo mas apto
        conf.setPreservFittestIndividual(true);
        conf.setPopulationSize(4000);
        //mantiene el tamaño de la poblacion
        conf.setKeepPopulationSizeConstant(false);

        //llamo a mi funcion
        fitnessFunction = new EFitnessFunction(equipos);

        conf.setFitnessFunction(fitnessFunction);

        Gene[] equiposGenes = new Gene[4];

        equiposGenes[0] = new IntegerGene(conf, 0, equipos.size() - 1);
        equiposGenes[1] = new IntegerGene(conf, 0, equipos.size() - 1);
        equiposGenes[2] = new IntegerGene(conf, 0, equipos.size() - 1);
        equiposGenes[3] = new IntegerGene(conf, 0, equipos.size() - 1);

        equipoChromosome = new Chromosome(conf, equiposGenes);
        equiposGenes[0].setAllele(new Integer(0));
        equiposGenes[1].setAllele(new Integer(1));
        equiposGenes[2].setAllele(new Integer(2));
        equiposGenes[3].setAllele(new Integer(3));

        //guia para la construccion de mas cromosomas
        conf.setSampleChromosome(equipoChromosome);
	}
	
	
	
//accion del btn predecir
	
	public void testSelectFittestEquipos() throws Exception {
        Genotype population = Genotype.randomInitialGenotype(conf);
        
        //txtareaproceso.setText(""+population.toString());

        IChromosome bestSolutionSoFar = equipoChromosome;
        String c = "";
        // numero de generaciones
        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            population.evolve();
            IChromosome candidateBestSolution = population.getFittestChromosome(); //Obtenemos el mejor individuo para esta generacion
            
            if (candidateBestSolution.getFitnessValue() > bestSolutionSoFar.getFitnessValue()) {
                bestSolutionSoFar = candidateBestSolution;
            }
            
            c+= "\nMejor Individuo de la generacion "+i+":\n"+candidateBestSolution;
          
        }
        printSolution(bestSolutionSoFar, equipos);
        txtareaproceso.setText(c);
    }
	
	public void printSolution(IChromosome solution, List equipos) {
        
        lblFitness.setText("Fitness: "+solution.getFitnessValue());
        String total = "Pais\t\tRanking\tCopas\tGrupo\tPartido1\tPartido2\tPartido3\n\n";
        
        for (int i = 0; i < solution.size(); i++) {
            int index = (Integer) solution.getGene(i).getAllele();
            Equipo equipo = (Equipo) equipos.get(index);
            System.out.println(equipo.toString());
            
            total+=equipo.toString()+"\n";
        }
        textArea.setText(total);
        
        
    }
}

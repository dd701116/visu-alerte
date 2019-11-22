package item.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import item.Room;
import item.Sensor;

public class StatPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -147118238037862361L;
	

	private Hashtable<String,XYSeries> series;
	private JFreeChart chart;
	
	
	public StatPanel() {
		
		series = new Hashtable<>();
		

	}
	
	private XYDataset createDataset(DefaultListModel<Sensor> sensors) {
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		for (Object o : sensors.toArray()) {
			
			Sensor s = (Sensor)o;
			
			XYSeries xys = new XYSeries(s.getName());
			
			
	
			int save = 0;
			int curseur = 0;
			Double average_temp = 0.0;
			int count = 0;
			
			String elem;
			for (int i = 0; i<s.getTime().size(); i++) {
				
				elem = s.getTime().get(i);
				//	[0] => heure; [1]=> min; [2]=> seconde
				curseur = new Integer(elem.trim().split(":")[1]);
				count++;
				
				if (save!=curseur && i>0) {
					count--;
					Double average = average_temp/count;
					xys.add(curseur,average);
					System.err.println((average_temp/count));
					average_temp = 0.0;
					save = curseur;
					count = 0;
					
				}else if(save!=curseur && i==0){
					save = curseur;
				}else{
					average_temp+=new Double(s.getData().get(i));
					System.out.println("avg : "+average_temp+" count => "+count+" | "+(average_temp/count));
					System.out.println(s.getData().get(i)+"  =>  "+new Double(s.getData().get(i)));
				}
				
			}
			
			series.put(s.getName(),xys);
			dataset.addSeries(xys);
	
		}

        
        

        return dataset;
    }
	
	private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Temperature reading", 
                "Time", 
                "emperature (dC)", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
	    for (int i = 0; i<series.size();i++){
	    	
	    	Random rand = new Random();
	    	float r = rand.nextFloat();
	    	float g = rand.nextFloat();
	    	
	        renderer.setSeriesPaint(i, new Color(r, g, 0));
	        renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle("Temperature reading");

        return chart;

    }
	
	public void draw(Room room){
		
		//	create dataset
		
		XYDataset dataset = createDataset(room.getSensors());
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        chartPanel.setBounds(0, 55, 746, 468);
        add(chartPanel);
		//	create chart
		
	}

}

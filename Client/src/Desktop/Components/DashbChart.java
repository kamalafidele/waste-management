package Desktop.Components;
import Desktop.Components.Registration;
import Desktop.Components.testPanel;
import Desktop.Components.testPanel2;
import Desktop.Components.viewNotifications;

import Components.*;
import Desktop.Components.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mindfusion.charting.FunctionSeries;
import com.mindfusion.charting.GridType;
import com.mindfusion.charting.swing.LineChart;
import com.mindfusion.drawing.DashStyle;
import com.mindfusion.drawing.SolidBrush;

public class DashbChart extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    new DashbChart().setVisible(true);
                }
                catch (Exception exp)
                {
                }
            }
        });
    }

    protected DashbChart()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 400);
        setTitle("Java Swing Library for Charts and Gauges: FunctionSeries");

        getContentPane().add(initializeChart(), BorderLayout.CENTER);

    }

    private LineChart initializeChart()
    {
        LineChart lineChart = new LineChart();

        lineChart.getXAxis().setMinValue(-7.0);
        lineChart.getXAxis().setMaxValue(7.0);
        lineChart.getXAxis().setInterval(0.5);
        lineChart.getXAxis().setOrigin(0.0);
        lineChart.getXAxis().setTitle("X-axis");

        lineChart.getYAxis().setMaxValue(0.5);
        lineChart.getYAxis().setTitle("Y-axis");

        //specifying color and stroke for the axes
        lineChart.getTheme().setAxisStroke(new SolidBrush(new Color(190, 190, 190)));
        lineChart.getTheme().setAxisStrokeThickness(1.0);

        //styling the legend
        lineChart.getLegendRenderer().setShowTitle(false);
        lineChart.getTheme().setDataLabelsFontSize(12);

        //styling the grid
        lineChart.setGridType(GridType.Horizontal);
        lineChart.getTheme().setGridLineColor(new Color(192, 192, 192));
        lineChart.getTheme().setGridLineStyle(DashStyle.Dash);

        //setting the chart colors
        lineChart.getTheme().setHighlightStroke(new SolidBrush(new Color(255, 147, 66)));
        lineChart.getTheme().setCommonSeriesStrokes(
                Arrays.asList(
                        new SolidBrush( new Color (0, 52, 102 )),
                        new SolidBrush( new Color (206, 0, 0))));
        lineChart.getTheme().setCommonSeriesFills(
                Arrays.asList(
                        new SolidBrush( new Color (0, 52, 102 )),
                        new SolidBrush( new Color (206, 0, 0))));
        lineChart.getTheme().setCommonSeriesStrokeThicknesses(
                Arrays.asList(3.0));

        FunctionSeries series1;
        FunctionSeries series2;

        try
        {
            series1 = new FunctionSeries("Pow(2.71828, -1*x*x/2) / Sqrt(6.28)",
                    1000, -5, 5);
            series1.setTitle("Standard normal distribution");
            lineChart.getSeries().add(series1);

            series2 = new FunctionSeries("Pow(2.71828, -1*((x + 2)*(x + 2)/2))/Sqrt(6.28)",
                    1000, -5, 5);
            series2.setTitle("Standard normal distribution with mean -2");
            lineChart.getSeries().add(series2);
        }
        catch(Exception e)
        {
            e.printStackTrace();

        }

        return lineChart;
    }

}

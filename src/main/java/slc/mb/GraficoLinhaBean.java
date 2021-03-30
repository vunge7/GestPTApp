/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slc.mb;

/**
 *
 * @author Domingos Dala Vunge
 */
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

@Named( value = "graficoLinhaBean" )
public class GraficoLinhaBean implements Serializable
{

    private LineChartModel lineModel1;

    @PostConstruct
    public void init()
    {
        createLineModels();
    }

    public CartesianChartModel getLineModel1()
    {
        return lineModel1;
    }

    private void createLineModels()
    {
        lineModel1 = initLinearModel();
        lineModel1.setTitle( "Pesquisa de Opinião" );
        lineModel1.setLegendPosition( "e" );
        lineModel1.setShowPointLabels( true );
        lineModel1.getAxes().put( AxisType.X, new CategoryAxis( "Pesquisas" ) );
        lineModel1.setZoom( true );

        Axis yAxis = lineModel1.getAxis( AxisType.Y );
        yAxis.setLabel( "% de votos" );
        yAxis.setMin( 0 );
        yAxis.setMax( 100 );

    }

    private LineChartModel initLinearModel()
    {
        LineChartModel model = new LineChartModel();

        ChartSeries series1 = new ChartSeries();
        series1.setLabel( "Candidato 1" );

        series1.set( "Pesquisa 1", 40 );
        series1.set( "Pesquisa 2", 42 );
        series1.set( "Pesquisa 3", 47 );
        series1.set( "Pesquisa 4", 44 );
        series1.set( "Pesquisa 5", 40 );

        ChartSeries series2 = new ChartSeries();
        series2.setLabel( "Candidato 2" );

        series2.set( "Pesquisa 1", 20 );
        series2.set( "Pesquisa 2", 22 );
        series2.set( "Pesquisa 3", 26 );
        series2.set( "Pesquisa 4", 25 );
        series2.set( "Pesquisa 5", 29 );

        ChartSeries series3 = new ChartSeries();
        series3.setLabel( "Candidato 3" );

        series3.set( "Pesquisa 1", 10 );
        series3.set( "Pesquisa 2", 12 );
        series3.set( "Pesquisa 3", 16 );
        series3.set( "Pesquisa 4", 15 );
        series3.set( "Pesquisa 5", 19 );

        model.addSeries( series1 );
        model.addSeries( series2 );
        model.addSeries( series3 );

        return model;
    }

}

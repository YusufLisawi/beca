package beans;

import model.Categorie;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import service.CategorieDAOImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "stats", eager = true)
@RequestScoped
public class Stats implements Serializable {

    private static final long serialVersionUID = 1L;
    private BarChartModel barModel;
    private List<Categorie> categories;

    @PostConstruct
    public void init() {
        categories = new CategorieDAOImpl().listCategories();
        createBarModel();
    }

    public void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        // Create and configure the dataset
        BarChartDataSet barDataSet = createBarDataSet();

        data.addChartDataSet(barDataSet);
        data.setLabels(getCategoryNames());

        barModel.setData(data);

        // Configure the options
        BarChartOptions options = createChartOptions();
        barModel.setOptions(options);
    }

    private BarChartDataSet createBarDataSet() {
        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Nombre des Produits dans Categories");
        barDataSet.setData(getProductCounts());
        barDataSet.setBackgroundColor(getBackgroundColors());
        barDataSet.setBorderColor(getBorderColors());
        barDataSet.setBorderWidth(1);
        return barDataSet;
    }

    private List<Number> getProductCounts() {
        List<Number> values = new ArrayList<>();
        for (Categorie ctg : categories) {
            values.add(ctg.getProduits().size());
        }
        return values;
    }

    private List<String> getCategoryNames() {
        List<String> labels = new ArrayList<>();
        for (Categorie ctg : categories) {
            labels.add(ctg.getNom());
        }
        return labels;
    }

    private List<String> getBackgroundColors() {
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        return bgColor;
    }

    private List<String> getBorderColors() {
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        return borderColor;
    }

    private BarChartOptions createChartOptions() {
        BarChartOptions options = new BarChartOptions();
        options.setScales(createScales());
        options.setTitle(createTitle());
        options.setLegend(createLegend());
        return options;
    }

    private CartesianScales createScales() {
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        cScales.addYAxesData(linearAxes);
        return cScales;
    }

    private Title createTitle() {
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        return title;
    }

    private Legend createLegend() {
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        return legend;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
}
package tn.esprit.pidev.views;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Order;
import tn.esprit.pidev.entities.Pack;
import tn.esprit.pidev.services.OrderService;
import tn.esprit.pidev.services.PackService;

public class OverviewPacksScreen extends Form {
    Form current;
OrderService orderService = new OrderService();
    public OverviewPacksScreen(Form previous) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Declined Packs");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
// WE GO FOR STATS HERE
        int pendingOrder = 0;
        int acceptedOrder = 0;
        int declinedOrder = 0;
for(Order order : orderService.showAll()){
if(order.getStatus().equals("Pending")){
    pendingOrder++;
}else if (order.getStatus().equals("Accepted")){
    acceptedOrder++;
} else {
    declinedOrder++;
}
}
        double[] values = new double[]{acceptedOrder, pendingOrder, declinedOrder};
        // Set up the renderer
        int[] colors = new int[]{ ColorUtil.GREEN, ColorUtil.YELLOW, ColorUtil.MAGENTA};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(160);
        renderer.setChartTitle("Statistique");
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer simpleSeriesRendererAccepted = renderer.getSeriesRendererAt(0);
        SimpleSeriesRenderer simpleSeriesRendererPending = renderer.getSeriesRendererAt(1);
          SimpleSeriesRenderer simpleSeriesRendererDeclined = renderer.getSeriesRendererAt(2);
        simpleSeriesRendererAccepted.setHighlighted(true);
        simpleSeriesRendererPending.setHighlighted(true);
        simpleSeriesRendererDeclined.setHighlighted(true);
        // Create the chart ... pass the values and renderer to the chart object.
        PieChart pieChart = new PieChart(buildCategoryDataset("Packs", values), renderer);
        // Wrap the chart in a Component so we can add it to a form
        ChartComponent chartComponent = new ChartComponent(pieChart);
        add(chartComponent);

        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {    });
        getToolbar().addCommandToLeftSideMenu("Home", null, (evt) -> {        new MenuScreen().show(); });
        getToolbar().addCommandToLeftSideMenu("All Packs", null, (evt) -> {        new AllPacksScreen(current).show(); });
        getToolbar().addCommandToLeftSideMenu("My Packs", null, (evt) -> { new MyPacksScreen(current).show(); });
        getToolbar().addCommandToLeftSideMenu("My Orders", null, (evt) -> { new MyOrdersScreen(current).show(); });
        getToolbar().addCommandToLeftSideMenu("Declined Packs", null, (evt) -> { new DeclinedPacksScreen(current).show(); });
        getToolbar().addCommandToLeftSideMenu("Overview Packs", null, (evt) -> { new OverviewPacksScreen(current).show(); });

    }
    /* *** *PIECHART METHODS REQUIRED* *** */
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(32);
        renderer.setLegendTextSize(64);
        renderer.setLabelsColor(ColorUtil.BLACK);
        renderer.setAxesColor(ColorUtil.BLACK);

        renderer.setMargins(new int[]{20, 30, 15, 0});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("Accepted", values[0]);
        series.add("Pending", values[1]);
        series.add("Declined", values[2]);
        return series;
    }
}

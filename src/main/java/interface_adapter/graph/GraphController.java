package interface_adapter.graph;

import org.jfree.chart.JFreeChart;
import use_case.graph.GraphInputBoundary;

public class GraphController {
    GraphInputBoundary boundary;
    public GraphController(GraphInputBoundary boundary) {
        this.boundary = boundary;
    }
    public void smallchange(JFreeChart chart, String direction){
        switch (direction){
            case "yup":
                boundary.yUp(chart);
                break;
            case "xup":
                boundary.xUp(chart);
                break;
            case "ydown":
                boundary.yDown(chart);
                break;
            case "xdown":
                boundary.xDown(chart);
                break;
        }
    }

    public void change(JFreeChart chart, String xMin, String xMax, String yMin, String yMax){
        boundary.updateAxisRange(chart, xMin, xMax, yMin, yMax);
    }
}

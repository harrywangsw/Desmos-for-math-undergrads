package entity;

public class Graph {

    private final String equation;
    private final String pathToImage;



    public Graph(String equation, String pathToImage) {
        this.equation = equation;
        this.pathToImage = pathToImage;
    }

    public Graph() {
        this.equation = "";
        this.pathToImage = "";
    }
}

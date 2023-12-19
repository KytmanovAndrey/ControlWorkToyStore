import View.ToyShopView;
import service.ToyService;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        ToyShopView toyShopView = new ToyShopView(new ToyService(new PriorityQueue<>()));
        toyShopView.showUI();
    }
}

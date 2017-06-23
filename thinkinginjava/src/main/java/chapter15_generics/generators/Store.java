package chapter15_generics.generators;

import chapter15_generics.watercolors.Generators;
import net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by xhtc on 2017/5/5.
 */

/**
 * 创建商店中，每个过道上的货架，每个货架中的商品
 *
 * 打印所有商品
 */
public class Store extends ArrayList<Aisle>{

    private ArrayList<CheckoutStand> checkouts = new ArrayList<CheckoutStand>();

    private Office office = new Office();

    public Store(int nAisles, int nShelves, int nProducts) {
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelves, nProducts));
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Aisle a : this)
            for (Shelf s : a)
                for (Product p : s) {
                    result.append(p);
                    result.append("\n");
                }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Store(14,5,10));
    }

}


class Product {
    private final int id;
    private String description;
    private double price;

    public Product(int id, String des, double price) {
        this.id = id;
        this.description = des;
        this.price = price;

    }

    public String toString() {
        return id + ": " + description + ". price: $" + price;
    }

    public void priceChange(double change) {
        price += change;
    }

    public static Generator<Product> generator = new Generator<Product>() {
//        @Override
        public Product next() {
            return new Product(new Random().nextInt(1000), "Test", Math.round(new Random().nextDouble() * 1000.0) + 0.99);
        }
    };
}

class Shelf extends ArrayList<Product> {
    public Shelf(int nProducts) {
        Generators.fill(this, Product.generator, nProducts);
    }
}

class Aisle extends ArrayList<Shelf> {
    public Aisle(int nShelves, int nProducts) {
        for (int i = 0; i < nShelves; i++) {
            add(new Shelf(nProducts));
        }
    }
}

class CheckoutStand {
}

class Office {
}

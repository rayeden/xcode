package chapter1_objects;

/**
 * Builder模式构建器
 */
public class NutritionFacts {

    private int servingSize;

    private int servings;

    private int calories;

    private int fat;

    private int sodium;

    private int carbohydrate;

    public NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }

    public static class Builder {

        private int servingSize;

        private int servings;

        private int calories = 0;

        private int fat = 0;

        private int carbohydrate = 0;

        private int sodium = 0;

        private Builder(int servingSize, int servings) {
            this.servings = servings;
            this.servingSize = servingSize;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts.Builder(240,8).calories(100).sodium(35).carbohydrate(27).build();
    }

}

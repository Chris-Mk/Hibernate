package fdmc.domain.entities;

public class Cat {
    private String name;
    private String breed;
    private String color;
    private int numberOfLegs;

    public Cat(Builder builder) {
        this.name = builder.name;
        this.breed = builder.breed;
        this.color = builder.color;
        this.numberOfLegs = builder.numberOfLegs;
    }

    public static class Builder {
        private String name;
        private String breed;
        private String color;
        private int numberOfLegs;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder breed(String breed) {
            this.breed = breed;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder numberOfLegs(int numberOfLegs) {
            this.numberOfLegs = numberOfLegs;
            return this;
        }

        public Cat build() {
            return new Cat(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public int getNumberOfLegs() {
        return numberOfLegs;
    }
}

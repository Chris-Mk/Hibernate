package chushka.domain.models;

public class ProductCreateModel {
    private String name;
    private String description;
    private String type;

    public ProductCreateModel(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.type = builder.type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public static class Builder {
        private String name;
        private String description;
        private String type;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public ProductCreateModel build() {
            return new ProductCreateModel(this);
        }
    }
}

class Computer {
    private String cpu;
    private String ram;
    private String storage;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }

    @Override
    public String toString() {
        return "Computer Configuration:" +
                "\nCPU: " + cpu +
                "\nRAM: " + ram +
                "\nStorage: " + storage;
    }

    public static class Builder {
        private String cpu;
        private String ram;
        private String storage;

        public Builder setCPU(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setRAM(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class BuilderPatternExample 
{
    public static void main(String[] args) {
        Computer gamingComputer = new Computer.Builder()
                .setCPU("High-End Gaming CPU")
                .setRAM("32GB DDR4")
                .setStorage("1TB NVMe SSD")
                .build();

        Computer officeComputer = new Computer.Builder()
                .setCPU("Mid-Range CPU")
                .setRAM("16GB DDR4")
                .setStorage("512GB SSD")
                .build();

        System.out.println(gamingComputer);
        System.out.println();
        System.out.println(officeComputer);
    }
}

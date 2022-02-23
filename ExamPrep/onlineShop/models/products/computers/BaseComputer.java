package onlineShop.models.products.computers;
//created by J.M.

import onlineShop.models.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.LinkedList;
import java.util.List;

import static onlineShop.common.constants.ExceptionMessages.*;
import static onlineShop.common.constants.OutputMessages.COMPUTER_COMPONENTS_TO_STRING;
import static onlineShop.common.constants.OutputMessages.COMPUTER_PERIPHERALS_TO_STRING;


public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id,
                           String manufacturer,
                           String model,
                           double price,
                           double overallPerformance) {

        super(id, manufacturer, model, price, overallPerformance);

        this.components = new LinkedList<>();
        this.peripherals = new LinkedList<>();
    }

    @Override
    public double getOverallPerformance() {

        return this.components.isEmpty() ? super.getOverallPerformance()
                : super.getOverallPerformance() + getComponentsAveragePerformance();

    }

    @Override
    public double getPrice() {

        return super.getPrice() +
                components.stream().mapToDouble(Component::getPrice).sum() +
                peripherals.stream().mapToDouble(Peripheral::getPrice).sum();
    }

    @Override
    public void addComponent(Component component) {

        if (components.stream().anyMatch(e -> e.getClass().getSimpleName()
                .equals(component.getClass().getSimpleName()))) {

            throw new IllegalArgumentException(String.format(EXISTING_COMPONENT,
                    component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        this.components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {

        Component exist = components.stream().filter(e -> e.getClass().getSimpleName()
                .equals(componentType)).findFirst().orElse(null);

        if (exist == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_COMPONENT,
                    componentType, this.getClass().getSimpleName(), this.getId()));
        }
        components.remove(exist);
        return exist;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {

        if (peripherals.stream().anyMatch(c -> c.getClass().getSimpleName()
                .equals(peripheral.getClass().getSimpleName()))) {

            throw new IllegalArgumentException(String.format(EXISTING_PERIPHERAL,
                    peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
        }
        this.peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {

        Peripheral exist = peripherals.stream().filter(e -> e.getClass().getSimpleName()
                .equals(peripheralType)).findFirst().orElse(null);

        if (exist == null) {
            throw new IllegalArgumentException(String.format(NOT_EXISTING_PERIPHERAL,
                    peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        peripherals.remove(exist);

        return exist;
    }

    @Override
    public String toString() {

        StringBuilder out = new StringBuilder(super.toString());

        out.append(System.lineSeparator()).append(" ")
                .append(String.format(COMPUTER_COMPONENTS_TO_STRING, this.components.size()))
                .append(System.lineSeparator());
        if (!this.components.isEmpty()) {
            components.forEach(component -> out.append("  ").append(component).append(System.lineSeparator()));
        }
        out.append(" ")
                .append(String.format(COMPUTER_PERIPHERALS_TO_STRING
                        , this.peripherals.size(), getPeripheralsAveragePerformance()))
                .append(System.lineSeparator());
        if (!this.peripherals.isEmpty()) {
            peripherals.forEach(peripheral -> out.append("  ").append(peripheral).append(System.lineSeparator()));
        }
        return out.toString().trim();
    }

    @Override
    public List<Component> getComponents() {

        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {

        return this.peripherals;
    }

    private double getPeripheralsAveragePerformance() {

        return this.getPeripherals().stream()
                .mapToDouble(Peripheral::getOverallPerformance).average().orElse(0);
    }

    private double getComponentsAveragePerformance() {

        return this.getComponents().stream()
                .mapToDouble(Component::getOverallPerformance).average().orElse(0);
    }
}

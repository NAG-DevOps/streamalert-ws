/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temperature;

/**
 *
 * @author root
 */
public class TemperatureBean {
    private String temperature = "0.0";
    
    private TemperatureBean() {}
    
    public static TemperatureBean getInstance() {
        return TemperatureBeanHolder.INSTANCE;
    }
    
    private static class TemperatureBeanHolder {
        private static final TemperatureBean INSTANCE = new TemperatureBean();
    }
    
    public String getTemperature() {
        return temperature;
    }
    
    public void setTemperature(String temp) {
        this.temperature = temp;
    }
}

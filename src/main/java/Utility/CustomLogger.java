/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrben
 */
public class CustomLogger {
    private static Logger loggerInstance;
    public static void CustomLogger(String className,String errorMessage,Level severity) {
        loggerInstance = Logger.getLogger(className);
        loggerInstance.log(severity, "An error occurred: {0}", errorMessage);
    }
}

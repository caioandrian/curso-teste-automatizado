package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class BasePage {
    // Protected permite que uma classe filha quando estendida da BasePage, possa usar esse atributo.
    protected WebDriver navegador;

    public BasePage(WebDriver navegador){
        this.navegador = navegador;
    }

    // Notificacao que aparece no sistema após adicionar, excluir, editar, etc.
    public String capturarTextoToast(){
        // Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        return navegador.findElement(By.id("toast-container")).getText();
    }
}

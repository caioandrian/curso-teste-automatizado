package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddContactPage extends BasePage{
    public AddContactPage(WebDriver navegador) {
        super(navegador);
    }

    public AddContactPage escolherTipoDeContato(String tipo){
        // Selecionar opção do dropbox
        WebElement campoType = navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        return this;
    }

    public AddContactPage digitarContato(String contato){
        navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contato);

        return this;
    }

    public MePage clicarSalvar(){
        // Identificar e clicar no link que tem o texto "SAVE" que está na div "addmoredata"
        navegador.findElement(By.linkText("SAVE")).click();

        return new MePage(navegador);
    }

    // Método funcional com a abordagem estrutural
    public MePage adicionarContato(String tipo, String contato){
        escolherTipoDeContato(tipo);
        digitarContato(contato);
        clicarSalvar();

        return new MePage(navegador);
    }
}

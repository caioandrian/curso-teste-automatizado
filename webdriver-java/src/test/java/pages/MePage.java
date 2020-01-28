package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage{

    public MePage(WebDriver navegador) {
        //como o atributo da BasePage é protect, a Classe Filha pode usar esse atributo da classe Pai.
        super(navegador);
    }

    public MePage clicarNaAbaMoreDataAboutYou(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        return this;
    }


    // o popup que será aberto para cadastrar um novo contato é considerado uma nova página
    // logo iremos para AddContactPage
    public AddContactPage clicarNoBotaoAddMoreDataAbout(){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        return new AddContactPage(navegador);
    }

}

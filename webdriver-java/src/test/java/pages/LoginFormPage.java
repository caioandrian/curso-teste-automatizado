package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {

    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    // Quando se cria um método para cada campo, chama-se de abordagem estrutural
    // Como a próxima ação irá para a mesma página, permanece na mesma página LoginFormPage.
    public LoginFormPage digitarLogin(String login){
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);

        return this;
    }

    public LoginFormPage digitarPassword(String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);

        return this;
    }

    public SecretaPage clicarSignIn(){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).click();
        navegador.findElement(By.linkText("SIGN IN")).click();

        return new SecretaPage(navegador);
    }

    // Abordagem funcional + Abordagem estrutural
    public SecretaPage fazerLogin(String login, String password){
        digitarLogin(login);
        digitarPassword(password);
        clicarSignIn();

        return new SecretaPage(navegador);
    }
}

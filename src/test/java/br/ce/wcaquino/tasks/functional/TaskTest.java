package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em ADD TODO
			driver.findElement(By.id("addTodo")).click();
			// escrever DESCRICAO
			driver.findElement(By.id("task")).sendKeys("Test par le Selenium");
			// escrever a DATA
			driver.findElement(By.id("dueDate")).sendKeys("11/02/2021");
			// clicar em SALVAR
			driver.findElement(By.id("saveButton")).click();
			// validar MSG de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			// fechar tela
			driver.quit();
		}
	}
	@Test
	public void naoSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em ADD TODO
			driver.findElement(By.id("addTodo")).click();
			// escrever a DATA
			driver.findElement(By.id("dueDate")).sendKeys("11/02/2021");
			// clicar em SALVAR
			driver.findElement(By.id("saveButton")).click();
			// validar MSG de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
			// fechar tela
			driver.quit();
		}
	}
	@Test
	public void naoSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em ADD TODO
			driver.findElement(By.id("addTodo")).click();
			// escrever DESCRICAO
			driver.findElement(By.id("task")).sendKeys("Test par le Selenium");
			// clicar em SALVAR
			driver.findElement(By.id("saveButton")).click();
			// validar MSG de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
			// fechar tela
			driver.quit();
		}
	}
	@Test
	public void naoSalvarTarefaDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em ADD TODO
			driver.findElement(By.id("addTodo")).click();
			// escrever DESCRICAO
			driver.findElement(By.id("task")).sendKeys("Test par le Selenium");
			// escrever a DATA
			driver.findElement(By.id("dueDate")).sendKeys("11/02/2020");
			// clicar em SALVAR
			driver.findElement(By.id("saveButton")).click();
			// validar MSG de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			// fechar tela
			driver.quit();
		}
	}
}

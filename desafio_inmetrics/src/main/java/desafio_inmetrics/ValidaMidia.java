package desafio_inmetrics;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import org.apache.commons.io.FileUtils;


import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class ValidaMidia {
	
	WebDriver driver;
	String dirEvidencias = "C:\\Users\\marci\\OneDrive\\Documentos\\Evidencias\\";
	String dirDriveChrome = "C:\\Users\\marci\\git\\desafio_inmetrics\\desafio_inmetrics\\drivers\\chrome\\83\\";
	
	@Before
	public void iniciar() {
		System.setProperty("webdriver.chrome.driver",dirDriveChrome.concat("chromedriver.exe"));
		driver = new ChromeDriver();
		driver.get("https://www.inmetrics.com.br/");
		driver.manage().window().maximize();
	}
	
	@Dado("^que o usuario esteja na tela home do site da Inmetrics$")
	public void que_o_usuario_esteja_na_tela_home_do_site_da_Inmetrics() throws Throwable {

	}

	@Quando("^ele clicar no menu \"([^\"]*)\"$")
	public void ele_clicar_no_menu(String termo) throws Throwable {
		driver.findElement(By.linkText(termo)).click();
	}

	@Entao("^sera exibida a tela \"([^\"]*)\" com as midias disponiveis$")
	public void sera_exibida_a_tela_com_as_midias_disponiveis(String titulo) throws Throwable {
		assertEquals(titulo, driver.getTitle());
		this.tiraPrint(driver, dirEvidencias.concat("NaMidiaInmetrics.png"));
	}

	@Entao("^usuario clica na midia desejada$")
	public void usuario_clica_na_midia_desejada() throws Throwable {				
		driver.findElement(By.linkText("Inmetrics abre mais de 100 vagas em São Paulo, Minas Gerais e Paraná"));	
	}

	@Entao("^sera exibida a tela da midia correspondente$")
	public void sera_exibida_a_tela_da_midia_correspondente() throws Throwable {
		driver.findElement(By.linkText("Inmetrics abre mais de 100 vagas em São Paulo, Minas Gerais e Paraná")).click();
		this.tiraPrint(driver, dirEvidencias.concat("MidiaSelecionada.png"));
	}
	
	public void tiraPrint(WebDriver webdriver,String fileWithPath) throws Exception{

		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);

    }

}

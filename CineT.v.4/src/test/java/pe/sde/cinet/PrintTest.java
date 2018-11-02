package pe.sde.cinet;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class PrintTest {

	
	public static void main(String[] args) {
		try {
			Desktop.getDesktop().print(new File("http://localhost:8080/ticket/genera/?format=pdf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

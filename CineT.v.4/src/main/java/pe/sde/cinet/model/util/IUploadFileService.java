package pe.sde.cinet.model.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	public Resource load(String fileName) throws MalformedURLException;
	
	public String copy(MultipartFile file) throws IOException;
	
	public boolean delete(String fileName);
	
	public void deletAll();
	
	public void init() throws IOException;
	public Path getPath(String fileName);
	public void saveCodBar(BufferedImage bufImg, String codBarr);
}

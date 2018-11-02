package pe.sde.cinet.model.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	private final static String UPLOAD_FOLDER = "uploads";
	
	
	
	@Override
	public Resource load(String fileName) throws MalformedURLException {
		Path pathFile = getPath(fileName);
		log.info("pathFile: " + pathFile);
		Resource recurso = null;
		recurso = new UrlResource(pathFile.toUri());
		if(!recurso.exists() || !recurso.isReadable()){
			throw new RuntimeException("Error: No se puede cargar imagen: " + pathFile.toString());
		}				
		return recurso;
	}
	

	@Override
	public String copy(MultipartFile file)  throws IOException{
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path rootPath = getPath(uniqueFilename);
		Files.copy(file.getInputStream(), rootPath);
		return uniqueFilename;
	}
	
	@Override
	public void saveCodBar(BufferedImage bufImg, String codBarr){
		Path rootPath = getPath(codBarr+".png");
		File file = new File(rootPath.toUri());
		try {
			ImageIO.write(bufImg, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(String fileName) {
		Path rootPath = getPath(fileName);
		File archivo = rootPath.toFile();
		
		if(archivo.exists() && archivo.canRead()){
			if(archivo.delete()){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Path getPath(String fileName){
		return Paths.get(UPLOAD_FOLDER).resolve(fileName).toAbsolutePath();
	}


	@Override
	public void deletAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOAD_FOLDER).toFile());
	}


	@Override
	public void init() throws IOException {
		Files.createDirectory(Paths.get(UPLOAD_FOLDER));		
	}

}

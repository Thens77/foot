package ma.emsi.foot.ServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Terrain;
import ma.emsi.foot.repository.TerrainRepository;
import ma.emsi.foot.service.TerrainService;

@Service
@Transactional
public class TerrainServiceImpl implements TerrainService {

	@Autowired
	private TerrainRepository repository;

	@Override
	public Terrain ajouter(Terrain terrain) {
		return repository.save(terrain);
	}

	@Override
	public Terrain modifier(Terrain terrain, Long id) {
		// TODO Auto-generated method stub
		Terrain terrain2 = repository.findById(id).get();
		if (terrain2 != null) {
			terrain2.setClub(terrain.getClub());
			terrain2.setDescription(terrain.getDescription());
			terrain2.setNbrJoueurs(terrain.getNbrJoueurs());
			terrain2.setPrix(terrain.getPrix());
			terrain2.setSize(terrain.getSize());
			terrain2.setPhotos(terrain.getPhotos());
			return repository.save(terrain2);
		}
		return null ;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Terrain getTerrain(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Terrain> liste() {
		return repository.findAll();
	}

	@Override
	public List<Terrain> listeByClub(Long id) {
		System.out.println("here " + repository.findByClub(id));
		return repository.findByClub(id);
	}
	
	@Override
	public byte[] decompressBytes(byte[] data) {
		// TODO Auto-generated method stub
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	@Override
	public byte[] compressBytes(byte[] data) {
		// TODO Auto-generated method stub
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

}

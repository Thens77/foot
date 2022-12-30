package ma.emsi.foot.ServiceImpl;

import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Club;
import ma.emsi.foot.repository.ClubRepository;
import ma.emsi.foot.service.ClubService;

@Service
@Transactional
public class ClubServiceImpl implements ClubService{
	
	@Autowired
	private ClubRepository repository;

	@Override
	public Club ajouter(Club club) {
		club.setEtat(false);
		return repository.save(club);
	}

	@Override
	public Club modifier(Club club) {
		// TODO Auto-generated method stub
		System.out.println(club);
		Club club2 = repository.findById(club.getId()).get();
		if (club2 != null) {
			
			club2.setClubName(club.getClubName());
			club2.setDescription(club.getDescription());
			club2.setIdFiscal(club.getIdFiscal());
			club2.setLatitude(club.getLatitude());
			club2.setLongitude(club.getLongitude());
			club2.setSosName(club.getSosName());
			return repository.save(club2);
		}
		return null ;
		
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Club getClub(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Club> liste() {
		return repository.findAll();
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
		if(data!=null) {
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
		return null;
	}

	@Override
	public List<Club> getByProprietaire(Long id) {
		return repository.getByProprietaire(id);
	}
}

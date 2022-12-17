package ma.emsi.foot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.foot.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{

}

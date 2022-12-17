package ma.emsi.foot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.foot.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

package chamados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chamados.model.Photo;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{

}

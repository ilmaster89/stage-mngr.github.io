package itsar.selectmngr.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import itsar.selectmngr.models.Cv;

public interface CvDao extends JpaRepository<Cv, Integer> {

}

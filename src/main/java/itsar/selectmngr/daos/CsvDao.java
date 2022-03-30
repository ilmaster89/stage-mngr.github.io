package itsar.selectmngr.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import itsar.selectmngr.models.Csv;

public interface CsvDao extends JpaRepository<Csv, Integer> {

}

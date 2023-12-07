package ru.skypro.homework.repostitory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Ad;

import java.util.Collection;
import java.util.List;

public interface AdRepository extends CrudRepository<Ad, Long> {



    List<Ad> findAllAdByUserId(long id);




}

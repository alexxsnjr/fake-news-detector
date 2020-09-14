package com.fakenews.commons.fakenewsCommons.models.dao;

import com.fakenews.commons.fakenewsCommons.models.entity.New;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface NewRepository extends CrudRepository<New, Long> {
    public New findByUrl(String url);

    @Query("FROM New n WHERE n.createAt BETWEEN :dateFrom and :dateTo")
    public List<New> getBetweenDate(@Param("dateFrom") Date dateFrom,@Param("dateTo") Date dateTo);
}

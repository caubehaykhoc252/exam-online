package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.Option;
import com.ntrungthanh1.subjectTest.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

    @Query(value = "select * from options  where id = :id ", nativeQuery = true)
    Option getOptionById(Long id);

    @Query(value = "SELECT o from Option o where inventoryId in :ids")
    List<Option> getListByInventoryIds(@Param("ids") List<Long> listId);

    @Query
    List<Option> findListByQuestion(Question question);
}

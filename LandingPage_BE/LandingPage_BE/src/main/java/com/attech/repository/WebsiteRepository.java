package com.attech.repository;

import com.attech.model.dto.website.ResponseWebsite;
import com.attech.model.entity.Websites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WebsiteRepository extends JpaRepository<Websites,Long> {
    @Query(value = "select \n" +
            "ws.id,\n" +
            "ws.name,\n" +
            "date_format(ws.time_start, '%d/%m/%y %h:%m:%s'),\n" +
            "date_format(ws.last_edited,'%d/%m/%y %h:%m:%s'),\n" +
            "ws.alias_website,\n" +
            "ws.name_server,\n" +
            "ws.domain,\n" +
            "u.user_name,\n" +
            "u.email from tbl_website ws \n" +
            "left join tbl_user u on ws.user_id = u.id\n" +
            "where 1=1 \n" +
            "and upper(ws.name) like upper(concat('%',:keySearch,'%'))\n" +
            "or upper(ws.alias_website) like upper(concat('%',:keySearch,'%'));"
            ,nativeQuery = true)
    List<Object[]> findAllByKeySearch(@Param("keySearch") String keySearch);

    boolean existsByNameAndAliasWebsite(String name,String aliasWebsite);

    Websites findByName(String webName);


    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByAliasWebsiteAndIdNot(String alias, Long id);
    List<Websites> findByUserId(Long userId);
    @Query(value = "select \n" +
            "ws.id,\n" +
            "ws.name,\n" +
            "date_format(ws.time_start, %d/%m/%y %h:%m:%s),\n" +
            "date_format(ws.last_edited, %d/%m/%y %h:%m:%s),\n" +
            "ws.alias_website,\n" +
            "ws.name_server,\n" +
            "ws.domain,\n" +
            "w.user_id" +
            " from tbl_website ws where w.user_id = :userID "
            ,nativeQuery = true)
    List<ResponseWebsite> findAllByUserId(Long userID);

}

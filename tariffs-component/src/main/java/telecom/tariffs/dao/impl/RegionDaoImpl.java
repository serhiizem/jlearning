package telecom.tariffs.dao.impl;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import telecom.tariffs.dao.RegionDao;
import telecom.tariffs.model.Region;

import java.sql.CallableStatement;
import java.sql.ResultSet;

import static telecom.tariffs.util.Queries.*;

@Repository
public class RegionDaoImpl extends CrudDaoImpl<Region>
        implements RegionDao {

    @Autowired
    public RegionDaoImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    @SneakyThrows
    public void fillSaveStatement(CallableStatement statement, Region entity) {
        statement.setString("name", entity.getName());
    }

    @Override
    public void fillUpdateStatement(CallableStatement statement, Region entity) {
    }

    @Override
    public Region getEntity(ResultSet resultSet) {
        return new Region(resultSet);
    }

    @Override
    public String getSaveQuery() {
        return CREATE_REGION_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getGetByIdQuery() {
        return GET_REGION_QUERY;
    }

    @Override
    public String getGetAllQuery() {
        return GET_ALL_REGIONS_QUERY;
    }
}

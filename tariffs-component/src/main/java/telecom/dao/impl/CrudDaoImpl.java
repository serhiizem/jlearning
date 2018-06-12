package telecom.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.*;
import telecom.dao.CrudDao;
import telecom.dao.PersistentEntity;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class CrudDaoImpl<T extends PersistentEntity>
        implements CrudDao<T> {

    private final JdbcTemplate jdbcTemplate;

    public T save(T entity) {
        CallableStatementCreator creator = this.getCreatorForEntity(entity);
        CallableStatementCallback<Long> callback = this.getStatementCallback();
        Long generatedId = this.jdbcTemplate.execute(creator, callback);
        log.debug("Created entity with an id: {}", generatedId);

        return this.findOne(generatedId);
    }

    @Override
    public T update(T entity) {
        CallableStatementCreator updator = this.getUpdatorForEntity(entity);
        T oldEntity = this.findOne(entity.getId());
        CallableStatementCallback<Long> callback = this.getStatementCallback();
        this.jdbcTemplate.execute(updator, callback);
        return oldEntity;
    }

    private CallableStatementCreator getCreatorForEntity(T entity) {
        return connection -> {
            String saveQuery = getSaveQuery();
            log.debug("Saving entity by query: {}", saveQuery);
            CallableStatement call = connection.prepareCall(saveQuery);
            fillSaveStatement(call, entity);
            return call;
        };
    }

    private CallableStatementCallback<Long> getStatementCallback() {
        return cs -> {
            cs.execute();
            return cs.getLong(1);
        };
    }


    private CallableStatementCreator getUpdatorForEntity(T entity) {
        return connection -> {
            String updateQuery = getUpdateQuery();
            log.debug("Updating entity by query: {}", updateQuery);
            CallableStatement call = connection.prepareCall(updateQuery);
            fillUpdateStatement(call, entity);
            return call;
        };
    }

    @Override
    public T findOne(Long id) {
        return this.jdbcTemplate.query(getGetByIdQuery(),
                this.getSetterWithOneLongParameter(id),
                resultSet -> resultSet.next() ? getEntity(resultSet) : null);
    }

    @Override
    public List<T> findAll() {
        return this.jdbcTemplate.query(getGetAllQuery(),
                (resultSet, i) -> getEntity(resultSet));
    }

    private PreparedStatementSetter getSetterWithOneLongParameter(Long parameter) {
        return preparedStatement -> preparedStatement.setLong(1, parameter);
    }

    ResultSetExtractor<List<T>> getListExtractor() {
        return resultSet -> {
            List<T> values = new ArrayList<>();
            while (resultSet.next()) {
                values.add(getEntity(resultSet));
            }
            return values;
        };
    }

    abstract public void fillSaveStatement(CallableStatement statement, T entity)
            throws SQLException;

    abstract public void fillUpdateStatement(CallableStatement statement, T entity)
            throws SQLException;

    public abstract T getEntity(ResultSet resultSet) throws SQLException;

    public abstract String getSaveQuery();

    public abstract String getUpdateQuery();

    public abstract String getGetByIdQuery();

    public abstract String getGetAllQuery();
}

package kz.aidyn.javadocker.user;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<User> findAll(){
        return jdbcTemplate.query("SELECT * FROM users", new UserRowmapper());
    }
    public User findById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserRowmapper(), id);
    }

    public void save(User user) {
        String sql = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getId(), user.getName(), user.getEmail());
    }

    public void update(Long id, User updatedUser){
        jdbcTemplate.update("UPDATE users SET name=?, email=? WHERE id = ?", updatedUser.getName(),
                updatedUser.getEmail(), id);
    }
    public void delete(Long id){
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

}

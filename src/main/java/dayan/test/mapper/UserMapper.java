package dayan.test.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Statement;

/**
 * @author xsg
 * @date 2018-03-20 9:39
 */
@Mapper
public interface UserMapper {
    @Select("select exists(select id from base_user where username = #{username})")
    boolean isUsernameValid(@Param("username") String username);

    @Select("select exists(select id from base_user where username = #{username} and password=#{password})")
    boolean isPasswordValid(@Param("username") String username, @Param("password") String password);

    @Insert("insert into base_user(username,password) values(#{username},#{password})")
    void saveMsg(@Param("username") String username, @Param("password") String password);
}
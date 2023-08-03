package com.example.mapper;

import com.example.entity.auth.Account;
import com.example.entity.user.AccountInfo;
import com.example.entity.user.AccountUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /***
     * 根据用户名或邮箱查询用户所有信息
     * @param text 登录信息
     * @author jinze
     */
    @Select("select * from db_account where username = #{text} or email = #{text}")
    Account findAccountByNameOrEmail(String text);


    /***
     * 根据用户名或邮箱查询用户基本信息
     * @param text 登录信息
     * @author jinze
     */
    @Select("select * from db_account where username = #{text} or email = #{text}")
    AccountUser findAccountUserByNameOrEmail(String text);

    /***
     * 创建用户
     * @param username 用户名
     * @param password 猫猫
     * @param email 邮箱
     * @return int
     * @author jinze
     */
    @Insert("insert into db_account (email, username, password) values (#{email}, #{username}, #{password})")
    int createAccount(String username, String password, String email);

    /***
     * 根据邮箱设置密码
     * @param password 密码
     * @param email 邮箱
     * @return int
     * @author jinze
     */
    @Update("update db_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);


    @Insert("""
            INSERT INTO db_account_info (uid, sex, wx, qq, phone, blog, user_desc)
            values(#{uid}, #{sex}, #{wx}, #{qq},#{phone},#{blog}, #{user_desc})
             on duplicate key update uid = #{uid},sex = #{sex},wx = #{wx},qq = #{qq},
             phone = #{phone},blog = #{blog},user_desc = #{user_desc}
            """)
    void saveUserInfo(AccountInfo info);

    @Update("update db_account set username = #{username} where id = #{uid}")
    void updateUserName(String username, int uid);

    @Select("select * from db_account_info left join db_account on id = uid where id = #{uid}")
    AccountInfo findInfoById(int uid);
}

package com.springboot.project.service.user.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.UserVO;
import com.springboot.project.service.user.UserDAO;

@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public UserDAOImpl() {
		System.out.println("[" + getClass().getName() + " Default Constructor] Call");
	}
	
	public UserDAOImpl(SqlSession sqlSession) {
		System.out.println("[" + getClass().getName() + " SqlSeesion set Constructor] Call");
		this.sqlSession = sqlSession;
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("[" + getClass().getName() + ".setSqlSession] Call");
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int addUser(UserVO userVO) {
		System.out.println("[" + getClass().getName() + ".addUser] Call");
		return sqlSession.insert("UserMapper.addUser", userVO);
	}

	@Override
	public UserVO getUser(String userId) {
		System.out.println("[" + getClass().getName() + ".getUser] Call");
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}

	@Override
	public int updateUser(UserVO userVO) {
		System.out.println("[" + getClass().getName() + ".updateUser] Call");
		return sqlSession.update("UserMapper.updateUser", userVO);
	}

	@Override
	public int deleteUser(String userId) {
		System.out.println("[" + getClass().getName() + ".deleteUser] Call");
		return sqlSession.delete("UserMapper.deleteUser", userId);
	}

	@Override
	public List<UserVO> getUserList(SearchVO searchVO) {
		System.out.println("[" + getClass().getName() + ".getUserList] Call");
		return sqlSession.selectList("UserMapper.getUserList", searchVO);
	}

	@Override
	public int getUserCount(SearchVO searchVO) {
		System.out.println("[" + getClass().getName() + ".getUserCount] Call");
		return sqlSession.selectOne("UserMapper.getUserCount", searchVO);
	}

	@Override
	public List<UserVO> getUserIdAndUserNames() {
		System.out.println("[" + getClass().getName() + ".getUserIdAndUserNames] Call");
		return sqlSession.selectList("UserMapper.getUserIdAndUserNames");
	}

	@Override
	public int updateMileage(UserVO user) {
		System.out.println("[" + getClass().getName() + ".updateMileage] Call");
		return sqlSession.update("UserMapper.updateMileage");
	}
}
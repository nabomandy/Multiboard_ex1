package service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import mapper.MemberMapper;
import model.Member;

@Repository
public class MemberDaoMybatis {
	
	@Autowired
	 private SqlSessionTemplate sqlSession;
	
	 
	 public int insert(Member mem) {
		
		return sqlSession.getMapper(MemberMapper.class).insert(mem);
	}

	public Member selectOne(String id) {
		
		return sqlSession.getMapper(MemberMapper.class).selectMember(id);
	}

	public int update(Member mem) {

		
		return sqlSession.getMapper(MemberMapper.class).update(mem);

	}

	public int delete(String id) {
		
		return sqlSession.getMapper(MemberMapper.class).delete(id);
	}

	public List<Member> list() {
		
		return sqlSession.getMapper(MemberMapper.class).select();

	}

	public int updatePass(String id, String chgpass) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("chgpass", chgpass);
		
		return sqlSession.getMapper(MemberMapper.class).updatepass(map);
	}

}
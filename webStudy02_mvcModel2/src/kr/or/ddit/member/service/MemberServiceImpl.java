package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	//다오랑 연ㄹ결
	//결합력 최상  =>HCLC 지향 -> 1.Factory Object pattern, Stategy pattern(DI) 
	public IMemberDAO dao =MemberDAOImpl.getInstance();
	private IAuthenticateService service = new AuthenticateServiceImpl();
	
	private static MemberServiceImpl instance;
	public static IMemberService getInstance() {
		if(instance ==null) {
			instance = new MemberServiceImpl();
		}
		return instance;
	}
	
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMember(member)==null) {
			int cnt = dao.insertMember(member);
			if(cnt>0) result = ServiceResult.OK;
			else result = ServiceResult.FAILED;
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}
	


	@Override
	public MemberVO retrieveMember(MemberVO member) {
		MemberVO saved =  dao.selectMember(member);
		if(saved == null) throw new UserNotFoundException(member.getMem_id()+"가 없음"); //서비스임플에서 예외처리 !
		return saved;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		// TODO Auto-generated method stub
		return dao.selectMemeberList();
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
		try {
			service.authenticate(member);
			int cnt = dao.updateMember(member);
			if (cnt > 0) result = ServiceResult.OK;
			else result = ServiceResult.FAILED;
		} catch (NotAuthenticatedException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult result = null;
		try {
			service.authenticate(member);
			int cnt = dao.deleteMember(member);
			if (cnt > 0)
				result = ServiceResult.OK;
			else
				result = ServiceResult.FAILED;
		} catch (NotAuthenticatedException e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	
		
//	MemberVO savedMember = retrieveMember(member);
//	ServiceResult result =null;
//	// 인증
//	if(savedMember.getMem_pass().equals(member.getMem_pass()))
//	{
//		int cnt = dao.deleteMember(member);
//		if (cnt > 0)
//			result = ServiceResult.OK;
//		else
//			result = ServiceResult.FAILED;
//	}else
//	{
//		result = ServiceResult.INVALIDPASSWORD;
//	}return result;
//}

}

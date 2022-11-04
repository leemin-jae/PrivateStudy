//package com.ssafy.soldsolve.service;
//
//import java.util.Optional;
//
//import javax.transaction.Transactional;
//
//import org.springframework.stereotype.Service;
//
//import com.ssafy.soldsolve.entity.EmailToken;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
////@RequiredArgsConstructor
//public class EmailService {
//
//	private final EmailTokenService emailTokenService;
//    
//	@Transactional
//	public boolean verifyEmail(String token) {
		// 이메일 토큰을 찾아옴
//		EmailToken findEmailToken = emailTokenService.findByIdAndExpirationDateAfterAndExpired(token);
//
//		// TODO: 여기서부터는 이메일 성공 인증 로직을 구현합니다.
//		// 지금 예시는 유저의 인증내용 변경하는 방법입니다.
//
//		// 토큰의 유저 ID를 이용하여 유저 인증 정보를 가져온다.
//		Optional<Member> findMember = memberRepository.findById(findEmailToken.getMemberId());
//		findEmailToken.setTokenToUsed();    // 사용 완료
//		
//		if (findMember.isPresent()) {
//			Member member = findMember.get();
//			member.setVerified();
//			return true;
//		} else {
//			throw new BaseException(DATABASE_ERROR);    // TODO: 토큰 에러
//		}
//	}
//}
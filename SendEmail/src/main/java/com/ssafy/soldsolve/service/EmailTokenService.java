package com.ssafy.soldsolve.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ssafy.soldsolve.entity.EmailToken;
import com.ssafy.soldsolve.repository.EmailTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailTokenService {

	private final EmailSenderService emailSenderService;
	private final EmailTokenRepository emailTokenRepository;

	// 이메일 인증 토큰 생성
	public String createEmailToken(Long memberId, String receiverEmail) {

		Assert.notNull(memberId, "memberId는 필수입니다");
		Assert.hasText(receiverEmail, "receiverEmail은 필수입니다.");

		// 이메일 토큰 저장
		EmailToken emailToken = EmailToken.createEmailToken(memberId);
		emailTokenRepository.save(emailToken);

		// 이메일 전송
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(receiverEmail);
		mailMessage.setSubject("회원가입 이메일 인증");
		mailMessage.setText("http://[서버주소]/confirm-email?token=" + emailToken.getId());
		emailSenderService.sendEmail(mailMessage);

		return emailToken.getId(); // 인증메일 전송 시 토큰 반환

	}

	// 유효한 토큰 가져오기
	public EmailToken findByIdAndExpirationDateAfterAndExpired(String emailTokenId) {
		Optional<EmailToken> emailToken = emailTokenRepository.findByIdAndExpirationDateAfterAndExpired(emailTokenId,
				LocalDateTime.now(), false);

		if (emailToken == null) {
			return null;
		} else {
			return emailToken.get();

		}
	}
}
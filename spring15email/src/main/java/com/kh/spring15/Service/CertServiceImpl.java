package com.kh.spring15.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring15.entity.Cert;
import com.kh.spring15.repository.CertDao;

import lombok.extern.slf4j.Slf4j;

// 어노테이션 설정을 통해 @Scheduled 어노테이션 사용 가능
// @Configuration
// @EnableScheduling
@Slf4j
@Service
public class CertServiceImpl implements CertService {
	@Autowired
	private RandomService randomService;
	
	@Override
	public String create(String who) {
		// 1. 인증번호 객체 생성
//		Cert cert = Cert.builder().who(id).what("123123").build();
		String number = randomService.generateNumber();	// 랜덤
		Cert cert = Cert.builder().who(who).what(number).build();
		
		// 2. DB 등록 - sqlSession / CertDao
		certDao.add(cert);
		
		return number;
	}
	
	@Autowired
	private CertDao certDao;
	
	// 검사와 삭제 기능 합쳐서 구현
	@Override
	public boolean check(Cert cert) {
		if(certDao.check(cert)) {	// 인증 성공
			certDao.remove(cert);
			return true;
		}
		
		return false;
	}

//	@Scheduled(fixedRate = 1000)
	// cron 표현식: 총 7자리로 구성
	// - 초, 분, 시, 일, 월, 요일, (연도)
	// - *: 와일드카드 = 매 번
	// - ?: 설정값 없음 (일, 요일에서만 사용 가능)
	// - /: 값 증가	ex) 10/15 = 10부터 매 15마다
	// - #: ~번째~	ex) k/N = N번째 K요일
	// - ~L: 마지막~
	// - ~W: ~일로부터 가장 가까운 평일
	
	// @Scheduled(cron = "* * * * * *")		// 1초마다
	// @Scheduled(cron = "*/2 * * * * *")		// 2초마다
	// @Scheduled(cron = "10-20 * * * * *")		// 매 10~20초 사이
	// @Scheduled(cron = "10, 20 * * * * *")		// 매 10, 20초
	// @Scheduled(cron = "0 0 * * * *)	// 매 정각마다 실행
	// @Scheduled(cron = "0 0 1 * * *)	// 매일 1시마다 실행
	// @Scheduled(cron = "0 0,50 9-12 * * MON-FRI")	// 월-금 오전 매시 50분, 정각마다 실행
	// @Scheduled(cron = "0 0,50 9-12 * * 1-5")		// 월-금 오전 매시 50분, 정각마다 실행
	// @Scheduled(cron = "0 20,30 13-15 * * 1-5")	// 월-금 오후 매시 20분, 30분마다 실행
	@Override
	public void clear() {
		log.info("clear 실행");
		certDao.clear();
		System.out.println();
	}
	
}

package az.doshabcatering.doshabcatering.utils.schedlog;

import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.jpaRepo.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

//@Component
public class OtpExpiryScheduler {

//    private final UserRepository userRepository;
//
//    public OtpExpiryScheduler(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Scheduled(fixedRate = 60000 * 60 * 2)
//    public void clearExpiredOtps() {
//
//        List<UserEntity> users = userRepository.findAll();
//        for (UserEntity user : users) {
//            if (user.getOtp_expires_at().isBefore(LocalDateTime.now())) {
//
//            }
//            user.setOtp(null);
//            userRepository.save(user);
//        }
//    }
}

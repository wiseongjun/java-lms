package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private Long courseId;
    private Long generation;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SessionStatus sessionStatus;
    private SessionCondition sessionCondition;
//    private List<NsUserSession> nsUserSessions;
    private CoverImage coverImage;

    public Session(Long courseId,
//                   CoverImage coverImage,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionCondition sessionCondition) {
        this(0L, courseId, 0L, sessionPeriod, sessionStatus, sessionCondition);
    }

    public Session(Long id,
                   Long courseId,
                   Long generation,
//                   CoverImage coverImage,
                   SessionPeriod sessionPeriod,
                   SessionStatus sessionStatus,
                   SessionCondition sessionCondition) {
        validate(courseId);
        this.id = id;
        this.courseId = courseId;
        this.generation = generation;
//        this.coverImage = coverImage;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.sessionStatus = sessionStatus;
        this.sessionCondition = sessionCondition;
    }

    public Session(Long id,
                   Long courseId,
                   Long generation,
//                   CoverImage coverImage,
                   SessionPeriod sessionPeriod,
                   String sessionStatus,
                   SessionCondition sessionCondition) {
        this(id, courseId, generation, sessionPeriod, SessionStatus.valueOf(sessionStatus), sessionCondition);
    }

    public Session coverImage(CoverImage coverImage){
        if (coverImage.sessionId() != id){
            throw new IllegalArgumentException("id가 일치하지 않습니다.");
        }
        this.coverImage = coverImage;
        return this;
    }

    private void validate(Long courseId) {
        if (courseId == null || courseId == 0L) {
            throw new IllegalArgumentException("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        }
    }

    public void enroll(Payment payment, long userNumber) throws CannotEnrollException {
        if (!sessionStatus.isRecruiting()) {
            throw new CannotEnrollException("강의가 모집중인 상태가 아닙니다.");
        }
        sessionCondition.match(payment, userNumber);
    }

    public Long courseId() {
        return courseId;
    }

    public Long generation() {
        return generation;
    }

//    public CoverImage coverImage() {
//        return coverImage;
//    }

    public SessionPeriod sessionPeriod() {
        return sessionPeriod;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public SessionStatus sessionStatus() {
        return sessionStatus;
    }

    public SessionCondition sessionPaymentCondition() {
        return sessionCondition;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", generation=" + generation +
//                ", coverImage=" + coverImage +
                ", sessionPeriod=" + sessionPeriod +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sessionStatus=" + sessionStatus +
                ", sessionPaymentCondition=" + sessionCondition +
                '}';
    }
}

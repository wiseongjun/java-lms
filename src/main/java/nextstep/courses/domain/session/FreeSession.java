package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.engine.Session;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.name.SessionName;
import nextstep.courses.domain.session.period.Period;

public class FreeSession extends Session {

    public FreeSession(Session session, Enrollment enrollment) {
        this(session.getId(), new SessionName(session.getSessionName()), enrollment,
            session.getImage(), session.getPeriod());
    }

    public FreeSession(SessionName SessionName, Enrollment enrollment, Image image, Period period) {
        this(null, SessionName, enrollment, image, period);
    }

    public FreeSession(Long id, SessionName SessionName, Enrollment enrollment, Image image,
        Period period) {
        super(id, SessionName, enrollment, image, period);
    }
}
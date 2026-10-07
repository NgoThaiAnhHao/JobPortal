package jobportal.infrastructure.seeder.constants;

import jobportal.domain.entity.UserType;
import jobportal.domain.enums.UserTypeEnum;

public final class SeedUserTypeConstants {

    public static final UserType ADMIN_TYPE = new UserType(UserTypeEnum.ADMIN);
    public static final UserType RECRUITER_TYPE = new UserType(UserTypeEnum.RECRUITER);
    public static final UserType JOB_SEEKER_TYPE = new UserType(UserTypeEnum.JOB_SEEKER);

    public SeedUserTypeConstants() {
    }
}

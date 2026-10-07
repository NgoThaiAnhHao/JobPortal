package jobportal.infrastructure.seeder;

import jobportal.domain.entity.Company;
import jobportal.infrastructure.persistence.companies.JpaCompanyRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CompanySeeder {

    private final JpaCompanyRepository jpaCompanyRepository;

    public CompanySeeder(JpaCompanyRepository jpaCompanyRepository) {
        this.jpaCompanyRepository = jpaCompanyRepository;
    }

    public void seed() {
        if (jpaCompanyRepository.count() > 0) {
            return;
        }

        // Create company entity
        Company fptSoftware = new Company(
                "FPT Software",
                "https://fptsoftware.com",
                "A global IT services and digital transformation company.",
                "1000+",
                "https://example.com/logos/fpt-software.png",
                "Ho Chi Minh City",
                "Vietnam",
                true,
                LocalDateTime.of(2026, 8, 20, 9, 0)
        );

        Company vng = new Company(
                "VNG Corporation",
                "https://vng.com.vn",
                "A technology company providing digital products and online services.",
                "1000+",
                "https://example.com/logos/vng.png",
                "Ho Chi Minh City",
                "Vietnam",
                true,
                LocalDateTime.of(2026, 8, 19, 10, 30)
        );

        Company kms = new Company(
                "KMS Technology",
                "https://kms-technology.com",
                "A technology company specializing in software development and engineering services.",
                "501-1000",
                "https://example.com/logos/kms.png",
                "Ho Chi Minh City",
                "Vietnam",
                true,
                LocalDateTime.of(2026, 8, 21, 14, 0)
        );

        Company nashTech = new Company(
                "NashTech Vietnam",
                "https://nashtechglobal.com",
                "A global technology solutions company delivering software engineering and digital transformation services.",
                "1000+",
                "https://example.com/logos/nashtech.png",
                "Ho Chi Minh City",
                "Vietnam",
                true,
                LocalDateTime.of(2026, 8, 18, 8, 45)
        );

        Company abcTechnology = new Company(
                "ABC Technology",
                "https://abc-technology.example.com",
                "A software development company focused on web and enterprise applications.",
                "51-200",
                "https://example.com/logos/abc-technology.png",
                "Hanoi",
                "Vietnam",
                false,
                null
        );

        jpaCompanyRepository.saveAll(
                List.of(fptSoftware, vng, kms, nashTech, abcTechnology)
        );
    }
}

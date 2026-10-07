package jobportal.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false)
    private String name;

    private String website;

    private String description;

    private String companySize;

    private String logo;

    private String city;

    private String country;

    @Column(nullable = false)
    private boolean isVerified;

    private LocalDateTime verificationRequestedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public Company(String name, String website, String description, String companySize, String logo, String city, String country, boolean isVerified, LocalDateTime verificationRequestedAt) {
        this.name = name;
        this.website = website;
        this.description = description;
        this.companySize = companySize;
        this.logo = logo;
        this.city = city;
        this.country = country;
        this.isVerified = isVerified;
        this.verificationRequestedAt = verificationRequestedAt;
    }
}
